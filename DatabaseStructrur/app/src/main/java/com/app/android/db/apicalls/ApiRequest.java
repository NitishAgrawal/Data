package com.app.android.db.apicalls;


import com.app.android.db.apicalls.Requests.ApiNames;
import com.app.android.db.apicalls.ServiceHandler.RequestMethod;

public class ApiRequest {

    public String path;
    public RequestMethod method;
    public boolean authunticate;
    public boolean isShowProgressBar;
    public boolean isDownload;
    public boolean isUpload;
    public ApiNames reqID;
    public String pDialogMsg = "";

    public ApiRequest(String path, ApiNames reqID) {
        this(path,ServiceHandler.RequestMethod.GET,true,true,false,false,reqID);
    }

    public ApiRequest(String path, RequestMethod method, ApiNames reqID) {
            this(path,method,true,true,false,false,reqID);
    }

    public ApiRequest(String path, RequestMethod method, ApiNames reqID, boolean isShowProgressBar) {
        this(path,method,true,isShowProgressBar,false,false,reqID);
    }

    public ApiRequest(String path, RequestMethod method, boolean authunticate, ApiNames reqID) {
        this(path,method,authunticate,true,false,false,reqID);
    }

    public ApiRequest(String path, RequestMethod method, boolean authunticate, boolean isShowProgressBar,ApiNames reqID) {
        this(path,method,authunticate,isShowProgressBar,false,false,reqID);
    }

    public ApiRequest(String path, RequestMethod method, boolean authunticate, boolean isShowProgressBar, boolean isDownload, boolean isUpload,ApiNames reqID) {
        this(path,method,authunticate,isShowProgressBar,isDownload,isUpload,reqID,"");
    }

    public ApiRequest(String path, RequestMethod method, boolean authunticate, boolean isShowProgressBar, ApiNames reqID,String pDialogMsg) {
        this(path,method,authunticate,isShowProgressBar,false,false,reqID,pDialogMsg);
    }

    public ApiRequest(String path, RequestMethod method, boolean authunticate, boolean isShowProgressBar, boolean isDownload, boolean isUpload,ApiNames reqID,String pDialogMsg) {
        this.path = path;
        this.method = method;
        this.authunticate = authunticate;
        this.isShowProgressBar = isShowProgressBar;
        this.isDownload = isDownload;
        this.isUpload = isUpload;
        this.reqID = reqID;
        this.pDialogMsg = pDialogMsg;
    }


}
