package com.soucheng.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.soucheng.application.MainApplication;
import com.soucheng.activity.PhoneCallActivity;


/**
 * 打电话事件监听器
 */
public class SouchengPhoneStateListener extends PhoneStateListener {

    private Context context;
    private PhoneCallActivity dialog;
    private MainApplication application = null;

    public SouchengPhoneStateListener(Context context) {
        this.context = context;
        application = (MainApplication) context.getApplicationContext();
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                application.getScreenLockActivity().stop();
                break;
            case TelephonyManager.CALL_STATE_IDLE:

                if (application.getPhoneCallActivity() == null) {
                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            application.disableKeyguard();
                            Intent intent = new Intent(context, PhoneCallActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    };
                    handler.sendEmptyMessage(0);
                }

//                if (!dialog.isShowing()) {
//                    dialog = new PhoneCallActivity(context);
//                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//                    dialog.show();
//                    dialog = null;
//                }

                break;
        }
    }
}