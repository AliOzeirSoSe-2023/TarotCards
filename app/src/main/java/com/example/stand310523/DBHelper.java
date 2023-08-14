package com.example.stand310523;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(SpiritualName TEXT PRIMARY KEY, email TEXT, password TEXT)");
    }

    boolean d = false;
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        if(d==true){
        MyDB.execSQL("DROP TABLE IF EXISTS users");}
    }

    public String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertData(String spiritualName, String email, String password) {
        spiritualName = spiritualName.toLowerCase();
        email = email.toLowerCase();
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SpiritualName", spiritualName);
        contentValues.put("email", email);
        contentValues.put("password", hashPassword(password));
        long result = MyDB.insert("users", null, contentValues);
        return result != -1;
    }

    public boolean checkSpiritualName(String spiritualName) {
        spiritualName = spiritualName.toLowerCase();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE SpiritualName = ?", new String[]{spiritualName});
        return cursor.getCount() > 0;
    }

    public boolean checkEmailExists(String email) {
        email = email.toLowerCase();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE Email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public boolean checkPassword(String spiritualName, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE SpiritualName = ? AND password = ?", new String[]{spiritualName, hashPassword(password)});  // Hash the password
        return cursor.getCount() > 0;
    }
    public boolean checkSpiritualNameEmailPassword(String spiritualName, String email, String password) {
        spiritualName = spiritualName.toLowerCase();
        email = email.toLowerCase();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE SpiritualName = ? AND Email = ? AND password = ?", new String[]{spiritualName, email, hashPassword(password)});  // Hash the password
        return cursor.getCount() > 0;
    }


    public void deleteAllUsers() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("DELETE FROM users");
    }



}