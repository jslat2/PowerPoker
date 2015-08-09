package com.example.gaming.powerpoker;


import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gaming on 7/23/2015.
 */
public class PlayerNotes extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.player_notes, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Cursor cr = MainActivity.db.getPlayerNotesTableWithRowid(MainActivity.db);

        String[] dbFields = new String[] {"opponentLast", "opponentFirst", "rating"};
        int[] fields = new int[] {R.id.plLast, R.id.plFirst, R.id.plRating};

        SimpleCursorAdapter c = new SimpleCursorAdapter(getActivity(), R.layout.player_notes_list, cr, dbFields, fields);
        ListView list = (ListView)getActivity().findViewById(R.id.listView);
        list.setAdapter(c);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                MainActivity.currentNotePosition = i;
                Note n = new Note();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, n).addToBackStack(null)
                        .commit();
            }
        });

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
