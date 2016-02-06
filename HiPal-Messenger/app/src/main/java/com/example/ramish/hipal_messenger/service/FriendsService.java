package com.example.ramish.hipal_messenger.service;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.firebase.FirebaseHandler;
import com.example.ramish.hipal_messenger.listener.ListenerService;
import com.example.ramish.hipal_messenger.model.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by ramish on 2/6/2016.
 */
public class FriendsService {

    public static void findPeopleFromFirebase(final String name,View view, final ListenerService listenerService){

        final LinearLayout searching=(LinearLayout)view.findViewById(R.id.search_progress_layout);
        final TextView result=(TextView)view.findViewById(R.id.search_result_message);
        searching.setVisibility(View.VISIBLE);

        FirebaseHandler.getInstance().getUserRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    User specificUser=userSnapshot.getValue(User.class);
                    if (name.equals(specificUser.getUserName())){
                        listenerService.passObject(specificUser);
                        Log.d("FromFindPeopleService","User Found="+specificUser.getUserName());
                        break;
                    }
                    else {
                        searching.setVisibility(View.GONE);
                        result.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("FromFindPeopleService",firebaseError.toString());
            }
        });
    }
}
