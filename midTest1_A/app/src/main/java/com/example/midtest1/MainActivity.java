package com.example.midtest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 제목 설정
        setTitle("구구단 맞추기");
        // 화면 구성요소 바인딩
        // 숫자 입력.(랜덤)
        final EditText num1 = (EditText) findViewById(R.id.num1);
        // 숫자 입력.(랜덤)
        final EditText num2 = (EditText) findViewById(R.id.num2);
        // 정답 입력.(사용자 입력)
        final EditText num3 = (EditText) findViewById(R.id.num3);
        // 버튼(난수발생)
        Button btnRan = (Button) findViewById(R.id.btnRan);
        // 버튼(정답확인)
        Button btnChk = (Button) findViewById(R.id.btnChk);
        // 리스트뷰 바인딩
        final ListView listView = (ListView) findViewById(R.id.listView);

        // 난수 발생 버튼 이벤트
        btnRan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 난수 설정(2 사이 9)
                int Num1 = new Random().nextInt(8)+2;
                String str1 = String.valueOf(Num1);
                int Num2 = new Random().nextInt(8)+2;
                String str2 = String.valueOf(Num2);
                // 발생된 난수 입력되게
                num1.setText(str1);
                num2.setText(str2);
            }
        });

        btnChk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 입력된 값 받기.
                int Num1 = Integer.parseInt(num1.getText().toString());
                int Num2 = Integer.parseInt(num2.getText().toString());
                // 구구단 목록 띄우기
                // 구구단 배열 만들기
                final ArrayList<String> gugudan = new ArrayList<String>();
                // 틀렸을때 구구단 목록 띄우기위해서
                for(int i=0; i<9;i++){
                    // i번째 인덱스 구구단배열에 저장.
                    // 0부터 시작하여 8로 끝나기에, 1을 더하여 1~9사이로 만든다. 그리고 해당 값을 문자열로 저장.
                    String str1 = String.valueOf(i+1);
                    // 구구단의 1~9까지 곱한 값을 문자열로 저장
                    String result1 = String.valueOf(Num1*(i+1));
                    // gugudan배열에 추가 한다.
                    gugudan.add(num1.getText().toString()+" x "+str1+" = "+result1);
                }
                // 어뎁터 객체를 만든다.
                ArrayAdapter<String>adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,gugudan);
                // 리스트뷰에 어뎁터 장착
                listView.setAdapter(adapter);
                // 리스트뷰가 보이지 않게 해놓는다.
                listView.setVisibility(View.INVISIBLE);

                // 사용자가 입력하지않고 버튼을 누를수도 있기에,
                try {
                    // 입력후 곱셈 연산을 한다.(정답)
                    int result = Num1*Num2;
                    // 사용자 입력값 받기
                    int Num3 = Integer.parseInt(num3.getText().toString());
                    // 둘의 비교
                    if (Num3 == result) {
                        // 토스트 메시지 띄우기
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        // 리스트뷰 영역을 보여주지 않는다.
                        listView.setVisibility(View.INVISIBLE);
                    } else {
                        // 리스트뷰 영역을 보여준다.
                        listView.setVisibility(View.VISIBLE);
                        // 토스트 메시지 띄우기
                        Toast.makeText(MainActivity.this, "틀렸습니다!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){   // 사용자가 값을 입력하지 않고 정답 클릭시,
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "정답을 입력하세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}