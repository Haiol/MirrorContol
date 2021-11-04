package com.lovehp30.mirrorcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    String Client_code = "TestWemos01";
    String Topics = "Sensor";
    public static String ip ="lovehp12.duckdns.org";;
    RequestData requestData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar = findViewById(R.id.dt_toolbar);
        TextView subtitle = findViewById(R.id.dt_subtitle);
        subtitle.setText(Topics);
        toolbar.setTitle(Client_code);
        DataSphereFragment.putID(Client_code,Topics);
        setSupportActionBar(toolbar);
        ViewPager2 pager = findViewById(R.id.dt_viewpager);
        // ToolBar Setting
        List<SensorDataFormat> list = new ArrayList<>();
        final ProgressDialog myProgressDialog = ProgressDialog.show(this, "Gathering the Your Data", "please Wait..", true);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://"+ip+":3000/getData/"+Client_code+"/7Days";
        Log.e("JSON_in",url);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response ->  {
                    try{
                        JSONArray array = new JSONArray(response);
                        for(int i=0;i<array.length();i++){

                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    myProgressDialog.dismiss();
                },
                error -> {
                    Toast.makeText(this,"Fail to load data...",Toast.LENGTH_SHORT);
                    myProgressDialog.dismiss();


                }
        );
        queue.add(request);

        //Json 분석
        DataActViewAdapter adapter = new DataActViewAdapter(Client_code,Topics,getSupportFragmentManager(), getLifecycle());
        pager.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {

//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}