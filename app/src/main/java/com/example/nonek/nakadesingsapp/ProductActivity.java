package com.example.nonek.nakadesingsapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class ProductActivity extends AppCompatActivity {

    Resources res;

    private TabHost tabhost;
    private TabHost.TabSpec spec;
    private LinearLayout tab_product_view, tab_product_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        initComponents();
    }

    private void initComponents() {

        tabhost= (TabHost) findViewById(R.id.product_tabHost);
        tab_product_view= (LinearLayout) findViewById(R.id.product_view_linearLayout);
        tab_product_insert= (LinearLayout) findViewById(R.id.product_insert_linearLayout);

        initListeners();
    }

    private void initListeners() {


        initOperations();
    }

    private void initOperations() {

        res=getResources();
        tabhost.setup();

        //View Tab
        spec=tabhost.newTabSpec("VIEW");
        spec.setContent(R.id.product_view_linearLayout);
        spec.setIndicator("", res.getDrawable(R.drawable.product_128x128));
        tabhost.addTab(spec);

        //Insert Tab
        spec=tabhost.newTabSpec("INSERT");
        spec.setContent(R.id.product_insert_linearLayout);
        spec.setIndicator("", res.getDrawable(R.drawable.add1_128x128));
        tabhost.addTab(spec);

        tabhost.setCurrentTab(1);

    }

}
