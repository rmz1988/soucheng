package com.soucheng.vo;

import java.io.Serializable;

/**
 * 配置信息
 *
 * @author lichen
 */
public class Config implements Serializable {

	private static final long serialVersionUID = -3140350785913121978L;

	public static final String FIRST_OPEN = "1";
	public static final String NOT_FIRST_OPEN = "0";

	private int id;
	private String firstOpen;
	private String loginUsername;

	/**
	 * 是否第一次打开程序
	 */
	public boolean isFirstOpen() {
		return FIRST_OPEN.equals(firstOpen);
	}

	public boolean hasUserLogin() {
		return loginUsername != null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getFirstOpen() {
		return firstOpen;
	}

	public void setFirstOpen(String firstOpen) {
		this.firstOpen = firstOpen;
	}
}
