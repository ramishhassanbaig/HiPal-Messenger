package com.example.ramish.hipal_messenger.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import com.example.ramish.hipal_messenger.activity.HomeActivity;
import com.example.ramish.hipal_messenger.firebase.FirebaseHandler;
import com.example.ramish.hipal_messenger.model.User;
import com.example.ramish.hipal_messenger.utils.Util;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by ramish on 1/22/2016.
 */
public class UserCreationService {

    private static User currentUser;
    private static User loggedInUser;

    public static void setLoggedInUser(User loggedInUser) {
        UserCreationService.loggedInUser = loggedInUser;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void createAuthenticatedUser(User user,final Activity activity, final ProgressDialog progressDialog){

        currentUser=user;
        FirebaseHandler.getInstance().getRootRef().createUser(currentUser.getEmail(),
                currentUser.getPassword(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        createFirebaseUser(currentUser);
                        progressDialog.setMessage("Signing in...");
                        loginAuthenticatedUser(currentUser.getEmail(),currentUser.getPassword(),progressDialog,activity);
//                        Intent i = new Intent(activity, HomeActivity.class);
//                        activity.startActivity(i);
                        Log.d("NewUserCreated", "Successfully created new user");
//                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Log.d("NewUserCreated", firebaseError.toString());
                        if (firebaseError.getCode() == -18) {
                            Util.showToast("user already exists");
                            progressDialog.dismiss();
                        }
                    }
                });
    }


    public static void createFirebaseUser(final User user){

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


    public static void loginAuthenticatedUser(final String email,String password, final ProgressDialog progressDialog,final Activity activity){

        FirebaseHandler.getInstance().getRootRef().authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
//                Log.d("Logging in", "User=" + authData.getProviderData().toString());
                Log.d("Logging in", "authData=" + authData.getAuth().toString());

                FirebaseHandler.getInstance().getUserRef().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            User specificUser = userSnapshot.getValue(User.class);
                            Log.d("FromFirebase_Iterating", "User=" + specificUser.getUserName());
                            if (email.equals(specificUser.getEmail())) {

                                Map<String, Object> changeOnlineStatus = new Hashtable<String, Object>();
                                changeOnlineStatus.put("online", true);
                                FirebaseHandler.getInstance().getUserRef().child(specificUser.getUserName())
                                        .updateChildren(changeOnlineStatus);

                                LocalUserService.setUserLocally(specificUser);
                                break;
                            }
                        }

                        Intent i = new Intent(activity, HomeActivity.class);
                        activity.startActivity(i);
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Log.d("FromFirebaseUser", firebaseError.toString());
                        Util.failureToast();
                    }
                });

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.d("Logging in", firebaseError.toString());
                if (firebaseError.getCode() == -15 || firebaseError.getCode() == -16 || firebaseError.getCode() == -17) {
                    Util.showToast("incorrect email or password");
                    progressDialog.dismiss();

                }
            }
        });

    }

}

