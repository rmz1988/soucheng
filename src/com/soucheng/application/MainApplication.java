package com.soucheng.application;

import android.app.Application;
import android.location.Geocoder;
import android.util.Log;
import com.soucheng.vo.Address;
import com.soucheng.vo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Application记录公共资源
 *
 * @author lichen
 */
public class MainApplication extends Application {

	//地球半径
	private static final double EARTH_RADIUS = 6378137.0;

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

	/**
	 * 查询地址信息
	 *
	 * @param name 位置名称
	 */
	public List<Address> queryAddressByName(String name) {
		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		List<Address> targetAddressList = new ArrayList<>();
		try {
			List<android.location.Address> addressList = geocoder.getFromLocationName(name, 20);
			Address address;
			for (android.location.Address addr : addressList) {
				address = new Address();
				address.setName(addr.getFeatureName());
				address.setProvince(addr.getAdminArea());
				address.setCity(addr.getCountryName());
				address.setLatitude(addr.getLatitude());
				address.setLongitude(addr.getLongitude());
				address.setDetail(addr.getAddressLine(0));
				address.setCanNotify("1");
				targetAddressList.add(address);
			}
		} catch (IOException e) {
			Log.e("MainApplication", "Query address by name failed", e);
		}

		return targetAddressList;
	}

	/**
	 * 查询地址细腻些
	 *
	 * @param latitude  纬度
	 * @param longitude 经度
	 */
	public List<Address> queryAddressByLocation(double latitude, double longitude) {
		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		List<Address> targetAddressList = new ArrayList<>();
		try {
			List<android.location.Address> addressList = geocoder.getFromLocation(latitude, longitude, 20);
			Address address;
			for (android.location.Address addr : addressList) {
				address = new Address();
				address.setName(addr.getFeatureName());
				address.setProvince(addr.getAdminArea());
				address.setCity(addr.getCountryName());
				address.setLatitude(addr.getLatitude());
				address.setLongitude(addr.getLongitude());
				address.setDetail(addr.getAddressLine(0));
				address.setCanNotify("1");
				targetAddressList.add(address);
			}
		} catch (IOException e) {
			Log.e("MainApplication", "Query address by location failed", e);
		}

		return targetAddressList;
	}

	/**
	 * 计算两位置间的距离
	 *
	 * @param targetLatitude   目标纬度
	 * @param targetLongitude  目标经度
	 * @param currentLatitude  当前纬度
	 * @param currentLongitude 当前经度
	 * @return 两点之间的距离 单位：米
	 */
	public double calcDistance(double targetLatitude, double targetLongitude, double currentLatitude,
	                           double currentLongitude) {
		double Lat1 = rad(targetLatitude);
		double Lat2 = rad(currentLatitude);
		double a = Lat1 - Lat2;
		double b = rad(targetLongitude) - rad(currentLongitude);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(Lat1) * Math.cos(Lat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
}
