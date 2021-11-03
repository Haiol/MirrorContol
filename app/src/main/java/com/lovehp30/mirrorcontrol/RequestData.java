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

public class RequestData {
    private String ip;
    private String Client_code;
    private String Topics;
    private Context context;

    private JSONArray data;
    public RequestData(String Client_code,String Topics, Context context){
        ip = "http://lovehp12.duckdns.org";
        this.Client_code = Client_code;
        this.Topics = Topics;
        this.context = context;
    }
    public void getJsonData() throws JSONException {
        String url = "http://"+ip+":3000/getData/"+Client_code;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
               response ->  {
                    try {
                        data = new JSONArray(response);

                    }catch (Exception e){
                    }
                },
                error -> {
                }
        );
        Log.e("JSON",data.getJSONObject(1).getString("message").toString());
        request.setShouldCache(false);
        AppHelper.requestQueue = Volley.newRequestQueue(context);
        AppHelper.requestQueue.add(request);
    }



}
