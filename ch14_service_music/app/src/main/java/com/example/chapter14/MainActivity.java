package com.example.chapter14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 전역변수 설정
    Intent intent;  // 인텐트 변수설정.
    Button btnstart,btnend; // 버튼 변수 설정.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 인텐스 변수에 저장.시작클래스와 도착클래스
        intent = new Intent(MainActivity.this,MusicService.class);
        // 버튼 바인딩
        btnstart = (Button) findViewById(R.id.btnstart);
        btnend  =  (Button) findViewById(R.id.btnend);
        // 버튼 이벤트 설정
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MusicService클래스는 Service를 상속받기 때문에, 서비스 시작
                startService(intent);
                // 로그찍기
                android.util.Log.i("서비스 테스트","startService()");
                // 토스트 띄우기
                Toast.makeText(getApplicationContext(),"startService()",Toast.LENGTH_SHORT).show();

            }
        });
        // 버튼 이벤트 설정
        btnend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 서비스 중단.
                stopService(intent);
                // 로그찍기
                android.util.Log.i("서비스 테스트","stopService()");
                // 토스트 띄우기
                Toast.makeText(getApplicationContext(),"stopService()",Toast.LENGTH_SHORT).show();
            }
        });
        // Manifest도 수정을 해줘야한다.
    }
}