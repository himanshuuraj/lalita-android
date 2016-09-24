package com.example.cliqr.lalita;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button proceedButton;
    private EditText pinCodeEditText;
    private EditText cityEditText;
    private EditText stateEditText;
    int pinCode;
    String city;
    String state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proceedButton = (Button) findViewById(R.id.proceed);
        pinCodeEditText = (EditText) findViewById(R.id.pinCodeInput);
        cityEditText = (EditText) findViewById(R.id.cityInput);
        stateEditText = (EditText) findViewById(R.id.stateInput);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pinCode = Integer.parseInt(pinCodeEditText.getText().toString());
                city = cityEditText.getText().toString();
                state = stateEditText.getText().toString();
                Log.v("pinCodeEditText", pinCodeEditText.getText().toString());
                Log.v("cityEditText", cityEditText.getText().toString());
                Log.v("stateEditText", stateEditText.getText().toString());
                String serverURL = "http://jsonparsing.parseapp.com/jsonData/moviesData.txt";
                new LongOperation().execute(serverURL);
            }
        });
    }

    // Class with extends AsyncTask class
    private class LongOperation  extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            // NOTE: You can call UI Element here.

            //UI Element

        }

        // Call after onPreExecute method
        protected String doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String output = "t";
            try{
                //URL url= new URL("https://beacon.spotdy.com/spotdy-apievents/craftsvilla/events?inputJson={'properties':[]}");
                URL url = new URL("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
                connection= (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line= "";
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                output = buffer.toString();
                //Toast.makeText(getBaseContext(),a,Toast.LENGTH_LONG).show();
            }catch(MalformedURLException e){
            }catch(IOException e){
            }
            return output;
        }

        protected void onPostExecute(String result) {
            // NOTE: You can call UI Element here.
            if(result != null) {
                String t = result;
                // Close progress dialog
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
            Intent i=new Intent(getBaseContext(),Home.class);
            startActivity(i);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
