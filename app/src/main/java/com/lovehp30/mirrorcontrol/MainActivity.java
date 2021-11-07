package com.lovehp30.mirrorcontrol;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    ActionBar actionBar;
    FloatingActionButton fab;
    Animation fadeInAnim,fadeOutAnim;
    Drawable menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.outline_menu_24);
        actionBar.setDisplayHomeAsUpEnabled(true);


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                navigationView.clearFocus();
                navigationView.requestFocus();
                drawer.closeDrawers();

                return true;
            }
        });

        /// 메뉴 생성-------------
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //Make snackbar
                Snackbar snackbar = Snackbar.make(view,"",Snackbar.LENGTH_INDEFINITE);
                Snackbar.SnackbarLayout sLayout = (Snackbar.SnackbarLayout)snackbar.getView();

                View snackView = getLayoutInflater().inflate(R.layout.exit_snackbar, null);
                Button button = snackView.findViewById(R.id.sb_ok);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                sLayout.addView(snackView,0);
                snackbar.show();

            }
        });

        //Float----


        Fragment fragment1 = new ListMQFragment();
        ViewPager2 pager2 = findViewById(R.id.viewPager);
        MainActViewAdapter adapter=new MainActViewAdapter(getSupportFragmentManager(),getLifecycle());
        pager2.setAdapter(adapter);
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @SuppressLint("RestrictedApi")
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

//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                if(position == 0){
//                    actionBar.setDisplayHomeAsUpEnabled(true);
//
//                    fab.startAnimation(fadeOutAnim);
//                    fab.setVisibility(View.INVISIBLE);
//                }else{
//                    actionBar.setDisplayHomeAsUpEnabled(false);
//                    fab.startAnimation(fadeInAnim);
//                    fab.setVisibility(View.VISIBLE);
//                }
////                Toast.makeText(getApplicationContext(),position+"",Toast.LENGTH_SHORT).show();
//            }
        });

        //        ViewPager2




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}