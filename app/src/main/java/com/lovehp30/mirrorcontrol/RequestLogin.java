package com.lovehp30.mirrorcontrol;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class RequestLogin {
    public static boolean IPVerify;
    public static boolean APIVerify;
    public void VerifyIP(String ip, Context context){
        String url = "http://"+ip+":8080/api/test";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject array = new JSONObject(response);
                            IPVerify = array.getBoolean("success");
                            Log.e("LOGIN_IP",IPVerify+"");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        IPVerify = false;
                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue = Volley.newRequestQueue(context);
        AppHelper.requestQueue.add(request);
    }

    public void VerifyAPI(String ip,String api, Context context){
        String url = "http://"+ip+":8080/api/config?apiKey="+api;
        Log.e("LOGIN_API",url);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject array = new JSONObject(response);
                            APIVerify = array.getBoolean("success");
                            Log.e("LOGIN_API",APIVerify+"");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        IPVerify = false;
                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue = Volley.newRequestQueue(context);
        AppHelper.requestQueue.add(request);
    }

    public boolean getLoginStatus(){
        if (APIVerify && IPVerify)
            return true;
        return false;
    }

}
