package com.example.ramish.hipal_messenger;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.ramish.hipal_messenger.model.User;
import com.firebase.client.Firebase;

/**
 * Created by ramish on 1/8/2016.
 */
public class HiPalMessengerApp extends Application {
    private static Context context;
    private static User applicationUser;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this.getApplicationContext();
        Firebase.setAndroidContext(this);
    }

    public static User getAppUserInstance(){
        if (applicationUser==null){
            applicationUser=new User();
        }
        return applicationUser;
    }

    public static Context getContext(){
        return context;
    }

    public static User getApplicationUser() {
        return applicationUser;
    }

    public static void setApplicationUser(User applicationUser) {
        HiPalMessengerApp.applicationUser = applicationUser;
    }
}



