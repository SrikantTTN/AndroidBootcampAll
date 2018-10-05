package com.example.srikant.day4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class FilesActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et;
    Button writeI;
    Button readI;
    Button writeE;
    Button readE;
    TextView tv;
    Button nextE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        et = findViewById(R.id.edittext);
        tv = findViewById(R.id.textview);
        writeI = findViewById(R.id.writeInternalFile);
        readI = findViewById(R.id.readInternalFile);
        writeE = findViewById(R.id.writeExternalFile);
        readE = findViewById(R.id.readExternalFile);
        nextE = findViewById(R.id.nextExercise);
        writeE.setOnClickListener(this);
        writeI.setOnClickListener(this);
        readI.setOnClickListener(this);
        readE.setOnClickListener(this);
        nextE.setOnClickListener(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            writeInExternalFile();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.writeInternalFile:
                String text = et.getText().toString();
                if(TextUtils.isEmpty(text)){
                    et.setError("Empty");
                }
                else{
                    writeInInternalFile(text);
                }
                return ;
            case R.id.readInternalFile:
                String data = readInternalFile();
                et.setText(data);
                return ;
            case R.id.writeExternalFile:
                String text1 = et.getText().toString();
                if(!TextUtils.isEmpty(text1)) {
                    if (!requestPermissionIfRequired(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        writeInExternalFile();
                    }
                }else{
                    et.setError("Empty");
                }
                return ;
            case R.id.readExternalFile:
                data = readExternalFile();
                et.setText(data);
                return ;
            case R.id.nextExercise:
                Intent intent = new Intent();
                intent.setClass(this,SqlActivity.class);
                startActivity(intent);
                return;
        }
    }



    private boolean requestPermissionIfRequired(String permissions){
        int i=0;
        boolean toR = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(permissions)!= PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{permissions},100);
                    toR = true;
                }
            }
        return toR;
    }
    private void writeInInternalFile(String text){
        String fileName = "myText";
        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput(fileName,MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
            et.setText(null);
            Log.d("FilesActivity","Success");
        }catch (Exception e){
            Log.e("FilesActivity",e.getMessage());
            e.printStackTrace();
        }
    }
    private void writeInExternalFile() {
        String text = et.getText().toString();
        String fileName = "myText";
        File file = new File(Environment.getExternalStorageDirectory() ,getString(R.string.app_name));
        if(!file.exists() && !file.mkdirs()){
            Log.e("FilesActivity", "Not Created");
        } else {
            File file1 = new File(file, fileName);
            try {
                FileOutputStream fileWriter = new FileOutputStream(file1);
                fileWriter.write(text.getBytes());
                fileWriter.close();
            } catch (Exception e) {
                Log.e("FilesActivity", "Not Created");
                e.printStackTrace();
            }
        }
        et.setText("");
    }

    private String readExternalFile() {
        String fileName = "myText";
        File file = new File(Environment.getExternalStorageDirectory()+"/"+getString(R.string.app_name),fileName);
        FileReader inputStream;
        String temp = "";
        try {
            inputStream = new FileReader(file);
            int c;
            while ((c = inputStream.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            Log.d("FilesActivity", "Success");
            inputStream.close();
        } catch (Exception e) {
            Toast.makeText(this, "File was missing", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return temp;
    }

        private String readInternalFile(){
        String fileName = "myText";
        FileInputStream inputStream;
        String temp="";
        try{
            inputStream = openFileInput(fileName);
            int c;
            while( (c = inputStream.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            Log.d("FilesActivity","Success");
            inputStream.close();
        }catch (Exception e){
            Toast.makeText(this,"File was missing",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return temp;
    }

}
