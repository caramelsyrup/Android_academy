package com.example.project4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 전역변수
    TextView Text1,Text2;
    CheckBox ChkAgree;
    RadioGroup Rgroup1;
    RadioButton RdoDog,RdoCat,RdoRabbit;
    Button BtnOK;
    ImageView ImgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.ex04_28);
        setTitle("애완동물 보자");
        // 변수에 저장
        Text1 = (TextView) findViewById(R.id.Text1);
        ChkAgree = (CheckBox) findViewById(R.id.ChkAgree);
        Text2 = (TextView) findViewById(R.id.Text2);
        Rgroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        RdoDog = (RadioButton) findViewById(R.id.RdoDog);
        RdoCat = (RadioButton) findViewById(R.id.RdoCat);
        RdoRabbit = (RadioButton) findViewById(R.id.RdoRabbit);
        BtnOK = (Button) findViewById(R.id.BtnOK);
        ImgPet = (ImageView) findViewById(R.id.ImgPet);

        // 체크박스 누를시 시작
        ChkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 체크가 되면 Visible로
                if(ChkAgree.isChecked()==true){
                    Text2.setVisibility(View.VISIBLE);
                    Rgroup1.setVisibility(View.VISIBLE);
                    BtnOK.setVisibility(View.VISIBLE);
                    ImgPet.setVisibility(View.VISIBLE);
                // 체크가 되지 않으면, 그대로 안보이도록
                }else{
                    Text2.setVisibility(View.INVISIBLE);
                    Rgroup1.setVisibility(View.INVISIBLE);
                    BtnOK.setVisibility(View.INVISIBLE);
                    ImgPet.setVisibility(View.INVISIBLE);
                }
            }
        });

        // 애완동물 선택하기
        BtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 분기점이 여러개 이므로 스위치문을 활용함. 아니면 if와 elseif를 지속적으로 사용
                switch (Rgroup1.getCheckedRadioButtonId()){
                    case R.id.RdoDog:
                        ImgPet.setImageResource(R.drawable.dog);
                        break;
                    case  R.id.RdoCat:
                        ImgPet.setImageResource(R.drawable.cat);
                        break;
                    case  R.id.RdoRabbit:
                        ImgPet.setImageResource(R.drawable.rabbit);
                        break;
                    default:
                        Toast.makeText(MainActivity.this,"동물을 선택하세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}