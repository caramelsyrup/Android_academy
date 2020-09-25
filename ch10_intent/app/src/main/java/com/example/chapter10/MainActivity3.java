package com.example.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {
    String str3 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        // edt3 바인딩
        EditText edt3 = (EditText) findViewById(R.id.edt3);
        // btn3 바인딩
        Button btn3 = (Button) findViewById(R.id.btn3);

        // intent 받기
        Intent intent3 = getIntent();
        // str3에 보내주는 데이터 받아서 저장.
        str3 = intent3.getStringExtra("data1");
        // 받은 데이터 출력
        edt3.setText(str3);
        // 버튼 기능
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}