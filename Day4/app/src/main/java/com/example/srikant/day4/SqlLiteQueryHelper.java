package com.example.srikant.day4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SqlLiteQueryHelper {
    private SqlLiteDatabase sqlLiteDatabase;
    private SqlLiteQueryHelper(Context context){
        sqlLiteDatabase = new SqlLiteDatabase(context);
    }

    static SqlLiteQueryHelper instance;


    public static SqlLiteQueryHelper getInstance(Context context){
        if(instance==null){
            instance = new SqlLiteQueryHelper(context);
        }
        return instance;
    }
    public long insertIntoTable(Person p){
        SQLiteDatabase db = sqlLiteDatabase.getWritableDatabase();
        String name = p.getName();
        String email = p.getEmail();
        int age = p.getAge();
        ContentValues values = new ContentValues();
        values.put(PersonContract.PersonEntry.COLUMN_NAME_NAME, name);
        values.put(PersonContract.PersonEntry.COLUMN_NAME_EMAIL, email);
        values.put(PersonContract.PersonEntry.COLUMN_NAME_AGE, age);
        long id = db.insert(PersonContract.PersonEntry.TABLE_NAME,null,values);
        return id;
    }

    public ArrayList<Person> queryDatabase(String q){
        String[] projection = {
                PersonContract.PersonEntry.COLUMN_NAME_NAME,
                PersonContract.PersonEntry.COLUMN_NAME_EMAIL,
                PersonContract.PersonEntry.COLUMN_NAME_AGE,
        };

        String selection = PersonContract.PersonEntry.COLUMN_NAME_NAME + " like ?";
        String[] selectionArgs = { "%"+q+"%" };

        SQLiteDatabase db = sqlLiteDatabase.getReadableDatabase();
        Cursor cursor = db.query(
                PersonContract.PersonEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                //sort Order
        );

        ArrayList<Person> list = new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(PersonContract.PersonEntry.COLUMN_NAME_NAME));
            String email = cursor.getString(
                    cursor.getColumnIndexOrThrow(PersonContract.PersonEntry.COLUMN_NAME_EMAIL));
            int age = cursor.getInt(
                    cursor.getColumnIndexOrThrow(PersonContract.PersonEntry.COLUMN_NAME_AGE));
            Person p = new Person();
            p.setName(name);
            p.setEmail(email);
            p.setAge(age);
            list.add(p);
        }
        cursor.close();
        return list;
    }
}
