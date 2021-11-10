package com.lovehp30.mirrorcontrol;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
//            Log.e("Main","not Null");
            actionBar.setHomeAsUpIndicator(R.drawable.outline_menu_24);
            actionBar.setDisplayHomeAsUpEnabled(true);
            drawer = findViewById(R.id.drawer_layout);
            actionBar.setDisplayHomeAsUpEnabled(true);
            mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.hello_world, R.string.hello_world)
            {

                public void onDrawerClosed(View view)
                {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = false;
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
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                item.setChecked(true);
//                drawer.closeDrawers();
//                return true;
//            }
//        });





        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
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
//                    toolbar.setTitle("MirrorControl");

                }
                else if(position==0 && positionOffset>0){
                    fab.setVisibility(View.VISIBLE);
                    fab.setAlpha(positionOffset);
                }else if(position==1 && positionOffset==0){

                }else{
//                    toolbar.setTitle("SearchData");
                }
            }

        });

    }




}