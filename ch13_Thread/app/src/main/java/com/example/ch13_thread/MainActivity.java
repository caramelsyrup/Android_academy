package com.example.ch13_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 1. 프로그레스바/시크바 선언
/*    ProgressBar progressBar1;
    Button btnInc,btnDec;
    TextView tvSeek;
    SeekBar seekBar1;*/
    // 2. 쓰레드 기본 선언
    ProgressBar pb1,pb2;
    TextView tv1,tv2;
    Button threadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.pr_sb);*/     // 1. 프로그레스바/시크바
        setContentView(R.layout.thread);    // 2. 쓰레드 기본
        /*setContentView(R.layout.activity_main);*/

    /*    // 1. 프로그레스바/시크바 코딩
        // 바인딩
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        btnInc = (Button) findViewById(R.id.btnInc);
        btnDec = (Button) findViewById(R.id.btnDec);
        // 버튼 이벤트
        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar1.incrementProgressBy(10);
            }
        });
        // 버튼 이벤트
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar1.incrementProgressBy(-10);
            }
        });
        // 바인딩
        tvSeek = (TextView) findViewById(R.id.tvSeek);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);

        // 원모양을 터치하여 좌우로 끌면 동작한다.
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 동작시 해당 진행률이 출력.
                tvSeek.setText("진행률 : "+progress+" %");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
*/
        // 2. 쓰레드 기본
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        threadBtn = (Button) findViewById(R.id.threadBtn);
        threadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 첫번째 프로그래스바 이동시키는 쓰레드 메소드 만듦.
                new Thread() {
                    public void run() {
                        for (int i = 0; i < 100; i = i + 2) {
                            // UI가 메소드 실행되는 동안의 값들을 출력하기 위해서
                            // 메소드 추가. 진행되는 동안 위젯이 변경됨.
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb1.setProgress(pb1.getProgress() + 3);
                                    tv1.setText("진행률 : "+pb1.getProgress()+" %");
                                }
                            });
                            // 0.1초 멈춤
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
                // 두번째 프로그래스바 이동시키는 쓰레드 메소드 만듦.
                new Thread() {
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb2.setProgress(pb2.getProgress() + 2);
                                    tv2.setText("진행률 : "+pb2.getProgress()+" %");
                                }
                            });
                            // 0.1초 멈춤
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });
       // 기능 하다당 쓰레드 하나씩 설정하여, 여러 쓰레드를 실행.
    }
}