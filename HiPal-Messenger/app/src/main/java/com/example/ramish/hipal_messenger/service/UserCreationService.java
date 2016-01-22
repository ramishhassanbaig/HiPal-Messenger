package com.example.ramish.hipal_messenger.service;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.ramish.hipal_messenger.activity.MainActivity;
import com.example.ramish.hipal_messenger.firebase.FirebaseHandler;
import com.example.ramish.hipal_messenger.fragments.SignupFragment;
import com.example.ramish.hipal_messenger.model.User;
import com.example.ramish.hipal_messenger.utils.Util;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by ramish on 1/22/2016.
 */
public class UserCreationService {

    private static User currentUser;

    public static void createAuthenticatedUser(User user){

        currentUser=user;

        FirebaseHandler.getInstance().getRootRef().createUser(currentUser.getEmail(),
                currentUser.getPassword(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        createFirebaseUser(currentUser);
                        Log.d("NewUserCreated", "Successfully created new user");
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Log.d("NewUserCreated",firebaseError.toString());
                    }
                });

    }


    public static void createFirebaseUser(User user){

        FirebaseHandler.getInstance().getUserRef().child(user.getUserName()).setValue(user, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError == null) {
                    Util.showToast("User Created Successfully");
                } else {
                    Util.showToast("User Creation Failed");
                }
            }
        });

    }

}
