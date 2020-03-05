package com.example.mobileappgroup8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Date;

/**
 * created by Kerttuli 5.3.2020
 * To manage the ArrayAdapter
 */


public class ListAdapter extends ArrayAdapter<Points> {
    private static final String TAG = "ListAdapter";
    private Context mContext;
    int mResource;

    public ListAdapter(@NonNull Context context, int resource, ArrayList<Points> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String points = getItem(position).getPoints();
        Date date = getItem(position).getDate();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        tvDate.setText(date.toString());

        return convertView;
    }
}
