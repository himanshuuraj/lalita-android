package com.example.cliqr.newapplication;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    float x1,x2;
    float y1, y2;
    private ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        addElementInView();
    }

    protected void addElementInView(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.layoutInScrollBar);
        LinearLayout outerLayout = new LinearLayout(this);
        outerLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams outerLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,getPixel(100)
        );
        outerLayoutParams.setMargins(getPixel(5), getPixel(5), getPixel(5), getPixel(10));
        outerLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.border_shadow));
        ll.addView(outerLayout,outerLayoutParams);

        LinearLayout left = new LinearLayout(this);
        left.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT,2.0f
        );
        left.setPadding(getPixel(5),0,0,0);
        //left.setBackgroundColor(Color.parseColor("#f90808"));
        outerLayout.addView(left,leftParams);
        LinearLayout right = new LinearLayout(this);
        right.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT,1.0f
        );
        outerLayout.addView(right,rightParams);
        View v = new View(this);
        v.setBackgroundColor(Color.parseColor("#c0c0c0"));
        RelativeLayout.LayoutParams viewParams = new RelativeLayout.LayoutParams(
                getPixel(2),getPixel(80)
        );
        v.setLayoutParams(viewParams);
        outerLayout.addView(v,1);

        LinearLayout insideLL = new LinearLayout(this);
        insideLL.setOrientation(LinearLayout.HORIZONTAL);
        left.addView(insideLL,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT
        ));
        insideLL.setPadding(getPixel(15),getPixel(5),getPixel(20),getPixel(5));
        insideLL.setBackgroundColor(Color.parseColor("#ffffff"));
        ImageButton imageButton = new ImageButton(this);
        insideLL.addView(imageButton,new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.FILL_PARENT
        ));
        LinearLayout rightVertical = new LinearLayout(this);
        rightVertical.setOrientation(LinearLayout.VERTICAL);
        right.addView(rightVertical,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT
        ));
        //rightVertical.setBackgroundColor(Color.GREEN);
        LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.HORIZONTAL);
        rightVertical.addView(ll1,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,getPixel(30)
        ));
        ll1.setPadding(getPixel(20),0,0,0);
        TextView textView = new TextView(this);
        ll1.addView(textView,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView.setText("Rice");
        textView.setTextAppearance(this,android.R.style.TextAppearance_DeviceDefault_Medium);

        LinearLayout ll2 = new LinearLayout(this);
        ll2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams ll2Param= new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,getPixel(20)
        );
        ll2.setLayoutParams(ll2Param);
        rightVertical.addView(ll2,1);
        ll2.setPadding(getPixel(20),0,0,0);
        TextView textView1 = new TextView(this);
        ll2.addView(textView1,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        textView1.setText("Par Boiled; Steamed");
        textView1.setTextAppearance(this,android.R.style.TextAppearance_DeviceDefault_Small);

        LinearLayout ll3 = new LinearLayout(this);
        ll3.setOrientation(LinearLayout.HORIZONTAL);
        rightVertical.addView(ll3,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT
        ));
        ImageView imageView1 = new ImageView(this);
        ll3.addView(imageView1,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT
        ));
        ll3.setPadding(getPixel(20),getPixel(5),getPixel(20),getPixel(5));
        imageView1.setBackgroundColor(Color.YELLOW);
        left.setBackgroundColor(Color.RED);
        /*
        right.setBackgroundColor(Color.BLUE);*/
    }

    protected int getPixel(int dp){
        Resources r = getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int)px;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                //if left to right sweep event on screen
                if (x1 < x2)
                {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                }

                // if right to left sweep event on screen
                if (x1 > x2)
                {
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                }

                // if UP to Down sweep event on screen
                if (y1 < y2)
                {
                    Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }

                //if Down to UP sweep event on screen
                if (y1 > y2)
                {
                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        return false;
    }


}
