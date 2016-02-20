package com.example.ramish.hipal_messenger.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.activity.HomeActivity;
import com.example.ramish.hipal_messenger.adapter.FriendRequestListAdapter;
import com.example.ramish.hipal_messenger.firebase.FirebaseHandler;
import com.example.ramish.hipal_messenger.listener.PassingService;
import com.example.ramish.hipal_messenger.model.FriendRequest;
import com.example.ramish.hipal_messenger.service.FriendsService;
import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendRequestFragment extends Fragment {

    private TextView resultMessage;
    private ListView requestsListView;
    private FriendRequestListAdapter adapter;
    private ArrayList<FriendRequest> friendRequests;

    public FriendRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_friend_request, container, false);

        resultMessage=(TextView)rootView.findViewById(R.id.message);
        requestsListView=(ListView)rootView.findViewById(R.id.friend_request_list_view);

        FriendsService.getAllFriendRequests(rootView,new PassingService<FriendRequest>() {
            @Override
            public void passArrayList(ArrayList<FriendRequest> obj) {
                friendRequests=obj;

                if (friendRequests.size()>=1) {
                    for (int i=0;i<friendRequests.size();i++){
                        Log.d("FriendRequestFragment","Name="+friendRequests.get(i).getName());
                    }
                    requestsListView.setVisibility(View.VISIBLE);
                    resultMessage.setVisibility(View.GONE);
                    adapter = new FriendRequestListAdapter(getContext(), R.layout.friend_request_list_item_layout, friendRequests);
                    requestsListView.setAdapter(adapter);
                }
                else {
                    resultMessage.setVisibility(View.VISIBLE);
                    requestsListView.setVisibility(View.GONE);
                }

//                Log.d("FromRequestAdapter","Count="+adapter.getCount());
            }
        });



        // Inflate the layout for this fragment
        return rootView;
    }

}
