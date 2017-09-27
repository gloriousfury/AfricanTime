package com.gloriousfury.africantime.utils;

import android.content.Context;

import com.crevation.nglocaltime.Time;
import com.crevation.nglocaltime.hausa.Hausa;
import com.crevation.nglocaltime.igbo.Igbo;
import com.crevation.nglocaltime.yoruba.Yoruba;
import com.gloriousfury.africantime.model.TimeQuestions;
import com.gloriousfury.africantime.model.TimeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Jedidiah on 17/07/2016.
 */
public class DataUtil {

    Context context;
    public static String LANGUAGE_CHOSEN = "Yoruba";
    int hour;
    int minutes;

    public DataUtil(Context context) {

        this.context = context;

    }

    private final String time_language[] = {
            "Igbo",
            "Yoruba",
            "Hausa",
    };


    private final String time_string[] = {
            "10:00",
            "5:00",
            "6:00",
            "5:00",
            "4:30"
    };

    private final String lang_time_questions[] = {
            "Isiwa",
            "Yoruba ssk sks  sls s ",
            "Hausas sl s sls s sls ",
            "Lawa lawa sl s sls s sls ",
            "Kosi sapa rara sl s sls s sls "
    };


    public ArrayList<TimeView> prepareData() {

        ArrayList<TimeView> time_list_array = new ArrayList<>();
        for (int i = 0; i < time_language.length; i++) {

            TimeView item = new TimeView();
            item.setLanguage(time_language[i]);
            time_list_array.add(item);
        }
        return time_list_array;
    }

    public ArrayList<TimeQuestions> prepareQuestions() {

        Random getTime = new Random();
        ArrayList<TimeQuestions> time_list_array = new ArrayList<>();


        for (int i = 0; i < 5; i++) {

            int hour = getTime.nextInt(12);
            int minutes = getTime.nextInt(60);
            if (hour < 10) {
                String hour_string = "1" + String.valueOf(hour);
                hour = Integer.parseInt(hour_string);
            }
            if (minutes < 10) {
                String minute_string = "1" + String.valueOf(minutes);
                minutes = Integer.parseInt(minute_string);
            }

            TimeQuestions item = new TimeQuestions();
            item.setQuestion(getTextInLanguage(hour,minutes));
            item.setTimeString(getTimeString(hour, minutes));
            time_list_array.add(item);
        }
        return time_list_array;
    }


    public String getTextInLanguage(int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);

        Time timeValue;
        String timeValueString = null;

        if (LANGUAGE_CHOSEN.contentEquals("Yoruba")) {
            timeValue = new Yoruba();
            timeValueString = timeValue.getTime(calendar);

        } else if (LANGUAGE_CHOSEN.contentEquals("Igbo")) {

            timeValue = new Igbo();
            timeValueString = timeValue.getTime(calendar);

        } else if (LANGUAGE_CHOSEN.contentEquals("Hausa")) {
            timeValue = new Hausa();
            timeValueString = timeValue.getTime(calendar);
        }


        return timeValueString;
    }

    public String getTimeString(int hour, int minutes){
        String time_string =  String.valueOf(hour)+":" +String.valueOf(minutes);


        return time_string;



    }

}
