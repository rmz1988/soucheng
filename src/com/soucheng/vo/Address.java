package com.soucheng.vo;

import java.io.Serializable;

/**
 * 地址
 *
 * @author lichen
 */
public class Address implements Serializable {

	private static final long serialVersionUID = -279856141645868124L;

	private int id;
	private String name;
	private String province;
	private String city;
	private double latitude;
	private double longitude;
	private String detail;
	private String canNotify;

	public boolean canNotify() {
		return "1".equals(canNotify);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCanNotify() {
		return canNotify;
	}

	public void setCanNotify(String canNotify) {
		this.canNotify = canNotify;
	}
}
