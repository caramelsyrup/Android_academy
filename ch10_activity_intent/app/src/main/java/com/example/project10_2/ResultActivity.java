package com.example.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("투표 결과 화면");

        // 출발 클래스로부터 온 인텐트를 받음.
        Intent intent = getIntent();
        // result 정수 배열을 만든다. 출발클래스에서 보낸 정보가 정수형 배열. 그리고 저장
        int result[] = intent.getIntArrayExtra("VoteCount");
        // imageName 문자열 배열을 만든다. 출발클래스에서 보낸 정보가 문자열 배열. 그리고 저장.
        String imageName[] = intent.getStringArrayExtra("ImageName");

        // 텍스트뷰에는 그림들의 숫자나 이름 숫자나 같기 떄문에 출발클래스로부터 받아온 정보를 이용.
        TextView tv[] = new TextView[imageName.length];
        // 레이팅 바 역시, 동일.
        RatingBar rbar[] =  new RatingBar[imageName.length];
        // 바인딩을 위해서 배열 객체 생성.(텍스트뷰용)
        Integer tvID[] = {
                R.id.tv1,R.id.tv2,R.id.tv3,
                R.id.tv4,R.id.tv5,R.id.tv6,
                R.id.tv7,R.id.tv8,R.id.tv9
        };
        // 바인딩을 위해서 배열 객체 생성.(레이팅바용)
        Integer rbarID[] = {
                R.id.rbar1,R.id.rbar2,R.id.rbar3,
                R.id.rbar4,R.id.rbar5,R.id.rbar6,
                R.id.rbar7,R.id.rbar8,R.id.rbar9
        };
        // 이미지 소스를 위해서
        Integer imageId[]={
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
                R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        };
        // 바인딩 하기, for문으로
        for(int i=0; i<imageName.length; i++){
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] = (RatingBar) findViewById(rbarID[i]);
        }
        // 바인딩 된 곳에 정보 뿌리기
        for(int i=0; i<imageName.length;i++){
            // 텍스트 넣기. 이름 배열에 따라. 인덱스 번호에 따라 정보가 출력되록
            tv[i].setText(imageName[i]);
            // 레이팅바 세팅. 인덱스 번호에 따라 정보가 출력
            rbar[i].setRating((float)result[i]);
        }

        // 최다 투표 그림 제목 뿌리기
        // 텍스트 바인딩.
        TextView bestImgName = (TextView) findViewById(R.id.bestImgName);
        // 이미지 바인딩.
        ImageView bestImg = (ImageView) findViewById(R.id.bestImg);
        // 인텐트로 받아온 레이팅 정보 활용.
        // 0번째는 먼저 저장. 초기 값으로. 투표 결과 값을 a에 저장.
        int a = result[0];
        bestImgName.setText(imageName[0]);
        bestImg.setImageResource(imageId[0]);
        for(int i=1; i<imageName.length; i++){
            // i번째 는 b에 저장.
            int b = result[i];
            // 0번쨰와 1번쨰의 비교.
            if(b>a){    // 1번째가 0번째 보다 클때, 1을 살리고 0을 죽임
                // a에 b를 저장함. 반복 문이 실행 될때, 다음 차순으로 b가 넘어가기에, 현재 값을 살려야함.
                a = b;
                // 해당 인덱스로 텍스트 넣기
                bestImgName.setText(imageName[i]);
                // 해당 인덱스로 이미지 넣기
                bestImg.setImageResource(imageId[i]);
            }
        }


        // 버튼 바인딩 하기
        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        // 버튼 기능
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 액티비티 종료.
                finish();
            }
        });
    }
}