package com.example.practice6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    FrameLayout Frame1;
    RadioGroup rGp;
    RadioButton Btn1, Btn2;
    DatePicker date1;
    TimePicker time1;
    TextView selectYear,selectMonth,selectDay,selectHour,selectMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.main);

        // 바인딩
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        Frame1 = (FrameLayout) findViewById(R.id.Frame1);
        rGp = (RadioGroup) findViewById(R.id.rGp);
        Btn1 = (RadioButton) findViewById(R.id.Btn1);
        Btn2 = (RadioButton) findViewById(R.id.Btn2);
        date1 = (DatePicker) findViewById(R.id.date1);
        time1 = (TimePicker) findViewById(R.id.time1);
        selectYear = (TextView) findViewById(R.id.selectYear);
        selectMonth = (TextView) findViewById(R.id.selectMonth);
        selectDay = (TextView) findViewById(R.id.selectDay);
        selectHour = (TextView) findViewById(R.id.selectHour);
        selectMin = (TextView) findViewById(R.id.selectMin);

        // 크로노 누르면 화면 뜸.
        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frame1.setVisibility(View.VISIBLE);
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });
        // 날짜 선정 누르면
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time1.setVisibility(View.GONE);
                date1.setVisibility(View.VISIBLE);
            }
        });
        // 시간 설정 누르면
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date1.setVisibility(View.GONE);
                time1.setVisibility(View.VISIBLE);
            }
        });

        // 하단에 년도 누르면 화면 꺼짐.
        selectYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setTextColor(Color.BLUE);
                chronometer.stop();
                Frame1.setVisibility(View.INVISIBLE);
                date1.setVisibility(View.GONE);
                time1.setVisibility(View.GONE);
            }
        });
    }
}