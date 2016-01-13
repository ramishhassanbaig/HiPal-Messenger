package com.example.ramish.hipal_messenger.fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.HiPalMessengerApp;
import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.activity.HomeActivity;
import com.example.ramish.hipal_messenger.activity.MainActivity;
import com.example.ramish.hipal_messenger.utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private TextView mHeading;
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mBirthDate;
    private Spinner mGenderSelect;
    private Typeface typeface;
    private ImageButton mBackButtonSignUp;
    private Button mNextButton1;
    private Button mNextButton2;
    private Button mSubmitButton;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mPasswordConfirmField;
    private ImageView mProfilePictureField;

    private LinearLayout signUpScreen1;
    private LinearLayout signUpScreen2;
    private LinearLayout signUpScreen3;

    private TextView mFirstIndicator;
    private TextView mSecondIndicator;
    private TextView mThirdIndicator;


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_signup, container, false);

        initializingView(rootView);

        typeface= Typeface.createFromAsset(getActivity().getAssets(), "fonts/ComicSansBold.ttf");
        mHeading.setTypeface(typeface);

        ArrayAdapter<CharSequence> genderSelectionAdapter=ArrayAdapter.createFromResource(HiPalMessengerApp.getContext(),R.array.genders,android.R.layout.simple_spinner_item);
        genderSelectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderSelect.setAdapter(genderSelectionAdapter);


        mNextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSecondIndicator.setBackgroundResource(R.drawable.signup_indicator_selected);
                signUpScreen1.setVisibility(signUpScreen1.GONE);
                signUpScreen2.setVisibility(signUpScreen2.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(((MainActivity) getActivity()), R.anim.right_to_left);
                signUpScreen2.startAnimation(animation);
            }
        });

        mNextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThirdIndicator.setBackgroundResource(R.drawable.signup_indicator_selected);
                signUpScreen2.setVisibility(signUpScreen2.GONE);
                signUpScreen3.setVisibility(signUpScreen3.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(((MainActivity) getActivity()), R.anim.right_to_left);
                signUpScreen3.startAnimation(animation);
            }
        });

        mBackButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateViews();

            }
        });


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
            }
        });



        return rootView;
    }




    public void initializingView(View view){
        mHeading=(TextView)view.findViewById(R.id.app_heading);
        mFirstNameField=(EditText)view.findViewById(R.id.first_name_field);
        mLastNameField=(EditText)view.findViewById(R.id.last_name_field);
        mBirthDate=(EditText)view.findViewById(R.id.birth_date_field);
        mGenderSelect=(Spinner)view.findViewById(R.id.gender_field);
        mBackButtonSignUp=(ImageButton)view.findViewById(R.id.back_button_signup);
        mNextButton1=(Button)view.findViewById(R.id.next1_button);
        mNextButton2=(Button)view.findViewById(R.id.next2_button);
        mSubmitButton=(Button)view.findViewById(R.id.submit_button);
        mEmailField=(EditText)view.findViewById(R.id.email_field);
        mPasswordField=(EditText)view.findViewById(R.id.password_field);
        mPasswordConfirmField=(EditText)view.findViewById(R.id.password_confirm_field);
        mProfilePictureField=(ImageView)view.findViewById(R.id.profile_picture_field);

        signUpScreen1=(LinearLayout)view.findViewById(R.id.signup_screen1);
        signUpScreen2=(LinearLayout)view.findViewById(R.id.signup_screen2);
        signUpScreen3=(LinearLayout)view.findViewById(R.id.signup_screen3);

        mFirstIndicator=(TextView)view.findViewById(R.id.first_indicator);
        mSecondIndicator=(TextView)view.findViewById(R.id.second_indicator);
        mThirdIndicator=(TextView)view.findViewById(R.id.third_indicator);

    }

    private void navigateViews(){
        if (signUpScreen1.getVisibility()==signUpScreen1.GONE && signUpScreen2.getVisibility()==signUpScreen2.GONE){
            signUpScreen3.setVisibility(signUpScreen3.GONE);
            signUpScreen2.setVisibility(signUpScreen2.VISIBLE);
            Animation animation= AnimationUtils.loadAnimation(((MainActivity)getActivity()),R.anim.left_to_right);
            signUpScreen2.startAnimation(animation);
            mThirdIndicator.setBackgroundResource(R.drawable.signup_indicator_normal);
        }
        else if (signUpScreen1.getVisibility()==signUpScreen1.GONE && signUpScreen3.getVisibility()==signUpScreen3.GONE){
            signUpScreen2.setVisibility(signUpScreen2.GONE);
            signUpScreen1.setVisibility(signUpScreen1.VISIBLE);
            Animation animation= AnimationUtils.loadAnimation(((MainActivity)getActivity()),R.anim.left_to_right);
            signUpScreen1.startAnimation(animation);
            mSecondIndicator.setBackgroundResource(R.drawable.signup_indicator_normal);
        }
        else if(signUpScreen2.getVisibility()==signUpScreen2.GONE && signUpScreen3.getVisibility()==signUpScreen3.GONE) {
            ((MainActivity) getActivity()).getSupportFragmentManager().popBackStack();
            Util.showToast("popped back");
        }
    }



}
