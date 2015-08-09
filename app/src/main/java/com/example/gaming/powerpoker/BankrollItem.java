package com.example.gaming.powerpoker;

import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

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


        EditText bankrollAmt = ((EditText) getActivity().findViewById(R.id.bankrollAmt));
        EditText bankrollName = ((EditText) getActivity().findViewById(R.id.bankrollName));

        bankrollAmt.setText(dollarFormat(Double.toString(MainActivity.db.getBankrollTotal(MainActivity.db, MainActivity.currentBankrollPosition))));
        bankrollName.setText(MainActivity.db.getCurrentBankrollName(MainActivity.db));

        Cursor cr = MainActivity.db.getBankrollAmtsDates(MainActivity.db, MainActivity.db.getCurrentBankrollName(MainActivity.db));

        String[] dbFields = new String[] {"transDate", "transAmt"};
        int[] fields = new int[] {R.id.bankDate, R.id.bankAmt};


        SimpleCursorAdapter c = new SimpleCursorAdapter(getActivity(), R.layout.bankroll_list, cr, dbFields, fields);
        ListView list = (ListView)getActivity().findViewById(R.id.transactions);
        list.setAdapter(c);




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
}
