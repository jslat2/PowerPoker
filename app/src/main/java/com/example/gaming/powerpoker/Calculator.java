package com.example.gaming.powerpoker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Gaming on 7/24/2015.
 */
public class Calculator extends Fragment implements Runnable{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.calculator, container, false);
    }

    public void onStart(){
        super.onStart();

        if(!MainActivity.hero.playerCards.equals(null) && MainActivity.hero.playerCards.size() == 2){
            EditText heroHan = ((EditText)getActivity().findViewById(R.id.heroHan));
            heroHan.setText(MainActivity.hero.playerCards.get(0) + ", " + MainActivity.hero.playerCards.get(1));
        }

        if(!MainActivity.opponentOne.playerCards.equals(null) && MainActivity.opponentOne.playerCards.size() == 2){
            ((EditText)getActivity().findViewById(R.id.oppOneHand)).setText(MainActivity.opponentOne.playerCards.get(0) + ", " + MainActivity.opponentOne.playerCards.get(1));
        }

    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.calcEquity).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println("starting!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                ((EditText) getActivity().findViewById(R.id.equity)).setText(("x"));
                Calc c = new Calc();
                Calc d = new Calc();
                   Thread one = new Thread(c);
                one.start();

                while(true) {
                    try {
                        Thread.sleep(4000);
                        Calculator e = new Calculator();
                        System.out.println("result is: " + c.result + "!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            ((EditText) getActivity().findViewById(R.id.equity)).setText((Double.toString(d.result)));
                            FragmentManager frag = getActivity().getSupportFragmentManager();
                            frag.beginTransaction().replace(R.id.container, e, null).commit();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
         //       while(c.calculating) {
         //           try {
         //               Thread.sleep(4000);
         //           } catch (InterruptedException e) {
         //               e.printStackTrace();
         //           }
         //           ((EditText) getActivity().findViewById(R.id.equity)).setText((Double.toString(d.result)));
         //       }

            }
        });
    }

    @Override
    public void run() {

        Calc c = new Calc();
        c.beginCalc();

            ((EditText) getActivity().findViewById(R.id.equity)).setText(("y"));
            try {
                Thread.sleep(4000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }



    }

    public void start(){
        run();
    }


}
