package com.zhanjixun.adapter;

import java.util.List;
import java.util.Map;

import com.zhanjixun.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ResourceAsColor") public class TimelineAdapter extends BaseAdapter {

    private List<Map<String, Object>> list;
    private LayoutInflater inflater;

    public TimelineAdapter(Context context, List<Map<String, Object>> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor") @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_logistics_information, null);
            viewHolder = new ViewHolder();
            viewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.view2 = convertView.findViewById(R.id.view_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.time.setText(list.get(position).get("time").toString());
        viewHolder.title.setText(list.get(position).get("title").toString());
        if (position != 0) {
        	viewHolder.image.setImageResource(R.drawable.timeline_content);
        } else {
        	viewHolder.image.setImageResource(R.drawable.timeline_orange);
        	viewHolder.title.setTextColor(R.color.orange);
        	viewHolder.time.setTextColor(R.color.orange);
        } 
        if (position == list.size()-1) {
        	viewHolder.view2.setVisibility(View.INVISIBLE);
        }
        
        return convertView;
    }

    static class ViewHolder {
        public TextView time;
        public TextView title;
        public ImageView image;
        public View view2; 
    }
}
