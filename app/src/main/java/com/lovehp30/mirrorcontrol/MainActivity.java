package com.lovehp30.mirrorcontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.lovehp30.mirrorcontrol.login.LoginActivity;
import com.lovehp30.mirrorcontrol.sqllite.MQDbOpenHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    String ip;
    boolean isVerifySunLite,isVerifySkyMoon;
    ActionBar actionBar;
    DrawerLayout drawer;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.parseColor("#3d3d3d"));
        Bundle bundle = getIntent().getExtras();
        ip = bundle.getString("ipAddress");
        isVerifySkyMoon = bundle.getBoolean("isVerifySkyMoon");
        isVerifySunLite = bundle.getBoolean("isVerifySunLite");
        Log.e("Main",isVerifySkyMoon+"  "+isVerifySunLite);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle;
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.outline_menu_24);
            actionBar.setDisplayHomeAsUpEnabled(true);
            drawer = findViewById(R.id.drawer_layout);
            actionBar.setDisplayHomeAsUpEnabled(true);
            mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.hello_world, R.string.hello_world)
            {

                public void onDrawerClosed(View view)
                {
                    supportInvalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView)
                {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = true;
                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            drawer.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(false);
            drawer.closeDrawers();
            return true;
        });




        ViewPager2 pager2 = findViewById(R.id.viewPager);
        MainActViewAdapter adapter=new MainActViewAdapter(getSupportFragmentManager(),getLifecycle());
        pager2.setAdapter(adapter);
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Log.e("Scroll",position+" "+positionOffset+" "+positionOffsetPixels);
                if(position ==0 && positionOffset ==0) {
                    fab.setVisibility(View.INVISIBLE);
                    setTheme(R.style.Theme_MaterialComponents_Light_NoActionBar);


                }
                else if(position==0 && positionOffset>0){
                    fab.setVisibility(View.VISIBLE);
                    fab.setAlpha(positionOffset);
                }else if(position==1 && positionOffset==0){

                }else{


                }
            }

        });


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                View edit  = getLayoutInflater().inflate(R.layout.edit_layout, null);
                Button ed_btn = edit.findViewById(R.id.ed_ok);
                builder.setView(edit);
                AlertDialog ad = builder.create();
                ed_btn.setOnClickListener(v1 -> {
                    TextInputEditText code = edit.findViewById(R.id.ed_code);
                    TextInputEditText topic = edit.findViewById(R.id.ed_topic);
                    if(!code.getText().toString().equals("")){
                        MQDbOpenHelper helper = new MQDbOpenHelper(getBaseContext(),"lovehp12duckdnsorg");
                        helper.open();
                        helper.insertColumn(code.getText().toString(),topic.getText().toString());
                        Cursor cursor = helper.getRecentColumns();
                        if(cursor.moveToNext()) {
                            adapter.addListData(new ListViewItem(
                                    cursor.getLong(cursor.getColumnIndex("_id")),
                                    cursor.getString(cursor.getColumnIndex("code")),
                                    cursor.getString(cursor.getColumnIndex("topic"))
                            ));
                        }
                        helper.close();
                        cursor.close();
                        ad.dismiss();
                    }
                });
                ad.show();
            }
        });
    }
    public void logOutThisIp(View v){
        Intent in = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(in);
        Animatoo.animateSlideRight(this);
        finish();

    }




}