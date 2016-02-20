package com.example.ramish.hipal_messenger.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.activity.HomeActivity;
import com.example.ramish.hipal_messenger.firebase.FirebaseHandler;
import com.example.ramish.hipal_messenger.model.FriendRequest;
import com.example.ramish.hipal_messenger.model.User;
import com.example.ramish.hipal_messenger.service.FriendsService;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by ramish on 2/14/2016.
 */
public class PeopleFoundListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<User> userArrayList;

    public PeopleFoundListAdapter(Context context,int resource,ArrayList<User> userArrayList){
        super(context,resource);
        this.context=context;
        this.userArrayList=userArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.people_found_list_item_layout,null);
        }

        final TextView foundUsername=(TextView)convertView.findViewById(R.id.people_found_username);
        final Button sendFriendRequestButton=(Button)convertView.findViewById(R.id.send_friend_request_button);

        foundUsername.setText(userArrayList.get(position).getUserName());

        Firebase ref=FirebaseHandler.getInstance().getFriendReqRef().child(HomeActivity.getCurrentLoggedInUser().getUserName()).child("ReqTo");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot reqSnapshot: dataSnapshot.getChildren()){
                    FriendRequest specificUserNames=reqSnapshot.getValue(FriendRequest.class);

                    if (specificUserNames.getName().equals(userArrayList.get(position).getUserName())){
                        sendFriendRequestButton.setText("Request Sent");
                        sendFriendRequestButton.setBackgroundResource(R.drawable.button_background_disabled);
                        sendFriendRequestButton.setEnabled(false);

                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        sendFriendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friendName=userArrayList.get(position).getUserName();
                FriendsService.SendFriendRequestService(HomeActivity.getCurrentLoggedInUser().getUserName(),friendName);
//                userArrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return userArrayList.size();
    }
}
