package com.example.cliqr.newapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class ShowAddress extends AppCompatActivity {

    JSONObject jsonObject;
    SharedPreferences sharedpreferences;
    JSONArray jsonArray = null;
    String addresses = "";
    int prevoiusCheckBoxIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_address);
        String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        addresses = sharedpreferences.getString("addresses", null);
        addresses= "[{'name':'Himanshu Raj','addressType':'Home','firstLine':'firstLine of address','secondLine':'secondLine Of address','phoneNumber':'7022623975','email':'hraj3116@gmail.com','pincode':'560075','city':'Benguluru','state':'Karnataka'},{'name':'Himanshu Raj','addressType':'Home','firstLine':'firstLine of address','secondLine':'secondLine Of address','phoneNumber':'7022623975','email':'hraj3116@gmail.com','pincode':'560075','city':'Benguluru','state':'Karnataka'},{'name':'Himanshu Raj','addressType':'Home','firstLine':'firstLine of address','secondLine':'secondLine Of address','phoneNumber':'7022623975','email':'hraj3116@gmail.com','pincode':'560075','city':'Benguluru','state':'Karnataka'}]";
        displayAddress(addresses);
    }

    protected void displayAddress(String addresses){
        LinearLayout rl = (LinearLayout) findViewById(R.id.relativeLayout);
        rl.removeAllViews();
        if(addresses == null || addresses==""){
            TextView textView = new TextView(getApplicationContext(),null,android.R.attr.textAppearanceLarge);
            textView.setText("There is no address");
            rl.addView(textView,new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,getPixel(60)
            ));
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.parseColor("#1cb12d"));


        }else {
            try {
                jsonArray = new JSONArray(addresses);
            } catch (Exception e) {
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    showAddress(i);
                } catch (Exception e) {
                }
            }
        }
        Button addAAddress = new Button(this);
        addAAddress.setText("Add a address");
        rl.addView(addAAddress,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,getPixel(40)
        ));
        addAAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),EnterAddress.class);
                startActivity(intent);
            }
        });
    }

    protected void showAddress(final Integer index){

        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(index);
        }catch(Exception e){}
        LinearLayout rl = (LinearLayout) findViewById(R.id.relativeLayout);
        RelativeLayout addressLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams rlParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,300//RelativeLayout.LayoutParams.MATCH_PARENT
        );
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
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),EnterAddress.class);
                intent.putExtra("indexOfAddress", index.toString());
                intent.putExtra("addresses", addresses);
                startActivity(intent);
            }
        });

        Button deleteButton = new Button(this, null, android.R.attr.buttonStyleSmall);
        RelativeLayout.LayoutParams deleteParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        deleteParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        deleteParam.addRule(RelativeLayout.LEFT_OF, 10*index+2);
        addressLayout.addView(deleteButton, deleteParam);
        deleteButton.setText("X");
        deleteButton.setId(10*index+3);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make an ajax call to delete
                //update addresses
                addresses = null;// "[{'name':'Himanshu Raj','addressType':'Home','firstLine':'firstLine of address','secondLine':'secondLine Of address','phoneNumber':'7022623975','email':'hraj3116@gmail.com','pincode':'560075','city':'Benguluru','state':'Karnataka'}]";
                try {
                    jsonArray = new JSONArray(addresses);
                }catch(Exception e){}
                for(int i = 0; i< jsonArray.length();i++){
                    try {
                        displayAddress(addresses);
                    }catch(Exception e){}
                }
            }
        });

        CheckBox checkBox = new CheckBox(this);
        RelativeLayout.LayoutParams checkBoxParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        checkBoxParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        checkBoxParam.addRule(RelativeLayout.LEFT_OF, 10*index+3);
        addressLayout.addView(checkBox, checkBoxParam);
        checkBox.setId(10*index+4);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                 @Override
                 public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                Toast.makeText(getApplicationContext(), String.valueOf(buttonView.getId()), Toast.LENGTH_SHORT).show();
                 }
            }
        );
        TextView tv;
        try {
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = jsonObject.get(key).toString();
                if(value!="" || value!=null){
                    if(key.equals("addressType") == false)
                        tv = new TextView(this, null, android.R.attr.textAppearanceSmall);
                    else
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

    protected int getPixel(int dp){
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int)px;
    }

}
