package com.example.project6_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    // xml 구성 요소들 변수 선언
    Chronometer chronometer;
    Button BtnRev,btnF;
    RadioGroup rGp;
    RadioButton rBn1,rBn2;
    CalendarView calv;
    TimePicker time;
    GridLayout Grid1;
    FrameLayout FrameCalv;
    TextView TextResult;
    int selectyear,selectmonth,selectday,selecthour,selectmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.time);
        setTitle("예약 잘 하기");
        // 변수와 xml요소들 바인딩
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        BtnRev = (Button) findViewById(R.id.BtnRev);
        btnF = (Button) findViewById(R.id.btnF);
        rGp = (RadioGroup) findViewById(R.id.rGp);
        rBn1 = (RadioButton) findViewById(R.id.rBn1);
        rBn2 = (RadioButton) findViewById(R.id.rBn2);
        calv = (CalendarView) findViewById(R.id.calv);
        time = (TimePicker) findViewById(R.id.time);
        Grid1 = (GridLayout) findViewById(R.id.Grid1);
        FrameCalv = (FrameLayout) findViewById(R.id.FrameCalv);
        TextResult = (TextView) findViewById(R.id.TextResult);

        // 예약시작버튼을 누르면 크로노미터가 작동.
        BtnRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 크로노 시작
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
                // 라디오 그룹 나타남.
                rGp.setVisibility(View.VISIBLE);
                // 예약완료버튼 나타남.
                Grid1.setVisibility(View.VISIBLE);
            }
        });

            // 라디오버튼(캘린더)를 누르면 캘린더가 출력
        rBn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    time.setVisibility(View.GONE);
                    FrameCalv.setVisibility(View.VISIBLE);
                }
        });
        // 타임피커를 출력
        rBn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameCalv.setVisibility(View.GONE);
                time.setVisibility(View.VISIBLE);
            }
        });
        // 캘린더에서 날짜 선택
        calv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectyear = year;
                selectmonth = month;
                selectday = dayOfMonth;
            }
        });
        // 타입피커에서 시간 선택
        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selecthour = hourOfDay;
                selectmin = minute;
            }
        });
        // 예약완료 버튼을 누르면 크로노미터가 멈추고, 날짜와 시간 설정 데이터가 TextResult에 작성
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 크로노 멈춤
                chronometer.stop();
                chronometer.setTextColor(Color.BLUE);
                // 날짜와 시간 정보를 TextResult에 출력
                TextResult.setText(selectyear+"년 "+(selectmonth+1)+"월 "+selectday+"일 "+selecthour+"시 "+selectmin+"분 "+"예약됨");
                // 캘린더 타임피커 없애기
                FrameCalv.setVisibility(View.GONE);
                time.setVisibility(View.GONE);
            }
        });

    }
}