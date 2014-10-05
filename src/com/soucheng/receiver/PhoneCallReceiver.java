package com.soucheng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.soucheng.dialog.PhoneCallDialog;

/**
 * @author lichen
 */
public class PhoneCallReceiver extends BroadcastReceiver {

    private PhoneCallDialog dialog;
    private static boolean hasShown;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (dialog == null) {
            dialog = new PhoneCallDialog(context);
        }
        if (!dialog.isShowing() && !hasShown) {
            dialog = new PhoneCallDialog(context);
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog.show();
            hasShown = true;
        }

        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //设置一个监听器
        manager.listen(new SouchengPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);


    }

    private class SouchengPhoneStateListener extends PhoneStateListener {
        private Context context;

        public SouchengPhoneStateListener(Context context) {
            this.context = context;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                /*case TelephonyManager.CALL_STATE_RINGING:
                    dialog = new PhoneCallDialog(context);
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    dialog.show();
                    break;*/
                case TelephonyManager.CALL_STATE_IDLE:
                    hasShown = false;
                    if (dialog != null)
                        dialog.cancel();
                    break;
            }
        }
    }
}