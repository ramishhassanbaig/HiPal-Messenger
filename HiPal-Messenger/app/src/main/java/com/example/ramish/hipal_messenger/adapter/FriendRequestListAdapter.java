package com.example.ramish.hipal_messenger.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by ramish on 2/20/2016.
 */
public class FriendRequestListAdapter extends ArrayAdapter<FriendRequest> {

    private TextView username;
    private Button acceptButton;
    private Button declineButton;
    private ArrayList<FriendRequest> friendRequestArrayList;
    private Context context;

    public FriendRequestListAdapter(Context context,int resource,ArrayList<FriendRequest> friendRequestArrayList){
        super(context,resource);
        this.context=context;
        this.friendRequestArrayList=friendRequestArrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.friend_request_list_item_layout,null);
        }

        username=(TextView)convertView.findViewById(R.id.request_username);
        acceptButton=(Button)convertView.findViewById(R.id.accept_request_button);
        declineButton=(Button)convertView.findViewById(R.id.decline_request_button);

        for (int i=0;i<friendRequestArrayList.size();i++){
            Log.d("FriendRequestListAdapter","Name="+friendRequestArrayList.get(i).getName());
        }

        username.setText(friendRequestArrayList.get(position).getName());

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String friendName = friendRequestArrayList.get(position).getName();

                final Firebase userRef = FirebaseHandler.getInstance().getFriendReqRef()
                        .child(HomeActivity.getCurrentLoggedInUser().getUserName()).child("ReqFrom");

                final Firebase friendRef=FirebaseHandler.getInstance().getFriendReqRef().child(friendName).child("ReqTo");

                final Firebase usersFriendsRef=FirebaseHandler.getInstance().getFriendsRef().child(HomeActivity.getCurrentLoggedInUser().getUserName());
                final Firebase friendsFriendsRef=FirebaseHandler.getInstance().getFriendsRef().child(friendName);

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot reqSnapshot : dataSnapshot.getChildren()) {
                            String key = reqSnapshot.getKey();
                            FriendRequest specificReq = reqSnapshot.getValue(FriendRequest.class);

                            if (specificReq.getName().equals(friendName)) {
                                usersFriendsRef.push().setValue(friendName);

                                Log.d("FromAcceptButton(User)", "Key=" + key);
                                userRef.child(key).removeValue();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                friendRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot reqSnapshot : dataSnapshot.getChildren()) {
                            String key = reqSnapshot.getKey();
                            FriendRequest specificReq = reqSnapshot.getValue(FriendRequest.class);

                            if (specificReq.getName().equals(HomeActivity.getCurrentLoggedInUser().getUserName())) {
                                friendsFriendsRef.push().setValue(HomeActivity.getCurrentLoggedInUser().getUserName());

                                Log.d("FromAcceptButton(Friend)", "Key=" + key);
                                friendRef.child(key).removeValue();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                friendRequestArrayList.remove(position);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    @Override
    public int getCount() {
        return friendRequestArrayList.size();
    }
}
