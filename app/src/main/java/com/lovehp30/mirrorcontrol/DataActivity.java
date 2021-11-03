package com.lovehp30.mirrorcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.magicgoop.tagsphere.OnTagTapListener;
import com.magicgoop.tagsphere.TagSphereView;
import com.magicgoop.tagsphere.item.TagItem;
import com.magicgoop.tagsphere.item.TextTagItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    String Client_code = "TestWemos01";
    String Topics = "Sensor";
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
        DataActViewAdapter adapter = new DataActViewAdapter(getSupportFragmentManager(),getLifecycle());
        pager.setAdapter(adapter);




    }

    @Override
    protected void onStart() {
        super.onStart();
        final ProgressDialog myProgressDialog = ProgressDialog.show(this, "Please Wait", "Trying to login..", true);
        RequestData requestData = new RequestData(Client_code,Topics,getBaseContext());

        // start async task to wait for 5 second that update the view
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    requestData.getJsonData();

//                    requestLogin.VerifyIP(ipAddress.getText().toString(),getApplicationContext());
//                    requestLogin.VerifyAPI(ipAddress.getText().toString(),APIkey.getText().toString(),getApplicationContext());

                    Thread.sleep(2500);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {

                myProgressDialog.hide();


            }
        };
        task.execute((Void[]) null);
    }
}