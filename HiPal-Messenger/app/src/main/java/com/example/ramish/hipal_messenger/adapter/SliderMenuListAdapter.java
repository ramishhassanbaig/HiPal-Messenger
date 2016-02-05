package com.example.ramish.hipal_messenger.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramish.hipal_messenger.R;
import com.example.ramish.hipal_messenger.model.SliderMenuListItem;

import java.util.ArrayList;

/**
 * Created by ramish on 1/13/2016.
 */
public class SliderMenuListAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<SliderMenuListItem> sliderMenuListItems;

    public SliderMenuListAdapter(Context context,int resource,ArrayList<SliderMenuListItem> sliderMenuListItems){
        super(context,resource);
        this.context=context;
        this.sliderMenuListItems=sliderMenuListItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.slider_menu_list_item_layout,null);
        }

        ImageView icon=(ImageView)convertView.findViewById(R.id.list_item_icon);
        TextView title=(TextView)convertView.findViewById(R.id.list_item_title);
        TextView count=(TextView)convertView.findViewById(R.id.list_item_count);

        icon.setImageResource(sliderMenuListItems.get(position).getIcon());
        title.setText(sliderMenuListItems.get(position).getTitle());

        if (sliderMenuListItems.get(position).getCount()==0){
            sliderMenuListItems.get(position).setCountVisible(false);
        }

        if (sliderMenuListItems.get(position).isCountVisible()){
            sliderMenuListItems.get(position).setCountVisible(true);
            count.setText(String.valueOf(sliderMenuListItems.get(position).getCount()));
        }
        else count.setVisibility(View.GONE);



        return convertView;
    }

    @Override
    public int getCount() {
        return sliderMenuListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return sliderMenuListItems.get(position);
    }
}
