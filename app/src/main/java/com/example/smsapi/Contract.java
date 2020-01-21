package com.example.smsapi;

import android.provider.BaseColumns;

public class Contract {

    private Contract() {
    }

    public static final class Entry implements BaseColumns {
        public static final String TABLE_NAME = "demo";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_OTP = "otp";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
