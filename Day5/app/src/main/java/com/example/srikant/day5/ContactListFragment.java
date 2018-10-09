package com.example.srikant.day5;

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
import android.widget.Toast;

import java.util.ArrayList;


public class ContactListFragment extends Fragment {
    Context mContext;
    ArrayList<Contact> list;
    Boolean deletable = false;
    ContactAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = getArguments().getParcelableArrayList("ContactList");
        if(getArguments().getString("Deletable")!=null){
            deletable = true;
            Toast.makeText(mContext, "Touch the contact you want to delete", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_frag,container,false);
        RecyclerView recyclerView = rootView.findViewById(R.id.dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new ContactAdapter(mContext,list,deletable);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
