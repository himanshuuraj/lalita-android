package com.example.cliqr.lalita;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        LinearLayout mainLinearLayout = new LinearLayout(this);

        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(mainLinearLayout,linearLayoutParams);

        for(int i = 0;i<15;i++) {
            LinearLayout outerLinearLayout = new LinearLayout(this);
            LinearLayout leftLinearLayout = new LinearLayout(this);
            LinearLayout rightLinearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams outerLinearLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            outerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            outerLinearLayout.setBaselineAligned(false);
            outerLinearLayout.setMinimumHeight(45);
            outerLinearLayout.setBackgroundColor(Color.parseColor("#e4d0d0"));
            outerLinearLayout.setLayoutParams(outerLinearLayoutParams);

            leftLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams leftLinearLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            leftLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            leftLinearLayoutParams.weight = 1.0f;
            leftLinearLayout.setPadding(15,0,0,0);
            leftLinearLayout.setBackgroundColor(Color.BLUE);
            leftLinearLayout.setLayoutParams(leftLinearLayoutParams);

            leftLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams rightLinearLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            rightLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            rightLinearLayoutParams.weight = 1.0f;
            rightLinearLayout.setPadding(15,0,0,0);
            rightLinearLayout.setBackgroundColor(Color.YELLOW);
            rightLinearLayout.setLayoutParams(leftLinearLayoutParams);
            mainLinearLayout.addView(outerLinearLayout);
            outerLinearLayout.addView(leftLinearLayout);
            outerLinearLayout.addView(rightLinearLayout);
        }
    }
}
