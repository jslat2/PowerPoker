package com.example.gaming.powerpoker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gaming on 7/24/2015.
 */
public class Calculator extends Fragment{
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

    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.calcEquity).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer[] hero = new Integer[4];
                Integer[] oppOne = new Integer[4];
                Integer[] oppTwo = new Integer[4];

                if(MainActivity.hero.playerCards.isEmpty()){
                    Toast.makeText(getActivity(), "Hero has no cards!", Toast.LENGTH_LONG).show();
                }
                else{
                    hero[0] = letterToRank(MainActivity.hero.playerCards.get(0).charAt(MainActivity.hero.playerCards.get(0).length() - 1));
                    hero[1] = letterToRank(MainActivity.hero.playerCards.get(0).charAt(0));
                    hero[2] = letterToRank(MainActivity.hero.playerCards.get(1).charAt(MainActivity.hero.playerCards.get(1).length() - 1));
                    hero[3] = letterToRank(MainActivity.hero.playerCards.get(1).charAt(0));
                }

                if(MainActivity.opponentOne.playerCards.isEmpty() && MainActivity.opponentTwo.playerCards.isEmpty()){
                    Toast.makeText(getActivity(), "Opponent has no cards!", Toast.LENGTH_LONG).show();
                }
                else {
                    if(!MainActivity.opponentOne.playerCards.isEmpty()) {
                        oppOne[0] = letterToRank(MainActivity.opponentOne.playerCards.get(0).charAt(MainActivity.opponentOne.playerCards.get(0).length() - 1));
                        oppOne[1] = letterToRank(MainActivity.opponentOne.playerCards.get(0).charAt(0));
                        oppOne[2] = letterToRank(MainActivity.opponentOne.playerCards.get(1).charAt(MainActivity.opponentOne.playerCards.get(1).length() - 1));
                        oppOne[3] = letterToRank(MainActivity.opponentOne.playerCards.get(1).charAt(0));
                    }

                    if (!MainActivity.opponentTwo.playerCards.isEmpty()) {
                        oppTwo[0] = letterToRank(MainActivity.opponentTwo.playerCards.get(0).charAt(MainActivity.opponentTwo.playerCards.get(0).length() - 1));
                        oppTwo[1] = letterToRank(MainActivity.opponentTwo.playerCards.get(0).charAt(0));
                        oppTwo[2] = letterToRank(MainActivity.opponentTwo.playerCards.get(1).charAt(MainActivity.opponentTwo.playerCards.get(1).length() - 1));
                        oppTwo[3] = letterToRank(MainActivity.opponentTwo.playerCards.get(1).charAt(0));
                    }

                    equityCalc a = new equityCalc();
                    a.execute(hero, oppOne, oppTwo, null);
                }
            }
        });
    }

    public int letterToRank(char c){
        switch (c){
            case 'a':return 12;
            case 'k':return 11;
            case 'q':return 10;
            case 'j':return 9;
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
            case 'd':return 2;
            case 's':return 3;
            case 'c':return 4;
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

        public double determineWinner(ArrayList hero, ArrayList oppOne, ArrayList oppTwo){

            ArrayList<Integer> results = new ArrayList<>();
            int playersLeft = 3;

            for(int i = 0; i < hero.size(); i++){
                if(oppOne.get(i) != null && (int)oppOne.get(i) > (int)hero.get(i)){
                    return 0;
                }
                else if(oppOne.get(i) != null && (int)hero.get(i) > (int)oppOne.get(i)){
                    playersLeft--;
                    break;
                }
            }

            if(oppTwo == null){
                playersLeft--;
            }
            else if(oppTwo != null) {
                for (int i = 0; i < hero.size(); i++) {
                    if (oppTwo.get(i) != null && (int) oppTwo.get(i) > (int) hero.get(i)) {
                        return 0;
                    } else if (oppTwo.get(i) != null && (int) hero.get(i) > (int) oppTwo.get(i)) {
                        playersLeft--;
                        break;
                    }
                }
            }

            return (double) ((double)1 / (double)playersLeft);
        }

        public double monteCarlo(Integer[] cards, Integer[] oppOne, Integer[]oppTwo, Integer[] board, int simLength){
            double wins = 0;
            double count = 0;
            boolean newCard = true;

            int[] cardsTotal = new int[14];
            int[] oppOneTotal = new int[14];
            int[] oppTwoTotal = new int[14];

            for(int i = 0; i < 4; i++){
                cardsTotal[i] = cards[i];
                oppOneTotal[i] = oppOne[i];
                if (oppTwo != null) {
                    oppTwoTotal[i] = oppTwo[i];
                }
            }

            if(board != null){
                for(int i = 0; i < board.length; i++) {
                    cardsTotal[i+4] = board[i];
                    oppOneTotal[i+4] = board[i];
                    if(oppTwo != null) {
                        oppTwoTotal[i + 4] = board[i];
                    }
                }
            }

            int cardsToTakeOut = 0;
            if(board != null){
                cardsToTakeOut += board.length;
            }
            if(oppTwo != null){
                cardsToTakeOut += 4;
            }

            //take cards out of space for potential cards to come
            int[] remainingCards = new int[96-cardsToTakeOut];

            int counter = 0;

            for(int i = 0; i < 104; i = i+2){
                for(int j = 0; j < cards.length - 1; j = j+2){
                    if(cards[j] == space[i] && cards[j+1] == space[i+1]){
                        newCard = false;
                    }
                }
                for(int j = 0; j < oppOne.length - 1; j = j+2){
                    if(oppOne[j] == space[i] && oppOne[j+1] == space[i+1]){
                        newCard = false;
                    }
                }
                if(oppTwo != null) {
                    for (int j = 0; j < oppTwo.length - 1; j = j + 2) {
                        if (oppTwo[j] == space[i] && oppTwo[j + 1] == space[i + 1]) {
                            newCard = false;
                        }
                    }
                }

                if(board != null){
                    for(int j = 0; j < board.length - 1; j = j+2) {
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


                    oppOneTotal[4] = remainingCards[a*2];
                    oppOneTotal[5] = remainingCards[a*2+1];
                    oppOneTotal[6] = remainingCards[b*2];
                    oppOneTotal[7] = remainingCards[b*2+1];
                    oppOneTotal[8] = remainingCards[c*2];
                    oppOneTotal[9] = remainingCards[c*2+1];
                    oppOneTotal[10] = remainingCards[d*2];
                    oppOneTotal[11] = remainingCards[d*2+1];
                    oppOneTotal[12] = remainingCards[e*2];
                    oppOneTotal[13] = remainingCards[e*2+1];

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

                    if(oppTwo != null) {
                        wins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal));
                    }
                    else{
                        wins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), null);
                    }
                    count++;
                    result = wins/count;
                    result = Math.round(result*1000);
                    publishProgress(Double.toString(result/1000));
                }
            }

            return result;
        }

        //calculate the equity in your hand
        public double equity(int[] cards, int[] oppOne, int[] oppTwo, int[] board){
            double wins = 0;
            double count = 0;
            boolean newCard = true;

            int[] cardsTotal = new int[14];
            int[] oppOneTotal = new int[14];
            int[] oppTwoTotal = new int[14];

            for(int i = 0; i < 4; i++){
                cardsTotal[i] = cards[i];
                oppOneTotal[i] = oppOne[i];
                if (oppTwo != null) {
                    oppTwoTotal[i] = oppTwo[i];
                }
            }
            if(board != null){
                for(int i = 0; i < board.length; i++) {
                    cardsTotal[i+4] = board[i];
                    oppOneTotal[i+4] = board[i];
                    if(oppTwo != null) {
                        oppTwoTotal[i + 4] = board[i];
                    }
                }
            }

            int cardsToTakeOut = 0;
            if(board != null){
                cardsToTakeOut += board.length;
            }
            if(oppTwo != null){
                cardsToTakeOut += 4;
            }

            //take cards out of space for potential cards to come
            int[] remainingCards = new int[100-cardsToTakeOut];


            int counter = 0;

            for(int i = 0; i < 103; i = i +2){
                for(int j = 0; j < cards.length - 1; j = j+2){
                    if(cards[j] == space[i] && cards[j+1] == space[i+1]){
                        newCard = false;
                    }
                }
                for(int j = 0; j < oppOne.length - 1; j = j+2){
                    if(oppOne[j] == space[i] && oppOne[j+1] == space[i+1]){
                        newCard = false;
                    }
                }
                if(oppTwo != null) {
                    for (int j = 0; j < oppTwo.length - 1; j = j + 2) {
                        if (oppTwo[j] == space[i] && oppTwo[j + 1] == space[i + 1]) {
                            newCard = false;
                        }
                    }
                }

                if(board != null){
                    for(int j = 0; j < board.length - 1; j = j+2) {
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
                for (int i = 0; i < 87; i = i + 2) {
                    cardsTotal[4] = remainingCards[i];
                    cardsTotal[5] = remainingCards[i + 1];
                    oppOneTotal[4] = remainingCards[i];
                    oppOneTotal[5] = remainingCards[i + 1];
                    if(oppTwo != null) {
                        oppTwoTotal[4] = remainingCards[i];
                        oppTwoTotal[5] = remainingCards[i + 1];
                    }

                    for (int j = i + 2; j < 89; j = j + 2) {
                        cardsTotal[6] = remainingCards[j];
                        cardsTotal[7] = remainingCards[j + 1];
                        oppOneTotal[6] = remainingCards[j];
                        oppOneTotal[7] = remainingCards[j + 1];
                        if (oppTwo != null){
                            oppTwoTotal[6] = remainingCards[j];
                            oppTwoTotal[7] = remainingCards[j + 1];
                        }

                        for(int k = j + 2; k < 91; k = k+2) {
                            cardsTotal[8] = remainingCards[k];
                            cardsTotal[9] = remainingCards[k + 1];
                            oppOneTotal[8] = remainingCards[k];
                            oppOneTotal[9] = remainingCards[k + 1];
                            if(oppTwo != null) {
                                oppTwoTotal[8] = remainingCards[k];
                                oppTwoTotal[9] = remainingCards[k + 1];
                            }

                            for(int l = k + 2; l < 93; l = l+2) {
                                cardsTotal[10] = remainingCards[l];
                                cardsTotal[11] = remainingCards[l + 1];
                                oppOneTotal[10] = remainingCards[l];
                                oppOneTotal[11] = remainingCards[l + 1];
                                if(oppTwo != null) {
                                    oppTwoTotal[10] = remainingCards[l];
                                    oppTwoTotal[11] = remainingCards[l + 1];
                                }

                                for(int m = l +2; m < 95; m = m+2) {
                                    cardsTotal[12] = remainingCards[m];
                                    cardsTotal[13] = remainingCards[m + 1];
                                    oppOneTotal[12] = remainingCards[m];
                                    oppOneTotal[13] = remainingCards[m + 1];
                                    if(oppTwo != null) {
                                        oppTwoTotal[12] = remainingCards[m];
                                        oppTwoTotal[13] = remainingCards[m + 1];
                                    }


                                    if(oppTwo != null) {
                                        wins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal));
                                    }
                                    else{
                                        wins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), null);
                                    }
                                    count++;

                                    result = wins/count;
                                    publishProgress(Double.toString(result));
                                }
                            }
                        }
                    }
                }
                return count;
            }

            if(board.length == 6) {
                for (int i = 0; i < 87; i = i + 2) {
                    cardsTotal[10] = remainingCards[i];
                    cardsTotal[11] = remainingCards[i + 1];
                    oppOneTotal[10] = remainingCards[i];
                    oppOneTotal[11] = remainingCards[i + 1];
                    if(oppTwo != null) {
                        oppTwoTotal[10] = remainingCards[i];
                        oppTwoTotal[11] = remainingCards[i + 1];
                    }

                    for (int j = i + 2; j < 89; j = j + 2) {
                        cardsTotal[12] = remainingCards[j];
                        cardsTotal[13] = remainingCards[j + 1];
                        oppOneTotal[12] = remainingCards[j];
                        oppOneTotal[13] = remainingCards[j + 1];
                        if(oppTwo != null) {
                            oppTwoTotal[12] = remainingCards[j];
                            oppTwoTotal[13] = remainingCards[j + 1];
                        }

                        if(oppTwo != null) {
                            wins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal));
                        }
                        else{
                            wins += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), null);
                        }

                        count++;

                        result = wins/count;
                        publishProgress(Double.toString(result));
                    }
                }
                return result;
            }

            else{
                for (int i = 0; i < 87; i = i + 2) {
                    cardsTotal[12] = remainingCards[i];
                    cardsTotal[13] = remainingCards[i + 1];
                    oppOneTotal[12] = remainingCards[i];
                    oppOneTotal[13] = remainingCards[i + 1];
                    if(oppTwo != null) {
                        oppTwoTotal[12] = remainingCards[i];
                        oppTwoTotal[13] = remainingCards[i + 1];
                    }

                    if(oppTwo != null) {
                        count += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), handRank(oppTwoTotal));
                    }
                    else{
                        count += determineWinner(handRank(cardsTotal), handRank(oppOneTotal), null);
                    }
                    count++;
                    result = wins/count;
                    publishProgress(Double.toString(result));
                }
                return result;
            }
        }

        @Override
        protected Double doInBackground(Integer[]... params) {
            monteCarlo(params[0], params[1], null, null, 50000);
            return result;
        }


        @Override
        protected void onProgressUpdate(String... x){
            super.onProgressUpdate(x);
            ((EditText)getActivity().findViewById(R.id.equity)).setText(x[0]);
        }

    }
}