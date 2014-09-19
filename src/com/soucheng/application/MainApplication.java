package com.soucheng.application;

import android.app.Application;
import com.soucheng.vo.User;

/**
 * Application记录公共资源
 *
 * @author lichen
 */
public class MainApplication extends Application {

	private User loginUser;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
}
