package com.app.android.db;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.app.android.db.apicalls.APIServiceAsyncTask;
import com.app.android.db.apicalls.ApiRequest;
import com.app.android.db.apicalls.Requests;
import com.app.android.db.apicalls.Requests.ApiNames;
import com.app.android.db.dbhelper.BaseModel;
import com.app.android.db.dbhelper.DatabaseHandler;
import com.app.android.db.dbhelper.SubModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    ArrayList<SubModel> modelList;
    ListView listView;
    RowListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        modelList = new ArrayList<SubModel>();
        callApi();
        //showData();
    }

    private void showData() {
        Log.e("print all data ","workin 1");
        SubModel subModel = new SubModel();
        Log.e("print all data ","workin 2");
        ArrayList<SubModel> lst= ((ArrayList) subModel.findDataById(84));
        Log.e("print all data ", "workin 4 " + lst.size());
        for (SubModel subNew2:lst) {

        Log.e("print all vaule test30", "is " + (subNew2.id.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.url.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.category_language.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.is_deleted.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.created_at.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.updated_at.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.category_name.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.sequence_no.getFieldDb()));
       }
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
                            //false,
                            row.getString("created_at"),
                            row.getString("updated_at"),
                            row.getString("category_name"),
                            row.getString("sequence_no")

                    );
                    try {
                        if(modelNew2.update()<1){
                            modelNew2.save();
                            modelList.add(modelNew2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //modelList.addAll(SubModel._instance.findAllData());
                adapter= new RowListAdapter(MainActivity.this,android.R.layout.simple_expandable_list_item_1,modelList);
                listView.setAdapter(adapter);

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
