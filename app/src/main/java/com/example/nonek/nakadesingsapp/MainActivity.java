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
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Components
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    ProductActivity productActivity;

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

        switch(title){
            case "Category":

                break;
            case "Product":

                productActivity=new ProductActivity();

                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_content, productActivity).commit();

                break;
            case "Employee":

                break;
            case "Basket":

                break;
            case "Log out":

                break;
        }

        drawerLayout.closeDrawers();
        setTitle(title);
    }

}
