package com.app.android.db.apicalls;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


public class ServiceHandler {

    static String response = null;

    public static enum RequestMethod{
        GET,POST,PUT,PATCH,DELETE;
    }
    /*public final static int GET = 1;
    public final static int POST = 2;
    public final static int PUT = 3;
    public final static int PATCH = 4;
    public final static int DELETE = 5;*/

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */

    private String getURlFormPath(String path) {
        return Requests.baseUrl+Requests.middleUrl+ path;
    }

/*
    private String getURlFormPath(String path,List<NameValuePair> params) {
        if (params != null) {
            String paramString = URLEncodedUtils
                    .format(params, "utf-8");
            return this.baseUrl + path + "?" + paramString;
        }
        return this.baseUrl + path;
    }

    private HttpPost postRequest(String path,List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(this.getURlFormPath(path));
        if (params != null) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return httpPost;
    }

    private HttpPatch patchRequest(String path,List<NameValuePair> params) {
        HttpPatch httpPatch = new HttpPatch(this.getURlFormPath(path));
        if (params != null) {
            try {
                httpPatch.setEntity(new UrlEncodedFormEntity(params));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return httpPatch;
    }

    private HttpGet getRequest(String path,List<NameValuePair> params) {
        return new HttpGet(this.getURlFormPath(path,params));
    }
    private HttpPut putRequest(String path,List<NameValuePair> params) {
        return new HttpPut(this.getURlFormPath(path,params));
    }
    private HttpDelete deleteRequest(String path,List<NameValuePair> params) {
        return new HttpDelete(this.getURlFormPath(path,params));
    }

    */

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */

    /*
    public String makeServiceCall1(String path, int method,
                                  List<NameValuePair> params) {
        try {
            // http client
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = this.postRequest(path,params);
                httpResponse = httpClient.execute(httpPost);
            } else if (method == GET) {
                HttpGet httpGet = this.getRequest(path,params);
                httpResponse = httpClient.execute(httpGet);
            } else if (method == PUT) {
                HttpPut httpPut = this.putRequest(path,params);
                httpResponse = httpClient.execute(httpPut);
            } else if (method == DELETE) {
                HttpDelete httpDelete = this.deleteRequest(path,params);
                httpResponse = httpClient.execute(httpDelete);
            }

            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }
    */

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */

    /*
    public JSONObject makeServiceCall(String path, int method,List<NameValuePair> params) {
        String paramsStr = null;
        try {
            paramsStr = getQuery(params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return makeServiceCallBase(path, method,paramsStr);
    }
    */

    public JSONObject makeServiceCall(ApiRequest apiRequest,
                                      Map<String, String> params) {
            String paramsStr = null;
            try {
                if(params != null)
                paramsStr = getQuery(params);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return makeServiceCallBase(apiRequest, paramsStr);
    }

    public JSONObject makeServiceCallBase(ApiRequest apiRequest,
                                  String paramsStr) {
        HttpURLConnection urlConnection = null;

        URL url = null;
        JSONObject object = null;
        InputStream inStream = null;
        String temp, response="";
        try {
            String urlStr = this.getURlFormPath(apiRequest.path);
            if(apiRequest.method == RequestMethod.DELETE && paramsStr!= null){
                urlStr=urlStr+"?"+paramsStr;
            }
            url = new URL(urlStr);

            Log.e("URL is ", "" + this.getURlFormPath(apiRequest.path));
            Log.e("Method is ", "" + apiRequest.method);
            Log.e("peramers is ", "" + paramsStr);

            urlConnection = (HttpURLConnection) url.openConnection();

            if (apiRequest.method == RequestMethod.POST) {
                urlConnection.setRequestMethod("POST");
            } else if (apiRequest.method == RequestMethod.GET) {
                urlConnection.setRequestMethod("GET");
            } else if (apiRequest.method == RequestMethod.PUT) {
                urlConnection.setRequestMethod("PUT");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
            } else if (apiRequest.method == RequestMethod.PATCH) {
                urlConnection.setRequestMethod("PATCH");
            } else if (apiRequest.method == RequestMethod.DELETE) {
                urlConnection.setDoInput(true);
                urlConnection.setInstanceFollowRedirects(false);
                if(apiRequest.authunticate) {
                    urlConnection.setRequestProperty("auth-token", Requests.getUserToker());
                }
                urlConnection.setRequestProperty("Connection", "Keep-Alive");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestMethod("DELETE");
                urlConnection.setRequestProperty("charset", "utf-8");
                urlConnection.setUseCaches(false);
                urlConnection.connect();
            }

            if(apiRequest.authunticate && apiRequest.method != RequestMethod.DELETE) {
                urlConnection.setRequestProperty("auth_token", Requests.getUserToker());
            }
            if(apiRequest.method != RequestMethod.DELETE && apiRequest.method != RequestMethod.GET) {
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                if(apiRequest.method == RequestMethod.PUT){
                    OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
                    if(paramsStr != null &&  paramsStr.length()>0)
                    osw.write(paramsStr);
                    osw.flush();
                    osw.close();
                    System.err.println(urlConnection.getResponseCode());
                }else {
                    OutputStream os = urlConnection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(paramsStr);
                    Log.e("paramsStr", "is " + paramsStr);
                    writer.flush();
                    writer.close();
                    os.close();
                    urlConnection.connect();
                }
            }
            try {
                int status = urlConnection.getResponseCode();
                Log.e("Print Response Code", "" + status);
                //if((status%100) != HttpStatus.SC_BAD_REQUEST)
                if((status/100) != 2)
                    inStream = urlConnection.getErrorStream();
                else
                    inStream = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                inStream = urlConnection.getErrorStream();
                if(inStream == null){
                    inStream = urlConnection.getInputStream();
                }
            }

            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            while ((temp = bReader.readLine()) != null) {
                response += temp;
            }

            Log.e("Response is ", "" + response);
            object = (JSONObject) new JSONTokener(response).nextValue();

            //object= new JSONObject(getStringFromInputStream(inStream));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    // this will close the bReader as well
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        try {
            Log.e("JsonObject", "" + object.toString());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return object;
    }


    public JSONObject makeDownloadCall(String downloadURLKey, String savePathKey,Map<String, String> params){
        String downloadUrl = params.get(downloadURLKey);
        String saveFilePath = params.get(savePathKey);
        if(downloadUrl == null || saveFilePath == null){
            return null;
        }
        JSONObject object = null;
        final int  BYTE = 1024;
        final int  MEGABYTE = BYTE * 1024;
        HttpURLConnection urlConnection = null;
        URL url = null;
        try {
            url = new URL(downloadUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.setDoInput(true);
            //urlConnection.setDoOutput(true);

            String afterSaveFilePath = saveFilePath;
            String lastStr = saveFilePath.substring(saveFilePath.lastIndexOf('/') + 1);
            saveFilePath = saveFilePath.replace(lastStr,"tempData__"+ lastStr);
            Log.e("FileName: ", afterSaveFilePath);
            Log.e("FileName: ", saveFilePath);


            urlConnection.connect();
            File tmpFile = new File(saveFilePath);
            FileOutputStream fileOutput = new FileOutputStream(tmpFile);
            InputStream inputStream = urlConnection.getInputStream();
            int totalSize = urlConnection.getContentLength();
            int downloadedSize = 0;
            float presentSize = totalSize/100;
            byte[] buffer = new byte[MEGABYTE];
            int bufferSize = 0;
            while ( (bufferSize = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferSize);
                downloadedSize += bufferSize;
                //Log.e("Progress", "Downloaded : "+  Math.round(downloadedSize/presentSize)+" %");
            }
            fileOutput.close();

            File file = new File(afterSaveFilePath);
            if(tmpFile.exists())
                tmpFile.renameTo(file);

            object = (JSONObject) new JSONTokener("{\"success\":true}").nextValue();
        } catch (Exception e) {
            //Log.e("Progress", "Error : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return object;
    }

  /*  public JSONObject makeUploadCall(String path, int method,Map<String, Object> params){
        String fileName = params.get("path").toString();
        JSONObject object = null;
        int serverResponseCode = 0;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(fileName);

        if (sourceFile.isFile()) {
            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(""+urls[APIBASEURL]+path);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName +"\"" + lineEnd);
                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                     int progress = (int)((bytesRead / (float) bufferSize) * 100);
                     //publishProgress(progress);

                }


               // publishProgress(100);

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){
                    RondogoApp.alertToast("Done");
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();
                object = (JSONObject) new JSONTokener("{\"success\":true}").nextValue();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } // End else block
        return object;
    }*/
    /*
    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
    */
    public static String getQuery(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuffer requestParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            Iterator<String> paramIterator = params.keySet().iterator();
            while (paramIterator.hasNext()) {
                String key = paramIterator.next();
                String value = params.get(key);
                requestParams.append(URLEncoder.encode(key, "UTF-8"));
                requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
                requestParams.append("&");
            }
        }
        int lenght =requestParams.toString().length();
        if(lenght>0)
            return requestParams.toString().substring(0,(lenght-1));
        else
            return requestParams.toString();
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
