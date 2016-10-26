package com.example.cliqr.newapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class EnterAddress extends AppCompatActivity {

    String name;
    String addressType;
    String firstLine;
    String secondLine;
    String phoneNumber;
    String email;
    String pinCode;
    String city;
    String state;
    Button enterAddress;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_address);

        String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String addresses = "";
        int index = 0;
        try {
            addresses = getIntent().getExtras().getString("addresses");
            index = Integer.parseInt(getIntent().getExtras().getString("indexOfAddress"));
        }catch(Exception e){}
        if(addresses != null){
            JSONObject jsonObject = null;
            try {
                JSONArray jsonArray = new JSONArray(addresses);
                jsonObject = jsonArray.getJSONObject(index);
                EditText et = (EditText) findViewById(R.id.name);
                et.setText(jsonObject.getString("name"));
                EditText et1 = (EditText) findViewById(R.id.addressType);
                et1.setText(jsonObject.getString("addressType"));
                et = (EditText) findViewById(R.id.firstLine);
                et.setText(jsonObject.getString("firstLine"));
                et = (EditText) findViewById(R.id.secondLine);
                et.setText(jsonObject.getString("secondLine"));
                et = (EditText) findViewById(R.id.phoneNumber);
                et.setText(jsonObject.getString("phoneNumber"));
                et = (EditText) findViewById(R.id.email);
                et.setText(jsonObject.getString("email"));
                et = (EditText) findViewById(R.id.pinCode);
                et.setText(jsonObject.getString("pincode"));
                et = (EditText) findViewById(R.id.city);
                et.setText(jsonObject.getString("city"));
                et = (EditText) findViewById(R.id.state);
                et.setText(jsonObject.getString("state"));
            }catch (Exception e){}
        }
        enterAddress = (Button) findViewById(R.id.enterAddress);
        enterAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                EditText et = (EditText) findViewById(R.id.name);
                name = et.getText().toString();
                et = (EditText) findViewById(R.id.addressType);
                addressType = et.getText().toString();
                et = (EditText) findViewById(R.id.firstLine);
                firstLine = et.getText().toString();
                et = (EditText) findViewById(R.id.secondLine);
                secondLine = et.getText().toString();
                et = (EditText) findViewById(R.id.phoneNumber);
                phoneNumber = et.getText().toString();
                et = (EditText) findViewById(R.id.email);
                email = et.getText().toString();
                et = (EditText) findViewById(R.id.pinCode);
                pinCode = et.getText().toString();
                et = (EditText) findViewById(R.id.city);
                city = et.getText().toString();
                et = (EditText) findViewById(R.id.state);
                state = et.getText().toString();

                if(name == "" || addressType == "" || firstLine == "" || phoneNumber == "" || email == "" || pinCode == "" || city == "" || state == "")
                {
                    Toast.makeText(getApplicationContext(), "Please fill the address properly", Toast.LENGTH_SHORT).show();
                    return;
                }
                // make an ajax call
                String allAddress = "";
                editor.putString("userInfo", allAddress);
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView map = (ImageView) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
