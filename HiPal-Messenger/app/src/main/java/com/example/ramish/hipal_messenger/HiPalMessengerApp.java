package com.example.ramish.hipal_messenger;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;

/**
 * Created by ramish on 1/8/2016.
 */
public class HiPalMessengerApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this.getApplicationContext();
        Firebase.setAndroidContext(this);
    }

    public static Context getContext(){
        return context;
    }

}



