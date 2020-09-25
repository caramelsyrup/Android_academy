package com.example.midtest2_b;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 활동 시간들 저장 변수 선언
    int time2,time3,time4,time5;
    // 스피너 선택값 저장을 위한 변수 선언
    String resultIdol = null;
    // 대화 상자 뷰를 위한 변수 선언
    View dlgView;
    // 그림을 출력하기 위해서 arraylist 선언
    ArrayList<Integer> ImgArr = new ArrayList<Integer>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 요소들 바인딩
        final EditText sleepTime = (EditText) findViewById(R.id.sleepTime);   // 수면시간
        final EditText codeTime = (EditText) findViewById(R.id.codeTime);     // 코딩시간
        final EditText readTime = (EditText) findViewById(R.id.readTime);     // 독서시간
        final EditText engTime = (EditText) findViewById(R.id.engTime);       // 영어시간
        final EditText exerTime = (EditText) findViewById(R.id.exerTime);     // 운동시간
        final CheckBox cbCode = (CheckBox) findViewById(R.id.cbCode);     // 코딩 체크박스
        final CheckBox cbRead = (CheckBox) findViewById(R.id.cbRead);     // 독서 체크박스
        final CheckBox cbEng = (CheckBox) findViewById(R.id.cbEng);     // 영어 체크박스
        final CheckBox cbExer = (CheckBox) findViewById(R.id.cbExer);     // 운동 체크박스
        Spinner spinner = (Spinner) findViewById(R.id.spinner);     // 이상형 스피너
        Button btnResult = (Button) findViewById(R.id.btnResult);   // 결과 버튼
        final TextView txt1 = (TextView) findViewById(R.id.txt1);     // 결과 1
        final TextView txt2 = (TextView) findViewById(R.id.txt2);     // 결과 2
        final TextView txt3 = (TextView) findViewById(R.id.txt3);     // 결과 3
        final LinearLayout image = (LinearLayout) findViewById(R.id.image);

        // 이상형 스피너를 위해 배열 만들기
        final String[] idol = {"Appearance(외모)","Ability(능력)","Personality(성격)","Lineage(가문)","Faith(신앙)"};
        // 배열 어뎁터 객체 생성
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,idol);
        // 스피너에 어뎁터 꽂기
        spinner.setAdapter(adapter);
        // 스피너에서 해당 항목 클릭시
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 해당 항목을 변수에 저장.
                resultIdol=idol[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // 버튼 눌렸을때, 이벤트
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 그림 영역 초기화
                image.removeAllViews();
                // 수면 시간 값 저장.
                String str1 = sleepTime.getText().toString();
                // 코딩,독서,영어,운동 값 저장.
                String str2 = codeTime.getText().toString().trim(); // 코딩
                String str3 = readTime.getText().toString().trim(); // 독서
                String str4 = engTime.getText().toString().trim();  // 영어
                String str5 = exerTime.getText().toString().trim(); // 운동
                // 수면시간 값이 없다면,
                if(str1.trim().isEmpty()){
                    // inflate해서 뷰에 넣는다.
                    dlgView = (View) View.inflate(MainActivity.this, R.layout.dlg, null);
                    // 대화상자 객체 생성
                    Dialog dlg = new Dialog(MainActivity.this);
                    // 뷰를 객체에 꽂기
                    dlg.setContentView(dlgView);
                    //  대화상자 보이기
                    dlg.show();
                }else{
                    // 수면 시간 출력
                    txt1.setText("1. 나는 "+str1+"시간 잠을 잡니다!");
                    // 활동시간 초기화
                    int total = 0;
                    // 그림 띄우기
                    ImgArr.clear(); // 초기화
                    // 체크박스 눌렀을때,(코딩)
                    if(cbCode.isChecked()==true){
                        // 시간 영역이 비어 있다면,
                        if(str2.trim().isEmpty()){
                            // 토스트 띄우기
                            Toast.makeText(getApplicationContext(),"시간을 입력 하세요!",Toast.LENGTH_SHORT).show();
                        }else{
                            // 값을 저장.
                            time2 = Integer.parseInt(str2);
                            // 해당 체크박스의 그림을 배열에 추가.
                            ImgArr.add(R.drawable.programming);
                        }
                    }else{
                        // 체크가 풀리면 0으로 저장
                        time2 =0;
                    }
                    // 체크박스 눌렀을때,(독서)
                    if(cbRead.isChecked()==true){
                        // 시간이 입력 되었는지 판단
                        if(str3.trim().isEmpty()){
                            // 토스트 띄우기
                            Toast.makeText(getApplicationContext(),"시간을 입력 하세요!",Toast.LENGTH_SHORT).show();
                        }else {
                            // 시간 값 저장.
                            time3 = Integer.parseInt(str3);
                            // 해당 체크박스의 그림을 배열에 추가.
                            ImgArr.add(R.drawable.book_reading);
                        }
                    }else{
                        time3 =0;
                    }
                    // 체크박스 눌렀을때,(영어)
                    if(cbEng.isChecked()==true){
                        if(str4.trim().isEmpty()){
                            // 토스트 띄우기
                            Toast.makeText(getApplicationContext(),"시간을 입력 하세요!",Toast.LENGTH_SHORT).show();
                        }else{
                            time4 = Integer.parseInt(str4);
                            // 해당 체크박스의 그림을 배열에 추가.
                            ImgArr.add(R.drawable.engligh_study);
                        }
                    }else{
                        time4 =0;
                    }
                    // 체크박스 눌렀을때,(운동)
                    if(cbExer.isChecked()==true){
                        if(str5.trim().isEmpty()){
                            // 토스트 띄우기
                            Toast.makeText(getApplicationContext(),"시간을 입력 하세요!",Toast.LENGTH_SHORT).show();
                        }else {
                            time5 = Integer.parseInt(str5);
                            // 해당 체크박스의 그림을 배열에 추가.
                            ImgArr.add(R.drawable.work_out);
                        }
                    }else{
                        time5 =0;
                    }
                    // 위의 시간들 총합
                    total = time2+time3+time4+time5;
                    // 각 활동시간들 총합 출력.
                    txt2.setText("2. 나는 꿈을 위해"+total+"시간 투자합니다!");
                    // 스피너 결과 출력.
                    txt3.setText("3. 나의 이상형은 "+resultIdol+"입니다!");
                  // 이미지 띄우기
                    for(int i=0; i<ImgArr.size();i++){
                        // 이미지뷰 객체 생성
                        ImageView imageView = new ImageView(MainActivity.this);
                        // 이미지 소스 설정
                        imageView.setBackgroundResource(ImgArr.get(i));
                        // 테이블 행에 이미지 뷰 추가
                        image.addView(imageView,150,150);
                    }
                }
            }
        });
    }
}