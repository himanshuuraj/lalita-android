package com.example.cliqr.newapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private Button signUp;
    private EditText nameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText confirmPasswordEditText;
    private EditText phoneNumberEditText;
    private TextView login;
    String name;
    String email;
    String password;
    String confirmPassword;
    String phoneNumber;

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        nameEditText = (EditText) findViewById(R.id.name);
        emailEditText = (EditText) findViewById(R.id.addressType);
        passwordEditText = (EditText) findViewById(R.id.firstLine);
        confirmPasswordEditText = (EditText) findViewById(R.id.secondLine);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumber);
        signUp = (Button) findViewById(R.id.signUp);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Login.class);
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                if(name == "") {
                    Toast.makeText(getApplicationContext(), "Name is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email == "") {
                    Toast.makeText(getApplicationContext(), "Email is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password == "") {
                    Toast.makeText(getApplicationContext(), "Password is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(confirmPassword == "") {
                    Toast.makeText(getApplicationContext(), "Confirm Password is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phoneNumber == "") {
                    Toast.makeText(getApplicationContext(), "PhoneNumber is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!isValidEmail(email)){
                    Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
                    Toast.makeText(getApplicationContext(), "Phone Number is invalid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(password == confirmPassword)){
                    Toast.makeText(getApplicationContext(), "Password and Confirm Password donot matches", Toast.LENGTH_SHORT).show();
                    return;
                }
                String urlString = "?name="+name+"&email="+email+"&password="+password+"&phoneNumber="+phoneNumber;
                // make an ajax call here
                SharedPreferences.Editor editor = sharedpreferences.edit();
                String userInfo = "{'userName' : 'Himanshu','email':'hraj3116@gmail.com'}";
                editor.putString("userInfo",userInfo);
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
