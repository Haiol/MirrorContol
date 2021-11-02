package com.lovehp30.mirrorcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText ipAddress, APIkey;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getActionBar().hide();
        ipAddress = findViewById(R.id.et_ipAddress);

        APIkey = findViewById(R.id.et_APIkey);

    InputFilter[] filters = new InputFilter[1];
    filters[0] = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (end > start) {
                String destTxt = dest.toString();
                String resultingTxt = destTxt.substring(0, dstart) + source.subSequence(start, end) + destTxt.substring(dend);
                if (!resultingTxt.matches ("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
                    return "";
                } else {
                    String[] splits = resultingTxt.split("\\.");
                    for (int i=0; i<splits.length; i++) {
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

        // start async task to wait for 5 second that update the view
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                myProgressDialog.hide();
                StartLoginTemplate();
            }
        };
        task.execute((Void[]) null);
    }

    public void StartLoginTemplate(){
        Intent intent = new Intent(this, MainActivity.class);

        String username = ((EditText) findViewById(R.id.et_ipAddress)).getText().toString();
        intent.putExtra("username",username);

        ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        startActivity(intent);

        //finish the activity. When user click back button doesn't come back this activity
        finish();
    }
}
