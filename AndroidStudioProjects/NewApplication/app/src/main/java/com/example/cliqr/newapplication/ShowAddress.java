package com.example.cliqr.newapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class ShowAddress extends AppCompatActivity {

    JSONObject jsonObject;
    SharedPreferences sharedpreferences;
    JSONArray jsonArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_address);
        String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String addresses = sharedpreferences.getString("pinCode", null);
        addresses= "[{'name':'Himanshu Raj','firstLine':'firstLine of address','secondLine':'secondLine Of address','phoneNumber':'7022623975','email':'hraj3116@gmail.com','pincode':'560075','city':'Benguluru','state':'Karnataka'},{'name':'Himanshu Raj','firstLine':'firstLine of address','secondLine':'secondLine Of address','phoneNumber':'7022623975','email':'hraj3116@gmail.com','pincode':'560075','city':'Benguluru','state':'Karnataka'},{'name':'Himanshu Raj','firstLine':'firstLine of address','secondLine':'secondLine Of address','phoneNumber':'7022623975','email':'hraj3116@gmail.com','pincode':'560075','city':'Benguluru','state':'Karnataka'}]";
        try {
            jsonArray = new JSONArray(addresses);
        }catch(Exception e){}
        for(int i = 0; i< jsonArray.length();i++){
            try {
                showAddress(i);
            }catch(Exception e){}
        }
    }

    protected void showAddress(int index){

        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(index);
        }catch(Exception e){}
        LinearLayout rl = (LinearLayout) findViewById(R.id.relativeLayout);
        RelativeLayout addressLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams rlParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,340//RelativeLayout.LayoutParams.MATCH_PARENT
        );
        //rlParam.setMargins(100,0,0,400);
        addressLayout.setId(10*index+1);
        rl.addView(addressLayout,rlParam);

        LinearLayout vl = new LinearLayout(this);
        vl.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams vlLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
        addressLayout.addView(vl,vlLayout);
        vlLayout.setMargins(0,0,0,40);
        //vl.setBackgroundColor(Color.GREEN);

        Button edit = new Button(this, null, android.R.attr.buttonStyleSmall);
        RelativeLayout.LayoutParams editParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        editParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        editParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addressLayout.addView(edit, editParam);
        edit.setText("EDIT");
        edit.setId(10*index+2);

        Button deleteButton = new Button(this, null, android.R.attr.buttonStyleSmall);
        RelativeLayout.LayoutParams deleteParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        deleteParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        deleteParam.addRule(RelativeLayout.LEFT_OF, 10*index+2);
        addressLayout.addView(deleteButton, deleteParam);
        deleteButton.setText("X");
        deleteButton.setId(10*index+3);

        TextView tv;
        try {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = jsonObject.get(key).toString();
                if(value!="" || value!=null){
                    tv = new TextView(this, null, android.R.attr.textAppearanceMedium);
                    RelativeLayout.LayoutParams tvParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                    tvParam.setMargins(0,0,0,10);
                    vl.addView(tv, tvParam);
                    tv.setText(value);
                    tv.setBackgroundColor(Color.GREEN);
                }
            }
        }catch(Exception e){}






    }
}
