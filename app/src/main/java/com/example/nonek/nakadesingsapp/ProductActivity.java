package com.example.nonek.nakadesingsapp;

import android.app.Activity;
import android.content.res.Resources;
import android.gesture.Gesture;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class ProductActivity extends Activity implements TabHost.OnTabChangeListener {

    Resources res;

    private View view;

    private TabHost tabhost;
    private TabHost.TabSpec spec;
    private LinearLayout tab_product_view, tab_product_insert;

    public static GestureDetector gestureDetector;

    private int currentTab;

//    public static ProductActivity newInstance(String sectionTitle) {
//
//        ProductActivity fragment = new ProductActivity();
//        return fragment;
//    }
//
    public ProductActivity(View view){

        this.view=view;
        initComponents();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

//        initComponents();
    }
//
//    public ProductActivity() {
//    }

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//
//        this.view = inflater.inflate(R.layout.activity_product, container, false);
//
//        initFragmentUtilities();
//
//        return this.view;
//    }

//    public View getCurrentView(){
//        return this.view;
//    }

//    private void initFragmentUtilities() {
//
//        initComponents();
//    }

    private void initComponents() {

        tabhost= (TabHost) this.findViewById(R.id.product_tabHost);
        tab_product_view= (LinearLayout) this.findViewById(R.id.product_view_linearLayout);
        tab_product_insert= (LinearLayout) this.findViewById(R.id.product_insert_linearLayout);

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

        tabhost.setCurrentTab(0);
        currentTab=0;

    }

    @Override
    public void onTabChanged(String tabId) {

        currentTab=tabhost.getCurrentTab();

        switch(tabhost.getCurrentTab()){
            case 0:

                break;
            case 1:

                break;
        }

    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeTop() {
        //Toast.makeText(this, "top", Toast.LENGTH_SHORT).show();
        animationBottom();
    }
    public void onSwipeRight() {
        //Toast.makeText(this, "right", Toast.LENGTH_SHORT).show();
        animationLeft();
    }
    public void onSwipeLeft() {
        //Toast.makeText(this, "left", Toast.LENGTH_SHORT).show();
        animationRight();
    }
    public void onSwipeBottom() {
        //Toast.makeText(this, "bottom", Toast.LENGTH_SHORT).show();
        animationTop();
    }

    public void animationRight(){
//        Toast.makeText(this,"animationRight",Toast.LENGTH_SHORT).show();
        Log.i("tab","Pasa por right");
        if(tabhost.getCurrentTab()>0){
            Log.i("tab","Entra dentro del primer if");
            tabhost.setCurrentTab(0);
        }else{
            tabhost.setCurrentTab(tabhost.getCurrentTab()+1);
        }
    }

    public void animationLeft(){
//        Toast.makeText(this,"animationLeft",Toast.LENGTH_SHORT).show();
        if(tabhost.getCurrentTab()==0){
            tabhost.setCurrentTab(1);
        }else{
            tabhost.setCurrentTab(tabhost.getCurrentTab()-1);
        }
    }

    public void animationTop(){

    }

    public void animationBottom(){

    }

}
