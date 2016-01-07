package com.example.ramish.hipal_messenger.firebase;

import com.firebase.client.Firebase;

/**
 * Created by ramish on 9/15/2015.
 */
public class FirebaseHandler {
    private static final String URL="https://hipal.firebaseio.com/";

    Firebase ref=new Firebase(URL);

    public Firebase getRef() {
        return ref;
    }



}
