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
  private LayoutInflater mInflater;
    private ArrayList<Points> pointList;
    private int mViewResourceId;


    public ListAdapter(@NonNull Context context, int textviewResourceId, ArrayList<Points> pointList) {
        super(context, textviewResourceId, pointList);
        this.pointList = pointList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textviewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        convertView = mInflater.inflate(mViewResourceId,null);

        Points points = pointList.get(position);

        if (points != null){
            TextView tvPoints = (TextView) convertView.findViewById(R.id.tvPoints);
            TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            TextView tvResult = (TextView) convertView.findViewById(R.id.tvResult);

            if (tvPoints != null){
                tvPoints.setText(points.getPoints());
            }

            if(tvDate != null){
                tvDate.setText(points.getDate());
            }
            
             if(tvResult != null){
                tvDate.setText(points.getResult());
            }
        }
        return convertView;
    }
}
