package com.app.android.db;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.app.android.db.apicalls.APIServiceAsyncTask;
import com.app.android.db.apicalls.ApiRequest;
import com.app.android.db.apicalls.Requests;
import com.app.android.db.apicalls.Requests.ApiNames;
import com.app.android.db.dbhelper.BaseModel;
import com.app.android.db.dbhelper.SubModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import library.module.dialog.DialogBuilder;
//import libary.module.dialog.Effectstype;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<SubModel> modelList;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelList = new ArrayList<SubModel>();
        editText = (EditText) findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        DialogBuilder.setDefaultCustomDialog(this,"This is a default msg",true,"Alert").setThemeLight().setCancelableOnTouchOutside(false);
        //DialogBuilder builder = DialogBuilder.setDefaultCustomDialog(this,"This is a default msg","Alert",false,Effectstype.Newspager, Effectstype.Fadein,700,true,DialogBuilder.THEME_LIGHT);
        //callApi();
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
        Log.e("print all vaule test30", "is " + (subNew2.category_id.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.is_deleted.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.created_at.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.updated_at.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.subcategory_name.getFieldDb()));
        Log.e("print all vaule test30", "is " + (subNew2.sequence_no.getFieldDb()));
       }
    }

    private void callApi() {
        AppService service = new AppService(this, Requests.login,null);
        service.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                //SubModel._instance.delete("is_deleted = 0");
                editText.setText("" + BaseModel.find(Integer.parseInt(editText.getText().toString()), SubModel._instance));
                break;

            case R.id.button2:
                //SubModel._instance.deleteAll();
                //editText.setText("");
                goToNextWithData();
                break;
        }
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
                            row.getString("category_id"),
                            row.getBoolean("is_deleted"),
                            //false,
                            row.getString("created_at"),
                            row.getString("updated_at"),
                            row.getString("subcategory_name"),
                            row.getString("sequence_no")
                    );
                    if(modelNew2.update()<1){
                     modelNew2.save();
                    }
                    modelList.add(modelNew2);
                    if(i==3){
                        Log.e("print toString ",modelNew2.toString());

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //goToNextWithData();
        }
    }

    private void goToNextWithData(){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("data", modelList.get(1));
        startActivity(intent);
    }

}
