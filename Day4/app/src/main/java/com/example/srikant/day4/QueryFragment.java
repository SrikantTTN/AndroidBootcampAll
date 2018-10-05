package com.example.srikant.day4;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class QueryFragment extends Fragment implements View.OnClickListener{
    EditText name;
    Button queryByName;
    QueryCallback mCallback;

    @Override
    public void onClick(View v) {
        mCallback.onQClick(v);
    }

    public interface QueryCallback{
        void onQClick(View v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (QueryCallback)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.query_frag,container,false);
        name = rootView.findViewById(R.id.personName);
        queryByName = rootView.findViewById(R.id.queryBy);
        queryByName.setOnClickListener(this);
        return rootView;
    }

    protected String returnQueryParameter(){
        String nameS = name.getText().toString();
        if(TextUtils.isEmpty(nameS)) {
            name.setError("Empty");
        }else{
            name.setText(null);
            return nameS;
        }
        return null;
    }
}
