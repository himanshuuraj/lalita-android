package com.example.cliqr.lalita;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstscreen);
        Thread mythread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i=new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        };
        mythread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}