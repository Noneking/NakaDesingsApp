package com.example.nonek.nakadesingsapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CategoryActivity.OnFragmentInteractionListener, ProductFragment.OnFragmentInteractionListener, EmployeeFragment.OnFragmentInteractionListener, BasketFragment.OnFragmentInteractionListener {

    //Components
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Intent intent;

    private ProductFragment productFragment;
    private CategoryActivity categoryFragment;
    private EmployeeFragment employeeFragment;
    private BasketFragment basketFragment;

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
        window.setStatusBarColor(this.getResources().getColor(R.color.blue_naka));

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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        drawerTitle = getResources().getString(R.string.app_title);

        if (navigationView != null) {
            // Añadir carácteristicas
        }

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
        FragmentManager fragmentManager=null;
        FragmentTransaction fragmentTransaction=null;

        switch(title){
            case "Category":

                categoryFragment=new CategoryActivity(drawerLayout);

                fragmentManager=getSupportFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, categoryFragment).commit();
                break;
            case "Product":

                productFragment=new ProductFragment(drawerLayout);

                fragmentManager=getSupportFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, productFragment).commit();
                break;
            case "Employee":

                employeeFragment=new EmployeeFragment(drawerLayout);

                fragmentManager=getSupportFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, employeeFragment).commit();
                break;
            case "Basket":

                basketFragment=new BasketFragment(drawerLayout);

                fragmentManager=getSupportFragmentManager();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, basketFragment).commit();
                break;
            case "Log out":

                intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawers();
        setTitle(title);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
