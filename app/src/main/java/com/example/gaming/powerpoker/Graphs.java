package com.example.gaming.powerpoker;

import android.database.Cursor;

import com.jjoe64.graphview.series.DataPoint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gaming on 7/30/2015.
 */
public class Graphs {

    public double bankrollMax = 0;
    public double bankrollMin = 0;


    public static String epochToNormal(long number){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return sdf.format(new Date(number));
    }

    public DataPoint[] getBankrollGraphPoints(Database db, String bankrollName) {
        ArrayList<DataPoint> point = new ArrayList<>();
        double bankrollTally = 0;

        Cursor cr = MainActivity.db.getSessionByBankroll(MainActivity.db, bankrollName);

        if (cr.moveToFirst()) {
            while (!cr.isAfterLast()) {
                String s = cr.getString(0);
                DateFormat dF = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                Date date = null;
                try {
                    date = dF.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (point.size() == 0) {
                    point.add(new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                } else {
                    for (int i = 0; i < point.size(); i++) {
                        if (date.getTime() < point.get(i).getX()) {
                            point.add(i, new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                            break;
                        } else if (i == point.size() - 1) {
                            point.add(new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                            break;
                        }
                    }
                }
                cr.moveToNext();
            }

            point.add(0, new DataPoint(point.get(0).getX(), 0));
            point.add(0, new DataPoint(point.get(0).getX(), 0));

            DataPoint[] dataPoints = new DataPoint[point.size()];
            for (int i = 0; i < point.size(); i++) {
                dataPoints[i] = point.get(i);
            }

            for (int i = 0; i < dataPoints.length; i++) {
                bankrollTally += dataPoints[i].getY();
                if(bankrollTally < bankrollMin){
                    bankrollMin = bankrollTally;
                }
                if (bankrollTally > bankrollMax) {
                    bankrollMax = bankrollTally;
                }
                dataPoints[i] = new DataPoint(dataPoints[i].getX(), bankrollTally);
            }

            cr.close();
            return dataPoints;
        }
        cr.close();
        return null;
    }

    public DataPoint[] getFormatGraphPoints(Database db, String formatName) {
        ArrayList<DataPoint> point = new ArrayList<>();
        double bankrollTally = 0;

        Cursor cr = MainActivity.db.getAmtsByFormat(MainActivity.db, formatName);

        if (cr.moveToFirst()) {
            while (!cr.isAfterLast()) {
                String s = cr.getString(0);
                DateFormat dF = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                Date date = null;
                try {
                    date = dF.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (point.size() == 0) {
                    point.add(new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                } else {
                    for (int i = 0; i < point.size(); i++) {
                        if (date.getTime() < point.get(i).getX()) {
                            point.add(i, new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                            break;
                        } else if (i == point.size() - 1) {
                            point.add(new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                            break;
                        }
                    }
                }
                cr.moveToNext();
            }

            point.add(0, new DataPoint(point.get(0).getX(), 0));
            point.add(0, new DataPoint(point.get(0).getX(), 0));

            DataPoint[] dataPoints = new DataPoint[point.size()];
            for (int i = 0; i < point.size(); i++) {
                dataPoints[i] = point.get(i);
            }

            for (int i = 0; i < dataPoints.length; i++) {
                bankrollTally += dataPoints[i].getY();
                if(bankrollTally < bankrollMin){
                    bankrollMin = bankrollTally;
                }
                if (bankrollTally > bankrollMax) {
                    bankrollMax = bankrollTally;
                }
                dataPoints[i] = new DataPoint(dataPoints[i].getX(), bankrollTally);
            }

            cr.close();
            return dataPoints;
        }
        cr.close();
        return null;
    }

    public DataPoint[] getStakesGraphPoints(Database db, String stakesName) {
        ArrayList<DataPoint> point = new ArrayList<>();
        double bankrollTally = 0;

        Cursor cr = MainActivity.db.getAmtsByStakes(MainActivity.db, stakesName);

        if (cr.moveToFirst()) {
            while (!cr.isAfterLast()) {
                String s = cr.getString(0);
                DateFormat dF = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                Date date = null;
                try {
                    date = dF.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (point.size() == 0) {
                    point.add(new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                } else {
                    for (int i = 0; i < point.size(); i++) {
                        if (date.getTime() < point.get(i).getX()) {
                            point.add(i, new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                            break;
                        } else if (i == point.size() - 1) {
                            point.add(new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                            break;
                        }
                    }
                }
                cr.moveToNext();
            }

            point.add(0, new DataPoint(point.get(0).getX(), 0));
            point.add(0, new DataPoint(point.get(0).getX(), 0));

            DataPoint[] dataPoints = new DataPoint[point.size()];
            for (int i = 0; i < point.size(); i++) {
                dataPoints[i] = point.get(i);
            }

            for (int i = 0; i < dataPoints.length; i++) {
                bankrollTally += dataPoints[i].getY();
                if(bankrollTally < bankrollMin){
                    bankrollMin = bankrollTally;
                }
                if (bankrollTally > bankrollMax) {
                    bankrollMax = bankrollTally;
                }
                dataPoints[i] = new DataPoint(dataPoints[i].getX(), bankrollTally);
            }

            cr.close();
            return dataPoints;
        }
        cr.close();
        return null;
    }

    public DataPoint[] getVenueGraphPoints(Database db, String venueName) {
        ArrayList<DataPoint> point = new ArrayList<>();
        double bankrollTally = 0;

        Cursor cr = MainActivity.db.getAmtsByVenue(MainActivity.db, venueName);

        if (cr.moveToFirst()) {
            while (!cr.isAfterLast()) {
                String s = cr.getString(0);
                DateFormat dF = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                Date date = null;
                try {
                    date = dF.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (point.size() == 0) {
                    point.add(new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                } else {
                    for (int i = 0; i < point.size(); i++) {
                        if (date.getTime() < point.get(i).getX()) {
                            point.add(i, new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                            break;
                        } else if (i == point.size() - 1) {
                            point.add(new DataPoint(date.getTime(), cr.getDouble(2) - cr.getDouble(1)));
                            break;
                        }
                    }
                }
                cr.moveToNext();
            }

            point.add(0, new DataPoint(point.get(0).getX(), 0));
            point.add(0, new DataPoint(point.get(0).getX(), 0));

            DataPoint[] dataPoints = new DataPoint[point.size()];
            for (int i = 0; i < point.size(); i++) {
                dataPoints[i] = point.get(i);
            }

            for (int i = 0; i < dataPoints.length; i++) {
                bankrollTally += dataPoints[i].getY();
                if(bankrollTally < bankrollMin){
                    bankrollMin = bankrollTally;
                }
                if (bankrollTally > bankrollMax) {
                    bankrollMax = bankrollTally;
                }
                dataPoints[i] = new DataPoint(dataPoints[i].getX(), bankrollTally);
            }

            cr.close();
            return dataPoints;
        }
        cr.close();
        return null;
    }



    public DataPoint[] getTotalEarningsGraphPoints(Database db) {
        ArrayList<DataPoint> point = new ArrayList<>();
        double bankrollTally = 0;

        Cursor cr = MainActivity.db.getSessionTableDateDesc(MainActivity.db);

        if (cr.moveToFirst()) {
            while (!cr.isAfterLast()) {
                String s = cr.getString(1);
                DateFormat dF = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                Date date = null;
                try {
                    date = dF.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (point.size() == 0) {
                    point.add(new DataPoint(date.getTime(), cr.getDouble(10) - cr.getDouble(5)));
                } else {
                    for (int i = 0; i < point.size(); i++) {
                        if (date.getTime() < point.get(i).getX()) {
                            point.add(i, new DataPoint(date.getTime(), cr.getDouble(10) - cr.getDouble(5)));
                            break;
                        } else if (i == point.size() - 1) {
                            point.add(new DataPoint(date.getTime(), cr.getDouble(10) - cr.getDouble(5)));
                            break;
                        }
                    }
                }
                cr.moveToNext();
            }

            point.add(0, new DataPoint(point.get(0).getX(), 0));
            point.add(0, new DataPoint(point.get(0).getX(), 0));

            DataPoint[] dataPoints = new DataPoint[point.size()];
            for (int i = 0; i < point.size(); i++) {
                dataPoints[i] = point.get(i);
            }

            for (int i = 0; i < dataPoints.length; i++) {
                bankrollTally += dataPoints[i].getY();
                if(bankrollTally < bankrollMin){
                    bankrollMin = bankrollTally;
                }
                if (bankrollTally > bankrollMax) {
                    bankrollMax = bankrollTally;
                }
                dataPoints[i] = new DataPoint(dataPoints[i].getX(), bankrollTally);
            }

            cr.close();
            return dataPoints;
        }
        cr.close();
        return null;
    }

}
