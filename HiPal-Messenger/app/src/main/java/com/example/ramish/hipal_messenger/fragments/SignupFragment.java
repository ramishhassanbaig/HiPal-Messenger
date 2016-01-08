package com.example.ramish.hipal_messenger.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ramish.hipal_messenger.HiPalMessengerApp;
import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mBirthDate;
    private Spinner mGenderSelect;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_signup, container, false);

        initializingView(rootView);

        ArrayAdapter<CharSequence> genderSelectionAdapter=ArrayAdapter.createFromResource(HiPalMessengerApp.getContext(),R.array.genders,android.R.layout.simple_spinner_item);
        genderSelectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderSelect.setAdapter(genderSelectionAdapter);

        return rootView;
    }



    public void initializingView(View view){
        mFirstNameField=(EditText)view.findViewById(R.id.first_name_field);
        mLastNameField=(EditText)view.findViewById(R.id.last_name_field);
        mBirthDate=(EditText)view.findViewById(R.id.birth_date_field);
        mGenderSelect=(Spinner)view.findViewById(R.id.gender_field);
    }

}
