package com.example.srikant.day3;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextInputEditText usernameET = findViewById(R.id.username);
        final TextInputEditText emailET = findViewById(R.id.email);
        final TextInputEditText mobileET = findViewById(R.id.mobile);
        final TextInputEditText passwordET = findViewById(R.id.password);
        Button signUp = findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = usernameET.getText().toString();
                String email = emailET.getText().toString();
                String mobileString = mobileET.getText().toString();
                String password = passwordET.getText().toString();
                if(TextUtils.isEmpty(uname)){
                    usernameET.setError("Username can't be empty");
                }
                else if(TextUtils.isEmpty(email)){
                    emailET.setError("Email can't be empty");
                }
                else if(TextUtils.isEmpty(mobileString)){
                    mobileET.setError("Mobile can't be empty");
                }
                else if(TextUtils.isEmpty(password)){
                    passwordET.setError("Username can't be empty");
                }
                else if (mobileString.length()>10 || mobileString.length()<10){
                    mobileET.setError("Enter valid mobile number");
                }
                else{
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.USERNAME_KEY,uname);
                    bundle.putString(Constants.EMAILID_KEY,email);
                    bundle.putString(Constants.MOBILE_KEY,mobileString);
                    Intent intent = new Intent();
                    intent.putExtra(Constants.BUNDLE_KEY,bundle);
                    intent.setClass(MainActivity.this,SecondScreen.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause");

    }
}
