package com.example.gaming.powerpoker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Gaming on 7/23/2015.
 */
public class Database extends SQLiteOpenHelper {
    public static final int database_version = 1;

    public Database(Context context) {
        super(context, "PowerPoker", null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE account(userNum INTEGER,userName TEXT, userPass TEXT,userEmail TEXT);");
        db.execSQL("CREATE TABLE playerNotes(userNum INTEGER, noteNum INTEGER, opponentFirst TEXT, opponentLast TEXT, opponentEmail TEXT, opponentPhone TEXT, opponentDescription TEXT, location TEXT, note TEXT, rating FLOAT);");
        db.execSQL("CREATE TABLE bankroll(userNum INTEGER,bankName TEXT, transNum INTEGER,transAmt REAL, transDate String, transNote TEXT);");
        db.execSQL("CREATE TABLE sessions(userNum INTEGER, date TEXT, sessionNum INTEGER, time TEXT, bankroll TEXT, buyIn REAL, venue TEXT, format TEXT, stakes TEXT, notes TEXT, cashOut REAL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //to do
    }


    public void addSession(Database db, String date, String time, String bankroll, double buyIn, String venue, String format, String stakes, String notes, double cashOut){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userNum", MainActivity.userNum);
        cv.put("sessionNum", getNextAvailableSessionNum(db));
        cv.put("date", date);
        cv.put("time", time);
        cv.put("bankroll", bankroll);
        cv.put("buyIn", buyIn);
        cv.put("venue", venue);
        cv.put("format", format);
        cv.put("stakes", stakes);
        cv.put("notes", notes);
        cv.put("cashOut", cashOut);

        long k = sq.insert("sessions", null, cv);
    }

    public Cursor getSessionsList(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"rowid _id", "date", "venue"};
        Cursor cr = sq.query("sessions", cols, null, null, null, null, " date DESC");
        return cr;
    }

    public Cursor getAmtsByFormat(Database db, String formatName){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"date", "buyIn", "cashOut"};
        Cursor cr = sq.query("sessions WHERE userNum = " + MainActivity.userNum + " AND format = " + "'" + formatName + "'", cols, null, null, null, null, " date DESC");
        return cr;
    }

    public Cursor getAmtsByVenue(Database db, String venueName){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"date", "buyIn", "cashOut"};
        Cursor cr = sq.query("sessions WHERE userNum = " + MainActivity.userNum + " AND venue = " + "'" + venueName + "'", cols, null, null, null, null, " date DESC");
        return cr;
    }

    public Cursor getAmtsByStakes(Database db, String stakesName){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"date", "buyIn", "cashOut"};
        Cursor cr = sq.query("sessions WHERE userNum = " + MainActivity.userNum + " AND stakes = " + "'" + stakesName + "'", cols, null, null, null, null, " date DESC");
        return cr;
    }

    public Cursor getSessionTableDateDesc(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"userNum", "date", "sessionNum", "time", "bankroll", "buyIn", "venue", "format", "stakes", "notes", "cashOut"};
        Cursor cr = sq.query("sessions", cols, null, null, null, null, " date DESC");
        return cr;
    }

    public Cursor getSessionVenueAmts(Database db, int userNum){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"venue", "buyIn", "cashOut"};
        Cursor cr = sq.query("sessions WHERE userNum = " + userNum, cols, null, null, null, null, " venue DESC");
        return cr;
    }

    public Cursor getBankrollNames(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"userNum","bankName", "transNum"};
        Cursor cr = sq.query("bankroll", cols, null, null, null, null, " bankName ASC");
        return cr;
    }

    public Cursor getStakesNames(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"stakes"};
        Cursor cr = sq.query("sessions WHERE userNum = " + MainActivity.userNum, cols, null, null, null, null, " stakes ASC");
        return cr;
    }

    public Cursor getFormatNames(Database db, int userNum){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"format"};
        Cursor cr = sq.query("sessions WHERE userNum = " + userNum, cols, null, null, null, null, " format ASC");
        return cr;
    }

    public Cursor getVenueNames(Database db, int userNum){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"venue"};
        Cursor cr = sq.query("sessions WHERE userNum = " + userNum, cols, null, null, null, null, " venue ASC");
        return cr;
    }

    public Cursor getBankrollAmtsDates(Database db, String bankrollName){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"rowid _id", "transAmt", "transDate"};
        Cursor cr = sq.query("bankroll WHERE bankName = '" + bankrollName + "' AND userNum = " + MainActivity.userNum, cols, null, null, null, null, " transDate DESC");
        return cr;
    }

    public Cursor getSessionByBankroll(Database db, String bankrollName){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"date", "buyIn", "cashOut"};
        Cursor cr = sq.query("sessions WHERE bankroll = '" + bankrollName + "' AND userNum = " + MainActivity.userNum, cols, null, null, null, null, " date DESC");
        return cr;
    }

    public Cursor getBankrollTable(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"userNum","bankName", "transNum", "transAmt", "transDate", "transNote"};
        Cursor cr = sq.query("bankroll", cols, null, null, null, null, " bankName ASC");
        return cr;
    }

    public Cursor getBankrollTotals(Database db, int userNum){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"transAmt"};
        Cursor cr = sq.query("bankroll WHERE userNum = " + userNum, cols, null, null, null, null, null);
        return cr;
    }

    public Cursor getSessionTimes(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"time"};
        Cursor cr = sq.query("sessions", cols, null, null, null, null, null);
        return cr;
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

    public void makeDeposit(Database db, double depositAmount, String date){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userNum", MainActivity.userNum);
        cv.put("bankName", getCurrentBankrollName(db));
        cv.put("transNum", getNextAvailableTransNum(db));
        cv.put("transAmt", depositAmount);
        cv.put("transDate", date);
        cv.put("transNote", "");

        long k = sq.insert("bankroll", null, cv);
    }

    public void saveSessionToBankroll(Database db, int userNum, String bankrollName, double earnings, String date){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userNum", userNum);
        cv.put("bankName", bankrollName);
        cv.put("transNum", getNextAvailableTransNum(db));
        cv.put("transAmt", earnings);
        cv.put("transDate", date);
        cv.put("transNote", "Session Earnings");

        long k = sq.insert("bankroll", null, cv);
    }

    public String getCurrentBankrollName(Database db){
        ArrayList<String> names = new ArrayList<>();
        names.add("");
        Cursor cr = getBankrollTable(db);
        if(cr.moveToFirst()) {
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
                if (cr.getInt(0) == MainActivity.userNum && !names.contains(cr.getString(1))) {
                    names.add(cr.getString(1));
                }
                if (names.size() == MainActivity.currentBankrollPosition + 2) {
                    break;
                }
                cr.moveToNext();
            }

        }
        return cr.getString(1);
    }

    public void addBankroll(Database db, String bankrollName, String bankrollDate, double initialDeposit){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userNum", MainActivity.userNum);
        cv.put("bankName", bankrollName);
        cv.put("transNum", getNextAvailableTransNum(db));
        cv.put("transAmt", initialDeposit);
        cv.put("transDate", bankrollDate);
        cv.put("transNote", "");

        long k = sq.insert("bankroll", null, cv);
    }

    //returns a unique identifier for the transaction
    public int getNextAvailableTransNum(Database db){
        int x = 0;
        Cursor cr = getBankrollNames(db);
        if (!cr.moveToFirst()){
            return 0;
        }

        cr.moveToFirst();
        while(!cr.moveToLast()){
            if(cr.getInt(2) > x){
                x = cr.getInt(2);
            }
            cr.moveToNext();
        }
        x++;
        return x;
    }

    public int getNextAvailableSessionNum(Database db){
        int x = 0;
        Cursor cr = getSessionNums(db);
        if (!cr.moveToFirst()){
            return 0;
        }

        cr.moveToFirst();
        while(!cr.isAfterLast()){
            if(cr.getInt(0) > x){
                System.out.println("sessionnum found: " + cr.getInt(0) + "!!!!!!!!!!!!");
                x = cr.getInt(0);
            }
            cr.moveToNext();
        }
        x++;
        System.out.println("adding sessionnum: " + x);
        cr.close();
        return x;
    }

    public double getBankrollTotal(Database db, int position){

        double bankTotal = 0;
        ArrayList<String> names = new ArrayList<>();
        names.add("");
        Cursor cr = getBankrollTable(db);
        if(cr.moveToFirst()) {
            cr.moveToFirst();
            while (!cr.isAfterLast()) {

                if (cr.getInt(0) == MainActivity.userNum && !names.contains(cr.getString(1))) {
                    names.add(cr.getString(1));
                }
                if (names.size() == position + 2) {
                    break;
                }
                cr.moveToNext();
            }

            while (!cr.isAfterLast()) {
                if (cr.getString(1).equals(names.get(names.size() - 1)) && cr.getInt(0) == MainActivity.userNum) {
                    bankTotal += cr.getDouble(3);
                    cr.moveToNext();
                } else {
                    break;
                }
            }
        }
        return bankTotal;
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

    public void changeNote(Database db, int position, String first, String last, String email, String phone, String description, String location, String note, float rating){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        int num = getNoteNum(db, position);

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
        sq.execSQL("DELETE FROM playerNotes WHERE userNum = " + MainActivity.userNum + " AND noteNum = " + num);
        long k = sq.insert("playerNotes", null, cv);
    }

    public void deleteCurrentSession(){
        SQLiteDatabase sq = MainActivity.db.getWritableDatabase();
        sq.execSQL("DELETE FROM sessions WHERE userNum = " + MainActivity.userNum + " AND sessionNum = " + getSessionNum(MainActivity.db, MainActivity.currentSessionPosition));
        sq.close();
    }


    public void changeSession(Database db, int currentSessionPosition, String time, String bankroll, double buyIn, String venue, String format, String stakes, String notes, double cashOut){
        SQLiteDatabase sq = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        int num = getSessionNum(db, currentSessionPosition);
        String date = getSessionDate(db, currentSessionPosition);

        cv.put("userNum", MainActivity.userNum);
        cv.put("sessionNum", num);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("bankroll", bankroll);
        cv.put("buyIn", buyIn);
        cv.put("venue", venue);
        cv.put("format", format);
        cv.put("stakes", stakes);
        cv.put("notes", notes);
        cv.put("cashOut", cashOut);

        sq.execSQL("DELETE FROM sessions WHERE userNum = " + MainActivity.userNum + " AND sessionNum = " + num);
        long k = sq.insert("sessions", null, cv);
        sq.close();
    }


    public int getSessionNum(Database db, int currentSessionPosition){
        Cursor cr = getSessionTableDateDesc(db);
        if(cr.moveToFirst()){
            cr.moveToPosition(currentSessionPosition);
            return cr.getInt(2);
        }
        cr.close();
        return -1;
    }

    public String getSessionDate(Database db, int currentSessionPosition){
        Cursor cr = getSessionTableDateDesc(db);
        if(cr.moveToFirst()){
            cr.moveToPosition(currentSessionPosition);
            return cr.getString(1);
        }
        cr.close();
        return "";
    }


    public int getNoteNum(Database db, int position){
        Cursor cr = getPlayerNotesTable(db);
        if(cr.moveToFirst()) {
            cr.moveToPosition(position);
            return cr.getInt(1);
        }
        return -1;
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
        int x  = 0;

        Cursor cr = getPlayerNotesTable(db);
        if(!cr.moveToFirst()){
            return 0;
        }

        cr.moveToFirst();
        while (!cr.isAfterLast()){
            if(cr.getInt(1) > x){
                x = cr.getInt(1);
            }
            cr.moveToNext();
        }
        x++;
        return x;
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
        Cursor cr = sq.query("playerNotes WHERE userNum = " + MainActivity.userNum, cols, null, null, null, null, "noteNum ASC");
        return cr;
    }

    public Cursor getPlayerNotesTableWithRowid(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"rowid _id", "userNum", "noteNum","opponentFirst", "opponentLast", "opponentEmail", "opponentPhone", "opponentDescription", "location", "note", "rating"};
        Cursor cr = sq.query("playerNotes WHERE userNum = " + MainActivity.userNum, cols, null, null, null, null, "noteNum ASC");
        return cr;
    }

    public Cursor getSessionNums(Database db){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"sessionNum"};
        Cursor cr = sq.query("sessions", cols, null, null, null, null, null);
        return cr;
    }

    public Cursor getSessionTotals(Database db, int userNum){
        SQLiteDatabase sq = db.getReadableDatabase();
        String[] cols = {"buyIn", "cashOut"};
        Cursor cr = sq.query("sessions WHERE userNum = " + userNum, cols, null, null, null, null, null);
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