package com.example.valera;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryInfoFragment extends Fragment {


    public CountryInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("InputString");
        return inflater.inflate(R.layout.fragment_country_info, container, false);
    }

}
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
            url = new URL("https://raw.githubusercontent.com/Dmtittrriy/testjasonlab/master/Guide.json");
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


        for(int i = 0; i< myjson.length; i++){

            Log.e("MyAsyncTask", "BriefInformation  = " + myjson[i].BriefInformation +
                    "; Capital = " + myjson[i].Capital +  "; Name = " + myjson[i].Name);
        }

    }
}