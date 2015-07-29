package com.example.gaming.powerpoker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RatingBar;

/**
 * Created by Gaming on 7/29/2015.
 */
public class BankrollItem extends Fragment {
    PopupWindow popupMessage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.bankroll_item, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ((EditText)getActivity().findViewById(R.id.bankrollAmt)).setText(Integer.toString(MainActivity.db.getBankrollTotal(MainActivity.db, MainActivity.currentBankrollPosition)));

        popupMessage = new PopupWindow(getActivity().findViewById(R.id.container), 100, 100, false);
        popupMessage.setContentView(getActivity().findViewById(R.id.equity));
        popupMessage.showAsDropDown(getActivity().findViewById(R.id.container));
    }

}
