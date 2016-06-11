package com.app.android.db.apicalls;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class APIServiceAsyncTask extends AsyncTask<Void, Integer, Object> {

    private Map<String, String> serviceParamsMap = null;
    private Map<String, Object> serviceParamsObjectMap = null;

    private ApiRequest apiRequest = null;
    private ProgressDialog mDialog = null;
    Context mContext;

    public APIServiceAsyncTask(Context mContext) {
        this.mContext = mContext;
    }

    public APIServiceAsyncTask(Context mContext,ApiRequest apiRequest,Map<String, String> serviceParamsMap) {
        this.mContext = mContext;
        this.apiRequest = apiRequest;
        this.serviceParamsMap = serviceParamsMap;
    }

    public APIServiceAsyncTask(Context mContext,Map<String, Object> serviceParamsMap,ApiRequest apiRequest) {
        this.mContext = mContext;
        this.apiRequest = apiRequest;
        this.serviceParamsObjectMap = serviceParamsMap;
    }

    public void showProgress(boolean isShow) {
        showProgress(isShow,null);
    }
    public void showProgress(boolean isShow,String pDialogMsg) {
            if (isShow) {
                mDialog = new ProgressDialog(mContext);
                mDialog.setMessage((pDialogMsg != null && pDialogMsg.length()>0)?pDialogMsg:"Please Wail...");
                mDialog.setCancelable(false);
                if(!((Activity) mContext).isFinishing())
                    mDialog.show();
            } else {
                if(mDialog != null && mDialog.isShowing())
                    mDialog.hide();
            }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(CheckNetwork.isNetworkAvailable(mContext)) {
            if(apiRequest.isShowProgressBar)
                try {
                    showProgress(true,apiRequest.pDialogMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }else {
            CheckNetwork.ShowDialog(mContext);
            APIServiceAsyncTask.this.cancel(true);
        }
    }

    @Override
    protected Object doInBackground(Void... params) {

            try {
                if (apiRequest != null) {
                    ServiceHandler sh = new ServiceHandler();
                    JSONObject jsonObj = null;
                    // Making a request to url and getting response
                    if (apiRequest.isDownload) {
                        //jsonStr = sh.makeDownloadCall(MediaDownloadURLKey, MediaSavePathKey,serviceParamsMap).toString();
                    //} else if (apiRequest.isUpload) {
                        //jsonStr = sh.makeUploadCall();
                    } else {
                        jsonObj = sh.makeServiceCall(apiRequest, serviceParamsMap);
                    }
                    if (jsonObj != null) {
                        Log.e("Working", "Json Object not null");
                        return jsonObj;
                    } else {
                        //Shadow.alertToast("Exception : Couldn't get any data from the url");
                        Log.e("Exception", "Couldn't get any data from the url");
                        failure("Couldn't get any data from the url");
                        return null;
                    }
                } else {
                    //Shadow.alertToast("Exception : Something went wrong");
                    Log.e("Exception", "Something went wrong");
                    failure("Something went wrong");
                    return null;
                }

            } catch (Exception e) {
                //Shadow.alertToast("Exception" + e.getMessage());
                //Log.e("Exception", e.getMessage());
                e.printStackTrace();
                return null;
            }

    }

    @Override
    protected void onPostExecute(Object o) {
        //super.onPostExecute(o);
        showProgress(false);
        if(o instanceof JSONObject){
            Log.e("print instanceof ", "json");
            JSONObject jsonObj = (JSONObject)o;
            try {
                Log.e("print jsonobject ", "is " + jsonObj.toString());
                if(jsonObj.getBoolean("success")){
                    this.success(jsonObj,apiRequest.reqID);
                    Log.e("Success", "Goto success block");
                }else{
                    Log.i("Failure", "Goto failure block");
                    this.failure("FAILURE");
                    this.failure(jsonObj, apiRequest.reqID);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(o == null){

        }

        onFinished(apiRequest.reqID);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        showProgress(false);
        Toast.makeText(mContext,"Please retry",Toast.LENGTH_LONG).show();
    }

    protected void success(JSONObject jsonObj,Requests.ApiNames serviceTaskType) {

    }

    protected void failure(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    protected void failure(JSONObject jsonObj,Requests.ApiNames serviceTaskType) {

    }

    protected void onFinished(Requests.ApiNames serviceTaskType) {

    }


}