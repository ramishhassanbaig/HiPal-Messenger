package com.example.ramish.hipal_messenger.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ramish.hipal_messenger.HiPalMessengerApp;
import com.example.ramish.hipal_messenger.firebase.FirebaseHandler;
import com.example.ramish.hipal_messenger.model.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by ramish on 2/5/2016.
 */
public class LocalUserService {

    public static void setUserLocally(User user){
        SharedPreferences sharedPref= HiPalMessengerApp.getContext()
                .getSharedPreferences("com.example.ramish.hipal_messenger.HiPalMessengerApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putString("USERNAME",user.getUserName());
        editor.putString("EMAIL",user.getEmail());
        editor.putInt("FRIENDS_COUNTER",user.getFriendCounter());
        editor.putInt("FRIEND_REQUEST_COUNTER",user.getFriendReqCounter());
        editor.putInt("FAVORITES_COUNTER",user.getFavoritesCounter());
        editor.putInt("NOTIFICATIONS_COUNTER",user.getNotificationCounter());
        editor.commit();
    }

    public static User getLocalUser(){
        User localUser;

        SharedPreferences sharedPreferences=HiPalMessengerApp.getContext()
                .getSharedPreferences("com.example.ramish.hipal_messenger.HiPalMessengerApp", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("USERNAME","Rob");
        String email=sharedPreferences.getString("EMAIL","rob@default.com");
        int fcnt=sharedPreferences.getInt("FRIENDS_COUNTER",10);
        int favcnt=sharedPreferences.getInt("FAVORITES_COUNTER",10);
        int freqcnt=sharedPreferences.getInt("FRIEND_REQUEST_COUNTER",10);
        int notcnt=sharedPreferences.getInt("NOTIFICATIONS_COUNTER",10);

        localUser=new User(name,email,fcnt,favcnt,freqcnt,notcnt);
        return localUser;
    }
}
