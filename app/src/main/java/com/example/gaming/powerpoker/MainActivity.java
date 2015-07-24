package com.example.gaming.powerpoker;



import android.accounts.Account;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class MainActivity extends ActionBarActivity {

    public static int userNum;
    public static Database db;
    public static int currentNote = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login l = new Login();
        switchFrag(l);

        db = new Database(this);
    }


    public void switchFrag(Fragment newFrag){
        FragmentManager frag = getSupportFragmentManager();
        frag.beginTransaction().replace(R.id.container, newFrag, null).addToBackStack(null).commit();
    }

    //if valid credentials are provided user userNum is updated to reflect current user and home screen shown
    public void login(View v){
        String user = ((EditText)findViewById(R.id.user)).getText().toString();
        String pass = ((EditText)findViewById(R.id.pass)).getText().toString();

        if(db.isValidLogin(db, user, pass)) {
            userNum = db.getUserNum(db, user);
            Home h = new Home();
            switchFrag(h);
        }
        else{
            Toast.makeText(this, "Invalid login", Toast.LENGTH_LONG).show();
        }
    }

    public void openPlayerNotes(View v){
        PlayerNotes p = new PlayerNotes();
        switchFrag(p);
    }

    public void openNote(View v){
        //make sure not to edit any of the existing notes
        currentNote = -1;

        Note n = new Note();
        switchFrag(n);
    }

    public void openBankroll(View v){
        Bankroll b = new Bankroll();
        switchFrag(b);
    }

    public void openSessionLog(View v){
        SessionLog s = new SessionLog();
        switchFrag(s);
    }

    public void openHandLog(View v){
        HandLog h = new HandLog();
        switchFrag(h);
    }

    public void openCalculator(View v){
        Calculator c = new Calculator();
        switchFrag(c);
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


        if(currentNote == -1){
            db.addNote(db, first, last, email, phone, description, location, note, rating);
        }
        else{
            db.changeNote(db, currentNote, first, last, email, phone, description, location, note, rating);
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
}
