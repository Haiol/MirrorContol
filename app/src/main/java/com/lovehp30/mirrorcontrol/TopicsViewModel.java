package com.lovehp30.mirrorcontrol;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.lovehp30.mirrorcontrol.sqllite.MQDbOpenHelper;

import java.util.ArrayList;

public class TopicsViewModel extends ViewModel {
    private MQDbOpenHelper databaseHelper;
    private ArrayList<ListViewItem> datas;
    // TODO: Implement the ViewModel
    void setDatabaseHelper(Context v,String ip){
        ip = ip.replaceAll("[.]","");
        databaseHelper = new MQDbOpenHelper(v,ip);
    }

    ArrayList<ListViewItem> getDBData(){
        databaseHelper.open();
        ArrayList<ListViewItem> list = new ArrayList<>();
        Cursor cursor = databaseHelper.getAllColumns();
        if(cursor.getColumnCount()==0){
            list.add(new ListViewItem(0,"",""));
            return list;
        }
            Log.e("DB","Count = "+cursor.getCount());
        while (cursor.moveToNext()){
            list.add(new ListViewItem(
                    cursor.getLong(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("code")),
                    cursor.getString(cursor.getColumnIndex("topic"))
            ));
        }
        cursor.close();
        databaseHelper.close();
        return list;
    }
}