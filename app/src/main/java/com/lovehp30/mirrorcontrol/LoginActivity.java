package com.lovehp30.mirrorcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText ipAddress, APIkey;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getActionBar().hide();
        ipAddress = findViewById(R.id.et_ipAddress);
        APIkey = findViewById(R.id.et_APIkey);
        checkBox = findViewById(R.id.cb_rememberMe);

        //Input filter IPAddress
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (end > start) {
                    String destTxt = dest.toString();
                    String resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend);
                    if (!resultingTxt.matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                        return "";
                    } else {
                        String[] splits = resultingTxt.split("\\.");
                        for (int i = 0; i < splits.length; i++) {
                            if (Integer.valueOf(splits[i]) > 255) {
                                return "";
                            }
                        }
                    }
                }
                return null;
            }
        };
        ipAddress.setFilters(filters);
        // CheckBox Memorized
        SharedPreferences sf  = getSharedPreferences("File",MODE_PRIVATE);
        String text1 = sf.getString("ip","");
        String text2 = sf.getString("key","");
        if(!text1.equals(""))
            checkBox.setChecked(true);
        APIkey.setText(text1);
        APIkey.setText(text2);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //CheckBox MEMO KEY
        SharedPreferences sharedPreferences = getSharedPreferences("File",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(checkBox.isChecked()){
            String text1 = APIkey.getText().toString();
            String text2 = ipAddress.getText().toString();

            editor.putString("api",text1);
            editor.putString("ip",text2);
        }
        else {
            editor.putString("api", "");
            editor.putString("ip", "");
        }
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Login_Click(final View view) throws InterruptedException {
        final ProgressDialog myProgressDialog = ProgressDialog.show(this, "Please Wait", "Trying to login..", true);
        RequestLogin requestLogin = new RequestLogin();

        // start async task to wait for 5 second that update the view
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    requestLogin.VerifyIP("lovehp12.duckdns.org",getApplicationContext());
                    requestLogin.VerifyAPI("lovehp12.duckdns.org","0bb2a5ddbc354cc5be0a24d120c4c289",getApplicationContext());

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
                if(requestLogin.getLoginStatus()) {
                    myProgressDialog.hide();
                    StartLoginTemplate();
                }
                else{
                    myProgressDialog.hide();
                    Toast.makeText(getBaseContext(),"Fail to Login",Toast.LENGTH_SHORT ).show();
                }
            }
        };
        task.execute((Void[]) null);
    }

    public void StartLoginTemplate() {
        Intent intent = new Intent(this, MainActivity.class);

        String ip = ipAddress.getText().toString();
        String api = APIkey.getText().toString();

        intent.putExtra("ip",ip);
        intent.putExtra("api",api);
        ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent);

        //finish the activity. When user click back button doesn't come back this activity
        finish();
    }
}
