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
import com.example.ramish.hipal_messenger.model.User;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.people_found_list_item_layout,null);
        }

        TextView foundUsername=(TextView)convertView.findViewById(R.id.people_found_username);
        Button sendFriendRequestButton=(Button)convertView.findViewById(R.id.send_friend_request_button);

        foundUsername.setText(userArrayList.get(position).getUserName());



        return convertView;
    }

    @Override
    public int getCount() {
        return userArrayList.size();
    }
}
