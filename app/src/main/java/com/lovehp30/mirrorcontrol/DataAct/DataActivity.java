package com.lovehp30.mirrorcontrol.DataAct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
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
import com.lovehp30.mirrorcontrol.SensorDataFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    String Client_code = "TestWemos01";
    String Topics = "Sensor";
    public static String ip ="lovehp12.duckdns.org";;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar = findViewById(R.id.dt_toolbar);
        TextView subtitle = findViewById(R.id.dt_subtitle);
        subtitle.setText(Topics);
        toolbar.setTitle(Client_code);
        setSupportActionBar(toolbar);
        ViewPager2 pager = findViewById(R.id.dt_viewpager);
        TabLayout tableLayout = findViewById(R.id.dt_tlTabs);
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
                            JSONObject object = array.getJSONObject(i);
                            list.add(new SensorDataFormat(object.getString("clientID"),object.getString("message"),object.getString("time")));
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    //Json 분석
                    DataActViewAdapter adapter = new DataActViewAdapter(Client_code,Topics,list,getSupportFragmentManager(), getLifecycle());
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