package com.example.gaming.powerpoker;



import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity{

    public static int userNum;
    public static Database db;
    public static int currentNotePosition = -1;
    public static int currentBankrollPosition = -1;
    public static HandEditor hero;
    public static HandEditor opponentOne;
    public static HandEditor opponentTwo;
    public static HandEditor opponentThree;
    public static HandEditor opponentFour;
    public static HandEditor opponentFive;
    public static HandEditor opponentSix;
    public static HandEditor opponentSeven;
    public static HandEditor opponentEight;
    public static int currentSessionPosition = -1;
    public static boolean sessionActive = false;
    public static long checkpoint = 0;
    public static Chronometer timer;
    public static boolean sessionTimerStopped = true;
    public static String currentVenue;
    public static double currentBuyIn = -1;
    public static double currentCashOut = -1;
    public static int currentSessionBankroll = -1;
    public static int currentSessionFormat = -1;
    public static int currentSessionStakes = -1;
    public static String currentNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(this);

        setContentView(R.layout.activity_main);

        Login l = new Login();
        switchFrag(l);

        hero = new HandEditor();
        opponentOne = new HandEditor();
        opponentTwo = new HandEditor();
        opponentThree = new HandEditor();
        opponentFour = new HandEditor();
        opponentFive = new HandEditor();
        opponentSix = new HandEditor();
        opponentSeven = new HandEditor();
        opponentEight = new HandEditor();

    }

    public void openNewSessionLog(View v){
        currentSessionPosition = -1;
        SessionLog s = new SessionLog();
        switchFrag(s);
    }

    public void openCurrentSessionLog(View v){
        SessionLog s = new SessionLog();
        switchFrag(s);
    }



    public void switchFrag(Fragment newFrag){
        FragmentManager frag = getSupportFragmentManager();
        frag.beginTransaction().replace(R.id.container, newFrag, null).addToBackStack(null).commit();
    }


    public void switchFragNoBackStack(Fragment newFrag){
        FragmentManager frag = getSupportFragmentManager();
        frag.beginTransaction().replace(R.id.container, newFrag, null).commit();
    }

    //if valid credentials are provided user userNum is updated to reflect current user and home screen shown
    public void login(View v) {
        String user = ((EditText) findViewById(R.id.user)).getText().toString();
        String pass = ((EditText) findViewById(R.id.pass)).getText().toString();

        if (db.isValidLogin(db, user, pass)) {
            userNum = db.getUserNum(db, user);
            Home h = new Home();
            switchFrag(h);
        } else {
            Toast.makeText(this, "Invalid login", Toast.LENGTH_LONG).show();
        }

    }



    public void openHeroCards(View v){
        switchFrag(hero);
    }

    public void openOppOneCards(View v){
        switchFrag(opponentOne);
    }

    public void openOppTwoCards(View v){
        switchFrag(opponentTwo);
    }
    public void openOppThreeCards(View v){
        switchFrag(opponentThree);
    }
    public void openOppFourCards(View v){
        switchFrag(opponentFour);
    }
    public void openOppFiveCards(View v){
        switchFrag(opponentFive);
    }
    public void openOppSixCards(View v){
        switchFrag(opponentSix);
    }
    public void openOppSevenCards(View v){
        switchFrag(opponentSeven);
    }
    public void openOppEightCards(View v){
        switchFrag(opponentEight);
    }

    public void openPlayerNotes(View v){
        PlayerNotes p = new PlayerNotes();
        switchFrag(p);
    }

    public void openNote(View v){
        //make sure not to edit any of the existing notes
        currentNotePosition = -1;

        Note n = new Note();
        switchFrag(n);
    }

    public void openBankroll(View v){
        Bankroll b = new Bankroll();
        switchFrag(b);
    }


    public void openBankrollCreator(View v){
        BankrollCreator b = new BankrollCreator();
        switchFrag(b);
    }

    public void openSessionLog(View v){
        SessionLogList s = new SessionLogList();
        switchFrag(s);
    }



    public void openCalculator(View v){
        hero.selectedCards = new ArrayList<String>();
        hero = new HandEditor();
        opponentOne = new HandEditor();
        opponentTwo = new HandEditor();
        opponentThree = new HandEditor();
        opponentFour = new HandEditor();
        opponentFive = new HandEditor();
        opponentSix = new HandEditor();
        opponentSeven = new HandEditor();
        opponentEight = new HandEditor();

        Calculator c = new Calculator();
        switchFrag(c);
    }

    public void openNewBankroll(View v){
        BankrollCreator b = new BankrollCreator();
        switchFrag(b);
    }

    public void saveBankroll(View v){
        String date = "";
        String bankrollName = ((EditText) findViewById(R.id.bankrollName)).getText().toString();
        Double initialDeposit = Double.parseDouble(((EditText) findViewById(R.id.initialDeposit)).getText().toString());

        if(bankrollName == null || bankrollName.equals("")){
            Toast.makeText(this, "Please Enter a Bankroll Name", Toast.LENGTH_LONG).show();
            return;
        }

        if(initialDeposit == null || initialDeposit <= 0){
            Toast.makeText(this, "Please Enter a Deposit Amount", Toast.LENGTH_LONG).show();
            return;
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        date = simple.format(cal.getTime());


        db.addBankroll(db, bankrollName, date, initialDeposit);

        Bankroll b = new Bankroll();
        switchFrag(b);
    }

    public void saveSession(View v){

        if(!sessionTimerStopped){
            Toast.makeText(this, "Please Stop Session Timer if Session is Complete", Toast.LENGTH_LONG).show();
        }
        else {

            String format = "";
            String stakes = "";
            String notes = "";
            String bankroll = "";
            String venue = "";
            double buyIn = 1;
            double cashOut = 0;

            if (((Spinner) findViewById(R.id.bankrollSpinner)).getSelectedItem() == null) {
                Toast.makeText(this, "Please Enter a Bankroll", Toast.LENGTH_SHORT).show();
                return;
            } else if (((EditText) findViewById(R.id.buyIn)).getText().length() == 0) {
                Toast.makeText(this, "Please Enter a Buy In Amount", Toast.LENGTH_SHORT).show();
                return;
            } else if (((EditText) findViewById(R.id.cashOut)).getText().length() == 0) {
                Toast.makeText(this, "Please Enter a Cash Out Amount", Toast.LENGTH_SHORT).show();
                return;
            } else if (((EditText) findViewById(R.id.venue)).getText().length() == 0) {
                Toast.makeText(this, "Please Enter a Venue", Toast.LENGTH_SHORT).show();
                return;
            }


            String time = ((Chronometer)findViewById(R.id.timer)).getText().toString();
            if ((((Spinner) findViewById(R.id.bankrollSpinner)).getSelectedItem()) != null) {
                bankroll = (String) (((Spinner) findViewById(R.id.bankrollSpinner)).getSelectedItem());
            }
            if ((((EditText) findViewById(R.id.buyIn)).getText()) != null && (((EditText) findViewById(R.id.buyIn)).getText().toString()).matches("^\\d*\\.?\\d*$")) {
                buyIn = Double.parseDouble(((EditText) findViewById(R.id.buyIn)).getText().toString());
            }
            if (((EditText) findViewById(R.id.venue)).getText() != null) {
                venue = ((EditText) findViewById(R.id.venue)).getText().toString();
            }
            if (((Spinner) findViewById(R.id.format)).getSelectedItem() != null) {
                format = (String) (((Spinner) findViewById(R.id.format)).getSelectedItem());
            }
            if (((Spinner) findViewById(R.id.stakes)).getSelectedItem() != null) {
                stakes = (String) (((Spinner) findViewById(R.id.stakes)).getSelectedItem());
            }
            if (((EditText) findViewById(R.id.notes)).getText() != null) {
                notes = ((EditText) findViewById(R.id.notes)).getText().toString();
            }
            if ((((EditText) findViewById(R.id.buyIn)).getText() != null && (((EditText) findViewById(R.id.buyIn)).getText().toString()).matches("^\\d*\\.?\\d*$"))) {
                cashOut = Double.parseDouble(((EditText) findViewById(R.id.cashOut)).getText().toString());
            }
            double earnings = cashOut - buyIn;

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
            String date = simple.format(cal.getTime());


            db.addSession(db, date, time, bankroll, buyIn, venue, format, stakes, notes, cashOut);
            db.saveSessionToBankroll(db, userNum, bankroll, earnings, date);

            sessionActive = false;
            SessionLogList s = new SessionLogList();
            switchFragNoBackStack(s);

            currentBuyIn = -1;
            currentCashOut = -1;
            currentSessionBankroll = -1;
            currentSessionFormat = -1;
            currentSessionStakes = -1;
            currentNotes = null;
            currentVenue = null;
        }

    }

    @Override
    public void onBackPressed(){
        if(hero != null && hero.playerCards != null && hero.playerCards.size() == 1 ||
                opponentOne != null && opponentOne.playerCards != null && opponentOne.playerCards.size() == 1 ||
                opponentTwo != null && opponentTwo.playerCards != null && opponentTwo.playerCards.size() == 1 ||
                opponentThree != null && opponentThree.playerCards != null && opponentThree.playerCards.size() == 1 ||
                opponentFour != null && opponentFour.playerCards != null && opponentFour.playerCards.size() == 1 ||
                opponentFive != null && opponentFive.playerCards != null && opponentFive.playerCards.size() == 1 ||
                opponentSix != null && opponentSix.playerCards != null && opponentSix.playerCards.size() == 1 ||
                opponentSeven != null && opponentSeven.playerCards != null && opponentSeven.playerCards.size() == 1 ||
                opponentEight != null && opponentEight.playerCards != null && opponentEight.playerCards.size() == 1) {

            Toast.makeText(this, "Player Cannot Have Only One Card", Toast.LENGTH_LONG).show();
            return;
        }

        else {
            super.onBackPressed();
        }
    }

    public void deleteCurrentSession(View v){
        db.deleteCurrentSession();
        currentSessionPosition = -1;
        SessionLogList s = new SessionLogList();
        switchFragNoBackStack(s);
    }

    public void changeSession(View v){

        String format = "";
        String stakes = "";
        String notes = "";
        String bankroll = "";
        String venue = "";
        double buyIn = 1;
        double cashOut = 0;

        if (((Spinner) findViewById(R.id.bankrollSpinner)).getSelectedItem() == null) {
            Toast.makeText(this, "Please Enter a Bankroll", Toast.LENGTH_SHORT).show();
            return;
        } else if (((EditText) findViewById(R.id.buyIn)).getText().length() == 0) {
            Toast.makeText(this, "Please Enter a Buy In Amount", Toast.LENGTH_SHORT).show();
            return;
        } else if (((EditText) findViewById(R.id.cashOut)).getText().length() == 0) {
            Toast.makeText(this, "Please Enter a Cash Out Amount", Toast.LENGTH_SHORT).show();
            return;
        } else if (((EditText) findViewById(R.id.venue)).getText().length() == 0) {
            Toast.makeText(this, "Please Enter a Venue", Toast.LENGTH_SHORT).show();
            return;
        }


        String time = ((Chronometer)findViewById(R.id.timer)).getText().toString();
        if ((((Spinner) findViewById(R.id.bankrollSpinner)).getSelectedItem()) != null) {
            bankroll = (String) (((Spinner) findViewById(R.id.bankrollSpinner)).getSelectedItem());
        }
        if ((((EditText) findViewById(R.id.buyIn)).getText()) != null && (((EditText) findViewById(R.id.buyIn)).getText().toString()).matches("^\\d*\\.?\\d*$")) {
            buyIn = Double.parseDouble(((EditText) findViewById(R.id.buyIn)).getText().toString());
        }
        if (((EditText) findViewById(R.id.venue)).getText() != null) {
            venue = ((EditText) findViewById(R.id.venue)).getText().toString();
        }
        if (((Spinner) findViewById(R.id.format)).getSelectedItem() != null) {
            format = (String) (((Spinner) findViewById(R.id.format)).getSelectedItem());
        }
        if (((Spinner) findViewById(R.id.stakes)).getSelectedItem() != null) {
            stakes = (String) (((Spinner) findViewById(R.id.stakes)).getSelectedItem());
        }
        if (((EditText) findViewById(R.id.notes)).getText() != null) {
            notes = ((EditText) findViewById(R.id.notes)).getText().toString();
        }
        if ((((EditText) findViewById(R.id.buyIn)).getText() != null && (((EditText) findViewById(R.id.buyIn)).getText().toString()).matches("^\\d*\\.?\\d*$"))) {
            cashOut = Double.parseDouble(((EditText) findViewById(R.id.cashOut)).getText().toString());
        }




        db.changeSession(db, currentSessionPosition, time, bankroll, buyIn, venue, format, stakes, notes, cashOut);
        SessionLogList s = new SessionLogList();
        switchFragNoBackStack(s);
    }



    public void saveNote(View v){

        String first = ((EditText)findViewById(R.id.first)).getText().toString();
        String last = ((EditText)findViewById(R.id.last)).getText().toString();
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phone)).getText().toString();
        String description = ((EditText)findViewById(R.id.description)).getText().toString();
        String location = ((EditText)findViewById(R.id.location)).getText().toString();
        String note = ((EditText)findViewById(R.id.notes)).getText().toString();
        float rating = ((RatingBar)findViewById(R.id.ratingBar)).getRating();


        if(currentNotePosition == -1){
            db.addNote(db, first, last, email, phone, description, location, note, rating);
        }
        else{
            db.changeNote(db, currentNotePosition, first, last, email, phone, description, location, note, rating);
        }

        openPlayerNotes(v);
    }

    public void openHome(View v){
        Home h = new Home();
        switchFrag(h);
    }

    public void createAccount(View v){
        String newUser = ((EditText)findViewById(R.id.newUser)).getText().toString();
        String newPass = ((EditText)findViewById(R.id.newPass)).getText().toString();
        String newEmail = ((EditText)findViewById(R.id.newEmail)).getText().toString();

        if(!(db.tableContainsString(db, "account", "userName", newUser) == -1)) {
            Toast.makeText(this, "Account already exists", Toast.LENGTH_LONG).show();
        }
        else if(newUser.equals("")){
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_LONG).show();
        }
        else if(newPass.equals("")){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
        }
        else if(newEmail.equals("")){
            Toast.makeText(this, "Please enter an email address", Toast.LENGTH_LONG).show();
        }
        else{
            db.addUser(db, newUser, newPass, newEmail);
            Login l = new Login();
            switchFrag(l);
        }
    }

    public void openNewAccount(View v){
        NewAccount n = new NewAccount();
        switchFrag(n);
    }

    public void openDepositDialog(View v){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.deposit_popup);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.light_border_popup);

        Button saveButton = (Button)dialog.findViewById(R.id.button12);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText amountText = (EditText) dialog.findViewById(R.id.depositAmount);
                if (amountText.getText().toString().trim().length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Cannot deposit $0", Toast.LENGTH_LONG).show();
                } else {
                    double depositAmount = Double.parseDouble((amountText).getText().toString());

                    String date;
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
                    date = simple.format(cal.getTime());

                    db.makeDeposit(db, depositAmount, date);
                    dialog.dismiss();
                    BankrollItem b = new BankrollItem();
                    FragmentManager ft = getSupportFragmentManager();
                    ft.popBackStack();
                    switchFrag(b);
                }

            }
        });

        Button exitButton = (Button)dialog.findViewById(R.id.depositExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void startTimer(View v){
        Chronometer timer = (Chronometer)findViewById(R.id.timer);
        checkpoint = timeToMillis((timer.getText().toString()));
        timer.setBase(SystemClock.elapsedRealtime() - checkpoint);
        sessionTimerStopped = false;
        sessionActive = true;
        timer.start();
    }

    public static long timeToMillis(String timeString){
        long totalTime = 0;
        for(int i = timeString.length()-1; i >= 0; i--){
            if(i == timeString.length()-1){
                System.out.println(Long.parseLong(timeString.substring(timeString.length() - 1, timeString.length()))+ "!!!!!!!!!!!!!!!!!!!!!");
                totalTime += (Long.parseLong(timeString.substring(timeString.length() - 1, timeString.length())))*1000;
            }
            if(i == timeString.length() -2){
                System.out.println(Long.parseLong(timeString.substring(timeString.length() - 2, timeString.length() -1))+ "!aaaaa!!!!!!!");
                totalTime += (Long.parseLong(timeString.substring(timeString.length() -  2, timeString.length() - 1)))*10000;
            }
            if(i == timeString.length() -4){
                System.out.println((long)(timeString.charAt(timeString.length()-4))+ "!!bbbbbbb!!!!!!!!!!!!");
                totalTime += (Long.parseLong(timeString.substring(timeString.length() - 4, timeString.length()-3)))*60000;
            }
            if(i == timeString.length() -5){
                System.out.println((long)(timeString.charAt(timeString.length()-5))+ "!!ccccccccccc!!!!!!!!!!!!");
                totalTime += (Long.parseLong(timeString.substring(timeString.length() - 5, timeString.length() - 4)))*600000;
            }
            if(i == timeString.length() -7){
                totalTime += (Long.parseLong(timeString.substring(timeString.length() - 7, timeString.length() - 6)))*3600000;
            }
            if(i == timeString.length() -8){
                totalTime += (Long.parseLong(timeString.substring(timeString.length() - 8, timeString.length() - 7)))*36000000;
            }
        }
        return totalTime;
    }

    public void stopTimer(View v){
        Chronometer timer = (Chronometer)findViewById(R.id.timer);
        checkpoint = timeToMillis((timer.getText().toString()));
        sessionTimerStopped = true;
        timer.stop();
    }

    public String addZeros(String s){
        String newString;
        if(s.length() == 1){
            newString = "0" + s;
            return newString;
        }
        return s;
    }

    public String convertAMPM(String s){
        String hour = s.substring(0, s.indexOf(':'));
        int newHourInt;
        if(Integer.parseInt(hour) > 12){
            newHourInt = Integer.parseInt(hour) - 12;
            return(addZeros(Integer.toString(newHourInt)) + s.substring(s.indexOf(':')) + " PM");
        }
        return s + " AM";
    }

    public void openWithdrawalDialog(View v){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.withdrawal_popup);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.light_border_popup);

        Button saveButton = (Button)dialog.findViewById(R.id.withdrawalSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText amountText = (EditText)dialog.findViewById(R.id.withdrawalAmount);
                if(amountText.getText().toString().trim().length() <= 0){
                    Toast.makeText(getApplicationContext(), "Cannot withdraw $0", Toast.LENGTH_LONG).show();
                }
                else {
                    double withdrawalAmount = -Double.parseDouble((amountText).getText().toString());


                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat simple = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
                    String date = simple.format(cal.getTime());

                    db.makeDeposit(db, withdrawalAmount, date);
                    dialog.dismiss();
                    BankrollItem b = new BankrollItem();
                    FragmentManager ft = getSupportFragmentManager();
                    ft.popBackStack();
                    switchFrag(b);
                }

            }
        });

        Button exitButton = (Button)dialog.findViewById(R.id.withdrawalExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}