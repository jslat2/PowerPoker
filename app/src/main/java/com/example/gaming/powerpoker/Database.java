package com.example.gaming.powerpoker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by Gaming on 7/23/2015.
 */
public class Database extends SQLiteOpenHelper {
    public static final int database_version = 1;

    public Database(Context context) {
        super(context, "PowerPoker", null, database_version);
        Log.d("Database", "database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE account(userNum INTEGER,userName TEXT, userPass TEXT,userEmail TEXT);");
        db.execSQL("CREATE TABLE playerNotes(userNum INTEGER, noteNum INTEGER, opponentFirst TEXT, opponentLast TEXT, opponentEmail TEXT, opponentPhone TEXT, opponentDescription TEXT, location TEXT, note TEXT, rating FLOAT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //to do
    }

    public Cursor getAccountTable(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"userNum", "userName", "userPass", "userEmail"};
        Cursor cr = sq.query("account", cols, null, null, null, null, null);
        return cr;
    }

    public void addUser(Database db, String userName, String userPass, String userEmail){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userNum", getNextAvailableUserNum(db));
        cv.put("userName", userName);
        cv.put("userPass", userPass);
        cv.put("userEmail", userEmail);

        long k = sq.insert("account", null, cv);
    }

    public void addNote(Database db, String first, String last, String email, String phone, String description, String location, String note, float rating){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userNum", MainActivity.userNum);
        cv.put("noteNum", getNextAvailableNoteNum(db));
        cv.put("opponentFirst", first);
        cv.put("opponentLast", last);
        cv.put("opponentEmail", email);
        cv.put("opponentPhone", phone);
        cv.put("opponentDescription", description);
        cv.put("location", location);
        cv.put("note", note);
        cv.put("rating", rating);

        long k = sq.insert("playerNotes", null, cv);
    }

    public void changeNote(Database db, int num, String first, String last, String email, String phone, String description, String location, String note, float rating){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userNum", MainActivity.userNum);
        cv.put("noteNum", num);
        cv.put("opponentFirst", first);
        cv.put("opponentLast", last);
        cv.put("opponentEmail", email);
        cv.put("opponentPhone", phone);
        cv.put("opponentDescription", description);
        cv.put("location", location);
        cv.put("note", note);
        cv.put("rating", rating);
        System.out.println("CHANGING NOTE NUMBER " + num + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        sq.execSQL("DELETE FROM playerNotes WHERE userNum = " + MainActivity.userNum + " AND noteNum = " + num);
        long k = sq.insert("playerNotes", null, cv);
    }



    //scan account table for unique userNum
    public int getNextAvailableUserNum(Database db){
        Cursor cr = getAccountTable(db);
        if(!cr.moveToFirst()){
            return 0;
        }

        for(int i = 0; i < cr.getCount() + 1; i++){
            if(tableContainsInt(db, "account", "userNum", i) == -1){
                return i;
            }
        }
        return cr.getCount() + 1;
    }


    public int getNextAvailableNoteNum(Database db){
        Cursor cr = getPlayerNotesTable(db);
        if(!cr.moveToFirst()){
            return 0;
        }

        System.out.println("ADDING NOTE NUMBER " + cr.getCount() + "!!!!!!!!!!!!!!!!!!!!!!!");
        return cr.getCount();

    }


    //check to see if provided user and pass are in database
    public boolean isValidLogin(Database db, String user, String pass){
        Cursor cr = getAccountTable(db);
        if(!cr.moveToFirst()){
            return false;
        }

        cr.moveToFirst();
        while(!cr.isAfterLast()){
            if(cr.getString(1).equals(user) && cr.getString(2).equals(pass)){
                return true;
            }
            cr.moveToNext();
        }
        return false;
    }

    public Cursor getPlayerNotesTable(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"userNum", "noteNum","opponentFirst", "opponentLast", "opponentEmail", "opponentPhone", "opponentDescription", "location", "note", "rating"};
        Cursor cr = sq.query("playerNotes", cols, null, null, null, null, "noteNum ASC");
        return cr;
    }

    public int getUserNum(Database db, String userName){
        Cursor cr = getAccountTable(db);
        if(!cr.moveToFirst()){
            return -1;
        }

        else{
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
                if(cr.getString(1).equals(userName)){
                    return cr.getInt(0);
                }
                cr.moveToNext();
            }
        }

        return -1;
    }

    //searches table for string in specified column, returns -1 if nothing found, otherwise returns row index of match
    public int tableContainsString(Database db, String tableName, String colName, String variable){

        int position = 0;
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {colName};
        Cursor cr = sq.query(tableName, cols, null, null, null, null, null);

        if(!cr.moveToFirst()){
            return -1;
        }

        cr.moveToFirst();
        while(!cr.isAfterLast()){
            if(cr.getString(0).equals(variable)){
                return position;
            }
            position++;
            cr.moveToNext();
        }
        return -1;
    }

    public int tableContainsInt(Database db, String tableName, String colName, int variable){

        int position = 0;
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {colName};
        Cursor cr = sq.query(tableName, cols, null, null, null, null, null);

        if(!cr.moveToFirst()){
            return -1;
        }

        cr.moveToFirst();
        while(!cr.isAfterLast()){
            if(cr.getInt(0) == variable){
                return position;
            }
            position++;
            cr.moveToNext();
        }
        return -1;
    }

}