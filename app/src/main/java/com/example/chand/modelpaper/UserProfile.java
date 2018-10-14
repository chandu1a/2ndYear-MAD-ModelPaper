package com.example.chand.modelpaper;

import android.provider.BaseColumns;

public final class UserProfile {
    private UserProfile(){}

    public static final class Users implements BaseColumns{
        public static final String TABLE_NAME = "UserInfo";
        public static final String ID_COL = "ID";
        public static final String USERNAME_COL = "userName";
        public static final String DOB_COL = "dateOfBirth";
        public static final String GENDER_COL = "gender";
        public static final String PASSWORD_COL = "password";


    }
}
