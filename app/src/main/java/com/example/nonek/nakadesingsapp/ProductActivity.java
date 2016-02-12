package com.example.nonek.nakadesingsapp;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import static android.view.GestureDetector.*;

/**
 * Created by nonek on 11/02/2016.
 */
public class ProductActivity extends Fragment implements TabHost.OnTabChangeListener, TabHost.OnTouchListener {

    Resources res;

    private View view;

    private TabHost tabhost;
    private TabHost.TabSpec spec;
    private LinearLayout tab_product_view, tab_product_insert;

//    public static GestureDetector gestureDetector;

    private int currentTab;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        this.view=inflater.inflate(R.layout.activity_product, container, false);
        initFragmentUtilities();

        return this.view;
    }

    public View getCurrentView(){
        return this.view;
    }

    private void initFragmentUtilities() {

        initComponents();
    }

    private void initComponents() {

        Log.i("tab", "PASA POR INITCOMPONENTS");

        tabhost= (TabHost) this.view.findViewById(R.id.product_tabHost);
        tab_product_view= (LinearLayout) this.view.findViewById(R.id.product_view_linearLayout);
        tab_product_insert= (LinearLayout) this.view.findViewById(R.id.product_insert_linearLayout);

        initListeners();
    }

    private void initListeners() {

        tabhost.setOnTouchListener(this);

        initOperations();
    }

    private void initOperations() {

//        gestureDetector = new GestureDetector(this, new GestureListener());

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

        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            TextView widgetTextView = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            widgetTextView.setTextColor(Color.parseColor("#FFFFFF"));
        }

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

    final GestureDetector gestureDetector=new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){

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
                            //Left Animation
                            if(tabhost.getCurrentTab()==0){
                                tabhost.setCurrentTab(1);
                            }else{
                                tabhost.setCurrentTab(tabhost.getCurrentTab()-1);
                            }
                        } else {
                            //Right Animation
                            if(tabhost.getCurrentTab()>1){
                                tabhost.setCurrentTab(0);
                            }else{
                                tabhost.setCurrentTab(tabhost.getCurrentTab()+1);
                            }
                        }
                    }
                    result = true;
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        //Top Animation

                    } else {
                        //Bottom Animation

                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    });

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}
