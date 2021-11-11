package com.lovehp30.mirrorcontrol.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;
import com.lovehp30.mirrorcontrol.MainActivity;
import com.lovehp30.mirrorcontrol.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    boolean isVerifySunLite = true;
    boolean isVerifySkyMoon = false;
    TextInputEditText ed_ipAddress,ed_Api;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MaterialComponents_DayNight_DarkActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(Color.parseColor("#3d3d3d"));
        ed_ipAddress = findViewById(R.id.login_Ip);
         ed_Api = findViewById(R.id.login_Ip);


    }


    public void verifyLogin(View view) {
        ProgressDialog myProgressDialog= ProgressDialog.show(this, "Please Wait", "Trying to login..", true);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://"+ed_ipAddress.getText().toString()+":8080/api/config?apiKey="+ed_Api.getText().toString();
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> {

                    try {
                        JSONObject array = new JSONObject(response);
                        isVerifySkyMoon = array.getBoolean("success");
                        Log.e("LOGIN_API",isVerifySkyMoon+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {

                }
        );

        //sunLite 에 대한 volley 작성해야함
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        in.putExtra("ipAddress",ed_ipAddress.getText().toString());
        in.putExtra("isVerifySunLite",isVerifySunLite);
        in.putExtra("isVerifySkyMoon",isVerifySkyMoon);
        startActivity(in);
        Animatoo.animateSlideLeft(this);
        myProgressDialog.dismiss();
        finish();

    }
}