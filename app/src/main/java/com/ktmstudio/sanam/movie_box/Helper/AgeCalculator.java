package com.ktmstudio.sanam.movie_box.Helper;

import android.content.Context;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by backtrack on 2/19/2017.
 */

public class AgeCalculator {
    private Date date;
    int pYear,pMonth,pDay;
Context context;

    public AgeCalculator(Context context) {
        this.context =context;
    }

    public String getAge(String pYear){
        Calendar my_Date = calculateDate(pYear);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) -my_Date.get(my_Date.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < my_Date.get(my_Date.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }


    public  Calendar calculateDate(String mydate){
        Toast.makeText(context,"year "+mydate,Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(mydate);
            calendar.setTime(date);

            Toast.makeText(context,"year "+calendar.get(calendar.YEAR),Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }

        return calendar;

    }
}
