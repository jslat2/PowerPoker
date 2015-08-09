package com.example.gaming.powerpoker;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.RelativeLayout;

/**
 * Created by Gaming on 7/28/2015.
 */
public class BankrollCreator extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.bankroll_creator, container, false);
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
