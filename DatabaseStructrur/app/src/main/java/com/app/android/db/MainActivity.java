package com.app.android.db;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.app.android.db.apicalls.APIServiceAsyncTask;
import com.app.android.db.apicalls.ApiRequest;
import com.app.android.db.apicalls.Requests;
import com.app.android.db.apicalls.Requests.ApiNames;
import com.app.android.db.dbhelper.SubModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity{

    ArrayList<SubModel> modelList = new ArrayList<SubModel>();
    ListView listView;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        if(SubModel._instance.count()<1) {
            callApi();
        }else {
            callAdapter();
        }
    }

    private void callAdapter() {
        modelList.addAll(SubModel._instance.findAllData());
        adapter= new CustomAdapter(MainActivity.this,R.layout.row,modelList);
        listView.setAdapter(adapter);
    }

    private void callApi() {
        AppService service = new AppService(this, Requests.login,null);
        service.execute();
    }


    class AppService extends APIServiceAsyncTask{

        public AppService(Context mContext, ApiRequest login, Object o) {
            super(mContext,login,(HashMap)o);
        }

        @Override
        protected void success(JSONObject jsonObj, ApiNames serviceTaskType) {
            super.success(jsonObj, serviceTaskType);
            try {
                JSONArray jsonArray = jsonObj.getJSONArray("data");
                SubModel modelNew2;
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject row = jsonArray.getJSONObject(i);
                    modelNew2 = new SubModel(
                            row.getInt("id"),
                            (row.getJSONObject("object_data").getString("url")).toString(),
                            row.getString("category_language"),
                            row.getBoolean("is_deleted"),
                            row.getString("created_at"),
                            row.getString("updated_at"),
                            row.getString("category_name"),
                            row.getString("sequence_no")
                    );
                    try {
                        if(modelNew2.update()<1){
                            modelNew2.save();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
               callAdapter();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void goToNextWithData(){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("data", modelList.get(1));
        startActivity(intent);
    }

}
