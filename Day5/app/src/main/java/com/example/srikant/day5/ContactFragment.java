package com.example.srikant.day5;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ContactFragment extends Fragment implements View.OnClickListener {
    Context mContext;
    FetchCallback mFetchCallback;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mFetchCallback = (FetchCallback) mContext;
    }

    @Override
    public void onClick(View v) {
        mFetchCallback.onClick(v);
    }

    public interface FetchCallback{
        void onClick(View v);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_frag,container,false);
        Button readContact = rootView.findViewById(R.id.readC);
        Button addContact = rootView.findViewById(R.id.addC);
        Button deleteContact = rootView.findViewById(R.id.deleteC);

        readContact.setOnClickListener(this);
        addContact.setOnClickListener(this);
        deleteContact.setOnClickListener(this);
        return rootView;
    }

}
