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

public class DatabaseFragment extends Fragment implements View.OnClickListener{
    EditText name;
    EditText email;
    EditText age;
    Button insert;
    Button query;
    Context mContext;
    DatabaseCallback mCallback;
    public interface DatabaseCallback{
        void onDClick(View v);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mCallback = (DatabaseCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sql_frag,container,false);
        name = rootView.findViewById(R.id.personName);
        email = rootView.findViewById(R.id.personEmail);
        age = rootView.findViewById(R.id.personAge);
        insert = rootView.findViewById(R.id.insert);
        query = rootView.findViewById(R.id.query);
        insert.setOnClickListener(this);
        query.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        mCallback.onDClick(v);
    }

    protected Person returnData(){
        String nameS = name.getText().toString();
        String emailS = email.getText().toString();
        String ageS = age.getText().toString();
        Person p = null;
        if(TextUtils.isEmpty(nameS)){
            name.setError("Empty");
        }else if (TextUtils.isEmpty(emailS)){
            email.setError("Empty");
        }else if(TextUtils.isEmpty(ageS)){
            age.setError("Empty");
        }
        else{
            p = new Person();
            p.setName(nameS);
            p.setEmail(emailS);
            p.setAge(Integer.parseInt(ageS));
            name.setText(null);
            email.setText(null);
            age.setText(null);
        }
        return p;
    }
}
