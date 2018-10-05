package com.example.srikant.day4;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class SqlActivity extends AppCompatActivity implements DatabaseFragment.DatabaseCallback,QueryFragment.QueryCallback {
    FrameLayout frame ;
    DatabaseFragment dbFrag;
    QueryFragment queryFrag;
    ListFragment listFrag;
    SqlLiteQueryHelper queryHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        frame = findViewById(R.id.fragContainer);
        dbFrag = new DatabaseFragment();
        queryFrag = new QueryFragment();
        listFrag = new ListFragment();
        queryHelper = SqlLiteQueryHelper.getInstance(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragContainer,dbFrag,"DatabaseFragment");
        ft.addToBackStack("d-q");
        ft.commit();
    }

    @Override
    public void onDClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                Person p = dbFrag.returnData();
                if (p != null) {
                    long id = queryHelper.insertIntoTable(p);
                    Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
                }
                return;
            case R.id.query:
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().addToBackStack("q-l")
                        .replace(R.id.fragContainer, queryFrag, "QueryFragment")
                        .commit();
                return;
        }
    }

    @Override
    public void onQClick(View v) {
        String parameter = queryFrag.returnQueryParameter();
        ArrayList<Person> list;
        if(parameter!=null){
            list = queryHelper.queryDatabase(parameter);
            Bundle args = new Bundle();
            args.putParcelableArrayList("personList",list);
            listFrag.setArguments(args);
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStack();
            fm.beginTransaction().addToBackStack("n")
                    .replace(R.id.fragContainer,listFrag)
                    .commit();
        }
    }

}
