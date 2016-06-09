package com.app.android.db;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.app.android.db.dbhelper.SubModel;

public class MainActivity2 extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            SubModel sub2 = (SubModel)getIntent().getSerializableExtra("data");
            Log.e("Print MainActivity 2 ","is "+sub2.url.getFieldDb());
    }
}
