package com.example.srikant.day4;

import android.provider.BaseColumns;

public class PersonContract {
        private PersonContract() {}

        /* Inner class that defines the table contents */
        public static class PersonEntry implements BaseColumns {
            public static final String TABLE_NAME = "Person";
            public static final String COLUMN_NAME_NAME= "name";
            public static final String COLUMN_NAME_EMAIL = "email";
            public static final String COLUMN_NAME_AGE = "age";
        }
    }
