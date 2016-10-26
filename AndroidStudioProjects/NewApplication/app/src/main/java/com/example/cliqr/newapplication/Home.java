package com.example.cliqr.newapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Home extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    JSONObject jsonObject; // brand in item
    JSONArray jsonBrandArrayInItem;
    JSONArray jsonArrayItems;  // item in product
    JSONArray jsonPriceArray; // price in product
    JSONArray productDataObjectArray; // whole product
    Integer indexOfproductDataToPass = null; // index of whole product
    Integer indexOfItemInProduct = null; // inde of item in product
    Integer indexOfbrandInItem = null; // index Of Product In Item
    Integer indexOfPriceInBrand= null; // index of price in brand
    TextView earlierPressedItem = null;

    JSONArray cartArray = new JSONArray();
    JSONObject cartData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String MyPREFERENCES = "MyPrefs" ;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        try {
            JSONObject jsonObject = new JSONObject(sharedpreferences.getString("productData", null));
            productDataObjectArray = new JSONArray(jsonObject.getString("products"));
            String cartArrayString = sharedpreferences.getString("cartArray", null);
            if(cartArrayString != null || cartArrayString!= "") {
                cartArray = new JSONArray(cartArrayString);
                Button cart = (Button) findViewById(R.id.cart);
                cart.setText("("+String.valueOf(cartArray.length())+")");
            }
        }catch(Exception e){}
        indexOfproductDataToPass = Integer.parseInt(getIntent().getExtras().getString("indexOfproductDataToPass"));
        try {
            jsonObject = productDataObjectArray.getJSONObject(indexOfproductDataToPass);
            jsonArrayItems = new JSONArray(jsonObject.getString("items"));
        }catch(Exception e){}

        for(indexOfItemInProduct= 0;indexOfItemInProduct<jsonArrayItems.length();indexOfItemInProduct++) {
            setItemInHorizontalScrollBar(indexOfItemInProduct);
        }
        TextView store = (TextView) findViewById(R.id.stores);
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        TextView doneShopping = (TextView) findViewById(R.id.doneShopping);
        doneShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Cart.class);
                startActivity(intent);
            }
        });

    }

    protected void setItemInScrollView(){
        int index = 1;
        String nameOfBrand = null;
        String imageOfBrand;
        try {
            nameOfBrand = jsonObject.getString("name");
            imageOfBrand = jsonObject.getString("image");
        }catch(Exception e){}
        LinearLayout lSV = (LinearLayout) findViewById(R.id.lSV);
        lSV.setPadding(0,getPixel(10),0,0);
        RelativeLayout rL = (RelativeLayout) new RelativeLayout(this);
        rL.setId(10*indexOfItemInProduct+index);
        index++;
        RelativeLayout left = new RelativeLayout(this);
        RelativeLayout right= new RelativeLayout(this);
        RelativeLayout middle= new RelativeLayout(this);
        LinearLayout top = new LinearLayout(this);
        TextView name = new TextView(this);
        LinearLayout middleHorizonal = new LinearLayout(this);
        HorizontalScrollView hSV = new HorizontalScrollView(this);
        LinearLayout lLHSV = new LinearLayout(this);
        ImageView imageButton = new ImageView(this);

        lSV.addView(rL,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,getPixel(100)));
        //rL.setBackgroundColor(Color.BLUE);
        rL.addView(left,new RelativeLayout.LayoutParams(getPixel(65),RelativeLayout.LayoutParams.MATCH_PARENT));
        rL.setPadding(0,0,0,getPixel(10));
        //left.setBackgroundColor(Color.RED);
        left.setId(10*indexOfItemInProduct+index);
        index++;
        RelativeLayout.LayoutParams rightP = new RelativeLayout.LayoutParams(getPixel(55),RelativeLayout.LayoutParams.MATCH_PARENT);
        rightP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rL.addView(right,rightP);
        ImageView imageViewOfProduct = new ImageView(this);
        left.addView(imageViewOfProduct, new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT
        ));
        imageViewOfProduct.setImageResource(R.drawable.lalita);
        //right.setBackgroundColor(Color.RED);
        right.setId(10*indexOfItemInProduct+index);
        index++;
        RelativeLayout.LayoutParams middleP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        middleP.addRule(RelativeLayout.LEFT_OF, right.getId());
        middleP.addRule(RelativeLayout.RIGHT_OF, left.getId());
        //middle.setBackgroundColor(Color.GREEN);
        middle.setPadding(getPixel(15),0,0,0);
        rL.addView(middle,middleP);
        middle.addView(top,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,getPixel(30)));
        //top.setBackgroundColor(Color.BLUE);
        name.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
        name.setText(nameOfBrand);// -------------------------------------------------------------------------------------------------------
        name.setGravity(View.TEXT_ALIGNMENT_CENTER);
        top.addView(name,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        top.setId(10*indexOfItemInProduct+index);
        index++;
        middleHorizonal.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams middleHorizontalP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,getPixel(50));
        middleHorizontalP.addRule(RelativeLayout.BELOW, top.getId());
        middleHorizontalP.addRule(RelativeLayout.ALIGN_PARENT_LEFT,rL.getId());
        middleHorizontalP.addRule(RelativeLayout.ALIGN_PARENT_START,rL.getId());
        middleHorizonal.setLayoutParams(middleHorizontalP);
        //middleHorizonal.setBackgroundColor(Color.RED);
        middle.addView(middleHorizonal);
        middleHorizonal.addView(hSV,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        lLHSV.setOrientation(LinearLayout.HORIZONTAL);
        lLHSV.setId(10*indexOfItemInProduct+index);
        index++;
        hSV.addView(lLHSV,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));

        RelativeLayout.LayoutParams imageButtonParam = new RelativeLayout.LayoutParams(
                    getPixel(40),getPixel(40)
                );
        imageButtonParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,rL.getId());
        imageButtonParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,rL.getId());
        rL.addView(imageButton,imageButtonParam);
        imageButton.setImageResource(R.drawable.plus);
        imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageButton.setAdjustViewBounds(true);
        imageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonshape));
        imageButton.setContentDescription("imagebutton");

        try {
            jsonPriceArray = jsonObject.getJSONArray("prices");
            for (indexOfPriceInBrand = 0; indexOfPriceInBrand < jsonPriceArray.length(); indexOfPriceInBrand++) {
                JSONObject priceObject = jsonPriceArray.getJSONObject(indexOfPriceInBrand);
                LinearLayout vert = new LinearLayout(this);
                vert.setOrientation(LinearLayout.VERTICAL);
                lLHSV.addView(vert, new RelativeLayout.LayoutParams(getPixel(65), RelativeLayout.LayoutParams.MATCH_PARENT));
                vert.setPadding(getPixel(2), 0, getPixel(3), 0);
                TextView tv1 = new TextView(this);
                vert.addView(tv1, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, getPixel(20)));
                tv1.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Small);
                tv1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                tv1.setText(priceObject.getString("amount"));
                tv1.setBackgroundColor(Color.parseColor("#38ec15"));
                TextView tv2 = new TextView(this);
                vert.addView(tv2, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                tv2.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Small);
                tv2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                tv2.setText(priceObject.getString("price"));
            }
        }catch(Exception e){}

        final String finalNameOfBrand = nameOfBrand;
        imageButton.setId(indexOfbrandInItem + 1000);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(Home.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle(finalNameOfBrand);
                dialog.show();
                Log.d("g","g");
                LinearLayout verticalLinearLayout = (LinearLayout) dialog.findViewById(R.id.verticalLayout);
                //verticalLinearLayout.setMinimumHeight(getPixel(50)*lengthOfPriceArray);
                final TextView totalTextView = (TextView) dialog.findViewById(R.id.total);
                try {
                    int id = view.getId() - 1000;
                    indexOfbrandInItem = id;
                    cartData = jsonBrandArrayInItem.getJSONObject(indexOfbrandInItem);
                    for(int k = 0;k< cartData.getJSONArray("prices").length();k++){
                        cartData.getJSONArray("prices").getJSONObject(k).put("quantity",0);
                        cartData.getJSONArray("prices").getJSONObject(k).put("totalAmount",0);
                    }
                    jsonPriceArray = jsonBrandArrayInItem.getJSONObject(indexOfbrandInItem).getJSONArray("prices");
                }catch(Exception e){}
                for(indexOfPriceInBrand = 0; indexOfPriceInBrand < jsonPriceArray.length(); indexOfPriceInBrand++) {
                    JSONObject priceObject = null;
                    try {
                        //put quantity
                        priceObject = jsonPriceArray.getJSONObject(indexOfPriceInBrand);
                        LinearLayout horizontalContainer = new LinearLayout(dialog.getContext());
                        horizontalContainer.setOrientation(LinearLayout.HORIZONTAL);
                        verticalLinearLayout.addView(horizontalContainer, new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, getPixel(50)
                        ));
                        LinearLayout leftContainer = new LinearLayout(dialog.getContext());
                        leftContainer.setOrientation(LinearLayout.HORIZONTAL);
                        horizontalContainer.addView(leftContainer, new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                        ));
                        LinearLayout middleContainer = new LinearLayout(dialog.getContext());
                        middleContainer.setOrientation(LinearLayout.HORIZONTAL);
                        horizontalContainer.addView(middleContainer, new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                        ));
                        final TextView totalAmountTextView = new TextView(dialog.getContext(), null, android.R.attr.textAppearanceMedium);
                        totalAmountTextView.setText("Rs 0");
                        totalAmountTextView.setGravity(Gravity.CENTER);
                        middleContainer.addView(totalAmountTextView, new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
                        ));
                        LinearLayout rightContainer = new LinearLayout(dialog.getContext());
                        rightContainer.setOrientation(LinearLayout.HORIZONTAL);
                        horizontalContainer.addView(rightContainer, new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f
                        ));
                        TextView amount = new TextView(dialog.getContext(), null, android.R.attr.textAppearanceMedium);
                        leftContainer.addView(amount, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                        amount.setText(priceObject.getString("amount"));
                        amount.setGravity(Gravity.CENTER);
                        ImageView add = new ImageView(dialog.getContext());
                        add.setImageResource(R.drawable.plus);
                        rightContainer.addView(add, new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT, getPixel(50), 1.0f
                        ));
                        final TextView qtty = new TextView(dialog.getContext());
                        rightContainer.addView(qtty, new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT, getPixel(50), 1.0f
                        ));
                        qtty.setText(String.valueOf(0));
                        qtty.setTextSize(20);
                        qtty.setGravity(Gravity.CENTER);
                        ImageView minus = new ImageView(dialog.getContext());
                        minus.setImageResource(R.drawable.minus);
                        rightContainer.addView(minus, new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT, getPixel(50), 1.0f
                        ));
                        minus.setId(indexOfPriceInBrand+100);
                        totalAmountTextView.setId(indexOfPriceInBrand+30);
                        minus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //dialog.dismiss();
                                try {
                                    indexOfPriceInBrand = v.getId()-100;
                                    Integer quantity = cartData.getJSONArray("prices").getJSONObject(indexOfPriceInBrand).getInt("quantity");
                                    quantity--;
                                    if(quantity<0)
                                        return;
                                    qtty.setText(String.valueOf(quantity));
                                    String priceInString = cartData.getJSONArray("prices").getJSONObject(indexOfPriceInBrand).getString("price");
                                    Integer price = Integer.parseInt(priceInString);
                                    cartData.getJSONArray("prices").getJSONObject(indexOfPriceInBrand).put("totalAmount",quantity*price);
                                    cartData.getJSONArray("prices").getJSONObject(indexOfPriceInBrand).put("quantity",quantity);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    totalAmountTextView.setText("Rs "+String.valueOf(quantity*price));
                                    editor.putString("cartData", cartData.toString());
                                    int total= 0;
                                    for(int index = 0;index<cartData.getJSONArray("prices").length();index++){
                                        total += cartData.getJSONArray("prices").getJSONObject(index).getInt("totalAmount");
                                    }
                                    totalTextView.setText("Rs "+String.valueOf(total));
                                    editor.commit();
                                }catch(Exception e){}
                            }
                        });
                        add.setId(indexOfPriceInBrand+50);
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //dialog.dismiss();
                                indexOfPriceInBrand = v.getId()-50;
                                try {
                                    Integer quantity = cartData.getJSONArray("prices").getJSONObject(indexOfPriceInBrand).getInt("quantity");
                                    quantity++;
                                    qtty.setText(String.valueOf(quantity));
                                    cartData.getJSONArray("prices").getJSONObject(indexOfPriceInBrand).put("quantity",quantity);
                                    String priceInString = cartData.getJSONArray("prices").getJSONObject(indexOfPriceInBrand).getString("price");
                                    Integer price = Integer.parseInt(priceInString);
                                    totalAmountTextView.setText("Rs "+String.valueOf(quantity*price));
                                    cartData.getJSONArray("prices").getJSONObject(indexOfPriceInBrand).put("totalAmount",quantity*price);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("cartData", cartData.toString());
                                    int total= 0;
                                    for(int index = 0;index<cartData.getJSONArray("prices").length();index++){
                                        total += cartData.getJSONArray("prices").getJSONObject(index).getInt("totalAmount");
                                    }
                                    totalTextView.setText("Rs "+String.valueOf(total));
                                    editor.commit();
                                }catch(Exception e){}
                            }
                        });
                        TextView priceTextView = new TextView(dialog.getContext());
                        rightContainer.addView(priceTextView, new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT, getPixel(50), 1.0f
                        ));
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cartData = null;
                        dialog.dismiss();
                    }
                });
                Log.d("h","h");
                Button Add = (Button) dialog.findViewById(R.id.add);
                Add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String MyPREFERENCES = "MyPrefs" ;
                            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            //cartData = new JSONObject(sharedpreferences.getString("cartData", null));
                            String cArray = sharedpreferences.getString("cartArray", null);
                            if(cArray == null)
                                cartArray = new JSONArray();
                            else
                                cartArray = new JSONArray(cArray);
                            if(cartArray == null)
                                cartArray = new JSONArray();
                            cartArray = removeCartdataInCartArray(cartArray,cartData);
                            cartArray.put(cartData);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("cartArray", cartArray.toString());
                            editor.commit();
                            Toast.makeText(getApplicationContext(), "Item Added to cart", Toast.LENGTH_SHORT).show();
                            Button cart = (Button) findViewById(R.id.cart);
                            cart.setText("("+String.valueOf(cartArray.length())+")");
                        }catch(Exception e){}
                        dialog.dismiss();
                    }
                });
                Log.d("i","i");

            }

        });
    }

    protected JSONArray removeCartdataInCartArray(JSONArray cartArray, JSONObject cartData){
        try {
            for (int index = 0; index < cartArray.length(); index++){
                if(cartArray.getJSONObject(index).getString("name").equals(cartData.getString("name")) == true){
                    return RemoveJSONArray(cartArray,index);
                }
            }
        }catch(Exception e){}
        return cartArray;
    }

    protected void setItemInHorizontalScrollBar(final Integer index){
        String nameOfItem = "";
        try {
            nameOfItem = jsonArrayItems.getJSONObject(index).getString("name");
        }catch(Exception e){}
        LinearLayout ll = (LinearLayout) findViewById(R.id.hSVLL);
        TextView tv = new TextView(this);
        ll.addView(tv,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        tv.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium );
        tv.setText(nameOfItem);
        tv.setPadding(getPixel(10),0,0,0);
        tv.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        if(index == 0){
            earlierPressedItem = tv;
            indexOfItemInProduct = index;
            try {
                jsonObject = jsonArrayItems.getJSONObject(index);
                jsonBrandArrayInItem = jsonObject.getJSONArray("brands");
                LinearLayout lSV = (LinearLayout) findViewById(R.id.lSV);
                lSV.removeAllViews();
                for (indexOfbrandInItem = 0; indexOfbrandInItem < jsonBrandArrayInItem.length(); indexOfbrandInItem++) {
                    jsonObject = jsonBrandArrayInItem.getJSONObject(indexOfbrandInItem);
                    setItemInScrollView();
                }
            }catch(Exception e){}
        }
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexOfItemInProduct = index;
                try {
                    jsonObject = jsonArrayItems.getJSONObject(index);
                    jsonBrandArrayInItem = jsonObject.getJSONArray("brands");
                    LinearLayout lSV = (LinearLayout) findViewById(R.id.lSV);
                    lSV.removeAllViews();
                    for (indexOfbrandInItem = 0; indexOfbrandInItem < jsonBrandArrayInItem.length(); indexOfbrandInItem++) {
                        jsonObject = jsonBrandArrayInItem.getJSONObject(indexOfbrandInItem);
                        setItemInScrollView();
                    }
                }catch(Exception e){}
            }
        });
    }

    protected int getPixel(int dp){
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int)px;
    }

    protected void setColorInHorizontal(){
        for(int index = 0;index < jsonArrayItems.length();index++) {

        }
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
