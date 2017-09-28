package com.gloriousfury.africantime.fragment;

import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.crevation.nglocaltime.Time;
import com.crevation.nglocaltime.yoruba.Yoruba;
import com.gloriousfury.africantime.R;
import com.gloriousfury.africantime.model.TimeQuestions;
import com.gloriousfury.africantime.utils.DataUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * Created by OLORIAKE KEHINDE on 11/16/2016.
 */

public class LearningFragment extends Fragment implements TimePickerDialog.OnTimeSetListener, View.OnClickListener {


    public LearningFragment() {

    }

    public static LearningFragment newInstance() {
        LearningFragment fragment = new LearningFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    TextView timeText, digitalTime, score, language;
    RelativeLayout testCover, testView;
    LinearLayout scoreView;
    int pickerHour = 0;// int value for the selected hour on the timepicker dialog
    int pickerMin = 0; // int value for the selected minute on the timepicker dialog
    Button btnAction, btnStart, btnRestart;
    boolean started;
    ArrayList<String> questions; //ArrayList for the generated random questions
    ArrayList<String> answers;  //ArrayList of the answers to the generated random questions
    ArrayList<TimeQuestions> preparedQuestions;
    int question_stage = 0;     //value for question counter . question limit is 5
    int total_score = 0;       //value for total score after questions are answered
    ImageView mediaChange;
    CardView cardLayout, testCardLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.learning_view2, container, false);

        timeText = (TextView) view.findViewById(R.id.time_text);
        digitalTime = (TextView) view.findViewById(R.id.digital_time);
        language = (TextView) view.findViewById(R.id.language);
        score = (TextView) view.findViewById(R.id.tv_score);
        mediaChange = (ImageView) view.findViewById(R.id.img_media);


        testCover = (RelativeLayout) view.findViewById(R.id.rl_test_cover);
        testView = (RelativeLayout) view.findViewById(R.id.rl_test_view);
        scoreView = (LinearLayout) view.findViewById(R.id.ln_test_score_view);
        cardLayout = (CardView) view.findViewById(R.id.img_layout);
        testCardLayout = (CardView) view.findViewById(R.id.cv_test_view);

        btnAction = (Button) view.findViewById(R.id.btn_action);
        btnStart = (Button) view.findViewById(R.id.btn_start);
        btnRestart = (Button) view.findViewById(R.id.btn_restart);


        btnStart.setOnClickListener(this);
        btnAction.setOnClickListener(this);
        btnRestart.setOnClickListener(this);
        digitalTime.setOnClickListener(this);


        return view;
    }

    //listener for the time picker dialog
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        pickerHour = hourOfDay;
        pickerMin = minute;

        changeTimeText(pickerHour, pickerMin);
    }


    //String formatter for the digitalTime textview
    public void changeTimeText(int Hour, int Min) {
        String formattedTime = String.valueOf(Hour) + ":" + String.valueOf(Min);
        digitalTime.setText(formattedTime);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.digital_time:
                Toast.makeText(getContext(), "I clicked", Toast.LENGTH_LONG).show();
                Calendar cal = Calendar.getInstance();
                TimePickerDialog tp1 = new TimePickerDialog(getContext(), this, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);
                tp1.show();

                break;
            case R.id.btn_action:
                if (question_stage == 0) {
                    //checking the answer before next question is set
                    checkAnswer(questions.get(0), pickerHour, pickerMin, preparedQuestions);

                    // Changing the question set
                    setQuestions(questions, answers);


                } else if (question_stage <= 3) {
                    checkAnswer(questions.get(question_stage -1), pickerHour, pickerMin, preparedQuestions);

                    setQuestions(questions, answers);


                } else if (question_stage == 4) {
                    checkAnswer(questions.get(4), pickerHour, pickerMin, preparedQuestions);
                    setQuestions(questions, answers);
                    btnAction.setText("Restart");
                    showScore();


                } else {

                    btnAction.setText("Next");


                }


                break;
            case R.id.btn_start:
                if (started) {


                } else {

                    testCover.setVisibility(View.INVISIBLE);
                    testView.setVisibility(View.VISIBLE);

                    testCardLayout.setVisibility(View.VISIBLE);
                    cardLayout.setVisibility(View.VISIBLE);
                    createQuiz();
                }


                break;

            case R.id.btn_restart:
                btnAction.setText("NEXT");
                restartTest();

                break;
            default:


        }
    }


    public void createQuiz() {
        DataUtil dataUtil = new DataUtil(getContext());

        /*The dataUtil.prepareQuestions method creates a set of 5 questions from randomized numbers(1-12 for hours & 1-60 for
        miniutes), while saving the chosen time and minutes in string format using the setTimeString Method*/
        preparedQuestions = dataUtil.prepareQuestions();

        //The random arraylist is persisted in this class
        ArrayList<TimeQuestions> arrayList = preparedQuestions;
        questions = new ArrayList<>();
        answers = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            String question = arrayList.get(i).getQuestion();
            String answer = arrayList.get(i).getTimeString();

            questions.add(question);
            answers.add(answer);


        }
        setQuestions(questions, answers);
    }


    //Changing the Questions and resetting the digital time
    public void setQuestions(ArrayList<String> questions, ArrayList<String> answers) {
        if (question_stage == 0) {
            timeText.setText(questions.get(0));
            digitalTime.setText("00:00");
            question_stage++;
        } else if (question_stage <= 4) {
            digitalTime.setText("00:00");
            timeText.setText(questions.get(question_stage));
            question_stage++;
        } else {

//            question_stage++;
        }
    }



    //Checking if an answer is wrong or right
    public void checkAnswer(String question, int selectedHour, int selectedMinutes, ArrayList<TimeQuestions> timeQuestion) {

        //2Ways the answer can be checked,
        // First Way: checking if the time chosen through the time picker is same thing with the one saved
        //in the TimeQuestions.getTimeString Item, this is done as each question is anwsered
        // Second Way: conversion of time chosen thrrought the time picker to respective language chosen and checking
        // if contentEquals the text of the question

        //This below is using the second method, which is harder and less effiecient - I forgot I saved the timeString.

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
        calendar.set(Calendar.MINUTE, selectedMinutes);

        Time yoruba = new Yoruba();
        String calendarTime = yoruba.getTime(calendar);
        String question_string = null;
        String answer_string = null;
        if (question_stage <= 4) {
            question_string = timeQuestion.get(question_stage).getQuestion();
            answer_string = timeQuestion.get(question_stage).getTimeString();
        }
        if (question.contentEquals(calendarTime)) {
            //YOu are right
            Toast.makeText(getContext(), question_string + "  " + answer_string, Toast.LENGTH_LONG).show();
            total_score++;
            changePictureCorrect();

        } else {
            //You are wrong
            Toast.makeText(getContext(), question_string + "  " + answer_string, Toast.LENGTH_LONG).show();
            changePictureWrong();

        }


    }

    public void restartTest() {
        testCover.setVisibility(View.VISIBLE);
        testView.setVisibility(View.INVISIBLE);
        testCardLayout.setVisibility(View.INVISIBLE);
        scoreView.setVisibility(View.INVISIBLE);
        cardLayout.setVisibility(View.INVISIBLE);
        question_stage = 0;


    }


    //Shows score at the end of each testing session
    public void showScore() {
        score.setText(String.valueOf(total_score));
        testCover.setVisibility(View.INVISIBLE);
        testView.setVisibility(View.INVISIBLE);
        testCardLayout.setVisibility(View.INVISIBLE);

        scoreView.setVisibility(View.VISIBLE);
        btnAction.setVisibility(View.VISIBLE);


    }


    //Pictures that  give you an hint you got an answer wrong
    private final int wrong_pictures[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4

    };

    //Pictures that  give you an hint you got an answer right
    public final int correct_pictures[] = {
            R.drawable.image1_correct,
            R.drawable.image2_correct,
            R.drawable.image3_correct

    };

    public void changePictureWrong() {
        if (question_stage != 1 || question_stage !=0) {
            Random example = new Random();
            int randomNo = example.nextInt(wrong_pictures.length);

            mediaChange.setImageResource(wrong_pictures[randomNo]);
        }
    }

    public void changePictureCorrect() {
        if (question_stage != 1  || question_stage !=0) {
            Random example = new Random();
            int randomNo = example.nextInt(correct_pictures.length);

            mediaChange.setImageResource(correct_pictures[randomNo]);
        }

    }


    // TODO Seperate the testing layout into different fragments
    // TODO Create a widget that tells the time in selected language and allow for customization
    // TODO Proper design of the ap
    // TODO Allow for language change for the testing page throught a spinner/dropdown



//
//    UtilsClass utils = new UtilsClass(dialog.getContext());
//
//    String city = String.valueOf(citySpinner.getSelectedItem());
//    String lang = String.valueOf(langSpinner.getSelectedItem());
//    String sort = String.valueOf(sortSpinner.getSelectedItem());
//
//                utils.storeFilterParameters(city, lang, sort);
////TODO Create Event when Dialog is launched
//                utils.storeFilterChange(true);
//
//    responseIntent = new Intent();
//
//    event = new AppMainServiceEvent();
//                event.setMainIntent(responseIntent);
//                event.setEventType(AppMainServiceEvent.FILTER_PARAMETERS_CHANGED);
//                EventBus.getDefault().post(event);
//                dialog.dismiss();

}