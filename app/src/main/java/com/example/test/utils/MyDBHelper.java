package com.example.test.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.test.fragments.EmployeeInfo;
import com.example.test.fragments.PersonalInfo;
import com.example.test.models.BankInfoModel;
import com.example.test.models.EmployeeInfoModel;
import com.example.test.models.PersonalInfoModel;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MachineTestDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PERSONAL_INFO = "PERSONAL_INFO";
    private static final String KEY_ID = "ID";
    private static final String KEY_FIRST_NAME = "KEY_FIRST_NAME";
    private static final String KEY_LAST_NAME = "KEY_LAST_NAME";
    private static final String KEY_PHONE_NUMBER = "KEY_PHONE_NUMBER";
    private static final String KEY_GENDER = "KEY_GENDER";
    private static final String KEY_DATE = "KEY_DATE";

    // Employee info
    private static final String TABLE_EMPLOYEE_INFO = "EMPLOYEE_INFO";
    private static final String KEY_EMPLOYEE_NAME = "KEY_EMPLOYEE_NAME";
    private static final String KEY_EMPLOYEE_NO = "KEY_EMPLOYEE_NO";
    private static final String KEY_DESIGNATION = "KEY_DESIGNATION";
    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    private static final String KEY_EXPERIENCE = "KEY_EXPERIENCE";

    //    Bank info
    private static final String TABLE_BANK_INFO = "BANK_INFO";
    private static final String KEY_BANK_NAME = "KEY_BANK_NAME";
    private static final String KEY_BRANCH = "KEY_BRANCH";
    private static final String KEY_ACCOUNT_NO = "ACCOUNT_NO";
    private static final String KEY_IFSC_CODE = "IFSC_CODE";
    private static final String KEY_PHOTO_PATH = "KEY_PHOTO_PATH";

    private static MyDBHelper instance;

    Context context;

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PERSONAL_INFO +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_FIRST_NAME + " TEXT, " +
                KEY_LAST_NAME + " TEXT, " +
                KEY_PHONE_NUMBER + " TEXT, " +
                KEY_GENDER + " TEXT, " +
                KEY_DATE + " TEXT" + ")");


        db.execSQL("CREATE TABLE " + TABLE_EMPLOYEE_INFO +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_EMPLOYEE_NO + " TEXT, " +
                KEY_EMPLOYEE_NAME + " TEXT, " +
                KEY_DESIGNATION + " TEXT, " +
                KEY_ACCOUNT + " TEXT, " +
                KEY_EXPERIENCE + " TEXT " + ")");

        db.execSQL("CREATE TABLE " + TABLE_BANK_INFO +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_BANK_NAME + " TEXT," +
                KEY_BRANCH + " TEXT, " +
                KEY_ACCOUNT_NO + " TEXT, " +
                KEY_IFSC_CODE + " TEXT, " +
                KEY_PHOTO_PATH + " TEXT " + ")");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PERSONAL_INFO);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_EMPLOYEE_INFO);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_BANK_INFO);
        onCreate(db);

    }

    public void addPersonalInfo(String firstName, String lastName, String phoneNumber, String gender, String dob) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIRST_NAME, firstName);
        contentValues.put(KEY_LAST_NAME, lastName);
        contentValues.put(KEY_PHONE_NUMBER, phoneNumber);
        contentValues.put(KEY_GENDER, gender);
        contentValues.put(KEY_DATE, dob);

        long newRowId = database.insert(TABLE_PERSONAL_INFO, null, contentValues);

        if (newRowId == -1) {
            Log.d("myTag", "Data not inserted");
        } else {
            Log.d("myTag", "Data  inserted");
        }

        database.close();

    }

    public void addEmployeeInfo(String employeeNo, String employeeName, String designation, String account, String experience) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EMPLOYEE_NO, employeeNo);
        contentValues.put(KEY_EMPLOYEE_NAME, employeeName);
        contentValues.put(KEY_DESIGNATION, designation);
        contentValues.put(KEY_ACCOUNT, account);
        contentValues.put(KEY_EXPERIENCE, experience);

        long newRowId = database.insert(TABLE_EMPLOYEE_INFO, null, contentValues);

        if (newRowId == -1) {
            Log.d("myTag", "Data not inserted");
        } else {
            Log.d("myTag", "Data  inserted");
        }
        database.close();
    }

    public void addBankInfo(String bankName, String branchName, String accountNo, String ifscCode, String photoPath) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_BANK_NAME, bankName);
        contentValues.put(KEY_BRANCH, branchName);
        contentValues.put(KEY_ACCOUNT_NO, accountNo);
        contentValues.put(KEY_IFSC_CODE, ifscCode);
        contentValues.put(KEY_PHOTO_PATH, photoPath);
        long newRowId = sqLiteDatabase.insert(TABLE_BANK_INFO, null, contentValues);
        if (newRowId == -1) {
            Log.d("myTag", "Data not inserted");
        } else {
            Log.d("myTag", "Data  inserted");
        }
        sqLiteDatabase.close();
    }

    public ArrayList<PersonalInfoModel> readPersonalData() {

        ArrayList<PersonalInfoModel> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_PERSONAL_INFO, null);
        while (cursor.moveToNext()) {
            PersonalInfoModel model = new PersonalInfoModel();
            model.setId(cursor.getInt(0));
            model.setFirstName(cursor.getString(1));
            model.setLastName(cursor.getString(2));
            model.setPhoneNumber(cursor.getString(3));
            model.setGender(cursor.getString(4));
            model.setDob(cursor.getString(5));
            arrayList.add(model);
        }
        sqLiteDatabase.close();
        return arrayList;
    }

    public ArrayList<EmployeeInfoModel> readEmployeeInfo() {

        ArrayList<EmployeeInfoModel> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_EMPLOYEE_INFO, null);
        while (cursor.moveToNext()) {
            EmployeeInfoModel model = new EmployeeInfoModel();
            model.setId(cursor.getInt(0));
            model.setEmployeeNo(cursor.getString(1));
            model.setEmployeeName(cursor.getString(2));
            model.setDesignation(cursor.getString(3));
            model.setAccountType(cursor.getString(4));
            model.setExperience(cursor.getString(5));
            arrayList.add(model);
        }
        sqLiteDatabase.close();
        return arrayList;
    }

    public ArrayList<BankInfoModel> readBankInfo() {

        ArrayList<BankInfoModel> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_BANK_INFO, null);
        while (cursor.moveToNext()) {
            BankInfoModel model = new BankInfoModel();
            model.setId(cursor.getInt(0));
            model.setBankName(cursor.getString(1));
            model.setBranchName(cursor.getString(2));
            model.setAccountNo(cursor.getString(3));
            model.setIfscCode(cursor.getString(4));
            model.setPhotoPath(cursor.getString(5));
            arrayList.add(model);
        }
        sqLiteDatabase.close();
        return arrayList;
    }


    public PersonalInfoModel readPersonalData(int id) {

        PersonalInfoModel model = new PersonalInfoModel();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_PERSONAL_INFO + " WHERE id = " + id, null);
        if (cursor.moveToFirst()) {
            model.setId(cursor.getInt(0));
            model.setFirstName(cursor.getString(1));
            model.setLastName(cursor.getString(2));
            model.setPhoneNumber(cursor.getString(3));
            model.setGender(cursor.getString(4));
            model.setDob(cursor.getString(5));
        }
        cursor.close();
        sqLiteDatabase.close();
        return model;
    }

    public EmployeeInfoModel readEmployeeInfo(int id) {

        EmployeeInfoModel model = new EmployeeInfoModel();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_EMPLOYEE_INFO + " WHERE id = " + id, null);
        if (cursor.moveToFirst()) {
            model.setId(cursor.getInt(0));
            model.setEmployeeNo(cursor.getString(1));
            model.setEmployeeName(cursor.getString(2));
            model.setDesignation(cursor.getString(3));
            model.setAccountType(cursor.getString(4));
            model.setExperience(cursor.getString(5));
        }
        cursor.close();
        sqLiteDatabase.close();
        return model;
    }

    public BankInfoModel readBankInfo(int id) {
        Log.d("myTag", "readBankInfo: id = " + id);

        BankInfoModel model = new BankInfoModel();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_BANK_INFO + " WHERE id = " + id, null);
        if (cursor.moveToFirst()) {
            Log.d("myTag", "readBankInfo: " + cursor.getColumnName(0) + " = " + cursor.getString(1));
            model.setId(cursor.getInt(0));
            model.setBankName(cursor.getString(1));
            model.setBranchName(cursor.getString(2));
            model.setAccountNo(cursor.getString(3));
            model.setIfscCode(cursor.getString(4));
            model.setPhotoPath(cursor.getString(5));
        }
        Log.d("myTag", "readBankInfo: cursor count = " + cursor.getCount());
        cursor.close();
        sqLiteDatabase.close();
        return model;
    }

    public void deleteAllDataFromTable(String tableName) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }

    public void updatePersonalInfo(PersonalInfoModel personalInfoModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, personalInfoModel.getFirstName());
        values.put(KEY_LAST_NAME, personalInfoModel.getLastName());
        values.put(KEY_PHONE_NUMBER, personalInfoModel.getPhoneNumber());
        values.put(KEY_GENDER, personalInfoModel.getGender());
        values.put(KEY_DATE, personalInfoModel.getDob());

        db.update(TABLE_PERSONAL_INFO, values, KEY_ID + " = ?", new String[]{String.valueOf(personalInfoModel.getId())});
        db.close();
    }

    public void updateEmployeeInfo(EmployeeInfoModel employeeInfoModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EMPLOYEE_NO, employeeInfoModel.getEmployeeNo());
        contentValues.put(KEY_EMPLOYEE_NAME, employeeInfoModel.getEmployeeName());
        contentValues.put(KEY_DESIGNATION, employeeInfoModel.getDesignation());
        contentValues.put(KEY_ACCOUNT, employeeInfoModel.getAccountType());
        contentValues.put(KEY_EXPERIENCE, employeeInfoModel.getExperience());

        db.update(TABLE_EMPLOYEE_INFO, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(employeeInfoModel.getId())});
        db.close();
    }

    public void updateBankInfo(BankInfoModel bankInfoModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_BANK_NAME, bankInfoModel.getBankName());
        contentValues.put(KEY_BRANCH, bankInfoModel.getBranchName());
        contentValues.put(KEY_ACCOUNT_NO, bankInfoModel.getAccountNo());
        contentValues.put(KEY_IFSC_CODE, bankInfoModel.getIfscCode());
        contentValues.put(KEY_PHOTO_PATH, bankInfoModel.getPhotoPath());

        db.update(TABLE_BANK_INFO, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(bankInfoModel.getId())});
        db.close();
    }


}
