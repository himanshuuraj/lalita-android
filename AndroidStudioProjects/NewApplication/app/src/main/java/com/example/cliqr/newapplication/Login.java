package com.example.cliqr.newapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    String email;
    String password;

    EditText emailEditText;
    EditText passwordEditText;
    Button login;
    TextView signup;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        emailEditText = (EditText) findViewById(R.id.addressType);
        passwordEditText = (EditText) findViewById(R.id.firstLine);
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        login = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signUp);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),SignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email == "" || email == null || password == "" || password == null){
                    Toast.makeText(getApplicationContext(), "Fields not set Properly", Toast.LENGTH_SHORT).show();
                    return;
                }
                //make a ajax call
                SharedPreferences.Editor editor = sharedpreferences.edit();
                String userInfo = "{'userName' : 'Himanshu','email':'hraj3116@gmail.com'}";
                editor.putString("userInfo",userInfo);
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
