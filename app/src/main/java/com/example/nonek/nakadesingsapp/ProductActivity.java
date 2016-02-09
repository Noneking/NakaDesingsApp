package com.example.nonek.nakadesingsapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class ProductActivity extends FragmentActivity {

    Resources res;

    private TabHost tabhost;
    private TabHost.TabSpec spec;
    private LinearLayout tab_product_view, tab_product_insert;

    public static ProductActivity newInstance(String sectionTitle) {

        ProductActivity fragment = new ProductActivity();
        return fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


    }

    public ProductActivity() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_product, container, false);

        initFragmentUtilities();

        return view;
    }

    private void initFragmentUtilities() {

        initComponents();
    }

    private void initComponents() {

        tabhost= (TabHost) tabhost.findViewById(R.id.product_tabHost);
        tab_product_view= (LinearLayout) tab_product_view.findViewById(R.id.product_view_linearLayout);
        tab_product_insert= (LinearLayout) tab_product_insert.findViewById(R.id.product_insert_linearLayout);

        initListeners();
    }

    private void initListeners() {


        initOperations();
    }

    private void initOperations() {

        res=getResources();
        tabhost.setup();

        //View Tab
        spec=tabhost.newTabSpec("PRODUCT_VIEW");
        spec.setContent(R.id.product_view_linearLayout);
        spec.setIndicator("View", res.getDrawable(R.drawable.product_128x128));
        tabhost.addTab(spec);

        //Insert Tab
        spec=tabhost.newTabSpec("PRODUCT_INSERT");
        spec.setContent(R.id.product_insert_linearLayout);
        spec.setIndicator("Insert", res.getDrawable(R.drawable.add1_128x128));
        tabhost.addTab(spec);

        tabhost.setCurrentTab(1);

    }

//    Resources res;
//
//    private TabHost tabhost;
//    private TabHost.TabSpec spec;
//    private LinearLayout tab_product_view, tab_product_insert;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product);
//
//        initComponents();
//    }
//
//    private void initComponents() {
//
//        tabhost= (TabHost) findViewById(R.id.product_tabHost);
//        tab_product_view= (LinearLayout) findViewById(R.id.product_view_linearLayout);
//        tab_product_insert= (LinearLayout) findViewById(R.id.product_insert_linearLayout);
//
//        initListeners();
//    }
//
//    private void initListeners() {
//
//
//        initOperations();
//    }
//
//    private void initOperations() {
//
//        res=getResources();
//        tabhost.setup();
//
//        //View Tab
//        spec=tabhost.newTabSpec("PRODUCT_VIEW");
//        spec.setContent(R.id.product_view_linearLayout);
//        spec.setIndicator("View", res.getDrawable(R.drawable.product_128x128));
//        tabhost.addTab(spec);
//
//        //Insert Tab
//        spec=tabhost.newTabSpec("PRODUCT_INSERT");
//        spec.setContent(R.id.product_insert_linearLayout);
//        spec.setIndicator("Insert", res.getDrawable(R.drawable.add1_128x128));
//        tabhost.addTab(spec);
//
//        tabhost.setCurrentTab(1);
//
//    }

}
