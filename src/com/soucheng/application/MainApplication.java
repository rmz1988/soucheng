package com.soucheng.application;

import android.app.ActivityManager;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.location.Location;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.soucheng.activity.R;
import com.soucheng.vo.Address;
import com.soucheng.vo.User;

import java.io.IOException;
import java.io.InputStream;
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
	private Location currentLocation;

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
				address.setCity(addr.getLocality());
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
	 * 查询地址信息
	 *
	 * @param latitude  纬度
	 * @param longitude 经度
	 */
	public Address queryAddressByLocation(double latitude, double longitude) {
		Geocoder geocoder = new Geocoder(this, Locale.getDefault());
		List<Address> targetAddressList = new ArrayList<>();
		try {
			List<android.location.Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
			Address address;
			for (android.location.Address addr : addressList) {
				address = new Address();
				address.setName(addr.getFeatureName());
				address.setProvince(addr.getAdminArea());
				address.setCity(addr.getLocality());
				address.setLatitude(addr.getLatitude());
				address.setLongitude(addr.getLongitude());
				address.setDetail(addr.getAddressLine(0));
				address.setCanNotify("1");
				targetAddressList.add(address);
			}
		} catch (IOException e) {
			Log.e("MainApplication", "Query address by location failed", e);
		}

		return targetAddressList.isEmpty() ? null : targetAddressList.get(0);
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

	/**
	 * 获取当前位置
	 */
	public Address getCurrentLocation() {

		Address address = new Address();
		address.setDetail("无法获取位置信息");
		if (currentLocation != null) {
			Address addr = queryAddressByLocation(currentLocation.getLatitude(), currentLocation.getLongitude());
			if (addr != null) {
				address = addr;
			}
		}

		return address;
	}

	/**
	 * 检查service是否在运行
	 *
	 * @param serviceFullName 服务的包名+类名
	 */
	public boolean isServiceWork(String serviceFullName) {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> runningServiceInfoList = manager.getRunningServices(500);
		for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServiceInfoList) {
			if (runningServiceInfo.service.getClassName().equals(serviceFullName)) {
				return true;
			}
		}

		return false;
	}

	public boolean isPhoneInUse() {
		TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		return manager.getCallState() != TelephonyManager.CALL_STATE_IDLE;
	}

	public void disableKeyguard() {
		KeyguardManager manager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		KeyguardManager.KeyguardLock lock = manager.newKeyguardLock("");
		lock.disableKeyguard();
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Bitmap loadBitmap(int resId, int size) {
		InputStream is = getResources().openRawResource(resId);
		BitmapFactory.Options options = new BitmapFactory.Options();
		if (size != 0) {
			options.inSampleSize = size;
		}
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(is, null, options);
	}

	public void recycleBitmap(Object obj) {
		if (obj != null) {
			if (obj instanceof ImageView) {
				ImageView view = (ImageView) obj;
				if (view.getDrawable() != null) {
					Bitmap oldBitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
					view.setImageDrawable(null);
					if (oldBitmap != null) {
						oldBitmap.recycle();
						oldBitmap = null;
					}
				}
			}
			if (obj instanceof ImageButton) {
				ImageButton btn = (ImageButton) obj;
				if (btn.getDrawable() != null) {
					Bitmap oldBitmap = ((BitmapDrawable) btn.getDrawable()).getBitmap();
					btn.setImageDrawable(null);
					if (oldBitmap != null) {
						oldBitmap.recycle();
						oldBitmap = null;
					}
				}
			}
		}

		System.gc();
	}
}
