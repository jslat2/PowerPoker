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
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

/**
 * Created by Gaming on 7/23/2015.
 */
public class Note extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.note, container, false);
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

        //add data to text fields
        if(MainActivity.currentNotePosition != -1){
            Cursor cr = MainActivity.db.getPlayerNotesTable(MainActivity.db);
            if(cr.moveToFirst()) {
                cr.moveToPosition(MainActivity.currentNotePosition);

                String first = cr.getString(2);
                String last = cr.getString(3);
                String email = cr.getString(4);
                String phone = cr.getString(5);
                String description = cr.getString(6);
                String location = cr.getString(7);
                String note = cr.getString(8);
                float rating = cr.getFloat(9);

                ((EditText) getActivity().findViewById(R.id.first)).setText(first);
                ((EditText) getActivity().findViewById(R.id.last)).setText(last);
                ((EditText) getActivity().findViewById(R.id.email)).setText(email);
                ((EditText) getActivity().findViewById(R.id.phone)).setText(phone);
                ((EditText) getActivity().findViewById(R.id.description)).setText(description);
                ((EditText) getActivity().findViewById(R.id.location)).setText(location);
                ((EditText) getActivity().findViewById(R.id.notes)).setText(note);
                ((RatingBar) getActivity().findViewById(R.id.ratingBar)).setRating(rating);
            }
        }
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

}
