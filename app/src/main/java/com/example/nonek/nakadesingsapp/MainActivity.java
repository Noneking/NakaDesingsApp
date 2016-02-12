package com.example.nonek.nakadesingsapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Components
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    ProductActivity productActivity;
    ProductFrament productFragment;

    GestureListener gestureListener;

    //Variables
    private String drawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            getMenuInflater().inflate(R.menu.navigation_menu, menu);
//            return true;
//        }

        //Change color of Status Bar for Menu's Bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.primaryColor));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void initComponents(Bundle savedInstanceState){

        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);


        initListeners(savedInstanceState);
    }

    public void initListeners(Bundle savedInstanceState){

        navigationView.setNavigationItemSelectedListener(this);

        initOperations(savedInstanceState);
    }

    public void initOperations(Bundle savedInstanceState){

        if (navigationView != null) {
            // Añadir carácteristicas
        }

        drawerTitle = getResources().getString(R.string.category_item);
        if (savedInstanceState == null) {
            // Seleccionar item
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        selectedItemInitOperations(item.getTitle().toString());
        return false;
    }

    public void selectedItemInitOperations(String title){

        Fragment fragment=null;

        switch(title){
            case "Category":

                break;
            case "Product":
//                fragment = ProductActivity.newInstance(title);

//                Intent intent=new Intent(getApplicationContext(), ProductActivity.class);
//                fragment=startActivityFromFragment(R.id.main_content, intent, RESULT_OK);

                productFragment=new ProductFrament();

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, productFragment).commit();

//                productActivity=new ProductActivity(productFragment.getCurrentView());

                break;
            case "Employee":

                break;
            case "Basket":

                break;
            case "Log out":

                break;
        }

//        if(fragment!=null){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager
//                    .beginTransaction()
//                    .replace(R.id.main_content, fragment)
//                    .commit();

//            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.product_mainLayout, Fragment.instantiate(getApplicationContext(), ProductActivity.class.getName()));
//            fragmentTransaction.commit();
//        }

        drawerLayout.closeDrawers();
        setTitle(title);
    }

//    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        private static final int SWIPE_THRESHOLD = 100;
//        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            boolean result = false;
//            try {
//                float diffY = e2.getY() - e1.getY();
//                float diffX = e2.getX() - e1.getX();
//                if (Math.abs(diffX) > Math.abs(diffY)) {
//                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
//                        if (diffX > 0) {
//                            onSwipeRight();
//                        } else {
//                            onSwipeLeft();
//                        }
//                    }
//                    result = true;
//                }
//                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                    if (diffY > 0) {
//                        onSwipeBottom();
//                    } else {
//                        onSwipeTop();
//                    }
//                }
//                result = true;
//
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//            return result;
//        }
//    }
//
//    public void onSwipeTop() {
//        //Toast.makeText(this, "top", Toast.LENGTH_SHORT).show();
//        animationBottom();
//    }
//    public void onSwipeRight() {
//        //Toast.makeText(this, "right", Toast.LENGTH_SHORT).show();
//        animationLeft();
//    }
//    public void onSwipeLeft() {
//        //Toast.makeText(this, "left", Toast.LENGTH_SHORT).show();
//        animationRight();
//    }
//    public void onSwipeBottom() {
//        //Toast.makeText(this, "bottom", Toast.LENGTH_SHORT).show();
//        animationTop();
//    }
//
//    public void animationRight(){
////        Toast.makeText(this,"animationRight",Toast.LENGTH_SHORT).show();
////        if(tabhost.getCurrentTab()>1){
////            tabhost.setCurrentTab(0);
////        }else{
////            tabhost.setCurrentTab(tabhost.getCurrentTab()+1);
////        }
//    }
//
//    public void animationLeft(){
////        Toast.makeText(this,"animationLeft",Toast.LENGTH_SHORT).show();
////        if(tabhost.getCurrentTab()==0){
////            tabhost.setCurrentTab(1);
////        }else{
////            tabhost.setCurrentTab(tabhost.getCurrentTab() - 1);
////        }
//    }
//
//    public void animationTop(){
//
//    }
//
//    public void animationBottom(){
//
//    }

}
