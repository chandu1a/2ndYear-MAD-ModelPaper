package com.example.chand.modelpaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UsersDatabase.db";
    private static final String SQL_CREATE_STATEMENT = "CREATE TABLE "+ UserProfile.Users.TABLE_NAME+" ( "+
            UserProfile.Users.ID_COL+ " INTEGER PRIMARY KEY, "+
            UserProfile.Users.USERNAME_COL+ " TEXT, "+
            UserProfile.Users.PASSWORD_COL+ " TEXT, "+
            UserProfile.Users.DOB_COL+ " TEXT, "+
            UserProfile.Users.GENDER_COL+ " TEXT )";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_STATEMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ UserProfile.Users.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addInfo(String userName, String password, String DOB, String gender){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserProfile.Users.USERNAME_COL, userName);
        contentValues.put(UserProfile.Users.PASSWORD_COL, password);
        contentValues.put(UserProfile.Users.DOB_COL, DOB);
        contentValues.put(UserProfile.Users.GENDER_COL, gender);

        long newRowId = sqLiteDatabase.insert(UserProfile.Users.TABLE_NAME, null, contentValues);
        System.out.println("================================================");
        System.out.println("Row Id: "+newRowId+" User: "+userName+" User Gender: "+gender);
        System.out.println("================================================");

        if (newRowId > 0){
            return true;
        }
        else {
            return false;
        }

    }

    public boolean updateInfo(String ID, String userName, String password, String DOB, String gender){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        String selection = UserProfile.Users.ID_COL +" = ?";
        String[] selectionArgs = {ID};

        int count = sqLiteDatabase.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        if (count > 0){
            return true;
        }
        else {
            return false;
        }

    }


    public List readAllInfo(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] projection = {
                UserProfile.Users.ID_COL,
                UserProfile.Users.USERNAME_COL,
                UserProfile.Users.PASSWORD_COL,
                UserProfile.Users.DOB_COL,
                UserProfile.Users.GENDER_COL
        };

        String sortOrder = UserProfile.Users.ID_COL + " ASC";

        Cursor cursor = sqLiteDatabase.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List<UserBean> userBeanList = new ArrayList<UserBean>();
        UserBean userBean;
        while (cursor.moveToNext()){
            String uID= String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(UserProfile.Users.ID_COL)));
            String uName = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.USERNAME_COL)));
            String uPwd = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.PASSWORD_COL)));
            String uDOB = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.DOB_COL)));
            String uGender = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.GENDER_COL)));
            userBean = new UserBean(uID, uName, uPwd,uDOB, uGender);
            userBeanList.add(userBean);
            System.out.println("================================================");
            System.out.println("Read User Name: "+userBean.getUserName()+" User Gender: "+userBean.getGender());
            System.out.println("================================================");
        }
        cursor.close();
        return  userBeanList;
    }

    public List readAllInfo(String userName){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] projection = {
                UserProfile.Users.ID_COL,
                UserProfile.Users.USERNAME_COL,
                UserProfile.Users.PASSWORD_COL,
                UserProfile.Users.DOB_COL,
                UserProfile.Users.GENDER_COL
        };
        String selection = UserProfile.Users.USERNAME_COL +" = ?";
        String[] selectionArgs = {userName};
        String sortOrder = UserProfile.Users.ID_COL + " ASC";

        Cursor cursor = sqLiteDatabase.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List<UserBean> userBeanList = new ArrayList<UserBean>();
        UserBean userBean;
        while (cursor.moveToNext()){
            String uID= String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(UserProfile.Users.ID_COL)));
            String uName = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.USERNAME_COL)));
            String uPwd = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.PASSWORD_COL)));
            String uDOB = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.DOB_COL)));
            String uGender = String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.GENDER_COL)));
            userBean = new UserBean(uID, uName, uPwd,uDOB, uGender);
            userBeanList.add(userBean);
            System.out.println("================================================");
            System.out.println("Read User Name: "+userBean.getUserName()+" User Gender: "+userBean.getGender());
            System.out.println("================================================");
        }
        cursor.close();
        return  userBeanList;
    }

    public boolean deleteInfo(String ID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String selection = UserProfile.Users.ID_COL +" = ?";
        String[] selectionArgs = {ID};
        int deletedRows = sqLiteDatabase.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
        if(deletedRows > 0){
            return true;
        }
        else {
            return false;
        }

    }
}
