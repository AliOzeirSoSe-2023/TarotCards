package com.example.stand310523;
import static com.example.stand310523.login.spiritualNametosend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelperSaveAnswer extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "readings.db";
    private static final int DATABASE_VERSION = 7;
    private static final String TABLE_NAME = "spiritual_readings";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SPIRITUAL_NAME = "spiritual_name";
    private static final String COLUMN_MIND_STATE = "mind_state";
    private static final String COLUMN_CARDS_INTERPRETATION = "cards_interpretation";

    public DBHelperSaveAnswer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SPIRITUAL_NAME + " TEXT,"
                + COLUMN_MIND_STATE + " TEXT,"
                + COLUMN_CARDS_INTERPRETATION + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void saveReading(String spiritualName, String mindState, String cardsInterpretation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SPIRITUAL_NAME, spiritualName);
        values.put(COLUMN_MIND_STATE, mindState);
        values.put(COLUMN_CARDS_INTERPRETATION, cardsInterpretation);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<String> getReadings() {
        ArrayList<String> readings = new ArrayList<>();
        String sName = spiritualNametosend;
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_SPIRITUAL_NAME + " LIKE ?"; // Use LIKE for matching

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { "%" + sName + "%" });

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String mindState = cursor.getString(cursor.getColumnIndex(COLUMN_MIND_STATE));
                String cardsInterpretation = cursor.getString(cursor.getColumnIndex(COLUMN_CARDS_INTERPRETATION));
                readings.add(id + " Mind State: " + mindState + "\nCards Interpretation: " + cardsInterpretation); // Include the ID
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return readings;
    }

    public void deleteReading(int id, String spiritualName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ? AND " + COLUMN_SPIRITUAL_NAME + " = ?";
        String[] whereArgs = {String.valueOf(id), spiritualName};
        db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
    }



}
