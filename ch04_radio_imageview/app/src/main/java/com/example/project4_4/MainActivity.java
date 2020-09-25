package com.example.project4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 변수 설정
    TextView Text1,Text2;
    Switch Switch;
    RadioGroup Rdg;
    //RadioButton Oreo,Pie,Q;
    RadioButton []rGroupArr = new RadioButton[3];
    Button endBtn,resetBtn;
    ImageView Iamge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.ex04_04);
        // xml영역에서 각 id에 맞는 값들 저장
        Text1 = (TextView) findViewById(R.id.Text1);
        Text2 = (TextView) findViewById(R.id.Text2);
        Switch = (Switch) findViewById(R.id.Switch);
        Rdg = (RadioGroup) findViewById(R.id.Rdg);

        //Oreo = (RadioButton) findViewById(R.id.Oreo);
        //Pie = (RadioButton) findViewById(R.id.Pie);
        //Q = (RadioButton) findViewById(R.id.Q);

        rGroupArr[0] = (RadioButton) findViewById(R.id.Oreo);
        rGroupArr[1] = (RadioButton) findViewById(R.id.Pie);
        rGroupArr[2] = (RadioButton) findViewById(R.id.Q);

        endBtn = (Button) findViewById(R.id.endBtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);
        Iamge = (ImageView) findViewById(R.id.Iamge);

        // 스위치를 클릭하면, 내용이 보이도록 설정
        Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Switch.isChecked()==true){
                    Text2.setVisibility(View.VISIBLE);
                    Rdg.setVisibility(View.VISIBLE);
                    endBtn.setVisibility(View.VISIBLE);
                    resetBtn.setVisibility(View.VISIBLE);
                    Iamge.setImageResource(0);
                    Iamge.setVisibility(View.VISIBLE);
                }else{
                    Text2.setVisibility(View.INVISIBLE);
                    Rdg.setVisibility(View.INVISIBLE);
                    endBtn.setVisibility(View.INVISIBLE);
                    resetBtn.setVisibility(View.INVISIBLE);
                    Iamge.setVisibility(View.INVISIBLE);
                }
             }
        });
        // 라디오 버튼 선택하면 이미지가 뜨도록, 각각3개를 만들지 말고 하나로
        // 1.라디오 버튼을 배열
        // 2. 이미지 객체를 배열화
        final int [] nImages = {R.drawable.oreo,R.drawable.pie,R.drawable.q10};

        for(int i=0; i<rGroupArr.length;i++) {
            // new onClickListener는 독립적인 메소드 객체이기 때문에, final로 변수가 변하지 않도록
            final int finalI = i;
            rGroupArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Iamge.setImageResource(nImages[finalI]);
                }
            });
        }

       /* Oreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iamge.setImageResource(R.drawable.oreo);
            }
        });

        Pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iamge.setImageResource(R.drawable.pie);
            }
        });

        Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iamge.setImageResource(R.drawable.q10);
            }
        });*/

        // 종료 버튼
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 초기화 버튼
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 스위치 초기화
                Switch.setChecked(false);
                // 텍스트 안보이게
                Text2.setVisibility(View.INVISIBLE);
                // 라디오 그룹 체크해제
                Rdg.clearCheck();
                // 라디오 그룹 안보이게
                Rdg.setVisibility(View.INVISIBLE);
                // 버튼 안보이게
                endBtn.setVisibility(View.INVISIBLE);
                // 버튼 안보이게
                resetBtn.setVisibility(View.INVISIBLE);
                // 그림 화면 초기화
                Iamge.setImageResource(0);
                // 그림 화면 안보이게
                Iamge.setVisibility(View.INVISIBLE);
            }
        });


    }
}