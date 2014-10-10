package com.soucheng.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.soucheng.activity.BehindDetailActivity;
import com.soucheng.activity.R;
import com.soucheng.application.MainApplication;

/**
 * @author lichen
 */
public class BehindViewLoader extends ViewLoader {
/*

    private EditText locationView;
    private ImageButton flushBtn;
*/

    private ImageButton eatBtn;
    private ImageButton houseBtn;
    private ImageButton xiuxianBtn;
    private ImageButton shopBtn;
    private ImageButton gongyiBtn;
    private ImageButton shequBtn;
    private ImageButton playBtn;
    private ImageButton jiazhengBtn;

    private MainApplication application;

    public BehindViewLoader(Context context, View view) {
        super(context, view);
        application = (MainApplication) context.getApplicationContext();
    }

    @Override
    public void load() {
        loadCurrentLocation();
        loadImageButton();
    }

    private void loadCurrentLocation() {
      /*  locationView = (EditText) view.findViewById(R.id.locationView);
        flushBtn = (ImageButton) view.findViewById(R.id.flushBtn);*/

        /*locationView.setText(application.getCurrentLocation().getDetail());
        flushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationView.setText(application.getCurrentLocation().getDetail());
            }
        });*/
    }

    private void loadImageButton() {
        eatBtn = (ImageButton) view.findViewById(R.id.eatBtn);
        houseBtn = (ImageButton) view.findViewById(R.id.houseBtn);
        xiuxianBtn = (ImageButton) view.findViewById(R.id.xiuxianBtn);
        shopBtn = (ImageButton) view.findViewById(R.id.shopBtn);
        gongyiBtn = (ImageButton) view.findViewById(R.id.gongyiBtn);
        shequBtn = (ImageButton) view.findViewById(R.id.shequBtn);
        playBtn = (ImageButton) view.findViewById(R.id.playBtn);
        jiazhengBtn = (ImageButton) view.findViewById(R.id.jiazhengBtn);

        eatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BehindDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://m.dianping.com/shoplist/22/d/500/c/10/s/s_-1/a.pbf");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        houseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BehindDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://m.dianping.com/shoplist/22/d/500/c/60/s/s_-1");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        xiuxianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BehindDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://m.ly.com");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BehindDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://m.taobao.com");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        gongyiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BehindDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://gongyi.163.com");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        shequBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BehindDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://bbs.e23.cn");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BehindDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://m.6366.com/game/?id=10102104");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        jiazhengBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BehindDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://m.dianping.com/shoplist/22/c/195");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }
}
