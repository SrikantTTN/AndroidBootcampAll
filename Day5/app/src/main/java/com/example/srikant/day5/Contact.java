package com.example.srikant.day5;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    Contact(){

    }

    protected Contact(Parcel in) {
        name = in.readString();
        number = in.readString();
        id = in.readLong();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setId(long id){this.id = id;}

    public long getId(){return id;}

    private String name;
    private String number;
    private long id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
        dest.writeLong(id);
    }
}
