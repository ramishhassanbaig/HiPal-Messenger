package com.example.ramish.hipal_messenger.fragments;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.activity.HomeActivity;
import com.example.ramish.hipal_messenger.adapter.SliderMenuListAdapter;
import com.example.ramish.hipal_messenger.firebase.FirebaseHandler;
import com.example.ramish.hipal_messenger.model.SliderMenuListItem;
import com.example.ramish.hipal_messenger.model.User;
import com.example.ramish.hipal_messenger.service.LocalUserService;
import com.example.ramish.hipal_messenger.utils.Util;
import com.firebase.client.AuthData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SliderMenuFragment extends Fragment {

    private TextView sliderUserName;
    private TextView sliderUserEmail;
    private ImageView sliderUserProfilePic;

    private ListView sliderListView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private SliderMenuListAdapter sliderMenuListAdapter;
    private View fragmentContainerView;

    private String[] itemTitles;
    private TypedArray itemIcons;
    private ArrayList<SliderMenuListItem> sliderMenuListItems;

    private User userLoggedIn;

    private FragmentDrawerListener drawerListener;

    public SliderMenuFragment() {
        // Required empty public constructor
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_slider_menu, container, false);

        userLoggedIn= LocalUserService.getLocalUser();
        Log.d("SliderMenuUser", userLoggedIn.getUserName().toString());

        initializingView(rootView);

        sliderUserName.setText(userLoggedIn.getUserName());
        sliderUserEmail.setText(userLoggedIn.getEmail());

        itemTitles=getResources().getStringArray(R.array.slider_menu_list_titles);
        itemIcons=getResources().obtainTypedArray(R.array.slider_menu_list_icons);

        sliderMenuListItems=new ArrayList<SliderMenuListItem>();

        sliderMenuListItems.add(new SliderMenuListItem(itemTitles[0],itemIcons.getResourceId(0, -1),userLoggedIn.getFriendCounter(),true));
        sliderMenuListItems.add(new SliderMenuListItem(itemTitles[1],itemIcons.getResourceId(1,-1),0,true));
        sliderMenuListItems.add(new SliderMenuListItem(itemTitles[2],itemIcons.getResourceId(2, -1),userLoggedIn.getFriendReqCounter(),true));
        sliderMenuListItems.add(new SliderMenuListItem(itemTitles[3],itemIcons.getResourceId(3,-1),0,true));
        sliderMenuListItems.add(new SliderMenuListItem(itemTitles[4],itemIcons.getResourceId(4, -1),userLoggedIn.getFavoritesCounter(),true));
        sliderMenuListItems.add(new SliderMenuListItem(itemTitles[5],itemIcons.getResourceId(5, -1),userLoggedIn.getNotificationCounter(),true));

        itemIcons.recycle();

        sliderMenuListAdapter=new SliderMenuListAdapter(getContext(),R.id.slider_menu_list_view,sliderMenuListItems);
        sliderListView.setAdapter(sliderMenuListAdapter);

        sliderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Util.showToast(itemTitles[position]+" clicked");
                ((HomeActivity)getActivity()).checkContainerVisibility();
                ((HomeActivity)getActivity()).fragmentHandler(position);
                mDrawerLayout.closeDrawer(fragmentContainerView);
            }
        });




        return rootView;
    }

    private void initializingView(View v){
        sliderUserName=(TextView)v.findViewById(R.id.user_full_name);
        sliderUserEmail=(TextView)v.findViewById(R.id.user_email);
        sliderUserProfilePic=(ImageView)v.findViewById(R.id.user_profile_pic);
        sliderListView=(ListView)v.findViewById(R.id.slider_menu_list_view);
    }

    public void setUpSliderMenuFragment(int fragmentId,DrawerLayout drawerLayout,final Toolbar toolbar){
        fragmentContainerView=getActivity().findViewById(fragmentId);
        mDrawerLayout=drawerLayout;
        mDrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1-slideOffset/2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }


    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
}
