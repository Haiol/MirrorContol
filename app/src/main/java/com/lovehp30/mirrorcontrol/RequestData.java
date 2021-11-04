package com.lovehp30.mirrorcontrol;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class RequestData {
    private String ip;
    private String Client_code;
    private String Topics;
    private Context context;
    public JSONArray nowData,days7Data;
    public RequestData(String Client_code,String Topics, Context context){
        ip = "lovehp12.duckdns.org";
        this.Client_code = Client_code;
        this.Topics = Topics;
        this.context = context;
    }
    public void getJsonNowData(){
        String url = "http://"+ip+":3000/getData/"+Client_code+"/limit/15";
        Log.e("JSON_in",url);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
               response ->  {
                    try {
                        nowData = new JSONArray(response);
                    }catch (Exception e){
                    }
                },
                error -> {
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue = Volley.newRequestQueue(context);
        AppHelper.requestQueue.add(request);
    }

    public void getJson7Data(){
        String url = "http://"+ip+":3000/getData/"+Client_code+"/limit/15";
        Log.e("JSON_in",url);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response ->  {
                    try {
                        days7Data = new JSONArray(response);
                    }catch (Exception e){
                    }
                },
                error -> {
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue = Volley.newRequestQueue(context);
        AppHelper.requestQueue.add(request);
    }

    public JSONArray getDays7Data() {
        return days7Data;
    }

    public JSONArray getNowData() {
        return nowData;
    }

    public boolean getValidData(){
        if(nowData.length()>0 && days7Data.length()>0)
            return true;
        Log.e("JSON",nowData.length()+" "+days7Data.length());
        return false;
    }
    public boolean getValidNowData(){
        if(nowData.length()>0)
            return true;
        return false;
    }
    public boolean getValid7Data(){
       if(days7Data.length()>0)
           return true;
        return false;
    }


}
