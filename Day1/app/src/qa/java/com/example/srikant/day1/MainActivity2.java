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
import android.widget.TextView;

import com.example.loginmodule.LoginManager;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ConstraintLayout cv = findViewById(R.id.mainConstraint);
        Button signInBtn = (Button) findViewById(R.id.signin);
        final TextInputEditText passwordET = (TextInputEditText) findViewById(R.id.password);
        final TextInputEditText usernameET = (TextInputEditText) findViewById(R.id.username);
        final LoginManager lmanager = new LoginManager();
        final TextView nextQuestion = (TextView)findViewById(R.id.nextQuestion);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = passwordET.getText().toString();
                String uname = usernameET.getText().toString();
                if (uname.equals("") || uname == null || pass.equals("") || pass == null) {
                    if (uname.equals("") || uname == null) {
                        usernameET.setError("Please Fill details");
                    } else {
                        passwordET.setError("Please fill details");
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
