package com.example.user1.sensorloggerwithandroid;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user1 on 2017/06/28.
 * JSONを渡すとそれをデータベースサーバにPOSTします
 */

public class HttpPostData extends AsyncTask<JSONArray, Void, JSONArray> {
    @Override
    protected JSONArray doInBackground(JSONArray... params) {

        try {
            URL url = new URL("http://taiyakon.xyz:3000/dots/api/post/json"); //データベースサーバのAPI
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//            os.writeBytes(URLEncoder.encode(params[0].toString(), "UTF-8"));
            os.writeBytes(params[0].toString());

            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());

            conn.disconnect();
        } catch (Exception e) {

        }
        return null;
    }
}
