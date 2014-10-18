package com.soucheng.listener;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.soucheng.application.MainApplication;
import com.soucheng.dialog.PhoneCallDialog;


/**
 * 打电话事件监听器
 */
public class SouchengPhoneStateListener extends PhoneStateListener {

    private Context context;
    private PhoneCallDialog dialog;
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

                if (dialog == null) {
                    dialog = new PhoneCallDialog(context);
                }

                if (!dialog.isShowing()) {
                    dialog = new PhoneCallDialog(context);
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    dialog.show();
                    dialog = null;
                }

                break;
        }
    }
}