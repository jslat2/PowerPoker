package com.example.gaming.powerpoker;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gaming on 7/24/2015.
 */
public class Calculator extends Fragment{

    public equityCalc a = new equityCalc();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.calculator, container, false);
    }

    public void onStart(){
        super.onStart();

        if(!MainActivity.hero.playerCards.equals(null) && MainActivity.hero.playerCards.size() == 2){
            EditText heroHan = ((EditText)getActivity().findViewById(R.id.heroHan));
            heroHan.setText("    " + MainActivity.hero.playerCards.get(0).toUpperCase() + ", " + MainActivity.hero.playerCards.get(1).toUpperCase());
        }
        if(!MainActivity.opponentOne.playerCards.equals(null) && MainActivity.opponentOne.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppOneHand)).setText("    " + MainActivity.opponentOne.playerCards.get(0).toUpperCase() + ", " + MainActivity.opponentOne.playerCards.get(1).toUpperCase());
        }
        if(!MainActivity.opponentTwo.playerCards.equals(null) && MainActivity.opponentTwo.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppTwoHand)).setText("    " + MainActivity.opponentTwo.playerCards.get(0).toUpperCase() + ", " + MainActivity.opponentTwo.playerCards.get(1).toUpperCase());
        }
        if(!MainActivity.opponentThree.playerCards.equals(null) && MainActivity.opponentThree.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppThreeHand)).setText("    " + MainActivity.opponentThree.playerCards.get(0).toUpperCase() + ", " + MainActivity.opponentThree.playerCards.get(1).toUpperCase());
        }
        if(!MainActivity.opponentFour.playerCards.equals(null) && MainActivity.opponentFour.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppFourHand)).setText("    " + MainActivity.opponentFour.playerCards.get(0).toUpperCase() + ", " + MainActivity.opponentFour.playerCards.get(1).toUpperCase());
        }
        if(!MainActivity.opponentFive.playerCards.equals(null) && MainActivity.opponentFive.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppFiveHand)).setText("    " + MainActivity.opponentFive.playerCards.get(0).toUpperCase() + ", " + MainActivity.opponentFive.playerCards.get(1).toUpperCase());
        }
        if(!MainActivity.opponentSix.playerCards.equals(null) && MainActivity.opponentSix.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppSixHand)).setText("    " + MainActivity.opponentSix.playerCards.get(0).toUpperCase() + ", " + MainActivity.opponentSix.playerCards.get(1).toUpperCase());
        }
        if(!MainActivity.opponentSeven.playerCards.equals(null) && MainActivity.opponentSeven.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppSevenHand)).setText("    " + MainActivity.opponentSeven.playerCards.get(0).toUpperCase() + ", " + MainActivity.opponentSeven.playerCards.get(1).toUpperCase());
        }
        if(!MainActivity.opponentEight.playerCards.equals(null) && MainActivity.opponentEight.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppEightHand)).setText("    " + MainActivity.opponentEight.playerCards.get(0).toUpperCase() + ", " + MainActivity.opponentEight.playerCards.get(1).toUpperCase());
        }

    }

    public void onDestroyView(){
        super.onDestroyView();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        Chronometer timer = (Chronometer)getActivity().findViewById(R.id.timer);
        MainActivity.checkpoint = MainActivity.timeToMillis(timer.getText().toString());
        a.cancel(true);
    }
    public void onDestroy(){
        super.onDestroy();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        Chronometer timer = (Chronometer)getActivity().findViewById(R.id.timer);
        MainActivity.checkpoint = MainActivity.timeToMillis(timer.getText().toString());
        a.cancel(true);
    }
    public void onPause(){
        super.onPause();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        Chronometer timer = (Chronometer)getActivity().findViewById(R.id.timer);
        MainActivity.checkpoint = MainActivity.timeToMillis(timer.getText().toString());
        a.cancel(true);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
        else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }



        getActivity().findViewById(R.id.calcEquity).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer[] hero = new Integer[4];
                Integer[] oppOne = null;
                Integer[] oppTwo = null;
                Integer[] oppThree = null;
                Integer[] oppFour = null;
                Integer[] oppFive = null;
                Integer[] oppSix = null;
                Integer[] oppSeven = null;
                Integer[] oppEight = null;


                if (MainActivity.hero.playerCards.isEmpty()) {
                    Toast.makeText(getActivity(), "Hero has no cards!", Toast.LENGTH_LONG).show();
                } else {
                    hero[0] = letterToRank(MainActivity.hero.playerCards.get(0).charAt(MainActivity.hero.playerCards.get(0).length() - 1));
                    hero[1] = letterToRank(MainActivity.hero.playerCards.get(0).charAt(0));
                    hero[2] = letterToRank(MainActivity.hero.playerCards.get(1).charAt(MainActivity.hero.playerCards.get(1).length() - 1));
                    hero[3] = letterToRank(MainActivity.hero.playerCards.get(1).charAt(0));
                }

                if (MainActivity.opponentOne.playerCards.isEmpty() && MainActivity.opponentTwo.playerCards.isEmpty()
                        && MainActivity.opponentThree.playerCards.isEmpty() && MainActivity.opponentFour.playerCards.isEmpty()
                        && MainActivity.opponentFive.playerCards.isEmpty() && MainActivity.opponentSix.playerCards.isEmpty()
                        && MainActivity.opponentSeven.playerCards.isEmpty() && MainActivity.opponentEight.playerCards.isEmpty()) {
                    Toast.makeText(getActivity(), "No opponent has cards", Toast.LENGTH_LONG).show();
                } else {
                    if (!MainActivity.opponentOne.playerCards.isEmpty()) {
                        oppOne = new Integer[4];
                        oppOne[0] = letterToRank(MainActivity.opponentOne.playerCards.get(0).charAt(MainActivity.opponentOne.playerCards.get(0).length() - 1));
                        oppOne[1] = letterToRank(MainActivity.opponentOne.playerCards.get(0).charAt(0));
                        oppOne[2] = letterToRank(MainActivity.opponentOne.playerCards.get(1).charAt(MainActivity.opponentOne.playerCards.get(1).length() - 1));
                        oppOne[3] = letterToRank(MainActivity.opponentOne.playerCards.get(1).charAt(0));
                    }

                    if (!MainActivity.opponentTwo.playerCards.isEmpty()) {
                        oppTwo = new Integer[4];
                        oppTwo[0] = letterToRank(MainActivity.opponentTwo.playerCards.get(0).charAt(MainActivity.opponentTwo.playerCards.get(0).length() - 1));
                        oppTwo[1] = letterToRank(MainActivity.opponentTwo.playerCards.get(0).charAt(0));
                        oppTwo[2] = letterToRank(MainActivity.opponentTwo.playerCards.get(1).charAt(MainActivity.opponentTwo.playerCards.get(1).length() - 1));
                        oppTwo[3] = letterToRank(MainActivity.opponentTwo.playerCards.get(1).charAt(0));
                    }
                    if (!MainActivity.opponentThree.playerCards.isEmpty()) {
                        oppThree = new Integer[4];
                        oppThree[0] = letterToRank(MainActivity.opponentThree.playerCards.get(0).charAt(MainActivity.opponentThree.playerCards.get(0).length() - 1));
                        oppThree[1] = letterToRank(MainActivity.opponentThree.playerCards.get(0).charAt(0));
                        oppThree[2] = letterToRank(MainActivity.opponentThree.playerCards.get(1).charAt(MainActivity.opponentThree.playerCards.get(1).length() - 1));
                        oppThree[3] = letterToRank(MainActivity.opponentThree.playerCards.get(1).charAt(0));
                    }
                    if (!MainActivity.opponentFour.playerCards.isEmpty()) {
                        oppFour = new Integer[4];
                        oppFour[0] = letterToRank(MainActivity.opponentFour.playerCards.get(0).charAt(MainActivity.opponentFour.playerCards.get(0).length() - 1));
                        oppFour[1] = letterToRank(MainActivity.opponentFour.playerCards.get(0).charAt(0));
                        oppFour[2] = letterToRank(MainActivity.opponentFour.playerCards.get(1).charAt(MainActivity.opponentFour.playerCards.get(1).length() - 1));
                        oppFour[3] = letterToRank(MainActivity.opponentFour.playerCards.get(1).charAt(0));
                    }
                    if (!MainActivity.opponentFive.playerCards.isEmpty()) {
                        oppFive = new Integer[4];
                        oppFive[0] = letterToRank(MainActivity.opponentFive.playerCards.get(0).charAt(MainActivity.opponentFive.playerCards.get(0).length() - 1));
                        oppFive[1] = letterToRank(MainActivity.opponentFive.playerCards.get(0).charAt(0));
                        oppFive[2] = letterToRank(MainActivity.opponentFive.playerCards.get(1).charAt(MainActivity.opponentFive.playerCards.get(1).length() - 1));
                        oppFive[3] = letterToRank(MainActivity.opponentFive.playerCards.get(1).charAt(0));
                    }
                    if (!MainActivity.opponentSix.playerCards.isEmpty()) {
                        oppSix = new Integer[4];
                        oppSix[0] = letterToRank(MainActivity.opponentSix.playerCards.get(0).charAt(MainActivity.opponentSix.playerCards.get(0).length() - 1));
                        oppSix[1] = letterToRank(MainActivity.opponentSix.playerCards.get(0).charAt(0));
                        oppSix[2] = letterToRank(MainActivity.opponentSix.playerCards.get(1).charAt(MainActivity.opponentSix.playerCards.get(1).length() - 1));
                        oppSix[3] = letterToRank(MainActivity.opponentSix.playerCards.get(1).charAt(0));
                    }
                    if (!MainActivity.opponentSeven.playerCards.isEmpty()) {
                        oppSeven = new Integer[4];
                        oppSeven[0] = letterToRank(MainActivity.opponentSeven.playerCards.get(0).charAt(MainActivity.opponentSeven.playerCards.get(0).length() - 1));
                        oppSeven[1] = letterToRank(MainActivity.opponentSeven.playerCards.get(0).charAt(0));
                        oppSeven[2] = letterToRank(MainActivity.opponentSeven.playerCards.get(1).charAt(MainActivity.opponentSeven.playerCards.get(1).length() - 1));
                        oppSeven[3] = letterToRank(MainActivity.opponentSeven.playerCards.get(1).charAt(0));
                    }
                    if (!MainActivity.opponentEight.playerCards.isEmpty()) {
                        oppEight = new Integer[4];
                        oppEight[0] = letterToRank(MainActivity.opponentEight.playerCards.get(0).charAt(MainActivity.opponentEight.playerCards.get(0).length() - 1));
                        oppEight[1] = letterToRank(MainActivity.opponentEight.playerCards.get(0).charAt(0));
                        oppEight[2] = letterToRank(MainActivity.opponentEight.playerCards.get(1).charAt(MainActivity.opponentEight.playerCards.get(1).length() - 1));
                        oppEight[3] = letterToRank(MainActivity.opponentEight.playerCards.get(1).charAt(0));
                    }
                    a = new equityCalc();
                    a.execute(hero, oppOne, oppTwo, oppThree, oppFour, oppFive, oppSix, oppSeven, oppEight, null);
                }
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


    public int letterToRank(char c){
        switch (c){
            case 'a':return 12;
            case 'A':return 12;
            case 'k':return 11;
            case 'K':return 11;
            case 'q':return 10;
            case 'Q':return 10;
            case 'j':return 9;
            case 'J':return 9;
            case '1':return 8;
            case '9':return 7;
            case '8':return 6;
            case '7':return 5;
            case '6':return 4;
            case '5':return 3;
            case '4':return 2;
            case '3':return 1;
            case '2':return 0;
            case 'h':return 1;
            case 'H':return 1;
            case 'd':return 2;
            case 'D':return 2;
            case 's':return 3;
            case 'S':return 3;
            case 'c':return 4;
            case 'C':return 4;
        }
        return -1;
    }

    private class equityCalc extends AsyncTask<Integer[], String, Double>{

        double result = 0;
        boolean calculating = true;

        private int[] space = {1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 1, 10, 1, 11, 1, 12,
                2, 0, 2, 1, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6, 2, 7, 2, 8, 2, 9, 2, 10, 2, 11, 2, 12,
                3, 0, 3, 1, 3, 2, 3, 3, 3, 4, 3, 5, 3, 6, 3, 7, 3, 8, 3, 9, 3, 10, 3, 11, 3, 12,
                4, 0, 4, 1, 4, 2, 4, 3, 4, 4, 4, 5, 4, 6, 4, 7, 4, 8, 4, 9, 4, 10, 4, 11, 4, 12};


        private ArrayList flushRank(int[] cards){
            int clubs = 0;
            int diamonds = 0;
            int hearts = 0;
            int spades = 0;
            int highSuit = 0;
            int straightFlushStart = -1;
            ArrayList none = new ArrayList<Integer>();
            ArrayList done = new ArrayList<Integer>();
            none.add(0);


            //check to see if there is 5 of one suit
            for(int i = 0; i<7; i++){
                if(cards[i*2] == 1){
                    clubs++;
                    if(clubs>=5){
                        highSuit = 1;
                    }
                }

                if(cards[i*2] == 2){
                    diamonds++;
                    if(diamonds>=5){
                        highSuit = 2;
                    }
                }

                if(cards[i*2] == 3){
                    hearts++;
                    if(hearts>=5){
                        highSuit = 3;
                    }
                }

                if(cards[i*2] == 4){
                    spades++;
                    if(spades>=5){
                        highSuit = 4;
                    }
                }
            }

            if(highSuit == 0){
                return none;
            }

            //Arrange suited cards of the flush suit in order largest to smallest
            ArrayList holder = new ArrayList<Integer>();
            for(int i = 0; i < 7; i++){
                if(cards[i*2] == highSuit){
                    if(holder.size() == 0){
                        holder.add((Integer)cards[i*2+1]);
                    }
                    else{
                        for(int j = 0; j < holder.size(); j++){
                            if(cards[i*2+1] > (int)holder.get(j)){
                                holder.add(j, cards[i*2+1]);
                                break;
                            }
                            if(j == holder.size()-1){
                                holder.add(holder.size(), cards[i*2+1]);
                                break;
                            }
                        }
                    }
                }

            }

            if(holder.get(0) == (Integer)12){
                holder.add((Integer)(-1));
            }

            //Check for straight flushes
            for(int i = 0; i < holder.size()-4; i++){
                if((int)holder.get(i) == (int)holder.get(i+1)+1){
                    if((int)holder.get(i+1) == (int)holder.get(i+2)+1){
                        if((int)holder.get(i+2) == (int)holder.get(i+3)+1){
                            if((int)holder.get(i+3) == (int)holder.get(i+4)+1){
                                straightFlushStart = i;
                                break;
                            }
                        }
                    }
                }
            }

            //If no straight flushes return flush value
            if(straightFlushStart == -1){
                done.add(5);
                for(int i = 0; i < 5; i++){
                    done.add(holder.get(i));
                }
                return done;
            }

            //If there is a straight flush return rank 8 and the highest card in the straight flush
            //no need to code for the lower ranks in the straight flush as they do not contribute
            //to the comparison value
            else if(straightFlushStart != -1){
                done.add(8);
                done.add(holder.get(straightFlushStart));
                return done;
            }
            return none;
        }
        private ArrayList straightRank(int[] cards){

            int straightStart = -1;

            ArrayList holder = new ArrayList<Integer>();
            ArrayList none = new ArrayList<Integer>();
            ArrayList done = new ArrayList<Integer>();
            none.add((Integer)0);
            holder.add(cards[1]);

            for(int i = 1; i < 7; i++){
                for(int j = 0; j < holder.size(); j++){
                    //don't add cards of same rank
                    if(cards[i*2+1] == (int)holder.get(j)){
                        break;
                    }
                    if(cards[i*2+1] > (int)holder.get(j)){
                        holder.add(j, cards[i*2+1]);
                        break;
                    }
                    if(j == holder.size()-1){
                        holder.add(holder.size(), cards[i*2+1]);
                        break;
                    }
                }
            }

            if(holder.get(0) == (Integer)12){
                holder.add((Integer)(-1));
            }
            for(int i = 0; i < holder.size()-4; i++){
                if((int)holder.get(i) == (int)holder.get(i+1)+1){
                    if((int)holder.get(i+1) == (int)holder.get(i+2)+1){
                        if((int)holder.get(i+2) == (int)holder.get(i+3)+1){
                            if((int)holder.get(i+3) == (int)holder.get(i+4)+1){
                                straightStart = i;
                                break;
                            }
                        }
                    }
                }
            }

            if(straightStart == -1){
                return none;
            }

            else{
                done.add(4);
                done.add(holder.get(straightStart));
            }

            return done;
        }

        private ArrayList pairRank(int[] cards){

            ArrayList threes = new ArrayList<Integer>();
            ArrayList twos = new ArrayList<Integer>();

            ArrayList holder = new ArrayList<Integer>();
            ArrayList done = new ArrayList<Integer>();
            int[] ranks = new int[7];
            int[] histogram = new int[13];
            for(int i =0; i < 12; i++){
                histogram[i] = 0;
            }

            for(int i = 0; i < 7; i++){
                ranks[i] = cards[i*2+1];
            }

            holder.add(ranks[0]);

            //Arrange cards by rank largest to smallest
            for(int i = 1; i < 7; i++){
                for(int j = 0; j < holder.size(); j++){
                    if(ranks[i] > (int)holder.get(j)){
                        holder.add(j, ranks[i]);
                        break;
                    }
                    if(j == holder.size()-1){
                        holder.add(holder.size(), ranks[i]);
                        break;
                    }
                }
            }

            //Make a histogram of the card ranks starting with high cards
            for(int i = 0; i < 7; i ++){
                if((int)holder.get(i) == 12){
                    histogram[0]++;
                }
                if((int)holder.get(i) == 11){
                    histogram[1]++;
                }
                if((int)holder.get(i) == 10){
                    histogram[2]++;
                }
                if((int)holder.get(i) == 9){
                    histogram[3]++;
                }
                if((int)holder.get(i) == 8){
                    histogram[4]++;
                }
                if((int)holder.get(i) == 7){
                    histogram[5]++;
                }
                if((int)holder.get(i) == 6){
                    histogram[6]++;
                }
                if((int)holder.get(i) == 5){
                    histogram[7]++;
                }
                if((int)holder.get(i) == 4){
                    histogram[8]++;
                }
                if((int)holder.get(i) == 3){
                    histogram[9]++;
                }
                if((int)holder.get(i) == 2){
                    histogram[10]++;
                }
                if((int)holder.get(i) == 1){
                    histogram[11]++;
                }
                if((int)holder.get(i) == 0){
                    histogram[12]++;
                }
            }

            //Scan the histogram for quads and return quad rank if applicable, add in kicker
            for(int i = 0; i < 13; i++){
                if(histogram[i] == 4){
                    done.add(7);
                    done.add(12-i);
                    for(int j = 0; j < histogram.length; j++){
                        if(histogram[j] != 0 && (histogram[j] != 4)){
                            done.add(12-j);
                            break;
                        }
                    }
                    return done;
                }
            }

            //Scan for triples and add to an arraylist
            for(int i = 0; i < 13; i++){
                if(histogram[i] == 3){
                    threes.add((Integer)(12-i));
                }
            }

            //Scan for pairs and add to an arraylist (disregarding a third pair if there is one)
            for(int i = 0; i < 13; i++){
                if(histogram[i] == 2){
                    twos.add((Integer)(12-i));
                    if(twos.size() == 2){
                        break;
                    }
                }
            }

            //Check for full houses, including two three of a kinds or 1 three of a kind and at least one pair
            if(threes.size() > 1 || (threes.size() > 0 && twos.size() > 0)){
                done.add(6);
                done.add(threes.get(0));
                if(threes.size() > 1 && twos.size() > 0){
                    if((int)threes.get(1) > (int)twos.get(0)){
                        done.add(threes.get(1));
                    }
                    else{
                        done.add(twos.get(0));
                    }
                    return done;
                }

                else if(threes.size() > 1){
                    done.add(threes.get(1));
                    return done;
                }

                done.add(twos.get(0));
                return done;
            }

            //Check for three of a kind, add in kickers and return
            else if(threes.size() > 0){
                done.add(3);
                done.add(threes.get(0));

                //add kicker
                for(int i = 0; i < holder.size(); i++){
                    if((int)holder.get(i) != (int)threes.get(0)){
                        done.add(holder.get(i));
                    }
                    if(done.size() == 4){
                        break;
                    }
                }
                return done;
            }

            //Check for two pair, add in kickers and return
            else if(twos.size() > 1){
                done.add(2);
                done.add(twos.get(0));
                done.add(twos.get(1));

                //add kickers
                for(int i = 0; i < holder.size(); i++){
                    if((int)holder.get(i) != (int)twos.get(0)  && ((int)holder.get(i) != (int)twos.get(1))){
                        done.add(holder.get(i));
                        break;
                    }
                }
                return done;

            }

            //Check for pairs add in kickers and return
            else if(twos.size() == 1){
                done.add(1);
                done.add(twos.get(0));
                for(int i = 0; i < holder.size(); i++){
                    if(holder.get(i) != twos.get(0)){
                        done.add(holder.get(i));
                        if(done.size() == 5){
                            break;
                        }
                    }

                }
                return done;
            }

            //If none of the above apply add 0 rank and add in 5 kickers
            done.add(0);
            for(int i = 0; i < 5; i++){
                done.add(holder.get(i));
            }

            return done;
        }


        private ArrayList handRank(int[] cards){

            if((int)flushRank(cards).get(0) != 0){
                return flushRank(cards);
            }

            else if((int)straightRank(cards).get(0) != 0){
                return straightRank(cards);
            }

            return pairRank(cards);
        }

        public ArrayList<Double> determineWinner(ArrayList hero, ArrayList oppOne, ArrayList oppTwo, ArrayList oppThree, ArrayList oppFour, ArrayList oppFive,
                                                 ArrayList oppSix, ArrayList oppSeven, ArrayList oppEight){

            ArrayList<Integer> winningRank = determineWinningRank(hero, oppOne, oppTwo, oppThree, oppFour, oppFive, oppSix, oppSeven, oppEight);
            ArrayList<Double> winningArray = new ArrayList<>();
            int numberWinners = 0;


            for(int i = 0; i < hero.size(); i++){
                if(hero.get(i) != winningRank.get(i)){
                    winningArray.add((double)0);
                    break;
                }
                if(i == hero.size() - 1){
                    winningArray.add((double)1);
                    numberWinners++;
                }
            }

            if(oppOne != null) {
                for (int i = 0; i < oppOne.size(); i++) {
                    if (oppOne.get(i) != winningRank.get(i)) {
                        winningArray.add((double) 0);
                        break;
                    }
                    if (i == oppOne.size() - 1) {
                        winningArray.add((double) 1);
                        numberWinners++;
                    }
                }
            }

            if(oppTwo != null) {
                for (int i = 0; i < oppTwo.size(); i++) {
                    if (oppTwo.get(i) != winningRank.get(i)) {
                        winningArray.add((double) 0);
                        break;
                    }
                    if (i == oppTwo.size() - 1) {
                        winningArray.add((double) 1);
                        numberWinners++;
                    }
                }
            }


            if(oppThree != null) {
                for (int i = 0; i < oppThree.size(); i++) {
                    if (oppThree.get(i) != winningRank.get(i)) {
                        winningArray.add((double) 0);
                        break;
                    }
                    if (i == oppThree.size() - 1) {
                        winningArray.add((double) 1);
                        numberWinners++;
                    }
                }
            }


            if(oppFour != null) {
                for (int i = 0; i < oppFour.size(); i++) {
                    if (oppFour.get(i) != winningRank.get(i)) {
                        winningArray.add((double) 0);
                        break;
                    }
                    if (i == oppFour.size() - 1) {
                        winningArray.add((double) 1);
                        numberWinners++;
                    }
                }
            }

            if(oppFive != null) {
                for (int i = 0; i < oppFive.size(); i++) {
                    if (oppFive.get(i) != winningRank.get(i)) {
                        winningArray.add((double) 0);
                        break;
                    }
                    if (i == oppFive.size() - 1) {
                        winningArray.add((double) 1);
                        numberWinners++;
                    }
                }
            }

            if(oppSix != null) {
                for (int i = 0; i < oppSix.size(); i++) {
                    if (oppSix.get(i) != winningRank.get(i)) {
                        winningArray.add((double) 0);
                        break;
                    }
                    if (i == oppSix.size() - 1) {
                        winningArray.add((double) 1);
                        numberWinners++;
                    }
                }
            }

            if(oppSeven != null) {
                for (int i = 0; i < oppSeven.size(); i++) {
                    if (oppSeven.get(i) != winningRank.get(i)) {
                        winningArray.add((double) 0);
                        break;
                    }
                    if (i == oppSeven.size() - 1) {
                        winningArray.add((double) 1);
                        numberWinners++;
                    }
                }
            }

            if(oppEight != null) {
                for (int i = 0; i < oppEight.size(); i++) {
                    if (oppEight.get(i) != winningRank.get(i)) {
                        winningArray.add((double) 0);
                        break;
                    }
                    if (i == oppEight.size() - 1) {
                        winningArray.add((double) 1);
                        numberWinners++;
                    }
                }
            }

            for(int i = 0; i < winningArray.size(); i++){
                if(winningArray.get(i) != 0){
                    winningArray.remove(i);
                    winningArray.add(i, (double)1/numberWinners);
                }
            }
            return winningArray;
        }

        public ArrayList<Integer> determineWinningRank(ArrayList<Integer> hero, ArrayList oppOne, ArrayList oppTwo, ArrayList oppThree, ArrayList oppFour,
                                                       ArrayList oppFive, ArrayList oppSix, ArrayList oppSeven, ArrayList oppEight){

            int numPlayers = 1;
            int highestColumnRank = -1;
            ArrayList<Integer> blankArray = new ArrayList();
            int lastRemainingIndex = 1;
            blankArray.add(-1);

            ArrayList<ArrayList> remainingPlayers = new ArrayList<>();
            remainingPlayers.add(hero);

            if(oppOne != null) {
                remainingPlayers.add(oppOne);
            }
            else{
                remainingPlayers.add(blankArray);
            }
            if(oppTwo != null) {
                remainingPlayers.add(oppTwo);
            }
            else{
                remainingPlayers.add(blankArray);
            }
            if(oppThree != null) {
                remainingPlayers.add(oppThree);
            }
            else{
                remainingPlayers.add(blankArray);
            }
            if(oppFour != null) {
                remainingPlayers.add(oppFour);
            }
            else{
                remainingPlayers.add(blankArray);
            }
            if(oppFive != null) {
                remainingPlayers.add(oppFive);
            }
            else{
                remainingPlayers.add(blankArray);
            }
            if(oppSix != null) {
                remainingPlayers.add(oppSix);
            }
            else{
                remainingPlayers.add(blankArray);
            }
            if(oppSeven != null) {
                remainingPlayers.add(oppSeven);
            }
            else{
                remainingPlayers.add(blankArray);
            }
            if(oppEight != null) {
                remainingPlayers.add(oppEight);
            }
            else{
                remainingPlayers.add(blankArray);
            }


            for(int i = 0; i < lastRemainingIndex; i++) {
                //loop through once to determine highest rank in the column
                for (int j = 0; j < remainingPlayers.size(); j++) {
                    if(remainingPlayers.get(j).get(0) != -1 && (int)remainingPlayers.get(j).get(i) > highestColumnRank){
                        highestColumnRank = (int)remainingPlayers.get(j).get(i);
                        lastRemainingIndex = remainingPlayers.get(j).size();
                    }
                }
                //second loop to eliminate all hands not of the highest rank
                for(int j = 0; j < remainingPlayers.size(); j++){
                    if(remainingPlayers.get(j).get(0) != -1 && (int)remainingPlayers.get(j).get(i) < highestColumnRank){
                        remainingPlayers.remove(j);
                        remainingPlayers.add(j, blankArray);
                    }
                }
                highestColumnRank = -1;
            }

            for(int i = 0; i < remainingPlayers.size(); i++){
                if(remainingPlayers.get(i).get(0) != -1){
                    return remainingPlayers.get(i);
                }
            }
            return blankArray;
        }

        public double monteCarlo(Integer[] cards, Integer[] oppOne, Integer[] oppTwo,  Integer[] oppThree,  Integer[] oppFour,  Integer[] oppFive,
                                 Integer[] oppSix,  Integer[] oppSeven,  Integer[] oppEight, Integer[] board, int simLength){


            double heroWins = 0;
            double oppOneWins = 0;
            double oppTwoWins = 0;
            double oppThreeWins = 0;
            double oppFourWins = 0;
            double oppFiveWins = 0;
            double oppSixWins = 0;
            double oppSevenWins = 0;
            double oppEightWins = 0;

            double count = 0;
            boolean newCard = true;

            int[] cardsTotal = new int[14];
            int[] oppOneTotal = new int[14];
            int[] oppTwoTotal = new int[14];
            int[] oppThreeTotal = new int[14];
            int[] oppFourTotal = new int[14];
            int[] oppFiveTotal = new int[14];
            int[] oppSixTotal = new int[14];
            int[] oppSevenTotal = new int[14];
            int[] oppEightTotal = new int[14];

            for(int i = 0; i < 4; i++){
                cardsTotal[i] = cards[i];

                if(oppOne != null) {
                    oppOneTotal[i] = oppOne[i];
                }
                if (oppTwo != null) {
                    oppTwoTotal[i] = oppTwo[i];
                }
                if (oppThree != null) {
                    oppThreeTotal[i] = oppThree[i];
                }
                if (oppFour != null) {
                    oppFourTotal[i] = oppFour[i];
                }
                if (oppFive != null) {
                    oppFiveTotal[i] = oppFive[i];
                }
                if (oppSix != null) {
                    oppSixTotal[i] = oppSix[i];
                }
                if (oppSeven != null) {
                    oppSevenTotal[i] = oppSeven[i];
                }
                if (oppEight != null) {
                    oppEightTotal[i] = oppEight[i];
                }
            }

            if(board != null){
                for(int i = 0; i < board.length; i++) {
                    cardsTotal[i+4] = board[i];
                    if(oppOne != null) {
                        oppOneTotal[i + 4] = board[i];
                    }
                    if(oppTwo != null) {
                        oppTwoTotal[i + 4] = board[i];
                    }
                    if(oppThree != null) {
                        oppThreeTotal[i + 4] = board[i];
                    }
                    if(oppFour != null) {
                        oppFourTotal[i + 4] = board[i];
                    }
                    if(oppFive != null) {
                        oppFiveTotal[i + 4] = board[i];
                    }
                    if(oppSix != null) {
                        oppSixTotal[i + 4] = board[i];
                    }
                    if(oppSeven != null) {
                        oppSevenTotal[i + 4] = board[i];
                    }
                    if(oppEight != null) {
                        oppEightTotal[i + 4] = board[i];
                    }
                }
            }

            int cardsToTakeOut = 0;
            if(board != null){
                cardsToTakeOut += board.length;
            }
            if(oppOne != null){
                cardsToTakeOut += 4;
            }
            if(oppTwo != null){
                cardsToTakeOut += 4;
            }
            if(oppThree != null){
                cardsToTakeOut += 4;
            }
            if(oppFour != null){
                cardsToTakeOut += 4;
            }
            if(oppFive != null){
                cardsToTakeOut += 4;
            }
            if(oppSix != null){
                cardsToTakeOut += 4;
            }
            if(oppSeven != null){
                cardsToTakeOut += 4;
            }
            if(oppEight != null){
                cardsToTakeOut += 4;
            }

            //take cards out of space for potential cards to come
            int[] remainingCards = new int[100-cardsToTakeOut];

            int counter = 0;

            for(int i = 0; i < 103; i = i+2){
                for(int j = 0; j < 3; j = j+2){
                    if(cards[j] == space[i] && cards[j+1] == space[i+1]){
                        newCard = false;
                    }
                }

                if(oppOne != null) {
                    for (int j = 0; j < 3; j = j + 2) {
                        if (oppOne[j] == space[i] && oppOne[j + 1] == space[i + 1]) {
                            newCard = false;
                        }
                    }
                }

                if((oppTwo != null)) {
                    for (int j = 0; j < 3; j = j + 2) {
                        if (oppTwo[j] == space[i] && oppTwo[j +1] == space[i+1]) {
                            newCard = false;
                        }
                    }
                }
                if((oppThree != null)) {
                    for (int j = 0; j < 3; j = j + 2) {
                        if (oppThree[j] == space[i] && oppThree[j +1] == space[i+1]) {
                            newCard = false;
                        }
                    }
                }

                if((oppFour != null)) {
                    for (int j = 0; j < 3; j = j + 2) {
                        if (oppFour[j] == space[i] && oppFour[j +1] == space[i+1]) {
                            newCard = false;
                        }
                    }
                }
                if((oppFive != null)) {
                    for (int j = 0; j < 3; j = j + 2) {
                        if (oppFive[j] == space[i] && oppFive[j +1] == space[i+1]) {
                            newCard = false;
                        }
                    }
                }
                if((oppSix != null)) {
                    for (int j = 0; j < 3; j = j + 2) {
                        if (oppSix[j] == space[i] && oppSix[j +1] == space[i+1]) {
                            newCard = false;
                        }
                    }
                }
                if((oppSeven != null)) {
                    for (int j = 0; j < 3; j = j + 2) {
                        if (oppSeven[j] == space[i] && oppSeven[j +1] == space[i+1]) {
                            newCard = false;
                        }
                    }
                }
                if((oppEight != null)) {
                    for (int j = 0; j < 3; j = j + 2) {
                        if (oppEight[j] == space[i] && oppEight[j +1] == space[i+1]) {
                            newCard = false;
                        }
                    }
                }
                if(board != null){
                    for(int j = 0; j < 3; j = j+2) {
                        if (board[j] == space[i] && board[j + 1] == space[i + 1]) {
                            newCard = false;
                        }
                    }
                }
                //only add the card to remaining if it is not one already on board or in a hand
                if(newCard){
                    remainingCards[counter] = space[i];
                    remainingCards[counter+1] = space[i+1];
                    counter+=2;
                }

                //reset the boolean
                newCard = true;
            }



            if(board == null) {

                for (int i = 0; i < simLength; i++) {

                    Random n = new Random();
                    int a = n.nextInt((remainingCards.length) / 2);
                    int b = n.nextInt((remainingCards.length) / 2);
                    while (b == a) {
                        b = n.nextInt((remainingCards.length) / 2);
                    }
                    int c = n.nextInt((remainingCards.length) / 2);
                    while (c == b || c == a) {
                        c = n.nextInt((remainingCards.length) / 2);
                    }
                    int d = n.nextInt((remainingCards.length) / 2);
                    while (d == c || d == b || d == a) {
                        d = n.nextInt((remainingCards.length) / 2);
                    }
                    int e = n.nextInt((remainingCards.length) / 2);
                    while (e == d || e == c || e == b || e == a) {
                        e = n.nextInt((remainingCards.length) / 2);
                    }


                    cardsTotal[4] = remainingCards[a*2];
                    cardsTotal[5] = remainingCards[a*2+1];
                    cardsTotal[6] = remainingCards[b*2];
                    cardsTotal[7] = remainingCards[b*2+1];
                    cardsTotal[8] = remainingCards[c*2];
                    cardsTotal[9] = remainingCards[c*2+1];
                    cardsTotal[10] = remainingCards[d*2];
                    cardsTotal[11] = remainingCards[d*2+1];
                    cardsTotal[12] = remainingCards[e*2];
                    cardsTotal[13] = remainingCards[e*2+1];

                    if(oppOne != null) {
                        oppOneTotal[4] = remainingCards[a * 2];
                        oppOneTotal[5] = remainingCards[a * 2 + 1];
                        oppOneTotal[6] = remainingCards[b * 2];
                        oppOneTotal[7] = remainingCards[b * 2 + 1];
                        oppOneTotal[8] = remainingCards[c * 2];
                        oppOneTotal[9] = remainingCards[c * 2 + 1];
                        oppOneTotal[10] = remainingCards[d * 2];
                        oppOneTotal[11] = remainingCards[d * 2 + 1];
                        oppOneTotal[12] = remainingCards[e * 2];
                        oppOneTotal[13] = remainingCards[e * 2 + 1];
                    }

                    if (oppTwo != null) {
                        oppTwoTotal[4] = remainingCards[a*2];
                        oppTwoTotal[5] = remainingCards[a*2+1];
                        oppTwoTotal[6] = remainingCards[b*2];
                        oppTwoTotal[7] = remainingCards[b*2+1];
                        oppTwoTotal[8] = remainingCards[c*2];
                        oppTwoTotal[9] = remainingCards[c*2+1];
                        oppTwoTotal[10] = remainingCards[d*2];
                        oppTwoTotal[11] = remainingCards[d*2+1];
                        oppTwoTotal[12] = remainingCards[e*2];
                        oppTwoTotal[13] = remainingCards[e*2+1];
                    }
                    if (oppThree != null) {
                        oppThreeTotal[4] = remainingCards[a*2];
                        oppThreeTotal[5] = remainingCards[a*2+1];
                        oppThreeTotal[6] = remainingCards[b*2];
                        oppThreeTotal[7] = remainingCards[b*2+1];
                        oppThreeTotal[8] = remainingCards[c*2];
                        oppThreeTotal[9] = remainingCards[c*2+1];
                        oppThreeTotal[10] = remainingCards[d*2];
                        oppThreeTotal[11] = remainingCards[d*2+1];
                        oppThreeTotal[12] = remainingCards[e*2];
                        oppThreeTotal[13] = remainingCards[e*2+1];
                    }

                    if (oppFour != null) {
                        oppFourTotal[4] = remainingCards[a*2];
                        oppFourTotal[5] = remainingCards[a*2+1];
                        oppFourTotal[6] = remainingCards[b*2];
                        oppFourTotal[7] = remainingCards[b*2+1];
                        oppFourTotal[8] = remainingCards[c*2];
                        oppFourTotal[9] = remainingCards[c*2+1];
                        oppFourTotal[10] = remainingCards[d*2];
                        oppFourTotal[11] = remainingCards[d*2+1];
                        oppFourTotal[12] = remainingCards[e*2];
                        oppFourTotal[13] = remainingCards[e*2+1];
                    }
                    if (oppFive != null) {
                        oppFiveTotal[4] = remainingCards[a*2];
                        oppFiveTotal[5] = remainingCards[a*2+1];
                        oppFiveTotal[6] = remainingCards[b*2];
                        oppFiveTotal[7] = remainingCards[b*2+1];
                        oppFiveTotal[8] = remainingCards[c*2];
                        oppFiveTotal[9] = remainingCards[c*2+1];
                        oppFiveTotal[10] = remainingCards[d*2];
                        oppFiveTotal[11] = remainingCards[d*2+1];
                        oppFiveTotal[12] = remainingCards[e*2];
                        oppFiveTotal[13] = remainingCards[e*2+1];
                    }

                    if (oppSix != null) {
                        oppSixTotal[4] = remainingCards[a*2];
                        oppSixTotal[5] = remainingCards[a*2+1];
                        oppSixTotal[6] = remainingCards[b*2];
                        oppSixTotal[7] = remainingCards[b*2+1];
                        oppSixTotal[8] = remainingCards[c*2];
                        oppSixTotal[9] = remainingCards[c*2+1];
                        oppSixTotal[10] = remainingCards[d*2];
                        oppSixTotal[11] = remainingCards[d*2+1];
                        oppSixTotal[12] = remainingCards[e*2];
                        oppSixTotal[13] = remainingCards[e*2+1];
                    }

                    if (oppSeven != null) {
                        oppSevenTotal[4] = remainingCards[a*2];
                        oppSevenTotal[5] = remainingCards[a*2+1];
                        oppSevenTotal[6] = remainingCards[b*2];
                        oppSevenTotal[7] = remainingCards[b*2+1];
                        oppSevenTotal[8] = remainingCards[c*2];
                        oppSevenTotal[9] = remainingCards[c*2+1];
                        oppSevenTotal[10] = remainingCards[d*2];
                        oppSevenTotal[11] = remainingCards[d*2+1];
                        oppSevenTotal[12] = remainingCards[e*2];
                        oppSevenTotal[13] = remainingCards[e*2+1];
                    }
                    if (oppEight != null) {
                        oppEightTotal[4] = remainingCards[a*2];
                        oppEightTotal[5] = remainingCards[a*2+1];
                        oppEightTotal[6] = remainingCards[b*2];
                        oppEightTotal[7] = remainingCards[b*2+1];
                        oppEightTotal[8] = remainingCards[c*2];
                        oppEightTotal[9] = remainingCards[c*2+1];
                        oppEightTotal[10] = remainingCards[d*2];
                        oppEightTotal[11] = remainingCards[d*2+1];
                        oppEightTotal[12] = remainingCards[e*2];
                        oppEightTotal[13] = remainingCards[e*2+1];
                    }

                    if(!isCancelled()) {
                        heroWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(0);
                        oppOneWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(1);
                        oppTwoWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(2);
                        oppThreeWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(3);
                        oppFourWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(4);
                        oppFiveWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(5);
                        oppSixWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(6);
                        oppSevenWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(7);
                        oppEightWins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal), handRank(oppThreeTotal), handRank(oppFourTotal), handRank(oppFiveTotal),
                                handRank(oppSixTotal), handRank(oppSevenTotal), handRank(oppEightTotal)).get(8);

                        count++;

                        String[] updateArray = new String[9];
                        updateArray[0] = Double.toString(heroWins / count);
                        updateArray[1] = Double.toString(oppOneWins / count);
                        updateArray[2] = Double.toString(oppTwoWins / count);
                        updateArray[3] = Double.toString(oppThreeWins / count);
                        updateArray[4] = Double.toString(oppFourWins / count);
                        updateArray[5] = Double.toString(oppFiveWins / count);
                        updateArray[6] = Double.toString(oppSixWins / count);
                        updateArray[7] = Double.toString(oppSevenWins / count);
                        updateArray[8] = Double.toString(oppEightWins / count);

                        if(count %100 == 0) {
                            publishProgress(updateArray);
                        }
                    }
                }
            }

            return result;
        }


        @Override
        protected Double doInBackground(Integer[]... params) {
            monteCarlo(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8], null, 50000);
            return result;
        }


        @Override
        protected void onProgressUpdate(String... x){
            super.onProgressUpdate(x);

            if(!isCancelled()) {
                double heroRounded = Math.round(Double.parseDouble(x[0]) * 1000);
                heroRounded = heroRounded / 1000;

                double oppOneRounded = Math.round(Double.parseDouble(x[1]) * 1000);
                oppOneRounded = oppOneRounded / 1000;

                double oppTwoRounded = Math.round(Double.parseDouble(x[2]) * 1000);
                oppTwoRounded = oppTwoRounded / 1000;

                double oppThreeRounded = Math.round(Double.parseDouble(x[3]) * 1000);
                oppThreeRounded = oppThreeRounded / 1000;

                double oppFourRounded = Math.round(Double.parseDouble(x[4]) * 1000);
                oppFourRounded = oppFourRounded / 1000;

                double oppFiveRounded = Math.round(Double.parseDouble(x[5]) * 1000);
                oppFiveRounded = oppFiveRounded / 1000;

                double oppSixRounded = Math.round(Double.parseDouble(x[6]) * 1000);
                oppSixRounded = oppSixRounded / 1000;

                double oppSevenRounded = Math.round(Double.parseDouble(x[7]) * 1000);
                oppSevenRounded = oppSevenRounded / 1000;

                double oppEightRounded = Math.round(Double.parseDouble(x[8]) * 1000);
                oppEightRounded = oppEightRounded / 1000;


                ((EditText) getActivity().findViewById(R.id.hEquity)).setText(Double.toString(heroRounded));
                ((EditText) getActivity().findViewById(R.id.oneEquity)).setText(Double.toString(oppOneRounded));
                ((EditText) getActivity().findViewById(R.id.twoEquity)).setText(Double.toString(oppTwoRounded));
                ((EditText) getActivity().findViewById(R.id.threeEquity)).setText(Double.toString(oppThreeRounded));
                ((EditText) getActivity().findViewById(R.id.fourEquity)).setText(Double.toString(oppFourRounded));
                ((EditText) getActivity().findViewById(R.id.fiveEquity)).setText(Double.toString(oppFiveRounded));
                ((EditText) getActivity().findViewById(R.id.sixEquity)).setText(Double.toString(oppSixRounded));
                ((EditText) getActivity().findViewById(R.id.sevenEquity)).setText(Double.toString(oppSevenRounded));
                ((EditText) getActivity().findViewById(R.id.eightEquity)).setText(Double.toString(oppEightRounded));
            }
        }

    }
}