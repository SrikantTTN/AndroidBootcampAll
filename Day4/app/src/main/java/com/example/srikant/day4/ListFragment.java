package com.example.srikant.day4;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListFragment extends Fragment{
    RecyclerView dataList;
    ArrayList<Person> mList;
    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mList = args.getParcelableArrayList("personList");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_frag,container,false);
        dataList = view.findViewById(R.id.dataList);
        PersonAdapter adapter = new PersonAdapter(mContext,mList);
        dataList.setLayoutManager(new LinearLayoutManager(mContext));
        dataList.setAdapter(adapter);
        return view;
    }
}
