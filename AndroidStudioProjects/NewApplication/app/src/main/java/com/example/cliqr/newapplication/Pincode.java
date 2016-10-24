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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //make an ajax call here
                Toast.makeText(getApplicationContext(), pinCode+" "+city+" "+state, Toast.LENGTH_SHORT).show();
                editor.putString("pinCode", pinCode);
                editor.putString("city", city);
                editor.putString("state", state);
                String productData = "{'products':[{'name':'rice','image':'/src/images/rice.png','descrption':'this is rice','items':[{'name':'basmati rice','description':'Basmati is a variety of long, slender-grained aromatic rice which is traditionally from the Indian subcontinent.','brands':[{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]}]},{'name':'BPT steam rice','description':'rice made using steam','brands':[{'name':'lalitha green super fine','shortDescription':'this is steam rice.','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'10KGS LALITHA GREEN SUPERFINE','shortDescription':'this is steam rice available only in 10kg bags','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]}]},{'name':'basmati rice','description':'Basmati is a variety of long, slender-grained aromatic rice which is traditionally from the Indian subcontinent.','brands':[{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]}]},{'name':'BPT steam rice','description':'rice made using steam','brands':[{'name':'lalitha green super fine','shortDescription':'this is steam rice.','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'10KGS LALITHA GREEN SUPERFINE','shortDescription':'this is steam rice available only in 10kg bags','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]}]},{'name':'basmati rice','description':'Basmati is a variety of long, slender-grained aromatic rice which is traditionally from the Indian subcontinent.','brands':[{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'},{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'5kg pouch lalitha classic super','shortDescription':'Super fine variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]}]},{'name':'BPT steam rice','description':'rice made using steam','brands':[{'name':'lalitha green super fine','shortDescription':'this is steam rice.','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'10KGS LALITHA GREEN SUPERFINE','shortDescription':'this is steam rice available only in 10kg bags','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]}]}]},{'name':'ravva','image':'/src/images/rice.png','descrption':'this is rice','items':[{'name':'Idly ravva','description':'this rava in combination with urad dal can be used to make soft idlys','brands':[{'name':'LALITHA GREEN','shortDescription':'basic variety of ravva','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]},{'name':'NETAJI PINK','shortDescription':'lorem ipsum','image':'/src/images/rice.png','prices':[{'amount':'1 k','price':'123'},{'amount':'2 kg','price':'500'}]}]}]}]}";
                editor.putString("productData", productData);
                editor.putString("cartArray", null);
                editor.commit();
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                Log.v("pinCodeEditText", pinCodeEditText.getText().toString());
                Log.v("cityEditText", cityEditText.getText().toString());
                Log.v("stateEditText", stateEditText.getText().toString());
            }
        });
    }
}
