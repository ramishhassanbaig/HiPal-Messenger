package com.example.ramish.hipal_messenger.fragments;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.activity.MainActivity;
import com.example.ramish.hipal_messenger.service.UserCreationService;
import com.example.ramish.hipal_messenger.utils.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginInputFragment extends Fragment {

    private TextView mHeading;
    private ImageView mAppIcon;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private ImageButton mBackButton;
    private Typeface typeface;

    private ProgressDialog loginProgressDialog;

    private String loginEmail;
    private String loginPassword;

    public LoginInputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_login_input, container, false);

        initializingView(rootView);

        typeface= Typeface.createFromAsset(getActivity().getAssets(), "fonts/ComicSansBold.ttf");
        mHeading.setTypeface(typeface);

        mAppIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation wavingHand = AnimationUtils.loadAnimation((MainActivity) getActivity(), R.anim.wave);
                mAppIcon.startAnimation(wavingHand);
            }
        });


        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).getSupportFragmentManager().popBackStack();
                Util.showToast("popped back");
            }
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmail=mEmailField.getText().toString();
                loginPassword=mPasswordField.getText().toString();

                loginProgressDialog= new ProgressDialog((MainActivity) getActivity());
                loginProgressDialog.setTitle("Signing In");
                loginProgressDialog.setMessage("Please Wait...");
                loginProgressDialog.setIndeterminate(true);
                loginProgressDialog.setCancelable(false);
                loginProgressDialog.show();
                UserCreationService.loginAuthenticatedUser(loginEmail,loginPassword,loginProgressDialog,getActivity());
            }
        });


        return  rootView;
    }

    private void initializingView(View view){
        mAppIcon=(ImageView)view.findViewById(R.id.app_icon);
        mHeading=(TextView)view.findViewById(R.id.app_heading);
        mEmailField=(EditText)view.findViewById(R.id.email_field);
        mPasswordField=(EditText)view.findViewById(R.id.password_field);
        mLoginButton=(Button)view.findViewById(R.id.login_button);
        mBackButton=(ImageButton)view.findViewById(R.id.back_button_login_input);
    }

}
