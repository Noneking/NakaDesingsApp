package com.example.nonek.nakadesingsapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BasketFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BasketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class BasketFragment extends Fragment implements TabHost.OnTabChangeListener, TabHost.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ///////////////////////////////////////////////////////////////////////////////

    Resources res;

    private View view;

    private DrawerLayout drawerLayout;

    private TabHost tabhost;
    private TabHost.TabSpec spec;
    private LinearLayout tab_basket_itemView, tab_basket_listView, tab_basket_insertView;

    private int currentTab;

    public BasketFragment(DrawerLayout drawerLayout) {
        // Required empty public constructor
        this.drawerLayout=drawerLayout;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasketFragment.
     */

    // TODO: Rename and change types and number of parameters
    public static BasketFragment newInstance(String param1, String param2) {
        BasketFragment fragment = new BasketFragment(null);//If we make instance of this, null parameter will throw an error
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.view=inflater.inflate(R.layout.fragment_basket, container, false);
        initFragmentUtilities();

        return this.view;
    }

    private void initFragmentUtilities() {

        initComponents();
    }

    private void initComponents() {

        tabhost= (TabHost) this.view.findViewById(R.id.basket_tabHost);
        tab_basket_itemView= (LinearLayout) this.view.findViewById(R.id.basket_itemView_linearLayout);
        tab_basket_listView= (LinearLayout) this.view.findViewById(R.id.basket_listView_linearLayout);
        tab_basket_insertView= (LinearLayout) this.view.findViewById(R.id.basket_insertView_linearLayout);

        initListeners();
    }

    private void initListeners() {

        tabhost.setOnTouchListener(this);

        initOperations();
    }

    private void initOperations() {

        res=getResources();
        tabhost.setup();

        //ItemView Tab
        spec=tabhost.newTabSpec("@strings/basket_itemView_tabSpec_name");
        spec.setContent(R.id.basket_itemView_linearLayout);
        spec.setIndicator("@strings/basket_itemView_tabSpec_indicator", res.getDrawable(R.drawable.shopping_cart_128x128));
        tabhost.addTab(spec);

        //ListView Tab
        spec=tabhost.newTabSpec("@strings/basket_listView_tabSpec_name");
        spec.setContent(R.id.basket_listView_linearLayout);
        spec.setIndicator("@strings/basket_listView_tabSpec_indicator", res.getDrawable(R.drawable.shopping_cart_128x128));
        tabhost.addTab(spec);

        //InsertView Tab
        spec=tabhost.newTabSpec("@strings/basket_insertView_tabSpec_name");
        spec.setContent(R.id.basket_insertView_linearLayout);
        spec.setIndicator("@strings/basket_insertView_tabSpec_indicator", res.getDrawable(R.drawable.add1_128x128));
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
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
//                                tabhost.setCurrentTab(1);
                                drawerLayout.openDrawer(Gravity.LEFT);
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
}
