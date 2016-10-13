package com.example.cliqr.newapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Pincode extends AppCompatActivity {

    private Button proceedButton;
    private EditText pinCodeEditText;
    private EditText cityEditText;
    private EditText stateEditText;
    String pinCode;
    String city;
    String state;
    JSONObject jsonObject;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode);
        jsonObject = new JSONObject();

        String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        proceedButton = (Button) findViewById(R.id.proceed);
        pinCodeEditText = (EditText) findViewById(R.id.pinCodeInput);
        cityEditText = (EditText) findViewById(R.id.cityInput);
        stateEditText = (EditText) findViewById(R.id.stateInput);

        proceedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                pinCode = pinCodeEditText.getText().toString();
                pinCode = pinCode.trim();
                city = cityEditText.getText().toString();
                city = city.trim();
                state = stateEditText.getText().toString();
                state = state.trim();
                if(pinCode=="" || city=="" && state==""){
                    Toast.makeText(getApplicationContext(), pinCode+" "+city+" "+state, Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    jsonObject.put("pinCode", pinCode);
                    jsonObject.put("city", city);
                    jsonObject.put("state", state);
                    editor.putString("pinCode", pinCode);
                    editor.putString("city", city);
                    editor.putString("state", state);
                    editor.commit();
                    pinCode = sharedpreferences.getString("pinCode", null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //make an ajax call here
                Toast.makeText(getApplicationContext(), pinCode+" "+city+" "+state, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
                Log.v("pinCodeEditText", pinCodeEditText.getText().toString());
                Log.v("cityEditText", cityEditText.getText().toString());
                Log.v("stateEditText", stateEditText.getText().toString());
            }
        });
    }
}
