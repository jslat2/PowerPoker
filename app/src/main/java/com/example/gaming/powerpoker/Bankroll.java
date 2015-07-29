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

import java.util.ArrayList;

/**
 * Created by Gaming on 7/24/2015.
 */
public class Bankroll extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.bankroll, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //set up adapter for generated note list
        ArrayList<String> bankrolls = new ArrayList<String>();
        Cursor cr = MainActivity.db.getBankrollNames(MainActivity.db);

        if (cr.moveToFirst()) {
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
                if(cr.getInt(0) == MainActivity.userNum && !bankrolls.contains(cr.getString(1))) {
                    bankrolls.add(cr.getString(1));
                    cr.moveToNext();
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.list, bankrolls);
        ListView l = (ListView) getView().findViewById(R.id.listView);
        l.setAdapter(adapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                MainActivity.currentBankrollPosition = i;
                BankrollItem b = new BankrollItem();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, b).addToBackStack(null)
                        .commit();
            }
        });

    }
}
