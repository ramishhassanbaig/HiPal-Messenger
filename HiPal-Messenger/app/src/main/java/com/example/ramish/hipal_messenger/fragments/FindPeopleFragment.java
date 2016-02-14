package com.example.ramish.hipal_messenger.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.adapter.PeopleFoundListAdapter;
import com.example.ramish.hipal_messenger.listener.ListenerService;
import com.example.ramish.hipal_messenger.model.User;
import com.example.ramish.hipal_messenger.service.FriendsService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindPeopleFragment extends Fragment {
    private EditText mSearchPeopleField;
    private ImageButton mSearchButton;
    private TextView mShowResult;
    private LinearLayout mSearchingLayout;
    private RelativeLayout mMessagesLayout;
    private ListView foundPeopleList;

    private PeopleFoundListAdapter foundListAdapter;



    public FindPeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView=inflater.inflate(R.layout.fragment_find_people, container, false);

        initializingView(rootView);

//        mMessagesLayout.setVisibility(View.GONE);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchString=mSearchPeopleField.getText().toString();
                if (!(searchString.equals(""))){
                    mShowResult.setVisibility(View.GONE);
                    mSearchingLayout.setVisibility(View.VISIBLE);
                    FriendsService.findPeopleFromFirebase(searchString,rootView, new ListenerService() {
                        @Override
                        public void passObject(User user) {

                        }

                        @Override
                        public void passArrayList(ArrayList<User> userArrayList) {

                            mMessagesLayout.setVisibility(View.GONE);
                            foundPeopleList.setVisibility(View.VISIBLE);
                            foundListAdapter=new PeopleFoundListAdapter(getContext(),R.id.people_found_list_view,userArrayList);
                            foundPeopleList.setAdapter(foundListAdapter);

                            Log.d("FromAdapter","Count="+foundListAdapter.getCount());

                            mSearchingLayout.setVisibility(View.GONE);
                            mShowResult.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });





        // Inflate the layout for this fragment
        return rootView;
    }

    private void initializingView(View v){
        mSearchPeopleField=(EditText)v.findViewById(R.id.search_people);
        mSearchButton=(ImageButton)v.findViewById(R.id.search_button);
        mShowResult=(TextView)v.findViewById(R.id.search_result_message);
        mSearchingLayout=(LinearLayout)v.findViewById(R.id.search_progress_layout);
        mMessagesLayout=(RelativeLayout)v.findViewById(R.id.messages_layout);
        foundPeopleList=(ListView)v.findViewById(R.id.people_found_list_view);
    }

}
