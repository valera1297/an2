package com.example.valera;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectCountryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context ctx;
    private RecyclerView.Adapter mAdapter;
    private SwipeRefreshLayout mySwipeRefreshLayout;


    public SelectCountryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_country, container, false);

        ctx = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.select_country_rv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(ctx);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SelectRecyclerViewAdapter(gg.recyclerData);

        mRecyclerView.setAdapter(mAdapter);


        mySwipeRefreshLayout = view.findViewById(R.id.mySwipeRefreshLayout);
        mySwipeRefreshLayout.setOnRefreshListener(new
          SwipeRefreshLayout.OnRefreshListener() {
              @Override
              public void onRefresh() {
                  MyAsyncTask myAsyncTask = new MyAsyncTask();
                  myAsyncTask.execute("InputString");
                  mySwipeRefreshLayout.setRefreshing(false);
              }
          });

        return view;
    }

}
