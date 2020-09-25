package com.example.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int n =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // edt 바인딩
        final EditText edt1 = (EditText) findViewById(R.id.edt1);
        // 버튼 바인딩
        Button btn1 = (Button) findViewById(R.id.btn1);
        // 라디오 버튼 바인딩
        final RadioButton main2 = (RadioButton) findViewById(R.id.main2);
        final RadioButton main3 = (RadioButton) findViewById(R.id.main3);

        // 버튼을 누르면 화면 전환 되도록
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 라디오버튼 2눌렀을 때,
                if(main2.isChecked()){
                    // 인텐트 객체 생성(시작클래스,도착클래스)
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    // edt1의 데이터를 String형으로 저장
                    String str1 = edt1.getText().toString();
                    // intent의 putExtra를 이용하여 str1을 data1이라는 이름으로 저장.
                    intent.putExtra("data1",str1);
                    startActivity(intent);
                }
                // 라디오버튼 3을 눌렀을 때,
                else if(main3.isChecked()){
                    Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                    String str1 = edt1.getText().toString();
                    intent.putExtra("data1",str1);
                    startActivity(intent);
                }
                // 라디오버튼 안누르면 메시지 띄우기
                else{
                    Toast.makeText(MainActivity.this,"라디오버튼을 선택하세요.",Toast.LENGTH_SHORT).show();
                }

//                // Intent는 연결하는 객체. (시작클래스,도착클래스)
//                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
//               // 데이터 가지고 가기
//                String str1 = edt1.getText().toString();
//                // key, value 형태. value의 데이터 형태에 유의한다. 단순 데이터형, 배열객체형 등 여러가지 가능.
//                intent.putExtra("data1",Integer.parseInt(str1));*/
//                n =  Integer.parseInt(edt1.getText().toString());
//                intent.putExtra("data1",n);
//                // extras도 존재함.
//                // intent.putExtras()
//                // 인텐트 실행
//                startActivity(intent);
            }
        });
    }
}