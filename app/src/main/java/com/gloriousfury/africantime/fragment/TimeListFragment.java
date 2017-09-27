package com.gloriousfury.africantime.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gloriousfury.africantime.R;
import com.gloriousfury.africantime.adapter.TimeListAdapter;
import com.gloriousfury.africantime.model.TimeView;
import com.gloriousfury.africantime.utils.DataUtil;

import java.util.ArrayList;


/**
 * Created by OLORIAKE KEHINDE on 11/16/2016.
 */

public class TimeListFragment extends Fragment {


    public TimeListFragment() {

    }

    public static TimeListFragment newInstance() {
        TimeListFragment fragment = new TimeListFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView recyclerView;
    ArrayList<TimeView> timeArray;


    boolean serviceBound = false;
    Cursor cursor;
    private static final int TASK_LOADER_ID = 0;

    public AlphabetIndexer mAlphabetIndexer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_general, container, false);
        initializeViews(v);
        return v;
    }


    private void initializeViews(View v) {
        DataUtil data = new DataUtil(getContext());


        timeArray = data.prepareData();

        TimeListAdapter feedAdapter = new TimeListAdapter(getContext(), timeArray);


        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(feedAdapter);


    }

}