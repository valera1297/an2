package com.example.valera;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SelectRecyclerViewAdapter extends
        RecyclerView.Adapter<SelectRecyclerViewAdapter.SelectViewHolder> {
    private List<String> mDataset;


    public static class SelectViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public SelectViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.count_name_rv_select);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Click", "on item click");
                }
            });
        }
    }

    public SelectRecyclerViewAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public SelectRecyclerViewAdapter.SelectViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_select_country, parent, false);

        SelectViewHolder vh = new SelectViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(SelectViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position));

        if (gg.recyclerData.get(position).length() < 6) {
            holder.mTextView.setTextColor(Color.RED);
        } else if (gg.recyclerData.get(position).length() > 7) {
            holder.mTextView.setTextColor(Color.GREEN);
        } else {
            holder.mTextView.setTextColor(Color.BLUE);
        }
    }
    @Override
    public int getItemCount() {
        return gg.recyclerData.size();
    }
}
