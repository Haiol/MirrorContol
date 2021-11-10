package com.lovehp30.mirrorcontrol.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.lovehp30.mirrorcontrol.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    private String Client_code = "TestWemos01";
    private String Topics = "Sensor";
    private String ip ="lovehp12.duckdns.org";
    private int port=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getWindow().setStatusBarColor(Color.parseColor("#3d3d3d"));
        Toolbar toolbar = findViewById(R.id.dt_toolbar);
        TextView subtitle = findViewById(R.id.dt_subtitle);
        subtitle.setText(Topics);
        toolbar.setTitle(Client_code);
        setSupportActionBar(toolbar);
        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        if(bundle==null)
            finish();
        else {
            Client_code = bundle.getString("code");
            Topics = bundle.getString("topic");
            addDataGraph();
        }

        // ToolBar Setting

    }
    void addDataGraph(){
        ViewPager2 pager = findViewById(R.id.dt_viewpager);
        TabLayout tableLayout = findViewById(R.id.dt_tlTabs);
        tableLayout.setSelectedTabIndicatorColor(Color.BLACK);
        List<SensorDataFormat> list = new ArrayList<>();
        final ProgressDialog myProgressDialog = ProgressDialog.show(this, "Gathering the Your Data", "please Wait..", true);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://"+ip+":"+port+"/getData/"+Client_code+"/7Days";
        Log.e("JSON_in",url);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response ->  {
                    try{
                        JSONArray array = new JSONArray(response);
                        for(int i=0;i<array.length();i++){
                            JSONObject object = array.getJSONObject(i);
                            list.add(new SensorDataFormat(object.getString("clientID"),object.getString("message"),object.getString("time")));
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    //Json 분석
                    DataActViewAdapter adapter = new DataActViewAdapter(ip,Client_code,Topics,list,getSupportFragmentManager(), getLifecycle());
                    pager.setAdapter(adapter);
                    String map[] = list.get(0).getMapKeyList();

                    new TabLayoutMediator(tableLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            if(position==0)
                                tab.setText("DataStream");
                            else
                                tab.setText(map[position-1]);
                        }
                    }).attach();
                    myProgressDialog.dismiss();
                },
                error -> {
                    Toast.makeText(this,"Fail to load data...",Toast.LENGTH_SHORT);
                    myProgressDialog.dismiss();
                }
        );
        queue.add(request);
    }
}