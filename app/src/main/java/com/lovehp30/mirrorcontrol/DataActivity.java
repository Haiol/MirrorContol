package com.lovehp30.mirrorcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    String Client_code = "TestWemos01";
    String Topics = "Sensor";

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

        final ProgressDialog myProgressDialog = ProgressDialog.show(this, "Gathering the Your Data", "please Wait..", true);
        requestData = new RequestData(Client_code, Topics, this);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                try {
                    requestData.getJsonNowData();
                    requestData.getJson7Data();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);

                    JSONArray test = requestData.getNowData();
                    Log.e("LIST",test.toString());
                    List<SensorDataFormat> list = new ArrayList<>();

                    for (int i = 0; i < test.length(); i++) {

                        JSONObject object = test.getJSONObject(i);
                        list.add(new SensorDataFormat(object.getString("clientID"),object.getString("message"),object.getString("time")));
                    }
                    Log.e("LIST",list.get(0).getMessages().toString());


                } catch (InterruptedException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                myProgressDialog.dismiss();
            }
        };
        task.execute();
        //get to the JSONData



        //Json 분석
        DataActViewAdapter adapter = new DataActViewAdapter(getSupportFragmentManager(), getLifecycle());
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