package com.example.gmanni.arduinoapp;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gmanni on 03/01/2018.
 */

public class blynkClass {
    String auth_token;
    String server;



    public blynkClass(String auth_token){
        server = "http://blynk-cloud.com/";
        this.auth_token = auth_token;
    }

    public String getValue(){
        return "";
    }

    public String setValue(){
        return "";
    }

    public boolean isConnected(){
        return true;
    }
    public boolean getConnectionStatus(){
        return true;
    }

    public void prova() {
        HttpGetRequest hgr = new HttpGetRequest();
        hgr.execute();
    }

    /*
    * Params, Progress, Result
    Result doInBackground (Params... params)
    void onProgressUpdate (Progress... values)
    void onPostExecute (Result result)
    */

    public class HttpGetRequest extends AsyncTask<Void, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        public String serverUrl;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("HttpGetRequest", "onPreExecute");

        }

        @Override
        protected String doInBackground(Void... params){
            Log.i("HttpGetRequest", "doInBackground");
            //String stringUrl = params[0];
            String result;
            String inputLine;
            serverUrl = "http://blynk-cloud.com/30fd52bdfd024481a8fc7db4ee924a20/get/d6/";
            //serverUrl = "http://www.google.it/";


            try {
                //Create a URL object holding our url
                //URL myUrl = new URL(stringUrl);
                URL myUrl = new URL(serverUrl);

                //Create a connection
                HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();

                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();

                //Create a new InputStreamReader
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }

                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                Log.i("VEDIAMO", stringBuilder.toString());
                result = stringBuilder.toString();

            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
            //return "CIAO";
        }
        protected void onPostExecute(String result){
            result="CIAO";
            Log.i("HttpGetRequest", "onPostExecute");
            Log.i("result", result.toString());
            super.onPostExecute(result);
        }


    }
}
