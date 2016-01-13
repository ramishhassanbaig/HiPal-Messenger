package com.example.ramish.hipal_messenger.fragments;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.activity.MainActivity;
import com.example.ramish.hipal_messenger.utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView mHeading;
    private ImageView mIcon;
    private Button mSignInButton;
    private Button mSignUpButton;
    private ImageButton mFacebookSignIn;
    private ImageButton mGoogleSignIn;
    private Typeface typeface;

    private LoginFragmentInterActionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_login, container, false);

        initializingView(rootView);

        typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/ComicSansBold.ttf");
        mHeading.setTypeface(typeface);

        mIcon.setRotation(45f);

//        ((MainActivity)getActivity()).fragmentHandler(2);

       mSignInButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Util.showToast("signin clicked");
               mListener.fragmentHandler(2);
           }
       });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showToast("signup clicked");
                mListener.fragmentHandler(3);
            }
        });


        return rootView;
    }



    private void initializingView(View view){
        mHeading=(TextView)view.findViewById(R.id.app_heading);
        mIcon=(ImageView)view.findViewById(R.id.app_icon);
        mSignInButton=(Button)view.findViewById(R.id.signin_button);
        mSignUpButton=(Button)view.findViewById(R.id.signup_button);
        mFacebookSignIn=(ImageButton)view.findViewById(R.id.facebook_signin);
        mGoogleSignIn=(ImageButton)view.findViewById(R.id.google_signin);
    }



    public interface LoginFragmentInterActionListener{
        public void fragmentHandler(int fragmentNumber);
        public void createFragment(Fragment fragment);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener=(LoginFragmentInterActionListener)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+"must Implement LoginFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener=null;
    }
}



