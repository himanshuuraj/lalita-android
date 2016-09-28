package com.example.cliqr.newapplication;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        int k = 0;
        for(k= 1;k<100;k++) {
            //setItemInScrollView(k);
        }
        for(k= 1;k<100;k++) {
            setItemInHorizontalScrollBar();
        }
        ImageButton buttonClick = (ImageButton) findViewById(R.id.buttonAdd);
        buttonClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Create custom dialog object
                final Dialog dialog = new Dialog(Home.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.dialog);
                // Set dialog title
                dialog.setTitle("Custom Dialog");

                // set values for custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Custom dialog Android example.");
                //ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                //image.setImageResource(R.drawable.buttonshape);

                dialog.show();

                /*Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });*/


            }

        });
    }

    protected void setItemInScrollView(Integer i){
        int j = 1;
        LinearLayout lSV = (LinearLayout) findViewById(R.id.lSV);
        lSV.setPadding(0,getPixel(10),0,0);
        RelativeLayout rL = (RelativeLayout) new RelativeLayout(this);
        rL.setId(i*j++);
        RelativeLayout left = new RelativeLayout(this);
        RelativeLayout right= new RelativeLayout(this);
        RelativeLayout middle= new RelativeLayout(this);
        LinearLayout top = new LinearLayout(this);
        TextView name = new TextView(this);
        LinearLayout middleHorizonal = new LinearLayout(this);
        HorizontalScrollView hSV = new HorizontalScrollView(this);
        LinearLayout lLHSV = new LinearLayout(this);
        ImageButton imageButton = new ImageButton(this);

        lSV.addView(rL,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,getPixel(100)));
        //rL.setBackgroundColor(Color.BLUE);
        rL.addView(left,new RelativeLayout.LayoutParams(getPixel(65),RelativeLayout.LayoutParams.MATCH_PARENT));
        rL.setPadding(0,0,0,getPixel(10));
        //left.setBackgroundColor(Color.RED);
        left.setId(i*j++);
        RelativeLayout.LayoutParams rightP = new RelativeLayout.LayoutParams(getPixel(55),RelativeLayout.LayoutParams.MATCH_PARENT);
        rightP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rL.addView(right,rightP);
        //right.setBackgroundColor(Color.RED);
        right.setId(i*j++);
        RelativeLayout.LayoutParams middleP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        middleP.addRule(RelativeLayout.LEFT_OF, right.getId());
        middleP.addRule(RelativeLayout.RIGHT_OF, left.getId());
        //middle.setBackgroundColor(Color.GREEN);
        middle.setPadding(getPixel(15),0,0,0);
        rL.addView(middle,middleP);
        middle.addView(top,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,getPixel(30)));
        //top.setBackgroundColor(Color.BLUE);
        name.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
        name.setText("Name");
        name.setGravity(View.TEXT_ALIGNMENT_CENTER);
        top.addView(name,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        top.setId(i*j++);
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
        lLHSV.setId(i*j++);
        hSV.addView(lLHSV,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));

        RelativeLayout.LayoutParams imageButtonParam = new RelativeLayout.LayoutParams(
                    getPixel(40),getPixel(40)
                );
        imageButtonParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,rL.getId());
        imageButtonParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,rL.getId());
        rL.addView(imageButton,imageButtonParam);
        imageButton.setImageResource(R.drawable.roundbutton);
        imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageButton.setAdjustViewBounds(true);
        imageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonshape));
        imageButton.setContentDescription("imagebutton");


        for(int k =0;k<4;k++) {
            LinearLayout vert = new LinearLayout(this);
            vert.setOrientation(LinearLayout.VERTICAL);
            lLHSV.addView(vert, new RelativeLayout.LayoutParams(getPixel(65), RelativeLayout.LayoutParams.MATCH_PARENT));
            vert.setPadding(getPixel(2), 0, getPixel(3), 0);
            TextView tv1 = new TextView(this);
            vert.addView(tv1, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, getPixel(20)));
            tv1.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Small);
            tv1.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            tv1.setText("25Kgs");
            tv1.setBackgroundColor(Color.parseColor("#38ec15"));
            TextView tv2 = new TextView(this);
            vert.addView(tv2, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            tv2.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Small);
            tv2.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            tv2.setText("1000");
        }
    }

    protected void setItemInHorizontalScrollBar(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.hSVLL);
        TextView tv = new TextView(this);
        ll.addView(tv,new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        tv.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium );
        tv.setText("Medium Text");
        tv.setPadding(getPixel(10),0,0,0);
        tv.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
    }

    protected int getPixel(int dp){
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int)px;
    }

}
