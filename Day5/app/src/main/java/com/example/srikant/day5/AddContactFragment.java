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
import android.widget.EditText;

public class AddContactFragment extends Fragment implements View.OnClickListener{

    EditText cName;
    EditText cNumber;
    Button addC;
    AddContactCallback addContactCallback;

    @Override
    public void onClick(View v) {
        addContactCallback.onAddButtonClick(cName.getText().toString(), cNumber.getText().toString());
    }

    interface AddContactCallback{
        void onAddButtonClick(String cName, String cNumber);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_contact_frag,container,false);
        cName = view.findViewById(R.id.contact_name);
        cNumber = view.findViewById(R.id.contact_number);
        addC = view.findViewById(R.id.add_contact);
        addC.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addContactCallback = (AddContactCallback) context;
    }
}
