package com.example.ramish.hipal_messenger.listener;

import com.example.ramish.hipal_messenger.model.User;

import java.util.ArrayList;

/**
 * Created by ramish on 2/6/2016.
 */
public interface ListenerService {
    void passObject(User user);
    void passArrayList(ArrayList<User> userArrayList);
}
