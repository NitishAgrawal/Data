package com.app.android.db.apicalls;

import android.util.Log;

public class Requests {

    public static final String AUTH_TOKEN = "auth_token";

    public static String baseUrl  = "";

    public static String middleUrl  = "";

    public static String token = "";

    public static void setMiddleUrl(String middleUrl){
            Requests.middleUrl = middleUrl;
    }

    public static void setBaseUrl(String baseUrl){
        Requests.baseUrl = baseUrl;
    }

    public static void setUserToker(String token){
        Requests.token = token;
    }

    public static String getUserToker(){
        /*String token = Shadow.getAppPreferences().getString(AUTH_TOKEN,"");
        Log.e("token ", "is " + token);
        return token;*/
        return token;
    }

    public static enum ApiNames{
        login;
    }

    public static final ApiRequest login = new ApiRequest
            ("https://rondogo.herokuapp.com/api/v1/sub_categories", ServiceHandler.RequestMethod.GET,
                    false,true,false,false, ApiNames.login);
}
