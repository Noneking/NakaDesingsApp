package com.example.nonek.nakadesingsapp;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TabHost;

/**
 * Created by nonek on 11/02/2016.
 */
public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private TabHost tabhost;

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    public GestureListener(TabHost tabhost){
        this.tabhost=tabhost;
    }

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
        if(tabhost.getCurrentTab()>1){
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
            tabhost.setCurrentTab(tabhost.getCurrentTab() - 1);
        }
    }

    public void animationTop(){

    }

    public void animationBottom(){

    }

}
