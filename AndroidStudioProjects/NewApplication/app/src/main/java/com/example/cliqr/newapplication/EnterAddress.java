package com.example.cliqr.newapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                if(name == "" || addressType == "" || firstLine == "" || secondLine == "" || phoneNumber == "" || email == "" || pinCode == "" || city == "" || state == "")
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
    }
}
