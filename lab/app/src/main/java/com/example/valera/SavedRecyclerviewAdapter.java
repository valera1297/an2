package com.example.valera;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SavedRecyclerviewAdapter extends
        RecyclerView.Adapter<SavedRecyclerviewAdapter.SavedViewHolder> {
    private List<String> mDataset;


    public static class SavedViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public SavedViewHolder(View v) {
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

    public SavedRecyclerviewAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public SavedRecyclerviewAdapter.SavedViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_select_country, parent, false);

        SavedRecyclerviewAdapter.SavedViewHolder vh = new SavedRecyclerviewAdapter.SavedViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(SavedRecyclerviewAdapter.SavedViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position));

    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
