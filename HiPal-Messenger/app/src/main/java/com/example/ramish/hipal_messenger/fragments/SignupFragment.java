package com.example.ramish.hipal_messenger.fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
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
import com.example.ramish.hipal_messenger.model.User;
import com.example.ramish.hipal_messenger.utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private TextView mHeading;
    private ImageView mAppIcon;
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

    private String firstName="";
    private String lastName="";
    private String dob="";
    private int gender;
    private String email="";
    private String passwordField="";
    private String confirmPasswordField="";
    private String userPassword;

    private User newCreatedUser;



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

        final ArrayAdapter<CharSequence> genderSelectionAdapter=ArrayAdapter.createFromResource(HiPalMessengerApp.getContext(),
                R.array.genders,android.R.layout.simple_spinner_item);
        genderSelectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderSelect.setAdapter(genderSelectionAdapter);


        mAppIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation wavingHand=AnimationUtils.loadAnimation((MainActivity)getActivity(),R.anim.wave);
                mAppIcon.startAnimation(wavingHand);
            }
        });


        mNextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFirstNameField.getText().toString().equals("") || mLastNameField.getText().toString().equals("") || mBirthDate.getText().toString().equals("")){
                    if (mFirstNameField.getText().toString().equals("")){
                        fieldShakingAnimation(mFirstNameField);
                        mFirstNameField.setError("necessary field");
                    }
                    if (mLastNameField.getText().toString().equals("")){
                        fieldShakingAnimation(mLastNameField);
                        mLastNameField.setError("necessary field");
                    }
                    if (mBirthDate.getText().toString().equals("")){
                        fieldShakingAnimation(mBirthDate);
                        mBirthDate.setError("necessary field");
                    }
                }
                else {
                    mSecondIndicator.setBackgroundResource(R.drawable.signup_indicator_selected);
                    signUpScreen1.setVisibility(signUpScreen1.GONE);
                    signUpScreen2.setVisibility(signUpScreen2.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(((MainActivity) getActivity()), R.anim.right_to_left);
                    signUpScreen2.startAnimation(animation);
                }
            }
        });

        mNextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mPasswordField.getText()==mPasswordConfirmField.getText()){
//                    userPassword=passwordField;
//                }
//                else {
//                    mPasswordConfirmField.setError("Passwords does not match");
//                }

                if (mEmailField.getText().toString().equals("") || mPasswordField.getText().toString().equals("") || mPasswordConfirmField.getText().toString().equals("")){
                    if (mEmailField.getText().toString().equals("")){
                        fieldShakingAnimation(mEmailField);
                        mEmailField.setError("necessary field");
                    }
                    if (mPasswordField.getText().toString().equals("")){
                        fieldShakingAnimation(mPasswordField);
                        mPasswordField.setError("necessary field");
                    }
                    if (mPasswordConfirmField.getText().toString().equals("")){
                        fieldShakingAnimation(mPasswordConfirmField);
                        mPasswordConfirmField.setError("necessary field");
                    }
                }
                if (!(mPasswordField.getText().toString().equals(mPasswordConfirmField.getText().toString()))){
                    mPasswordConfirmField.setError("Passwords does not match");
                }
                else {
                    mThirdIndicator.setBackgroundResource(R.drawable.signup_indicator_selected);
                    signUpScreen2.setVisibility(signUpScreen2.GONE);
                    signUpScreen3.setVisibility(signUpScreen3.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(((MainActivity) getActivity()), R.anim.right_to_left);
                    signUpScreen3.startAnimation(animation);
                }
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
                newCreatedUser=getSignUpDetails();
                Log.d("Signup:",newCreatedUser.getFirstName()+", "+newCreatedUser.getLastName()+", "+
                        newCreatedUser.getGender()+", "+newCreatedUser.getEmail()+", "+newCreatedUser.getPassword());
                Intent i = new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
            }
        });



        return rootView;
    }


    public User getSignUpDetails(){
        firstName=mFirstNameField.getText().toString();
        lastName=mLastNameField.getText().toString();
        dob=mBirthDate.getText().toString();
        email=mEmailField.getText().toString();
        passwordField =mPasswordField.getText().toString();
        confirmPasswordField =mPasswordConfirmField.getText().toString();
        if (passwordField.equals(confirmPasswordField)){userPassword=passwordField;}
        mGenderSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = mGenderSelect.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        User newUser=new User(firstName,lastName,gender,dob,email,userPassword);
        return newUser;
    }


    private void fieldShakingAnimation(EditText editText){
        Animation shaking=AnimationUtils.loadAnimation((MainActivity)getActivity(),R.anim.shake);
        editText.startAnimation(shaking);
    }

    public void initializingView(View view){
        mAppIcon=(ImageView)view.findViewById(R.id.app_icon);
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
