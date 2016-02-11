package com.example.nonek.nakadesingsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.view.GestureDetector.*;

/**
 * Created by nonek on 11/02/2016.
 */
public class ProductFrament extends Fragment {

    public View view;

    GestureDetector gestureDetector;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        this.view=inflater.inflate(R.layout.activity_product, container, false);
        return this.view;
    }

    public View getCurrentView(){
        return this.view;
    }

}
