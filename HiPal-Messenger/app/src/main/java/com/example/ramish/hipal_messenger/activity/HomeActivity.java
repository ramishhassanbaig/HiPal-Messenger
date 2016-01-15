package com.example.ramish.hipal_messenger.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.adapter.TabsPagerAdapter;
import com.example.ramish.hipal_messenger.fragments.ChatsFragment;
import com.example.ramish.hipal_messenger.fragments.FavoritesFragment;
import com.example.ramish.hipal_messenger.fragments.FindPeopleFragment;
import com.example.ramish.hipal_messenger.fragments.FriendRequestFragment;
import com.example.ramish.hipal_messenger.fragments.FriendsFragment;
import com.example.ramish.hipal_messenger.fragments.NotificationsFragment;
import com.example.ramish.hipal_messenger.fragments.SliderMenuFragment;
import com.example.ramish.hipal_messenger.model.Notification;

public class HomeActivity extends AppCompatActivity implements SliderMenuFragment.FragmentDrawerListener{

    private Toolbar toolbar;
    private SliderMenuFragment sliderMenuFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String[] tabTitles={"Chats","Friends","Notifications"};
    private int[] tabIcons={R.drawable.chats_icon,R.drawable.friends_icon,R.drawable.notification};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=(Toolbar)findViewById(R.id.hipal_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/ComicSansBold.ttf");
        TextView title=(TextView)LayoutInflater.from(this).inflate(R.layout.custom_title,null);
        title.setTypeface(typeface);
        title.setText("HI PAL !");

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(title);

        viewPager=(ViewPager)findViewById(R.id.home_view_pager);
        setUpViewPager(viewPager);

        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        for (int i=0;i<3;i++) {
            tabLayout.getTabAt(i).setCustomView(getTabView(i));
        }

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

    public void fragmentHandler(int fragmentNumber){
        switch (fragmentNumber){
            case 1:{
                changeFragment(new ChatsFragment());
            }
            case 2:{
                changeFragment(new FavoritesFragment());
            }
            case 3:{
                changeFragment(new FindPeopleFragment());
            }
            case 4:{
                changeFragment(new FriendRequestFragment());
            }
            case 5:{
                changeFragment(new FriendsFragment());
            }
            case 6:{
                changeFragment(new NotificationsFragment());
            }
        }

    }

    public  void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_home,fragment)
                .addToBackStack("Home")
                .commit();
    }

    private void setUpViewPager(ViewPager viewPager){
        TabsPagerAdapter pagerAdapter=new TabsPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmentToPager(new ChatsFragment());
        pagerAdapter.addFragmentToPager(new FriendsFragment());
        pagerAdapter.addFragmentToPager(new NotificationsFragment());
        viewPager.setAdapter(pagerAdapter);
    }

    private View getTabView(int position){
        View tab=(View) LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
        ImageView icon=(ImageView)tab.findViewById(R.id.tab_icon);
        TextView title=(TextView)tab.findViewById(R.id.tab_title);
        title.setText(tabTitles[position]);
        icon.setImageResource(tabIcons[position]);
        return tab;
    }
}
