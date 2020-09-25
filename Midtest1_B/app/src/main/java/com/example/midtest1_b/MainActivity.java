package com.example.midtest1_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // 연산 결과 값을 저장할 변수 선언
    double Num1;    // 숫자1
    double Num2;    // 숫자2
    double Num3;    // 결과(정답)
    // 결과 값을 소수 둘째 자리로 표현
    DecimalFormat format = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 요소들 바인딩
        final EditText num1 = (EditText) findViewById(R.id.num1); // 숫자1
        final EditText num2 = (EditText) findViewById(R.id.num2); // 숫자2
        final EditText num3 = (EditText) findViewById(R.id.num3); // 정답
        final TextView operator = (TextView) findViewById(R.id.operator); // 부호
        final RadioGroup rGp = (RadioGroup) findViewById(R.id.rGp);   // 라디오버튼 그룹
        final RadioButton rAdd = (RadioButton) findViewById(R.id.rAdd);   // 더하기 라디오버튼
        final RadioButton rSub = (RadioButton) findViewById(R.id.rSub);   // 빼기 라디오버튼
        final RadioButton rMul = (RadioButton) findViewById(R.id.rMul);   // 곱하기 라디오버튼
        final RadioButton rDiv = (RadioButton) findViewById(R.id.rDiv);   // 나누기(몫) 라디오버튼
        Button result = (Button) findViewById(R.id.result); // 계산하기 버튼

        // 더하기 라디오 버튼
        rAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 텍스트뷰 부호 변환
                operator.setText(rAdd.getText().toString());
            }
        });
        // 빼기 라디오 버튼
        rSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 텍스트뷰 부호 변환
                operator.setText(rSub.getText().toString());
            }
        });
        // 곱하기 라디오 버튼
        rMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 텍스트뷰 부호 변환
                operator.setText(rMul.getText().toString());
            }
        });
        // 나누기(몫) 라디오 버튼
        rDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 텍스트뷰 부호 변환
                operator.setText(rDiv.getText().toString());
            }
        });
        // 계산하기 버튼 누를떄,
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // 연산을 위해서 숫자 저장.
                    Num1 = Double.parseDouble(num1.getText().toString());
                    Num2 = Double.parseDouble(num2.getText().toString());
                    // 연산 체크가 된것을 확인
                    switch (rGp.getCheckedRadioButtonId()){
                        case (R.id.rAdd) :
                            // 숫자 연산이 되어 Num3에 저장.
                            Num3 = Num1+Num2;
                            break;
                        case (R.id.rSub) :
                            // 숫자 연산이 되어 Num3에 저장.
                            Num3 = Num1-Num2;
                            break;
                        case (R.id.rMul) :
                            // 숫자 연산이 되어 Num3에 저장.
                            Num3 = Num1*Num2;
                            break;
                        case (R.id.rDiv) :
                            if(Num2==0){    // 0으로 나누는 경우,
                                // 토스트로 안내문구 띄우기.
                                Toast.makeText(getApplicationContext(),"0으로 나눌수 없습니다.",Toast.LENGTH_SHORT).show();
                            }else{  // 0이 아닌 경우
                                // 숫자 연산이 되어 Num3에 저장.
                                Num3 = Num1/Num2;
                            }
                            break;
                    }
                    // 결과 값을 텍스트뷰에 출력
                    num3.setText(format.format(Num3));
                }catch (Exception e){
                    // 숫자 입력없이 버튼을 누르면 토스트 메시지 띄우기
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}