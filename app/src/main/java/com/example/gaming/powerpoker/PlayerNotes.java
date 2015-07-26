package com.example.gaming.powerpoker;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

        //set up adapter for generated note list
        ArrayList<String> notes = new ArrayList<String>();
        Cursor cr = MainActivity.db.getPlayerNotesTable(MainActivity.db);

        if(cr.moveToFirst()) {
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
                notes.add(cr.getString(2) + ", " + cr.getString(3) + "          " + cr.getString(6) + "          " + cr.getFloat(9));
                cr.moveToNext();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.list, notes);
        ListView l = (ListView)getView().findViewById(R.id.listView);
        l.setAdapter(adapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                MainActivity.currentNote = i;
                Note n = new Note();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, n).addToBackStack(null)
                        .commit();
            }
        });
    }
}
