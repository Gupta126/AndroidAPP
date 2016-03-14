package com.rahulgupta.androidapp.Activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rahulgupta.androidapp.Fragments.ScenarioOneFragment;
import com.rahulgupta.androidapp.Fragments.ScenarioTwoFragment;
import com.rahulgupta.androidapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ScenarioOneFragment.OnFragmentInteractionListener,
        ScenarioTwoFragment.OnFragmentInteractionListener {


    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    // default navigation index
    private int navIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(toggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        toggle.syncState();


        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // set default selection
        setdefaultNavigationSelected();
    }

    private void setdefaultNavigationSelected() {

        onNavigationItemSelected(navigationView.getMenu().getItem(navIndex));
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer(drawerLayout);


        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // This method will trigger on item Click of navigation menu
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // Handle navigation view item clicks here.

        //Checking if the item is in checked state or not, if not make it in checked state
        if (menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);

        //Closing drawer on item click
        closeDrawer(drawerLayout);
        // begin fragment trasaction.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {


            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.nav_senario_one:

                ScenarioOneFragment sc_one_fragment = ScenarioOneFragment.newInstance();

                fragmentTransaction.replace(R.id.content, sc_one_fragment);

                fragmentTransaction.commit();
                return true;


            case R.id.nav_senario_two:

                ScenarioTwoFragment sc_two_fragment = ScenarioTwoFragment.newInstance();

                fragmentTransaction.replace(R.id.content, sc_two_fragment);

                fragmentTransaction.commit();
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                return true;


        }
    }

    public void closeDrawer(DrawerLayout drawer) {

        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
