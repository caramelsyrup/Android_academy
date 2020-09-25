package com.example.ch05;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num1,num2;
//    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    Button btnAdd,btnSub,btnMul,btnDiv;
    TextView TextResult;
    Double result;
    Button btnArr[] = new Button[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.ex05_01);
        setContentView(R.layout.ex05_02);
        setContentView(R.layout.ex05_03);
        setContentView(R.layout.ex05_04);
        setContentView(R.layout.ex05_05);
        setContentView(R.layout.ex05_06);
        setContentView(R.layout.ex05_07);
        setContentView(R.layout.ex05_10);
        setContentView(R.layout.ex05_11);
        setContentView(R.layout.ex05_12);
        setContentView(R.layout.ex05_13);
        setContentView(R.layout.ex05_14);
        setContentView(R.layout.ex05_18);
        setContentView(R.layout.ex05_19);
        setContentView(R.layout.practice05_01);
        setContentView(R.layout.practice05_02);
        setContentView(R.layout.practice05_04);
        setContentView(R.layout.practice05_04_r);
        setContentView(R.layout.practice05_05);

        // 입력받는 숫자 넣기
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        // 연산버튼 바인딩
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        // 연산결과 비인딩
        TextResult = (TextView) findViewById(R.id.TextResult);

        // 버튼의 ID를 배열화
        final int btnIDs[] = {R.id.b0,R.id.b1,R.id.b2,R.id.b3,R.id.b4,
                R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9};

        // for문을 통하여 숫자버튼 저장 바인딩을 배열화
        for(int i=0; i<btnArr.length;i++){
            btnArr[i] = (Button)findViewById(btnIDs[i]);
        }
        // 이벤트 처리를 배열화
        for(int i=0; i<btnArr.length;i++){
            final int idx = i;
            btnArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(num1.isFocused()){
                        String str1 = num1.getText().toString() + btnArr[idx].getText().toString();
                        num1.setText(str1);
                    }else if(num2.isFocused()){
                        String str2 = num2.getText().toString() + btnArr[idx].getText().toString();
                        num2.setText(str2);
                    }else{
                        Toast.makeText(MainActivity.this,"숫자를 입력하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // 더하기
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num1.equals("")||num2.equals("")){
                    Toast.makeText(MainActivity.this,"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else {
                    result = Double.parseDouble(num1.getText().toString().trim()) + Double.parseDouble(num2.getText().toString().trim());
                    String Result = String.format("%.2f",result);
                    TextResult.setText("계산 결과 : " + Result);
                }
            }
        });

        // 빼기
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num1.equals("")||num2.equals("")){
                    Toast.makeText(MainActivity.this,"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else{
                    result = Double.parseDouble(num1.getText().toString().trim())-Double.parseDouble(num2.getText().toString().trim());
                    String Result = String.format("%.2f",result);
                    TextResult.setText("계산 결과 : "+Result);
                }

            }
        });

        // 곱하기
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = num1.getText().toString().trim();
                String str2 = num2.getText().toString().trim();
                if(str1.isEmpty()||str2.isEmpty()){
                    Toast.makeText(MainActivity.this,"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else{
                    result = Double.parseDouble(str1)*Double.parseDouble(str2);
                    String Result = String.format("%.2f",result);
                    TextResult.setText("계산 결과 : "+Result);
                }

            }
        });

        // 나누기
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = num1.getText().toString().trim();
                String str2 = num2.getText().toString().trim();

                    if(str1.isEmpty()||str2.isEmpty()){
                        Toast.makeText(MainActivity.this,"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                    }else{
                        result = Double.parseDouble(str1)/Double.parseDouble(str2);
                        if(result.isInfinite()){
                            Toast.makeText(MainActivity.this,"숫자를 확인하세요.",Toast.LENGTH_SHORT).show();
                        }else{
                            String Result = String.format("%.2f",result);
                            TextResult.setText("계산 결과 : " + Result);
                        }
                    }
            }
        });

    /*    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
          LinearLayout.LayoutParams.MATCH_PARENT,
          LinearLayout.LayoutParams.MATCH_PARENT
        );
        // LinearLayout객체를 생성(바탕화면)
        LinearLayout baseLayout =  new LinearLayout(this);
        // 현재 레이아웃에서 요소들 정렬 방법
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        // 현재 레이아웃 색깔
        baseLayout.setBackgroundColor(Color.rgb(0,255,0));
        //
        setContentView(baseLayout,params);
        // 버튼 객체 생성
        Button button = new Button(this);
        // 버튼에 텍스트
        button.setText("버튼입니다.");
        // 버튼 색깔
        button.setBackgroundColor(Color.rgb(200,140,170));
        // 버튼을 레이아웃에 추가
        baseLayout.addView(button);
        // 버튼을 클릭시 이벤트가 발생
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 토스트 메시지가 생성되어 출력
                Toast.makeText(getApplicationContext(),"코드로 생성한 버튼 입니다.",Toast.LENGTH_SHORT).show();
            }
        });
*/
/*
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        final LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.rgb(102,0,102));

        setContentView(baseLayout,params);

        final EditText editText = new EditText(this);
        editText.setText("IT COOK BOOK.ANDROID");
        editText.setTextColor(Color.rgb(255,255,255));

        Button btn = new Button(this);
        btn.setText("버튼입니다.");
        btn.setBackgroundColor(Color.rgb(255,255,102));

        final TextView txtView = new TextView(MainActivity.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(editText.getText());
                txtView.setTextColor(Color.rgb(255,255,255));
            }
        });

        baseLayout.addView(editText);
        baseLayout.addView(btn);
        baseLayout.addView(txtView);
*/



    }
}