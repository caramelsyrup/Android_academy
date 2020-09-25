package com.example.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 선호도 투표");

        // 그림에 대한 투표 수를 담을 배열 만들기
        final int voteCount[] = new int[9];
        // 배열에 초기값 0을 주기.
        for(int i=0; i<voteCount.length; i++){
            voteCount[i] = 0;
        }

        // 바인딩을 위해서
        // 이미지 뷰 9개를 담을 배열 만들기
        ImageView image[]= new ImageView[9];
        // 바인딩 할때 넣을 id값또한 배열화 시켜 놓음.
        Integer imageId[]={
            R.id.iv1,R.id.iv2,R.id.iv3,
            R.id.iv4,R.id.iv5,R.id.iv6,
            R.id.iv7,R.id.iv8,R.id.iv9
        };

        // 각 그림들 이름도 저장
        final String imgName[]={
                "독서하는 소녀","꽃장식 모자 소녀","부채를 든 소녀",
                "이레느깡 단 베르앙","잠자는 소녀","테라스의 두 자매",
                "피아노 레슨","피아노 앞의 소녀들","해변에서"
        };
        // for문을 통해서 모든 이미지가 배열화 된것을 실행 반복 하도록,
        for(int i=0; i<imageId.length;i++) {
            // index 변수 선언
            final int index;
            // index변수에 i값을 저장(0부터 8까지 저장됨)
            index=i;
            // 바인딩 작업
            image[index] = (ImageView) findViewById(imageId[i]);
            // 이미지를 클릭하면 투표 할수 있도록, 클릭리스너 정의
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 해당 인덱스의 그림을 클릭하면, 그 인덱스의 투표 또한 1증가 됨.
                    voteCount[index]++;
                    // 해당 인덱스의 투표 누적치를 토스트에 띄움
                    Toast.makeText(getApplicationContext(),imgName[index] + " : 총"+voteCount[index]+ " 표",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 투표 종료 버튼
        // 바인딩
        Button btnResult = (Button) findViewById(R.id.btnResult);
        // 버튼 클릭시 이벤트
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인텐트 객체를 생성. 출발 현재 액티비티에서, 도착은 ResultActivity클래스로.
                Intent intent  =  new Intent(getApplicationContext(),ResultActivity.class);
                // 액티비티 이동시, VoteCount이름으로 voteCount배열의 정보를 저장하여 가져감.
                intent.putExtra("VoteCount",voteCount);
                // 액티비티 이동시, ImageName이름으로 imgName배열의 정보를 저장하여 가져감.
                intent.putExtra("ImageName",imgName);
                // 액티비티 실행하고, intent 실행.
                startActivity(intent);
            }
        });
    }
}