package com.example.bhanuka.nicreader;

import org.joda.time.LocalDate;
import org.joda.time.MonthDay;
import org.joda.time.Period;
import org.joda.time.YearMonthDay;
import org.joda.time.Years;

import java.security.PrivateKey;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author
 */
public class NIC {

    private final String nicNumber;
    private Date dateAsAt = null;
    private String gender = null;
    private String birthDay = null;
    private String Age = null;
    private String bornDay = null;


    public NIC(String nicNumber) {
        this.dateAsAt = new Date();
        this.nicNumber = nicNumber;
        runDecorder();
    }


    public static String getSex(int text) {
        if (text < 500) {
            return "Male";
        } else if (text > 500) {
            return "Female";
        }
        return "Error";
    }

    public static String getMonthandDate(int text) {

        int counter = 1;
        int date;

        date = text;

        for (int x = 1;
             x < 13; x++) {

            if (x < 8) {

                if (x % 2 > 0 && (date - 31) > 0) {
                    date = date - 31;
                    counter++;

                } else if (x == 2 && (date - 29) > 0) {
                    date = date - 29;
                    counter++;

                } else {
                    if ((date - 30) > 0) {
                        date = date - 30;
                        counter++;
                    }
                }

            }
            if (x > 7) {

                if (x % 2 > 0 && (date - 31) > 0) {
                    date = date - 31;
                    counter++;

                } else if (x == 2 && (date - 29) > 0) {
                    date = date - 29;
                    counter++;

                } else {
                    if ((date - 30) > 0) {
                        date = date - 30;
                        counter++;
                    }
                }

            }

        }
        DecimalFormat format = new DecimalFormat("00");
        return format.format(counter) + "-" + format.format(date);
    }


    public Date getDateAsAt() {
        return dateAsAt;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getAge() {
        //
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat born = new SimpleDateFormat("EEEE");

        Date date = null;
        Years age = null;
        try {
            date = format.parse(getBirthDay());
            bornDay = born.format(date);
            LocalDate birthdate = new LocalDate(date.getYear(), date.getMonth(), date.getDay());
            LocalDate now = new LocalDate();
            age = Years.yearsBetween(birthdate, now);
        } catch (Exception ex) {

        }
        return String.valueOf(age.getYears()).substring(2) + " Years";
    }

    public String getNicNumber() {
        return nicNumber;
    }

    private void runDecorder() {
        String decodeString = this.nicNumber;
        decodeString = decodeString.toUpperCase().replace("V", "");
        try {
            int year = 1900;
            int bday = 0;
            if (decodeString.length() > 9) {
                //19932182092
                year = Integer.parseInt(decodeString.substring(0, 4));
                bday = Integer.parseInt(decodeString.substring(4, 7));

            } else {
                year += Integer.parseInt(decodeString.substring(0, 2));
                bday = Integer.parseInt(decodeString.substring(2, 5));
                //932182902v;

            }
            gender = getSex(bday);
            if (bday > 500) {
                bday = bday - 500;
            }

            birthDay = year + "-" + getMonthandDate(bday);


        } catch (Exception e) {
        }

    }

    public String getBornDay() {
        return bornDay;
    }
}
