package com.example.srikant.day1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.loginmodule.LoginManager;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ConstraintLayout cv = findViewById(R.id.mainConstraint);
        Button signInBtn = (Button) findViewById(R.id.signin);
        final TextInputEditText password = (TextInputEditText) findViewById(R.id.password);
        final TextInputEditText username = (TextInputEditText) findViewById(R.id.username);
        final LoginManager lmanager = new LoginManager();

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = password.getText().toString();
                String uname = username.getText().toString();
                if (uname.equals("") || uname == null || pass.equals("") || pass == null) {
                    if (pass.equals("") || pass == null) {
                        password.setError("Please Fill details");
                    } else {
                        password.setError("Please fill details");
                    }
                }
                else {
                    hideKeyboard(MainActivity2.this);
                    lmanager.setPass(pass);
                    lmanager.setUname(uname);
                    Snackbar.make(cv,lmanager.validateLogin(),Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}