package com.example.practice10_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // 인텐트 생성 후 받기
        Intent inIntent = getIntent();
        // 각 라디오 버튼의 기능별로 구분된 것을 판단하여, 기능
        String str = inIntent.getStringExtra("function");
        int Num1 = inIntent.getIntExtra("num1",0);
        int Num2 = inIntent.getIntExtra("num2",0);
        // 연산의 결과를 저장할 변수를 따로 생성.
        int value =0;
        // 연산 시작.
        if(str.equals("A")){
            value = Num1+Num2;
        }
        if(str.equals("B")){
            value = Num1-Num2;
        }
        if(str.equals("C")){
            value = Num1*Num2;
        }
        if(str.equals("D")){
            value = Num1/Num2;
        }

        // 버튼 바인딩
        Button btn2 = (Button) findViewById(R.id.btn2);
        // 연산의 결과 값을 변수에 저장.(인텐트로 이동시 값을 가져가기위해서)
        final int finalValue = value;
        // 버튼 클릭 이벤트
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이전 클래스로 다시 보내기 위해서 인텐트 객체 생성
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                // 연산된 결과 값을 다시 가지고 가서
                outIntent.putExtra("result", finalValue);
                // 결과를 가지고 감.
                setResult(RESULT_OK,outIntent);
                // 현재 액티비티 종료
                finish();
            }
        });


    }
}