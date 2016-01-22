package com.example.ramish.hipal_messenger.activity;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.fragments.LoginFragment;
import com.example.ramish.hipal_messenger.fragments.LoginInputFragment;
import com.example.ramish.hipal_messenger.fragments.SignupFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentInterActionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentHandler(1);

    }


    public void fragmentHandler(int fragmentNumber){

        switch (fragmentNumber){
            case 1:{
                changeFragment(new LoginFragment());
                break;
            }
            case 2:{
                changeFragment(new LoginInputFragment());
                break;
            }
            case 3:{
                changeFragment(new SignupFragment());
                break;
            }
        }

    }

    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main,fragment)
                .addToBackStack("Main")
                .commit();
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
//                        MainActivity.super.onBackPressed();
                        MainActivity.this.finish();
                    }
                }).create().show();
    }
}
