package com.soucheng.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.soucheng.activity.LocationNotifyActivity;
import com.soucheng.activity.R;
import com.soucheng.application.MainApplication;
import com.soucheng.db.DbAdapter;
import com.soucheng.vo.Address;

import java.util.Iterator;
import java.util.List;

/**
 * 定位服务
 *
 * @author lichen
 */
public class LocationService extends Service {

    private MainApplication application;
    private DbAdapter dbAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            application.disableKeyguard();
            Intent intent = new Intent(LocationService.this, LocationNotifyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        application = (MainApplication) getApplication();
        application.setLocationService(this);
        dbAdapter = new DbAdapter(this);

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(manager.isProviderEnabled(LocationManager.GPS_PROVIDER) ? Criteria.POWER_HIGH : Criteria.POWER_LOW);
        final String provider = manager.getBestProvider(criteria, true);

        Location location = manager.getLastKnownLocation(provider);
        if (location != null) {
            application.setCurrentLocation(location);
        }

        new Thread() {
            public void run() {
                while (true) {
                    criteria.setPowerRequirement(manager.isProviderEnabled(LocationManager.GPS_PROVIDER) ? Criteria.POWER_HIGH : Criteria.POWER_LOW);
                    final String provider = manager.getBestProvider(criteria, true);
                    Location location = manager.getLastKnownLocation(provider);
                    if (location != null) {
                        application.setCurrentLocation(location);
                        checkNotify(location);
                        sendNotify(location);
                    }
                    try {
                        Thread.sleep(10000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        manager.requestLocationUpdates(provider, 10000L, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    Log.i("LocationService", location.getLatitude() + "," + location.getLongitude());
                    application.setCurrentLocation(location);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setLargeIcon(((BitmapDrawable) (getResources().getDrawable(R.drawable.logo))).getBitmap());
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentInfo("LocationService is running...");
        Notification notification = builder.build();
        startForeground(0, notification);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }

    private void checkNotify(Location currentLocation) {
        dbAdapter.open();
        List<Address> addressList = dbAdapter.queryAddressList();
        Iterator<Address> addressIterator = addressList.iterator();
        while (addressIterator.hasNext()) {
            Address address = addressIterator.next();
            if (application.calcDistance(address.getLatitude(), address.getLongitude(), currentLocation.getLatitude(), currentLocation.getLongitude()) > 1000d) {
                address.setCanNotify("1");
                dbAdapter.updateAddress(address);
            }
        }
    }

    private void sendNotify(Location currentLocation) {
        dbAdapter.open();
        List<Address> addressList = dbAdapter.queryAddressList();
        Iterator<Address> addressIterator = addressList.iterator();
        while (addressIterator.hasNext()) {
            Address address = addressIterator.next();
            if (application.calcDistance(address.getLatitude(), address.getLongitude(), currentLocation.getLatitude(), currentLocation.getLongitude()) < 1000d) {
                if ("1".equals(address.getCanNotify())) {
                    address.setCanNotify("0");
                    dbAdapter.updateAddress(address);
                    handler.sendEmptyMessage(0);
                }
            }
        }
    }
}
