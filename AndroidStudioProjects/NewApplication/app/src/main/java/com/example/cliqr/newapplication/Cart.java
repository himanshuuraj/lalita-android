package com.example.cliqr.newapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Cart extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    JSONArray jsonCartArray = null;
    Integer indexOfJSONCartArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String cartArray = sharedpreferences.getString("cartArray", null);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        try {
            jsonCartArray = new JSONArray(cartArray);
        }catch(Exception e){}
        if(jsonCartArray == null || jsonCartArray.length() == 0){
            TextView textView = new TextView(this);
            textView.setText("Your Cart is Empty");
            linearLayout.addView(textView,new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,getPixel(60)
            ));
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.parseColor("#1cb12d"));
        }else {
            try {
                for (indexOfJSONCartArray = 0; indexOfJSONCartArray < jsonCartArray.length(); indexOfJSONCartArray++)
                    add(jsonCartArray.getJSONObject(indexOfJSONCartArray));
            } catch (Exception e) {
            }
        }
        TextView proceedCheckOut = (TextView) findViewById(R.id.proceedCheckOut);
        proceedCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartArray.length() > 0)
                {
                    Intent intent = new Intent(getBaseContext(),ShowAddress.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Your cart is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void add(JSONObject jsonObject){
        try {
            // add = 1,  minus = 2, quantity = 3, totalAmount = 4, // these are universal close = 5, Total = 6
            final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
            RelativeLayout relativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.cart, linearLayout, false);
            TextView nameOfProduct = (TextView) relativeLayout.findViewById(R.id.nameOfProduct);
            nameOfProduct.setText(jsonObject.getString("name"));
            linearLayout.addView(relativeLayout);
            Button close = (Button) relativeLayout.findViewById(R.id.close);
            close.setId(500+indexOfJSONCartArray);
            TextView total = (TextView) relativeLayout.findViewById(R.id.total);
            total.setId(600+indexOfJSONCartArray);
            close.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     int id = view.getId() - 500;
                     jsonCartArray = RemoveJSONArray(jsonCartArray,id);
                     linearLayout.removeAllViews();
                     try{
                         for(indexOfJSONCartArray = 0;indexOfJSONCartArray < jsonCartArray.length(); indexOfJSONCartArray++)
                             add(jsonCartArray.getJSONObject(indexOfJSONCartArray));
                     }catch(Exception e){}
                     SharedPreferences.Editor editor = sharedpreferences.edit();
                     editor.putString("cartArray", jsonCartArray.toString());
                     editor.commit();
                     if(jsonCartArray.length() == 0){
                         TextView textView = new TextView(getApplicationContext(),null,android.R.attr.textAppearanceLarge);
                         textView.setText("Your Cart is Empty");
                         linearLayout.addView(textView,new LinearLayout.LayoutParams(
                                 LinearLayout.LayoutParams.FILL_PARENT,getPixel(60)
                         ));
                         textView.setGravity(Gravity.CENTER);
                         textView.setBackgroundColor(Color.parseColor("#1cb12d"));
                     }
                 }
             });
            JSONArray jsonpriceArray = jsonObject.getJSONArray("prices");
            for (int index = 0; index < jsonpriceArray.length(); index++) {
                ImageButton add;
                ImageButton minus;
                TextView quantityTextView, totalAmountTextView, amountTextView;
                LinearLayout linearLayout1 = (LinearLayout) relativeLayout.findViewById(R.id.priceInfo);
                LinearLayout linearLayout2 = (LinearLayout) getLayoutInflater().inflate(R.layout.priceinfoincart, linearLayout1, false);
                linearLayout1.addView(linearLayout2);
                add = (ImageButton) linearLayout2.findViewById(R.id.add);
                minus = (ImageButton) linearLayout2.findViewById(R.id.minus);
                quantityTextView = (TextView) linearLayout2.findViewById(R.id.quantity);
                totalAmountTextView = (TextView) linearLayout2.findViewById(R.id.totalAmount);
                amountTextView = (TextView) linearLayout2.findViewById(R.id.amount);  // eg 1 kg - this is fixed
                //String t = "t";
                amountTextView.setText(jsonObject.getJSONArray("prices").getJSONObject(index).getString("amount"));
                quantityTextView.setText(jsonObject.getJSONArray("prices").getJSONObject(index).getString("quantity"));
                Integer totalAmount = jsonObject.getJSONArray("prices").getJSONObject(index).getInt("quantity") * jsonObject.getJSONArray("prices").getJSONObject(index).getInt("price");
                totalAmountTextView.setText("Rs "+totalAmount.toString());
                add.setId(100*indexOfJSONCartArray+10*index+1);
                minus.setId(100*indexOfJSONCartArray+10*index+2);
                quantityTextView.setId(100*indexOfJSONCartArray+10*index+3);
                totalAmountTextView.setId(100*indexOfJSONCartArray+10*index+4);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            TextView quantityTextView,totalAmountTextView;
                            //Toast.makeText(getApplicationContext(), String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();
                            int id = view.getId();   // quantity(Int), price,  totalAmount(Int),  amount
                            int cartArrayIndex = id / 100;
                            int priceArrayIndex = (id % 100) / 10;
                            int k = id % 10;
                            quantityTextView = (TextView) linearLayout.findViewById(100*cartArrayIndex+10*priceArrayIndex+3);
                            totalAmountTextView = (TextView) linearLayout.findViewById(100*cartArrayIndex+10*priceArrayIndex+4);
                            String priceInString = jsonCartArray.getJSONObject(cartArrayIndex).getJSONArray("prices").getJSONObject(priceArrayIndex).getString("price");
                            Integer price = Integer.parseInt(priceInString);//.substring(3));
                            Integer quantity = jsonCartArray.getJSONObject(cartArrayIndex).getJSONArray("prices").getJSONObject(priceArrayIndex).getInt("quantity");
                            quantity++;
                            quantityTextView.setText(String.valueOf(quantity));
                            totalAmountTextView.setText("Rs "+String.valueOf(quantity*price));
                            jsonCartArray.getJSONObject(cartArrayIndex).getJSONArray("prices").getJSONObject(priceArrayIndex).put("quantity",quantity);
                            jsonCartArray.getJSONObject(cartArrayIndex).getJSONArray("prices").getJSONObject(priceArrayIndex).put("totalAmount",quantity*price);
                            changeTotalPrice(cartArrayIndex);
                        }catch(Exception e){}
                    }
                });
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            TextView quantityTextView,totalAmountTextView;
                            //Toast.makeText(getApplicationContext(), String.valueOf(view.getId()), Toast.LENGTH_SHORT).show();
                            int id = view.getId();   // quantity(Int), price,  totalAmount(Int),  amount
                            int cartArrayIndex = id / 100;
                            int priceArrayIndex = (id % 100) / 10;
                            int k = id % 10;
                            Integer quantity = jsonCartArray.getJSONObject(cartArrayIndex).getJSONArray("prices").getJSONObject(priceArrayIndex).getInt("quantity");
                            quantity--;
                            if(quantity<0)
                                return;
                            quantityTextView = (TextView) linearLayout.findViewById(100*cartArrayIndex+10*priceArrayIndex+3);
                            totalAmountTextView = (TextView) linearLayout.findViewById(100*cartArrayIndex+10*priceArrayIndex+4);
                            String priceInString = jsonCartArray.getJSONObject(cartArrayIndex).getJSONArray("prices").getJSONObject(priceArrayIndex).getString("price");
                            Integer price = Integer.parseInt(priceInString);//.substring(3));
                            int totalAmount = quantity*price;
                            quantityTextView.setText(String.valueOf(quantity));
                            totalAmountTextView.setText("Rs "+String.valueOf(quantity*price));
                            jsonCartArray.getJSONObject(cartArrayIndex).getJSONArray("prices").getJSONObject(priceArrayIndex).put("quantity",quantity);
                            jsonCartArray.getJSONObject(cartArrayIndex).getJSONArray("prices").getJSONObject(priceArrayIndex).put("totalAmount",totalAmount);
                            changeTotalPrice(cartArrayIndex);
                        }catch(Exception e){}
                    }
                });
            }
        }catch(Exception e){}
        changeTotalPrice(indexOfJSONCartArray);
    }

    protected void changeTotalPrice(int indexInCartArray){
        try{
            int totalAmount = 0;
            JSONObject cartData = jsonCartArray.getJSONObject(indexInCartArray);
            JSONArray priceArray = cartData.getJSONArray("prices");
            for(int index = 0;index < priceArray.length(); index++){
                JSONObject priceObject = priceArray.getJSONObject(index);
                int price = Integer.parseInt(priceObject.getString("price"));
                int quantity = priceObject.getInt("quantity");
                priceObject.put("totalAmount",price*quantity);
                totalAmount += price*quantity;
            }
            TextView total = (TextView) findViewById(600+indexInCartArray);
            total.setText("Rs " + String.valueOf(totalAmount));
        }catch(Exception e){}
    }

    protected int getPixel(int dp){
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int)px;
    }

    public static JSONArray RemoveJSONArray( JSONArray jarray,int pos) {

        JSONArray Njarray=new JSONArray();
        try{
            for(int i=0;i<jarray.length();i++){
                if(i!=pos)
                    Njarray.put(jarray.get(i));
            }
        }catch (Exception e){e.printStackTrace();}
        return Njarray;
    }
}
