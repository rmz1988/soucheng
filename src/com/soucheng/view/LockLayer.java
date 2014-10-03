package com.soucheng.view;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

public class LockLayer {
    private Activity mActivty;
    private WindowManager mWindowManager;
    private View mLockView;
    private WindowManager.LayoutParams mLockViewLayoutParams;

    private static LockLayer lockLayer = null;

    private LockLayer(Activity act) {
        mActivty = act;
        init();
    }

    public static synchronized LockLayer getInstance(Activity act) {
        if (lockLayer == null) {
            lockLayer = new LockLayer(act);
        }

        return lockLayer;
    }

    private void init() {
        mWindowManager = mActivty.getWindowManager();
        mLockViewLayoutParams = new WindowManager.LayoutParams();
        mLockViewLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLockViewLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        //实现关键
        mLockViewLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
//        //apktool value，这个值具体是哪个变量还请网友帮忙
        mLockViewLayoutParams.flags = 1280;
    }

    public void lock() {
        if (mLockView != null) {
            mWindowManager.addView(mLockView, mLockViewLayoutParams);
        }

    }

    public void unlock() {
        if (mWindowManager != null) {
            mWindowManager.removeView(mLockView);
        }

    }

    public void setLockView(View v) {
        mLockView = v;
    }
}