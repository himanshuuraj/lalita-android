package com.example.cliqr.lalita;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class Product extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        LinearLayout linearLayoutInHorizontalScrollView = (LinearLayout) findViewById(R.id.linearLayoutIdHorizontalScrollView);
        insertButtonInHorizontalScrollView(linearLayoutInHorizontalScrollView);
        insertElementInScrollView();
    }

    protected void insertElementInScrollView(){
        LinearLayout linearLayoutInScrollView = (LinearLayout) findViewById(R.id.linearLayoutInScrollView);
        RelativeLayout repeatativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams repeatativeLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout outerLayout = new LinearLayout(this);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        int height = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 130, displaymetrics );
        height = 180;
        LinearLayout.LayoutParams outerLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                height
        );
        outerLayout.setBackgroundColor(Color.YELLOW);
        LinearLayout leftLayout = new LinearLayout(this);
        LinearLayout.LayoutParams leftLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                2.0f
        );
        leftLayout.setOrientation(LinearLayout.HORIZONTAL);
        leftLayout.setLayoutParams(leftLayoutParams);
        LinearLayout rightLayout = new LinearLayout(this);
        LinearLayout.LayoutParams rightLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );
        LinearLayout rightVerticalLayout = new LinearLayout(this);
        LinearLayout.LayoutParams rightVerticalLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        LinearLayout topLayout = new LinearLayout(this);
        LinearLayout.LayoutParams topLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                40
        );
        LinearLayout middleLayout = new LinearLayout(this);
        LinearLayout.LayoutParams middleLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                50
        );
        LinearLayout lowerLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lowerLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT
        );
        TextView nameOfTheProduct = new TextView(this);
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );
        LinearLayout horizontalMiddleLayout = new LinearLayout(this);
        LinearLayout.LayoutParams horizontalMiddleLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT
        );
        horizontalMiddleLayout.setLayoutParams(horizontalMiddleLayoutParams);
        Button button1 = new Button(this);
        Button button2 = new Button(this);
        Button button3 = new Button(this);
        LinearLayout.LayoutParams buttonParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT,
                1.0f
        );
        button1.setLayoutParams(buttonParam);
        button2.setLayoutParams(buttonParam);
        button3.setLayoutParams(buttonParam);
        nameOfTheProduct.setLayoutParams(textViewParams);
        nameOfTheProduct.setText("nameOfProduct");
        topLayout.setLayoutParams(topLayoutParams);
        topLayout.setOrientation(LinearLayout.HORIZONTAL);
        topLayout.setBackgroundColor(Color.GREEN);
        middleLayout.setOrientation(LinearLayout.HORIZONTAL);
        middleLayout.setLayoutParams(middleLayoutParams);
        lowerLayout.setOrientation(LinearLayout.HORIZONTAL);
        lowerLayout.setLayoutParams(lowerLayoutParams);
        rightVerticalLayout.setOrientation(LinearLayout.VERTICAL);
        rightVerticalLayout.setLayoutParams(rightVerticalLayoutParams);
        rightLayout.setOrientation(LinearLayout.HORIZONTAL);
        rightLayout.setBaselineAligned(false);
        rightLayout.setLayoutParams(rightLayoutParams);
        outerLayout.setOrientation(LinearLayout.HORIZONTAL);
        outerLayout.setLayoutParams(outerLayoutParams);
        repeatativeLayout.setLayoutParams(repeatativeLayoutParams);
        linearLayoutInScrollView.addView(repeatativeLayout);
        repeatativeLayout.addView(outerLayout);
        outerLayout.addView(leftLayout);
        outerLayout.addView(rightLayout);
        rightLayout.addView(rightVerticalLayout);
        rightVerticalLayout.addView(topLayout);
        topLayout.addView(nameOfTheProduct);
        topLayout.setBackgroundColor(Color.BLUE);
        rightVerticalLayout.addView(middleLayout);
        middleLayout.setBackgroundColor(Color.GREEN);
        middleLayout.addView(horizontalMiddleLayout);
        rightVerticalLayout.addView(lowerLayout);
        horizontalMiddleLayout.setBackgroundColor(Color.BLUE);
        horizontalMiddleLayout.addView(button1);
        horizontalMiddleLayout.addView(button2);
        horizontalMiddleLayout.addView(button3);
        lowerLayout.setBackgroundColor(Color.BLUE);
        linearLayoutInScrollView.setBackgroundColor(Color.BLUE);
    }

    protected void insertButtonInHorizontalScrollView(LinearLayout linearLayoutInHorizontalScrollView) {
        for (int i = 0; i < 5; i++) {
            Button button = new Button(this.getApplicationContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.FILL_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            linearLayoutInHorizontalScrollView.addView(button, layoutParams);
            button.setText("button");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Product Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.cliqr.lalita/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Product Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.cliqr.lalita/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
