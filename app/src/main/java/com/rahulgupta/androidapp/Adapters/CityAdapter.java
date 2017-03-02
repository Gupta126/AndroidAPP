package com.rahulgupta.androidapp.Adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rahulgupta.androidapp.R;

import java.util.List;

/**
 * Created by Gupta on 02/03/17.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    private List<String> cityNameList;
    private OnAdapterInteractionListener onAdapterInteractionListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvCityName;

        public MyViewHolder(View view) {
            super(view);
            mTvCityName = (TextView) view.findViewById(R.id.cityNameTV);
        }
    }


    public CityAdapter(List<String> cityNameList,OnAdapterInteractionListener onAdapterInteractionListener) {
        this.cityNameList = cityNameList;
       this.onAdapterInteractionListener=onAdapterInteractionListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTvCityName.setText(cityNameList.get(position));
        holder.mTvCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdapterInteractionListener.onViewClick(cityNameList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cityNameList.size();
    }

    public interface OnAdapterInteractionListener {
        void onViewClick(String cityName);
    }
}