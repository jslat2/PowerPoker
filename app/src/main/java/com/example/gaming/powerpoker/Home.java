package com.example.gaming.powerpoker;

import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;

/**
 * Created by Gaming on 7/23/2015.
 */
public class Home extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.home, container, false);
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

        EditText totalBank = (EditText)getActivity().findViewById(R.id.totalBank);
        EditText timeLogged = (EditText)getActivity().findViewById(R.id.timeLogged);
        EditText lifeTimeEarnings = (EditText)getActivity().findViewById(R.id.lifeTimeEarnings);
        EditText earningsPerHour = (EditText)getActivity().findViewById(R.id.earningsPerHour);
        EditText bestSingleSession = (EditText)getActivity().findViewById(R.id.bestSingleSession);
        EditText bestVenue = (EditText)getActivity().findViewById(R.id.bestVenue);


        timeLogged.setText(Double.toString(getTotalTimeLogged(MainActivity.db))  + " Minutes");
        totalBank.setText(dollarFormat(Double.toString(getTotalBank(MainActivity.db, MainActivity.userNum))));
        lifeTimeEarnings.setText(dollarFormat(Double.toString(getTotalEarnings(MainActivity.db, MainActivity.userNum))));

        if(getTotalTimeLogged(MainActivity.db) != 0) {
            earningsPerHour.setText(dollarFormat(Double.toString(getTotalEarnings(MainActivity.db, MainActivity.userNum) * 60 / getTotalTimeLogged(MainActivity.db))));
        }
        bestSingleSession.setText(dollarFormat(Double.toString(getBestSession(MainActivity.db, MainActivity.userNum))));
        bestVenue.setText(getBestVenueAndAmount(MainActivity.db, MainActivity.userNum));

        createTotalEarningsGraph(R.id.graph);

        Spinner spinner = (Spinner)getActivity().findViewById(R.id.graphTwoSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getBankrollNames());
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    createBankrollGraph((String) getBankrollNames().get(position), R.id.graphTwo);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner graphThreeSpinner = (Spinner)getActivity().findViewById(R.id.graphThreeSpinner);
        ArrayAdapter<String> adapterThree = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getFormatNames());
        graphThreeSpinner.setAdapter(adapterThree);

        graphThreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    createFormatGraph((String) getFormatNames().get(position), R.id.graphThree);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner graphFourSpinner = (Spinner)getActivity().findViewById(R.id.graphFourSpinner);
        ArrayAdapter<String> adapterFour = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getVenueNames());
        graphFourSpinner.setAdapter(adapterFour);

        graphFourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    createVenueGraph((String) getVenueNames().get(position), R.id.graphFour);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner graphFiveSpinner = (Spinner)getActivity().findViewById(R.id.graphFiveSpinner);
        ArrayAdapter<String> adapterFive = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getStakesNames());
        graphFiveSpinner.setAdapter(adapterFive);

        graphFiveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0) {
                    createStakesGraph((String)getStakesNames().get(position), R.id.graphFive);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    public ArrayList getBankrollNames(){
        ArrayList<String> names = new ArrayList<>();
        names.add("");
        Cursor cr = MainActivity.db.getBankrollNames(MainActivity.db);
        if(cr.moveToFirst()){
            cr.moveToFirst();
            while(!cr.isAfterLast()){
                if(!names.contains(cr.getString(1))) {
                    names.add(cr.getString(1));
                }
                cr.moveToNext();
            }
        }
        cr.close();
        return names;
    }

    public ArrayList getStakesNames(){
        ArrayList<String> names = new ArrayList<>();
        names.add("");
        Cursor cr = MainActivity.db.getStakesNames(MainActivity.db);
        if(cr.moveToFirst()){
            cr.moveToFirst();
            while(!cr.isAfterLast()){
                if(!names.contains(cr.getString(0))) {
                    names.add(cr.getString(0));
                }
                cr.moveToNext();
            }
        }
        cr.close();
        return names;
    }

    public ArrayList getVenueNames(){
        ArrayList<String> names = new ArrayList<>();
        names.add("");
        Cursor cr = MainActivity.db.getVenueNames(MainActivity.db, MainActivity.userNum);
        if(cr.moveToFirst()){
            cr.moveToFirst();
            while(!cr.isAfterLast()){
                if(!names.contains(cr.getString(0))) {
                    names.add(cr.getString(0));
                }
                cr.moveToNext();
            }
        }
        cr.close();
        return names;
    }

    public ArrayList getFormatNames(){
        ArrayList<String> names = new ArrayList<>();
        names.add("");
        Cursor cr = MainActivity.db.getFormatNames(MainActivity.db, MainActivity.userNum);
        if(cr.moveToFirst()){
            cr.moveToFirst();
            while(!cr.isAfterLast()){
                if(!names.contains(cr.getString(0))) {
                    names.add(cr.getString(0));
                }
                cr.moveToNext();
            }
        }
        cr.close();
        return names;
    }

    public double getTotalBank(Database db, int userNum){

        double total = 0;
        Cursor cr = db.getBankrollTotals(db, userNum);
        if(!cr.moveToFirst()){
            cr.close();
            return 0;
        }

        cr.moveToFirst();
        while(!cr.isAfterLast()){
            total += cr.getDouble(0);
            cr.moveToNext();
        }

        cr.close();
        return total;
    }

    public String getBestVenueAndAmount(Database db, int userNum){

        double total = 0;

        ArrayList<String> venueNames = new ArrayList<>();
        ArrayList<Double> earnings = new ArrayList<>();

        Cursor cr = db.getSessionVenueAmts(db, userNum);

        if(!cr.moveToFirst()){
            return "";
        }
        cr.moveToFirst();
        while(!cr.isAfterLast()){
            if(!venueNames.contains(cr.getString(0))){
                venueNames.add(cr.getString(0));
                earnings.add(cr.getDouble(2) - cr.getDouble(1));
            }
            else{
                double holder = earnings.get(earnings.size()-1);
                earnings.remove(earnings.size()-1);
                earnings.add(holder + cr.getDouble(2) - cr.getDouble(1));
            }
            cr.moveToNext();
        }

        for(int i = 0; i < earnings.size(); i++){
            if(earnings.get(i) > total){
                total = earnings.get(i);
            }
        }

        int indexOfGreatest = 0;
        for(int i = 0; i < earnings.size(); i++){
            if(earnings.get(i) == total){
                indexOfGreatest = i;
                break;
            }
        }

        return (dollarFormat(Double.toString(total)) + "   -   " + venueNames.get(indexOfGreatest));
    }

    public double getTotalEarnings(Database db, int userNum){

        int total = 0;
        Cursor cr = MainActivity.db.getSessionTotals(db, userNum);
        if(!cr.moveToFirst()){
            cr.close();
            return 0;
        }

        cr.moveToFirst();
        while(!cr.isAfterLast()){
            total += cr.getDouble(1) - cr.getDouble(0);
            cr.moveToNext();
        }

        cr.close();
        return total;
    }

    public double getBestSession(Database db, int userNum){

        double total = 0;
        Cursor cr = MainActivity.db.getSessionTotals(db, userNum);
        if(!cr.moveToFirst()){
            cr.close();
            return 0;
        }

        cr.moveToFirst();
        while(!cr.isAfterLast()){
            if(cr.getDouble(1) - cr.getDouble(0) > total){
                total = cr.getDouble(1) - cr.getDouble(0);
            }
            cr.moveToNext();
        }

        cr.close();
        return total;
    }

    public double getTotalTimeLogged(Database db){

        double total = 0;
        Cursor cr = MainActivity.db.getSessionTimes(db);

        if(cr == null || !cr.moveToFirst()){
            cr.close();
            return 0;
        }

        cr.moveToFirst();
        while(!cr.isAfterLast()){

            for(int i = cr.getString(0).length() -1; i >= 0; i--){
                if(i == cr.getString(0).length()-1){
                    total += Integer.parseInt(cr.getString(0).substring(cr.getString(0).length()-1, cr.getString(0).length()));
                }
                else if(i == cr.getString(0).length()-2){
                    total += 10 * Integer.parseInt(cr.getString(0).substring(cr.getString(0).length()-2, cr.getString(0).length()-1));
                }
                else if(i == cr.getString(0).length()-4){
                    total += 60 * Integer.parseInt(cr.getString(0).substring(cr.getString(0).length()-4, cr.getString(0).length()-3));
                }
                else if(i == cr.getString(0).length()-5){
                    total += 600 * Integer.parseInt(cr.getString(0).substring(cr.getString(0).length()-5, cr.getString(0).length()-4));
                }
                else if(cr.getString(0).length() > 6 && i == cr.getString(0).length()-7){
                    total += 3600 * Integer.parseInt(cr.getString(0).substring(cr.getString(0).length()-7, cr.getString(0).length()-6));
                }
                else if(cr.getString(0).length() > 7 && i == cr.getString(0).length()-8){
                    total += 36000 * Integer.parseInt(cr.getString(0).substring(cr.getString(0).length()-8, cr.getString(0).length()-7));
                }
            }
            cr.moveToNext();
        }

        cr.close();
        return total;
    }

    public void createVenueGraph(String venueName, int graphId){
        final Graphs g = new Graphs();
        final DataPoint[] dataPoints = g.getVenueGraphPoints(MainActivity.db, venueName);
        GraphView graph = (GraphView) getActivity().findViewById(graphId);
        graph.removeAllSeries();

        if(dataPoints == null || dataPoints.length < 1){
            return;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

        graph.addSeries(series);

        series.setDrawDataPoints(true);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                double previousTotal = 0;
                for (int i = 1; i < dataPoints.length; i++) {
                    if (dataPoints[i].getX() == dataPoint.getX()) {
                        previousTotal = dataPoints[i - 1].getY();
                        break;
                    }
                }

                double changeInTotal = dataPoint.getY() - previousTotal;
                String changeInTotalString = Double.toString(changeInTotal);
                String finalString = "";
                if(changeInTotalString.charAt(0) == '-'){
                    finalString = "-" + dollarFormat(changeInTotalString.substring(1, changeInTotalString.length()));
                }
                else if(changeInTotalString.charAt(0) == '0'){
                    finalString = dollarFormat(changeInTotalString);
                }
                else{
                    finalString = "+" + dollarFormat(changeInTotalString);
                }

                Toast.makeText(getActivity(), g.epochToNormal((long) dataPoint.getX()) + "      " + finalString, Toast.LENGTH_SHORT).show();
            }
        });


        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()) {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    if(value < 0) {
                        value = Math.abs(value);
                        return "-$" + super.formatLabel(value, isValueX);
                    }
                    else{
                        return "$" + super.formatLabel(value, isValueX);
                    }
                }
            }
        });
        graph.getViewport().setBackgroundColor(222222222);
        graph.getGridLabelRenderer().setNumHorizontalLabels(2);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);


        graph.getViewport().setMinX(dataPoints[0].getX() / 1.0000001);
        graph.getViewport().setMaxX(dataPoints[dataPoints.length-1].getX()*1.0000001);

        if(g.bankrollMin < 0) {
            graph.getViewport().setMinY(g.bankrollMin * 1.1);
        }
        else if(g.bankrollMin == 0){
            graph.getViewport().setMinY(-10);
        }
        else{
            graph.getViewport().setMinY(g.bankrollMin / 1.1);
        }
        if(g.bankrollMax < 0){
            graph.getViewport().setMaxY(g.bankrollMax / 1.1);
        }
        else if(g.bankrollMax == 0){
            graph.getViewport().setMaxY(10);
        }
        else{
            graph.getViewport().setMaxY(g.bankrollMax * 1.1);
        }

        graph.setVisibility(View.VISIBLE);
    }

    public void createStakesGraph(String venueName, int graphId){
        final Graphs g = new Graphs();
        final DataPoint[] dataPoints = g.getStakesGraphPoints(MainActivity.db, venueName);
        GraphView graph = (GraphView) getActivity().findViewById(graphId);
        graph.removeAllSeries();

        if(dataPoints == null || dataPoints.length < 1){
            return;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

        graph.addSeries(series);

        series.setDrawDataPoints(true);


        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                double previousTotal = 0;
                for (int i = 1; i < dataPoints.length; i++) {
                    if (dataPoints[i].getX() == dataPoint.getX()) {
                        previousTotal = dataPoints[i - 1].getY();
                        break;
                    }
                }

                double changeInTotal = dataPoint.getY() - previousTotal;
                String changeInTotalString = Double.toString(changeInTotal);
                String finalString = "";
                if (changeInTotalString.charAt(0) == '-') {
                    finalString = "-" + dollarFormat(changeInTotalString.substring(1, changeInTotalString.length()));
                } else if (changeInTotalString.charAt(0) == '0') {
                    finalString = dollarFormat(changeInTotalString);
                } else {
                    finalString = "+" + dollarFormat(changeInTotalString);
                }

                Toast.makeText(getActivity(), g.epochToNormal((long) dataPoint.getX()) + "      " + finalString, Toast.LENGTH_SHORT).show();
            }
        });


        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()) {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    if (value < 0) {
                        value = Math.abs(value);
                        return "-$" + super.formatLabel(value, isValueX);
                    } else {
                        return "$" + super.formatLabel(value, isValueX);
                    }
                }
            }
        });


        graph.getViewport().setBackgroundColor(222222222);
        graph.getGridLabelRenderer().setNumHorizontalLabels(2);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);


        graph.getViewport().setMinX(dataPoints[0].getX() / 1.0000001);
        graph.getViewport().setMaxX(dataPoints[dataPoints.length-1].getX()*1.0000001);

        if(g.bankrollMin < 0) {
            graph.getViewport().setMinY(g.bankrollMin * 1.1);
        }
        else if(g.bankrollMin == 0){
            graph.getViewport().setMinY(-10);
        }
        else{
            graph.getViewport().setMinY(g.bankrollMin / 1.1);
        }
        if(g.bankrollMax < 0){
            graph.getViewport().setMaxY(g.bankrollMax / 1.1);
        }
        else if(g.bankrollMax == 0){
            graph.getViewport().setMaxY(10);
        }
        else{
            graph.getViewport().setMaxY(g.bankrollMax * 1.1);
        }

        graph.setVisibility(View.VISIBLE);
    }

    public void createFormatGraph(String formatName, int graphId){
        final Graphs g = new Graphs();
        final DataPoint[] dataPoints = g.getFormatGraphPoints(MainActivity.db, formatName);
        GraphView graph = (GraphView) getActivity().findViewById(graphId);
        graph.removeAllSeries();

        if(dataPoints == null || dataPoints.length < 2){
            return;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

        graph.addSeries(series);

        series.setDrawDataPoints(true);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                double previousTotal = 0;
                for (int i = 1; i < dataPoints.length; i++) {
                    if (dataPoints[i].getX() == dataPoint.getX()) {
                        previousTotal = dataPoints[i - 1].getY();
                        break;
                    }
                }

                double changeInTotal = dataPoint.getY() - previousTotal;
                String changeInTotalString = Double.toString(changeInTotal);
                String finalString = "";
                if (changeInTotalString.charAt(0) == '-') {
                    finalString = "-" + dollarFormat(changeInTotalString.substring(1, changeInTotalString.length()));
                } else if (changeInTotalString.charAt(0) == '0') {
                    finalString = dollarFormat(changeInTotalString);
                } else {
                    finalString = "+" + dollarFormat(changeInTotalString);
                }

                Toast.makeText(getActivity(), g.epochToNormal((long) dataPoint.getX()) + "      " + finalString, Toast.LENGTH_SHORT).show();
            }
        });

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()) {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    if (value < 0) {
                        value = Math.abs(value);
                        return "-$" + super.formatLabel(value, isValueX);
                    } else {
                        return "$" + super.formatLabel(value, isValueX);
                    }
                }
            }
        });

        graph.getViewport().setBackgroundColor(222222222);
        graph.getGridLabelRenderer().setNumHorizontalLabels(2);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);


        graph.getViewport().setMinX(dataPoints[0].getX() / 1.0000001);
        graph.getViewport().setMaxX(dataPoints[dataPoints.length-1].getX()*1.0000001);

        if(g.bankrollMin < 0) {
            graph.getViewport().setMinY(g.bankrollMin * 1.1);
        }
        else if(g.bankrollMin == 0){
            graph.getViewport().setMinY(-10);
        }
        else{
            graph.getViewport().setMinY(g.bankrollMin / 1.1);
        }
        if(g.bankrollMax < 0){
            graph.getViewport().setMaxY(g.bankrollMax / 1.1);
        }
        else if(g.bankrollMax == 0){
            graph.getViewport().setMaxY(10);
        }
        else{
            graph.getViewport().setMaxY(g.bankrollMax * 1.1);
        }

        graph.setVisibility(View.VISIBLE);
    }

    public void createBankrollGraph(String bankroll, int graphResource){
        final Graphs g = new Graphs();
        final DataPoint[] dataPoints = g.getBankrollGraphPoints(MainActivity.db, bankroll);
        GraphView graph = (GraphView) getActivity().findViewById(graphResource);
        graph.removeAllSeries();

        if(dataPoints == null || dataPoints.length < 2){
            return;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

        graph.addSeries(series);

        series.setDrawDataPoints(true);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                double previousTotal = 0;
                for (int i = 1; i < dataPoints.length; i++) {
                    if (dataPoints[i].getX() == dataPoint.getX()) {
                        previousTotal = dataPoints[i - 1].getY();
                        break;
                    }
                }

                double changeInTotal = dataPoint.getY() - previousTotal;
                String changeInTotalString = Double.toString(changeInTotal);
                String finalString = "";
                if (changeInTotalString.charAt(0) == '-') {
                    finalString = "-" + dollarFormat(changeInTotalString.substring(1, changeInTotalString.length()));
                } else if (changeInTotalString.charAt(0) == '0') {
                    finalString = dollarFormat(changeInTotalString);
                } else {
                    finalString = "+" + dollarFormat(changeInTotalString);
                }

                Toast.makeText(getActivity(), g.epochToNormal((long) dataPoint.getX()) + "      " + finalString, Toast.LENGTH_SHORT).show();
            }
        });

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()) {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    if (value < 0) {
                        value = Math.abs(value);
                        return "-$" + super.formatLabel(value, isValueX);
                    } else {
                        return "$" + super.formatLabel(value, isValueX);
                    }
                }
            }
        });
        graph.getViewport().setBackgroundColor(222222222);
        graph.getGridLabelRenderer().setNumHorizontalLabels(2);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);


        graph.getViewport().setMinX(dataPoints[0].getX() / 1.0000001);
        graph.getViewport().setMaxX(dataPoints[dataPoints.length-1].getX()*1.0000001);

        if(g.bankrollMin < 0) {
            graph.getViewport().setMinY(g.bankrollMin * 1.1);
        }
        else if(g.bankrollMin == 0){
            graph.getViewport().setMinY(-10);
        }
        else{
            graph.getViewport().setMinY(g.bankrollMin / 1.1);
        }
        if(g.bankrollMax < 0){
            graph.getViewport().setMaxY(g.bankrollMax / 1.1);
        }
        else if(g.bankrollMax == 0){
            graph.getViewport().setMaxY(10);
        }
        else{
            graph.getViewport().setMaxY(g.bankrollMax * 1.1);
        }

        graph.setVisibility(View.VISIBLE);

    }

    public void createTotalEarningsGraph(int graphResource) {
        final Graphs g = new Graphs();
        final DataPoint[] dataPoints = g.getTotalEarningsGraphPoints(MainActivity.db);
        GraphView graph = (GraphView) getActivity().findViewById(graphResource);
        graph.removeAllSeries();

        if (dataPoints == null || dataPoints.length < 2) {
            return;
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);

        graph.addSeries(series);

        series.setDrawDataPoints(true);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                double previousTotal = 0;
                for (int i = 1; i < dataPoints.length; i++) {
                    if (dataPoints[i].getX() == dataPoint.getX()) {
                        previousTotal = dataPoints[i - 1].getY();
                        break;
                    }
                }

                double changeInTotal = dataPoint.getY() - previousTotal;
                String changeInTotalString = Double.toString(changeInTotal);
                String finalString = "";
                if (changeInTotalString.charAt(0) == '-') {
                    finalString = "-" + dollarFormat(changeInTotalString.substring(1, changeInTotalString.length()));
                } else if (changeInTotalString.charAt(0) == '0') {
                    finalString = dollarFormat(changeInTotalString);
                } else {
                    finalString = "+" + dollarFormat(changeInTotalString);
                }
                Toast.makeText(getActivity(), g.epochToNormal((long) dataPoint.getX()) + "      " + finalString, Toast.LENGTH_SHORT).show();
            }
        });

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()) {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    if (value < 0) {
                        value = Math.abs(value);
                        return "-$" + super.formatLabel(value, isValueX);
                    } else {
                        return "$" + super.formatLabel(value, isValueX);
                    }
                }
            }
        });
        graph.getViewport().setBackgroundColor(222222222);
        graph.getGridLabelRenderer().setNumHorizontalLabels(2);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);


        graph.getViewport().setMinX(dataPoints[0].getX() / 1.0000001);
        graph.getViewport().setMaxX(dataPoints[dataPoints.length - 1].getX() * 1.0000001);

        if (g.bankrollMin < 0) {
            graph.getViewport().setMinY(g.bankrollMin * 1.1);
        } else if (g.bankrollMin == 0) {
            graph.getViewport().setMinY(-10);
        } else {
            graph.getViewport().setMinY(g.bankrollMin / 1.1);
        }
        if (g.bankrollMax < 0) {
            graph.getViewport().setMaxY(g.bankrollMax / 1.1);
        } else if (g.bankrollMax == 0) {
            graph.getViewport().setMaxY(10);
        } else {
            graph.getViewport().setMaxY(g.bankrollMax * 1.1);
        }

        graph.setVisibility(View.VISIBLE);
    }
}
