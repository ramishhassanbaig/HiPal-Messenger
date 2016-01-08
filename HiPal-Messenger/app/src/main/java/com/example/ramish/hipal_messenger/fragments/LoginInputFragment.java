package com.example.ramish.hipal_messenger.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ramish.hipal_messenger.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginInputFragment extends Fragment {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;

    public LoginInputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_login_input, container, false);

        initializingView(rootView);

        return  rootView;
    }

    private void initializingView(View view){
        mEmailField=(EditText)view.findViewById(R.id.email_field);
        mPasswordField=(EditText)view.findViewById(R.id.password_field);
        mLoginButton=(Button)view.findViewById(R.id.login_button);
    }

}
