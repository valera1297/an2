package com.example.valera;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class MyGSON {
    @SerializedName("BriefInformation")
    @Expose
    public Integer BriefInformation;

    @SerializedName("Capital")
    @Expose
    public String Capital;

    @SerializedName("Name")
    @Expose
    public String Name;
}

class MyAsyncTask extends AsyncTask<String, Integer, String> {
    URL url = null;
    HttpURLConnection conn = null;
    protected void onPreExecute(){

        try {
            url = new URL("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/Guide2.json");
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }


        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }



        Log.e("MyAsyncTask", "onPreExecute");
    }
    protected String doInBackground(String... arg) {
        StringBuilder response = null;
        try {
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                response = new StringBuilder();
                BufferedReader input = new BufferedReader(new InputStreamReader
                        (conn.getInputStream()), 8192);
                String line = null;
                while ((line = input.readLine()) != null)
                {
                    response.append(line);
                }
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e("MyAsyncTask", response + "");
        return response + "";
    }
    protected void onPostExecute(String output) {
        Gson gson = new Gson();
        MyGSON[] myjson = gson.fromJson(output, MyGSON[].class);
        gg.recyclerData.clear();

        for(int i = 0; i< myjson.length; i++){

            gg.recyclerData.add(myjson[i].Name + "");
            gg.recyclerData2.add(myjson[i].Capital + "");
            Log.e("","" + gg.recyclerData.get(i));
            Log.e("MyAsyncTask", "BriefInformation  = " + myjson[i].BriefInformation +
                    "; Capital = " + myjson[i].Capital +  "; Naaaaaaame = " + gg.recyclerData.get(i));
        }

    }
}