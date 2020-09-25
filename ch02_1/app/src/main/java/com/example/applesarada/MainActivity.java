package com.example.applesarada;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText Edit1, Edit2;
    Button BtnAdd,BtnSub,BtnMul,BtnDiv;
    TextView TextResult;
    String num1;
    String num2;
    Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);
    //    setContentView(R.layout.ex04_02);
    //    setContentView(R.layout.main);

    /*    TextView tv1,tv2,tv3;
    // xml파일에서 해당 항목을 만들면 객체를 알아서 만들어 낸다.
        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);

        tv1.setText("안녕하세요?");
        tv1.setTextColor(Color.RED);

        tv2.setTextSize(30);
        tv2.setTypeface(Typeface.SANS_SERIF , Typeface.BOLD_ITALIC);

        tv3.setText("가나다라마바사아자차카타파하가나다라마바사아자차카타파하");
        tv3.setSingleLine();*/

        setContentView(R.layout.ex04_19);
        // 입력 값
        Edit1 = (EditText) findViewById(R.id.Edit1);
        Edit2 = (EditText) findViewById(R.id.Edit2);

        // 버튼
        BtnAdd = (Button)findViewById(R.id.BtnAdd);
        BtnAdd.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();
                result = (Integer.parseInt(num1)+Integer.parseInt(num2));
                TextResult.setText("계산 결과 : "+result.toString());
                return false;
            }
        });
        BtnSub = (Button)findViewById(R.id.BtnSub);
        BtnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();
                result = (Integer.parseInt(num1)-Integer.parseInt(num2));
                TextResult.setText("계산 결과 : "+result.toString());
                return false;
            }
        });
        BtnMul = (Button)findViewById(R.id.BtnMul);
        BtnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();
                result = (Integer.parseInt(num1)*Integer.parseInt(num2));
                TextResult.setText("계산 결과 : "+result.toString());
            }
        });
        BtnDiv = (Button)findViewById(R.id.BtnDiv);
        BtnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();
                result = (Integer.parseInt(num1)/Integer.parseInt(num2));
                TextResult.setText("계산 결과 : "+result.toString());
            }
        });

        // 결과 값
        TextResult = (TextView) findViewById(R.id.TextResult);
    }
}