package com.soucheng.utils;

import com.soucheng.vo.Address;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LocationUtils {

    public static List<Address> getFromLocationName(String addressName) {
        String url = "http://maps.google.com/maps/api/geocode/json";
        HttpGet httpGet = new HttpGet(url + "?sensor=false&address=" + addressName);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getAddress(jsonObject);
    }

    private static List<Address> getAddress(JSONObject jsonObject) {
        List<Address> addressList = new ArrayList<>();
        Address address = null;
        JSONArray array = null;
        try {
            array = (JSONArray) jsonObject.get("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (array == null) {
            return addressList;
        }

        for (int i = 0;i<array.length();i++) {
            try {
                JSONObject object = array.getJSONObject(i);
                address = new Address();
                address.setName(object.getString("formatted_address"));
                address.setProvince("");
                address.setCity("");
                JSONObject geometry = object.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                address.setLatitude(location.getDouble("lat"));
                address.setLongitude(location.getDouble("lng"));
                address.setDetail("");
                address.setCanNotify("1");
                addressList.add(address);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return addressList;
    }

}  