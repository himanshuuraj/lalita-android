package com.example.cliqr.newapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ShowOrder extends AppCompatActivity {

    JSONArray orderJSONArray = new JSONArray();
    Integer indexOfOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);
        String orderArray = "[{'orderId' : '','userId' : '','address' :{'email' : 'String','fullName': 'String','mobileNumber': 'String','pinCode': 'String','city': 'String','state': 'String','firstLine': 'String','secondLine': 'String','addressType': 'String'},'date' : '','product' : {'name':'classic lalitha','shortDescription':'classic variety of basmati','image':'/src/images/rice.png','prices':[{'amount':'3 kg','price':'500','totalAmount':1000,'quantity':4},{'amount':'4 kg','price':'500','totalAmount':1000,'quantity':4}]},'vendorId' : {'name' : 'String','mobileNumber' : 'String','password' : 'String','dateOfBirth' : 'String','email' : 'String','gender' : 'String','address' :{'email' : 'String','fullName': 'String','mobileNumber': 'String','pinCode': 'String','city': 'String','state': 'String','firstLine': 'String','secondLine': 'String','addressType': 'String'}},'status' : ''}]";
        if(orderArray != null || orderArray != "") {
            try {
                orderJSONArray = new JSONArray(orderArray);
            }catch(Exception e){}
        }
        for(int index = 0;index < 2;index++)
            showOrder(index);

    }

    protected void showOrder(int index){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int sum = 0;
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout relativeLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.order, linearLayout, false);
        linearLayout.addView(relativeLayout);
        LinearLayout allItemPrice = (LinearLayout) relativeLayout.findViewById(R.id.allItemPrice);
        try{
            jsonArray = orderJSONArray.getJSONObject(index).getJSONObject("product").getJSONArray("prices");
            for(int i = 0;i<jsonArray.length();i++) {
                jsonObject = jsonArray.getJSONObject(i);
                LinearLayout priceInfoInOrder = (LinearLayout) getLayoutInflater().inflate(R.layout.priceinfoinorder, linearLayout, false);
                allItemPrice.addView(priceInfoInOrder);
                TextView amount = (TextView) priceInfoInOrder.findViewById(R.id.amount);
                amount.setText(jsonObject.getString("amount"));
                TextView quantity = (TextView) priceInfoInOrder.findViewById(R.id.quantity);
                quantity.setText(String.valueOf(jsonObject.getInt("quantity")));
                TextView totalAmount = (TextView) priceInfoInOrder.findViewById(R.id.totalAmount);
                totalAmount.setText(String.valueOf("Rs. "+ String.valueOf(jsonObject.getInt("totalAmount"))));
                sum += jsonObject.getInt("totalAmount");
            }
            TextView total = (TextView) relativeLayout.findViewById(R.id.total);
            total.setText("Rs. "+String.valueOf(sum));
            TextView address = (TextView) relativeLayout.findViewById(R.id.address);
            JSONObject addressJSONObject = orderJSONArray.getJSONObject(index).getJSONObject("address");
            address.setText(addressJSONObject.getString("firstLine")+"\n"+addressJSONObject.getString("city")+addressJSONObject.getString("pinCode"));
        }catch(Exception e){}
    }

}
