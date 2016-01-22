package com.example.ramish.hipal_messenger.firebase;

import com.firebase.client.Firebase;

/**
 * Created by ramish on 9/15/2015.
 */
public class FirebaseHandler {
    private static FirebaseHandler instance;
    private static final String URL="https://hipal.firebaseio.com/";
    private Firebase rootRef;
    private Firebase userRef;

    public FirebaseHandler(){
        rootRef =new Firebase(URL);
    }

    public static FirebaseHandler getInstance(){
        if (instance==null){
            return instance=new FirebaseHandler();
        }
        return instance;
    }




    public Firebase getRootRef() {
        return rootRef;
    }

    public Firebase getUserRef() {
        return userRef=rootRef.child("Users");
    }


}
