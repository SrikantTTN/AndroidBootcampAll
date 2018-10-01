package com.example.srikant.day3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        TextView username = findViewById(R.id.username);
        TextView email = findViewById(R.id.email);
        TextView mobile = findViewById(R.id.mobile);
        TextView password = findViewById(R.id.password);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constants.BUNDLE_KEY);
        username.setText(bundle.getString(Constants.USERNAME_KEY));
        email.setText(bundle.getString(Constants.EMAILID_KEY));
        mobile.setText(bundle.getString(Constants.MOBILE_KEY));
        password.setText("Well you should remember your password it should not be printed here");
        final Button websiteBTN = findViewById(R.id.btn1);
        Button permissionBTN = findViewById(R.id.btn2);
        final EditText websiteET = findViewById(R.id.websiteURL);
        websiteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = websiteET.getText().toString();
                if(TextUtils.isEmpty(url)){
                    websiteET.setError("Please input an url");
                }else{
                    Intent urlIntent = new Intent();
                    urlIntent.setAction(Intent.ACTION_VIEW);
                    urlIntent.setData(Uri.parse(url));
                    urlIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    urlIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                    if(getPackageManager().queryIntentActivities( urlIntent,PackageManager.MATCH_DEFAULT_ONLY).size()<=0){
                        Toast.makeText(SecondScreen.this,"Enter Valid URL",Toast.LENGTH_SHORT).show();
                    }else {
                        startActivity(urlIntent);
                    }
                }
            }
        });
        permissionBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                        Intent cameraIntent = new Intent();
                        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(cameraIntent);
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.CAMERA},Constants.CAMERA_REQUEST_PERMISSION);
                    }
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==Constants.CAMERA_REQUEST_PERMISSION && grantResults!=null && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Intent cameraIntent = new Intent();
            cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(cameraIntent);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SecondActivity","onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SecondActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("SecondActivity","onPause");

    }
}
