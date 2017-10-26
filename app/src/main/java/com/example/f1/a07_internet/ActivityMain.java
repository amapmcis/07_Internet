package com.example.f1.a07_internet;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ActivityMain extends AppCompatActivity {

    private TextView textView;
    private String URL = "http://date.jsontest.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.myTV);

        OkHttpGet okHttpGet = new OkHttpGet();  // get an instance of AsyncTask
        okHttpGet.execute(URL);                 // execute AsyncTask
    }

    // AsyncTask
    public class OkHttpGet extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                String url = params[0];                                 // get the url
                OkHttpClient client = new OkHttpClient();               // OkHttp client
                Request request = new Request.Builder()
                        .url(url)
                        .build();                                       // Request
                Response response = client.newCall(request).execute();  // Execute request
                return response.body().string();
            }catch (Exception e){
                // do nothing
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);              // Make JSON from request
                String timestr = jsonObject.getString("time");          // get time from JSON
                textView.setText(timestr);                              // set time to TextView
            } catch (Exception e) {
                textView.setText("Something went wrong. \nNo Internet connection");  // error message
            }
        }
    }
}
