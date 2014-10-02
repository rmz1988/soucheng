package com.soucheng.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.soucheng.activity.adapter.AddressListAdapter;
import com.soucheng.db.DbAdapter;
import com.soucheng.vo.Address;

import java.util.List;

/**
 * @author lichen
 */
public class AddressShowActivity extends Activity {

    private Button backBtn;
    private ListView addressShowView;
    private TextView emptyTip;

    private DbAdapter dbAdapter;

    private ProgressDialog dialog;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            List<Address> addressList = (List<Address>) msg.obj;
            if (addressList != null && !addressList.isEmpty()) {
                addressShowView.setVisibility(View.VISIBLE);
                emptyTip.setVisibility(View.GONE);
                addressShowView.setAdapter(new AddressListAdapter(AddressShowActivity.this, addressList));
            } else {
                addressShowView.setVisibility(View.GONE);
                emptyTip.setVisibility(View.VISIBLE);
            }

            dialog.cancel();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_show);
        dbAdapter = new DbAdapter(this);

        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addressShowView = (ListView) findViewById(R.id.addressShowView);
        emptyTip = (TextView) findViewById(R.id.emptyTip);

        addressShowView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressShowActivity.this);
                builder.setMessage(R.string.confirm_delete_address).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Address address = (Address) addressShowView.getItemAtPosition(position);
                        dbAdapter.open();
                        dbAdapter.deleteAddress(address.getId());
                        Toast.makeText(AddressShowActivity.this, R.string.delete_address_success, Toast.LENGTH_SHORT).show();

                        loadAddressList();
                    }
                }).setNegativeButton(R.string.no, null);
                AlertDialog alertDialog = builder.create();
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.show();
            }
        });

        loadAddressList();
    }

    private void loadAddressList() {
        dialog = new ProgressDialog(AddressShowActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setMessage(getResources().getString(R.string.loading));
        dialog.show();

        new Thread() {
            public void run() {
                dbAdapter.open();
                List<Address> addressList = dbAdapter.queryAddressList();
                handler.sendMessage(handler.obtainMessage(0, addressList));
            }
        }.start();
    }
}