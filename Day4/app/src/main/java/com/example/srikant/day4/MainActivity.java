package com.example.srikant.day4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences pref;
    EditText et;
    Button write;
    Button read;
    Button next;
    Button clear;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        write = findViewById(R.id.writeSharedPref);
        read = findViewById(R.id.readSharedPref);
        next = findViewById(R.id.nextExercise);
        clear = findViewById(R.id.clearSharedPref);
        et = findViewById(R.id.edittext);
        tv = findViewById(R.id.textview);
        pref = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        write.setOnClickListener(this);
        read.setOnClickListener(this);
        next.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.writeSharedPref:
                String textToStore = et.getText().toString();
                if(TextUtils.isEmpty(textToStore)){
                    et.setError("Empty");
                }else {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(SharedPrefKeys.EDITTEXT_KEY, textToStore);
                    editor.commit();
                    et.setText("");
                }
                return;
            case R.id.readSharedPref:
                et.setText("");
                tv.setVisibility(View.GONE);
                try{
                    String readText = pref.getString(SharedPrefKeys.EDITTEXT_KEY,null);
                    if(readText==null || readText.equals("")){
                        Toast.makeText(this,"Nothing was written",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        tv.setVisibility(View.VISIBLE);
                        tv.setText(readText);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return;
            case R.id.nextExercise:
                Intent intent = new Intent();
                intent.setClass(this,FilesActivity.class);
                startActivity(intent);
                return;
            case R.id.clearSharedPref:
                pref.edit().clear().apply();
                return;
        }
    }
}
