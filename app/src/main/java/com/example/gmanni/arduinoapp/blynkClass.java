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


    public blynkClass(String authToken){
        this.server = "http://blynk-cloud.com/";
        this.auth_token = authToken;
    }

    public void getPinValue(String pinType, int pinNumber){
        //HttpGetRequest getRequest = new HttpGetRequest();
        //String risultato = getRequest.execute(this.server + this.auth_token + "/get/" + pinType + String.valueOf(pinNumber));
        Log.i("CONNESSO", this.isConnected());
    }

    public String setValue(){
        //getRequest.execute(this.server + this.auth_token + "/update/d6?value=0");
        return "";
    }

    public String isConnected(){
        // verifica se l'hardware legato al token è connesso
        HttpGetRequest getRequest = new HttpGetRequest();
        String risultato = String.valueOf(getRequest.execute(this.server + this.auth_token + "/isHardwareConnected"));
        return risultato;
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


    public class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);

                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();

                Log.i("RESPONSE CODE", String.valueOf(connection.getResponseCode()));

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
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){
            Log.i("VEDIAMO ", result.toString());
            super.onPostExecute(result);

        }
    }


    }
