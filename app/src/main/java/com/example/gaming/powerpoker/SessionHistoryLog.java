package com.example.gaming.powerpoker;

import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Gaming on 8/6/2015.
 */
public class SessionHistoryLog extends Fragment {

    public String selectedBankroll;
    public String format;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.session_history_log, container, false);
    }



    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        if (MainActivity.sessionActive) {
            Chronometer timer = (Chronometer) getActivity().findViewById(R.id.timer);
            RelativeLayout timerLayout = (RelativeLayout) getActivity().findViewById(R.id.timerLayout);

            Animation myFadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.flash);
            timerLayout.startAnimation(myFadeInAnimation);

            timer.setBase(SystemClock.elapsedRealtime() - MainActivity.checkpoint);
            if(!MainActivity.sessionTimerStopped) {
                timer.start();
            }
            timerLayout.setVisibility(View.VISIBLE);
        }

        Spinner bankrollSpinner = (Spinner)getActivity().findViewById(R.id.bankrollSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getBankrollNames());
        bankrollSpinner.setAdapter(adapter);

        bankrollSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBankroll = getBankrollNames().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner formatSpinner = (Spinner)getActivity().findViewById(R.id.format);
        ArrayAdapter<String> formatAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getFormats());
        formatSpinner.setAdapter(formatAdapter);

        formatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                format = getFormats().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner stakesSpinner = (Spinner)getActivity().findViewById(R.id.stakes);
        ArrayAdapter<String> stakesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getStakes());
        stakesSpinner.setAdapter(stakesAdapter);

        stakesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                format = getStakes().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bankrollSpinner.setSelection(getSpinnerBankrollIndex(MainActivity.currentSessionPosition));
        formatSpinner.setSelection(getSpinnerFormatIndex(MainActivity.currentSessionPosition));
        stakesSpinner.setSelection(getSpinnerStakesIndex(MainActivity.currentSessionPosition));
        ((EditText)(getActivity().findViewById(R.id.buyIn))).setText(dollarFormat(Double.toString(getBuyIn(MainActivity.currentSessionPosition))));
        ((EditText)getActivity().findViewById(R.id.notes)).setText(getNotes(MainActivity.currentSessionPosition));
        ((EditText)getActivity().findViewById(R.id.cashOut)).setText(dollarFormat(Double.toString(getCashOut(MainActivity.currentSessionPosition))));
        ((EditText)getActivity().findViewById(R.id.venue)).setText(getVenue(MainActivity.currentSessionPosition));
        ((EditText)getActivity().findViewById(R.id.duration)).setText(getDuration(MainActivity.currentSessionPosition));

        ArrayList<String> venues = getVenues(MainActivity.db);
        AutoCompleteTextView autoComplete = (AutoCompleteTextView) getActivity().findViewById(R.id.venue);
        ArrayAdapter<String> autoAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, venues);
        autoComplete.setAdapter(autoAdapter);

    }

    public void onResume() {
        super.onResume();

        if (MainActivity.sessionActive) {
            Chronometer timer = (Chronometer) getActivity().findViewById(R.id.timer);
            RelativeLayout timerLayout = (RelativeLayout) getActivity().findViewById(R.id.timerLayout);

            Animation myFadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.flash);
            timerLayout.startAnimation(myFadeInAnimation);

            timer.setBase(SystemClock.elapsedRealtime() - MainActivity.checkpoint);
            if(!MainActivity.sessionTimerStopped) {
                timer.start();
            }
            timerLayout.setVisibility(View.VISIBLE);
        }
    }

    public void onDestroyView(){
        super.onDestroyView();
        Chronometer timer = (Chronometer)getActivity().findViewById(R.id.timer);
        MainActivity.checkpoint = MainActivity.timeToMillis(timer.getText().toString());
    }

    public ArrayList getVenues(Database db){
        ArrayList venues = new ArrayList();
        Cursor cr = db.getSessionTableDateDesc(MainActivity.db);
        if(cr.moveToFirst()){
            cr.moveToFirst();
            while (!cr.isAfterLast()){
                if(!venues.contains(cr.getString(6))){
                    venues.add(cr.getString(6));
                }
                cr.moveToNext();
            }
        }
        cr.close();
        if(venues.size() > 0){
            return  venues;
        }
        else{
            venues.add("");
            return venues;
        }
    }

    public String getVenue(int sessionPositionIndex){
        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToPosition(sessionPositionIndex);
            String venue = cr.getString(6);
            return venue;
        }
        cr.close();
        return "";
    }

    public String getDuration(int sessionPositionIndex){
        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToPosition(sessionPositionIndex);
            String duration = cr.getString(3);
            return duration;
        }
        cr.close();
        return "";
    }

    public String getNotes(int sessionPositionIndex){
        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToPosition(sessionPositionIndex);
            String notes = cr.getString(9);
            return notes;
        }
        cr.close();
        return "";
    }

    public String dollarFormat(String s){
        String holder;
        String holder2;
        if(s.charAt(0) == '-'){
            holder = "-$" + s.substring(1, s.length());
        }
        else{
            holder = "$" + s;
        }

        if(holder.contains(".")){
            holder2 = holder.substring(0, holder.indexOf(".") + 1);
            if(holder.length() > holder2.length()){
                holder2 += holder.substring(holder.indexOf(".") + 1, holder.indexOf(".") + 2);
                if(holder.length() > holder2.length()){
                    holder2 += holder.substring(holder.indexOf(".") + 2, holder.indexOf(".") + 3);
                    return holder2;
                }
                else{
                    holder2 += "0";
                    return holder2;
                }
            }
            else{
                holder2 += "00";
                return holder2;
            }

        }
        else{
            holder += ".00";
            return holder;
        }


    }

    public double getBuyIn(int sessionPositionIndex){
        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToPosition(sessionPositionIndex);
            double buyIn = cr.getDouble(5);
            return buyIn;
        }
        cr.close();
        return -1;
    }

    public double getCashOut(int sessionPositionIndex){
        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToPosition(sessionPositionIndex);
            double cashOut = cr.getDouble(10);
            return cashOut;
        }
        cr.close();
        return -1;
    }

    public int getSpinnerStakesIndex(int sessionPositionIndex) {
        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToPosition(sessionPositionIndex);
            String stakesName = cr.getString(8);
            for (int i = 0; i < getStakes().size(); i++) {
                if (stakesName.equals(getStakes().get(i))) {
                    return i;
                }
            }
        }
        cr.close();
        return -1;
    }

    public int getSpinnerBankrollIndex(int sessionPositionIndex) {
        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToPosition(sessionPositionIndex);
            String bankrollName = cr.getString(4);
            for (int i = 0; i < getBankrollNames().size(); i++) {
                if (bankrollName.equals(getBankrollNames().get(i))) {
                    return i;
                }
            }
        }
        cr.close();
        return -1;
    }

    public int getSpinnerFormatIndex(int sessionPositionIndex){
        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToPosition(sessionPositionIndex);
            String formatName = cr.getString(7);
            for (int i = 0; i < getFormats().size(); i++) {
                if (formatName.equals(getFormats().get(i))) {
                    return i;
                }
            }
        }
        cr.close();
        return -1;
    }

    public ArrayList<String> getStakes(){
        ArrayList<String> stakes = new ArrayList<>();
        stakes.add("$1/$2");
        stakes.add("$1/$3");
        stakes.add("$2/$4");
        stakes.add("$2/$5");
        stakes.add("$3/$6");
        stakes.add("$4/$8");
        stakes.add("$5/$10");
        stakes.add("$6/$12");
        stakes.add("$8/$16");
        stakes.add("$9/$18");
        stakes.add("$10/$20");
        stakes.add("$15/$30");
        stakes.add("$20/$40");
        stakes.add("$30/$60");
        stakes.add("$40/$80");
        stakes.add("$50/$100");
        stakes.add("$75/$150");
        stakes.add("$100/$200");
        stakes.add("$150/$300");
        return stakes;
    }

    public ArrayList<String> getFormats(){
        ArrayList<String> formats = new ArrayList<>();
        formats.add("Cash - NL");
        formats.add("Cash - NL - 6Max");
        formats.add("Cash - NL - HU");
        formats.add("Cash - Limit");
        formats.add("Cash - Limit - 6Max");
        formats.add("Cash - Limit - HU");
        formats.add("MTT - NL");
        formats.add("MTT - NL - 6Max");
        formats.add("MTT - NL - HU");
        formats.add("MTT - Limit");
        formats.add("MTT - Limit - 6Max");
        formats.add("MTT - Limit - HU");
        formats.add("SNG - NL");
        formats.add("SNG - NL - 6Max");
        formats.add("SNG - NL - HU");
        formats.add("SNG - Limit");
        formats.add("SNG - Limit - 6Max");
        formats.add("SNG - Limit - HU");
        return formats;
    }

    public ArrayList<String> getBankrollNames(){
        ArrayList<String> names = new ArrayList<>();
        Cursor cr = MainActivity.db.getBankrollNames(MainActivity.db);
        if(cr.moveToFirst()){
            cr.moveToFirst();
            while(!cr.isAfterLast()){
                if(!names.contains(cr.getString(1))) {
                    names.add(cr.getString(1));
                }
                cr.moveToNext();
            }
        }
        cr.close();
        return names;
    }
}


