package com.example.myapplication.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class CategoryTwoViewHolder extends RecyclerView.ViewHolder{

    public TextView dataName;
    public TextView dataId;
    public TextView dataAge;

    public CategoryTwoViewHolder(@NonNull View itemView) {
        super(itemView);

        dataId = itemView.findViewById(R.id.data_id);
        dataName= itemView.findViewById(R.id.data_name);
        dataAge = itemView.findViewById(R.id.data_name);

    }


}