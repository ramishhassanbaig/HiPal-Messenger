package com.example.ramish.hipal_messenger.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.fragments.LoginFragment;
import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new LoginFragment())
                .commit();

    }
}
