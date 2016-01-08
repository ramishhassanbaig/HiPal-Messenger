package com.example.ramish.hipal_messenger.utils;

import android.widget.Toast;

import com.example.ramish.hipal_messenger.HiPalMessengerApp;

/**
 * Created by ramish on 1/7/2016.
 */
public class Util {

    public static void showToast(String  message){
        Toast.makeText(HiPalMessengerApp.getContext(),message,Toast.LENGTH_SHORT).show();
    }

    public static void failureToast(){
        showToast("Task is successful");
    }

    public static void successToast(){
        showToast("Task Failed");
    }
}
