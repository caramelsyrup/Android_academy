package com.example.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    // 출발 클래스에서 보낸 데이터를 저장할 수 있도록 선언.
    int n =0;
    String str2 ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText edt2 = (EditText) findViewById(R.id.edt2);
        Button btn2 = (Button) findViewById(R.id.btn2);

        // 도착 클래스에서는 인텐트를 받도록 한다.
        Intent intent2 =  getIntent();

       /*

        //
        str2 = String.valueOf(intent2.getIntExtra("data1",0));
        */
        // 출발 클래스에서 보낸 데이터 형태가 String이기에
        str2 = intent2.getStringExtra("data1");
          // n = intent2.getIntExtra("data1",0);
        // 바인딩 된 부분에 텍스트 삽입.
        edt2.setText(str2);
       // edt2.setText(String.valueOf(n));
        // 버튼 기능
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 activity만 종료.
                finish();
            }
        });
    }
}