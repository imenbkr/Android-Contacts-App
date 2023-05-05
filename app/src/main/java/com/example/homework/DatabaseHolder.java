package com.example.homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHolder extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Contacts.db";
    private static final String TABLE_NAME = "Contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "number";

    public DatabaseHolder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the Contacts table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT,"
                + COLUMN_NUMBER + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop the old Contacts table if it exists and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addContact(MyObject myObject) {
        // get the database in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // create a new ContentValues object and add the contact data to it
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, myObject.getName());
        values.put(COLUMN_NUMBER, myObject.getNumber());

        // insert the contact into the database and close the database connection
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<MyObject> getAllContacts() {
        List<MyObject> myObjectList = new ArrayList<>();

        // select all contacts from the Contacts table
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // loop through all rows and add the contacts to the list
        if (cursor.moveToFirst()) {
            do {
                MyObject myObject = new MyObject();
                myObject.setId(Integer.parseInt(cursor.getString(0)));
                myObject.setName(cursor.getString(1));
                myObject.setNumber(Integer.parseInt(cursor.getString(2)));
                myObjectList.add(myObject);
            } while (cursor.moveToNext());
        }

        // close the cursor and the database connection
        cursor.close();
        db.close();

        // return the list of contacts
        return myObjectList;
    }

    public void updateContact(MyObject myObject) {
        // get the database in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // create a new ContentValues object and add the updated contact data to it
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, myObject.getName());
        values.put(COLUMN_NUMBER, myObject.getNumber());

        // update the contact in the database and close the database connection
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(myObject.getId())});
        db.close();
    }

    public void deleteContact(MyObject myObject) {
        // get the database in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // delete the contact from the database and close the database connection
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(myObject.getId())});
        db.close();
    }


}


