package com.example.ramish.hipal_messenger.service;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.activity.HomeActivity;
import com.example.ramish.hipal_messenger.firebase.FirebaseHandler;
import com.example.ramish.hipal_messenger.listener.ListenerService;
import com.example.ramish.hipal_messenger.listener.PassingService;
import com.example.ramish.hipal_messenger.model.FriendRequest;
import com.example.ramish.hipal_messenger.model.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by ramish on 2/6/2016.
 */
public class FriendsService {

    public static void findPeopleFromFirebase(final String name,View view, final ListenerService listenerService){

        final LinearLayout searching=(LinearLayout)view.findViewById(R.id.search_progress_layout);
        final TextView result=(TextView)view.findViewById(R.id.search_result_message);
        final RelativeLayout messagesLayout=(RelativeLayout)view.findViewById(R.id.messages_layout);
        final ListView peopleListView=(ListView)view.findViewById(R.id.people_found_list_view);
        searching.setVisibility(View.VISIBLE);

        FirebaseHandler.getInstance().getUserRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int cnt=1;
                ArrayList<User> firebaseUserList=new ArrayList<User>();

                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    User specificUser=userSnapshot.getValue(User.class);
                    if ((name.equals(specificUser.getUserName()) || specificUser.getUserName().contains(name))
                                && (!(specificUser.getUserName().equals(HomeActivity.getCurrentLoggedInUser().getUserName()))) ){

                        firebaseUserList.add(specificUser);
//                        listenerService.passObject(specificUser);
                        Log.d("FromFindPeopleService", "User " + cnt + " Found=" + specificUser.getUserName());
                        cnt++;
                    }
                    else {
                        peopleListView.setVisibility(View.GONE);
                        messagesLayout.setVisibility(View.VISIBLE);
                        searching.setVisibility(View.GONE);
                        result.setVisibility(View.VISIBLE);
                    }
                }

                Log.d("FromFindPeople","ListSizeOutsideIf="+firebaseUserList.size());
                if (firebaseUserList.size()>=1){
                    listenerService.passArrayList(firebaseUserList);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("FromFindPeopleService",firebaseError.toString());
            }
        });
    }

    public static void SendFriendRequestService(String username,String friendName){
        Firebase userRef=FirebaseHandler.getInstance().getFriendReqRef().child(username);
        Firebase friendRef=FirebaseHandler.getInstance().getFriendReqRef().child(friendName);

        FriendRequest userReq=new FriendRequest(username,false);
        FriendRequest friendReq=new FriendRequest(friendName,false);

        userRef.child("ReqTo").push().setValue(friendReq);
        friendRef.child("ReqFrom").push().setValue(userReq);
    }

    public static void getAllFriendRequests(View v,final PassingService<FriendRequest> listener){

        final TextView resultMessage=(TextView)v.findViewById(R.id.message);
        final ListView requestsListView=(ListView)v.findViewById(R.id.friend_request_list_view);
        final ArrayList<FriendRequest> requests=new ArrayList<>();
        Firebase ref=FirebaseHandler.getInstance().getFriendReqRef()
                .child(HomeActivity.getCurrentLoggedInUser().getUserName()).child("ReqFrom");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    FriendRequest specificRequests=userSnapshot.getValue(FriendRequest.class);
                    requests.add(specificRequests);
                }
                if (requests.size()>=1) {
                    listener.passArrayList(requests);
                }
                else{
                    resultMessage.setVisibility(View.VISIBLE);
                    requestsListView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("GetFriendsService",firebaseError.toString());
            }
        });

    }
}
