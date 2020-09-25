package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 입력 받는 값 2개
    EditText Edit1,Edit2;
    // 버튼 5개
    Button BtnAdd,BtnSub,BtnMul,BtnDiv,BtnRm;
    // 입력 받는 값을 String으로 전환
    String num1,num2;
    // 연산 결과 값
    Float result;
    // 결과 값을 출력
    TextView TextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // 바인딩 (View)
        setContentView(R.layout.main);

        setTitle("찐 계산기");

        // 입력 받는 값 2개 정의
        // 바인딩(위젯), 객체 생성이 필요
        Edit1 = (EditText)findViewById(R.id.Edit1);
        Edit2 = (EditText)findViewById(R.id.Edit2);

        // 버튼 정의
        // 바인딩(위젯)
        BtnAdd = (Button)findViewById(R.id.BtnAdd);
        BtnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // 트림을 변수에 넣을때부터 사용
                num1 = Edit1.getText().toString().trim();
                num2 = Edit2.getText().toString().trim();
                // num1.equals("") 써도 댐
                if(num1.trim().isEmpty()||num2.trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else{
                    result = Float.parseFloat(num1)+Float.parseFloat(num2);
                    TextResult.setText("계산 결과 : "+result.toString());
                }
            }
        });

        BtnSub = (Button)findViewById(R.id.BtnSub);
        BtnSub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();
                if(num1.trim().isEmpty()||num2.trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else{
                    result = Float.parseFloat(num1)-Float.parseFloat(num2);
                    TextResult.setText("계산 결과 : "+result.toString());
                }
            }
        });

        BtnMul = (Button)findViewById(R.id.BtnMul);
        BtnMul.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();
                if(num1.trim().isEmpty()||num2.trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else{
                    result = Float.parseFloat(num1)*Float.parseFloat(num2);
                    TextResult.setText("계산 결과 : "+result.toString());
                }
            }
        });

        BtnDiv = (Button)findViewById(R.id.BtnDiv);
        BtnDiv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();
                    if(num1.trim().isEmpty()||num2.trim().isEmpty()){
                        Toast.makeText(getApplicationContext(),"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                    }else {
                        result = Float.valueOf(Math.round(Float.parseFloat(num1) / Float.parseFloat(num2)));
                        if(result.isInfinite()){
                            Toast.makeText(MainActivity.this,"숫자를 확인하세요.",Toast.LENGTH_SHORT).show();
                        }else{
                            TextResult.setText("계산 결과 : " + result.toString());
                        }
                    }
            }
        });

        BtnRm = (Button)findViewById(R.id.BtnRm);
        BtnRm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();
                    if(num1.trim().isEmpty()||num2.trim().isEmpty()){
                        Toast.makeText(getApplicationContext(),"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                    }else{
                        result = (Float.parseFloat(num1)%Float.parseFloat(num2));
                        if(result.isNaN()){
                            Toast.makeText(MainActivity.this,"숫자를 확인하세요.",Toast.LENGTH_SHORT).show();
                        }else{
                            TextResult.setText("계산 결과 : "+result.toString());
                        }
                    }
            }
        });

        // 결과 값 대입
        TextResult = (TextView) findViewById(R.id.TextResult);
    }
}