package com.example.ramish.hipal_messenger.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.fragments.SliderMenuFragment;

public class HomeActivity extends AppCompatActivity implements SliderMenuFragment.FragmentDrawerListener{

    private Toolbar toolbar;
    private SliderMenuFragment sliderMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=(Toolbar)findViewById(R.id.hipal_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.slider_menu_icon);

        sliderMenuFragment=(SliderMenuFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_slider_menu);
        sliderMenuFragment.setUpSliderMenuFragment(R.id.fragment_slider_menu,(DrawerLayout)findViewById(R.id.home_drawer_layout),toolbar);
        sliderMenuFragment.setDrawerListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onDrawerItemSelected(View view, int position) {

    }
}
