package com.soucheng.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.soucheng.application.MainApplication;
import com.soucheng.dialog.PhoneCallDialog;
import com.soucheng.listener.SouchengPhoneStateListener;

/**
 * @author lichen
 */
public class PhoneCallReceiver extends BroadcastReceiver {
    private static boolean hasListen;

    @Override
    public void onReceive(Context context, Intent intent) {
       /* if (dialog == null) {
            dialog = new PhoneCallDialog(context);
        }
        if (!dialog.isShowing() && !hasShown) {
            dialog = new PhoneCallDialog(context);
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog.show();
            hasShown = true;
        }*/

        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //设置一个监听器
        if (!hasListen) {
            manager.listen(new SouchengPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);
            hasListen = true;
        }

    }
}
