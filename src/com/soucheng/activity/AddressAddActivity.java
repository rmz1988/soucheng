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
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.vo.Address;

import java.util.List;

/**
 * @author lichen
 */
public class AddressAddActivity extends Activity {

    private Button backBtn;
    private EditText addressEdit;
    private Button searchBtn;
    private ListView addressQueryView;
    private TextView emptyTip;

    private MainApplication application;
    private DbAdapter dbAdapter;

    private ProgressDialog dialog;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            List<Address> addressList = (List<Address>) msg.obj;
            if (addressList != null && !addressList.isEmpty()) {
                addressQueryView.setVisibility(View.VISIBLE);
                emptyTip.setVisibility(View.GONE);
                addressQueryView.setAdapter(new AddressListAdapter(AddressAddActivity.this, addressList));
            } else {
                addressQueryView.setVisibility(View.GONE);
                emptyTip.setVisibility(View.VISIBLE);
            }

            dialog.cancel();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_add);
        application = (MainApplication) getApplication();
        dbAdapter = new DbAdapter(this);

        backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addressEdit = (EditText) findViewById(R.id.addressEdit);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        addressQueryView = (ListView) findViewById(R.id.addressQueryView);
        emptyTip = (TextView) findViewById(R.id.emptyTip);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(AddressAddActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setMessage(getResources().getString(R.string.loading));
                dialog.show();

                new Thread() {
                    public void run() {
                        String addressName = addressEdit.getText().toString().trim();
                        List<Address> addressList = application.queryAddressByName(addressName);
                        handler.sendMessage(handler.obtainMessage(0, addressList));
                    }
                }.start();
            }
        });

        addressQueryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressAddActivity.this);
                builder.setMessage(R.string.confirm_add_address).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Address address = (Address) addressQueryView.getItemAtPosition(position);
                        address.setCanNotify("1");
                        dbAdapter.open();
                        dbAdapter.saveAddress(address);
                        Toast.makeText(AddressAddActivity.this, R.string.add_address_success, Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton(R.string.no, null);
                AlertDialog alertDialog = builder.create();
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.show();
            }
        });
    }
}