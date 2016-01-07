package com.example.ramish.hipal_messenger.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    TextView mHeading;
    ImageView mIcon;
    Typeface typeface;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_login, container, false);

        mHeading=(TextView)rootView.findViewById(R.id.app_heading);
        mIcon=(ImageView)rootView.findViewById(R.id.app_icon);

        typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ComicSans.ttf");

        mHeading.setTypeface(typeface);

        mIcon.setRotation(45f);



        return rootView;
    }

}
