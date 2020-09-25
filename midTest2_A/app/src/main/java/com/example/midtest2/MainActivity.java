package com.example.midtest2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 대화상자에 이미지 띄우기
    View dlgView;
    // 혈액형 저장
    String BloodT;
    // 성별저장
    String gender;
    // 갤러리에 나타낼 이미지를 배열로 넣기 위해서 객체 생성
    ArrayList<Integer> habitImg = new ArrayList<Integer>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 화면 요소들 바인딩
        final EditText height = (EditText) findViewById(R.id.height); // 키
        final EditText weight = (EditText) findViewById(R.id.weight); // 체중
        final RadioGroup rGp1 = (RadioGroup) findViewById(R.id.rGp1); // 성별 그룹
        final Spinner spinner = (Spinner) findViewById(R.id.spinner); // 혈액형
        final RadioButton female = (RadioButton) findViewById(R.id.female);   // 여
        final RadioButton male = (RadioButton) findViewById(R.id.male);       // 남
        final CheckBox alchol = (CheckBox) findViewById(R.id.alchol); // 습관
        final CheckBox smoke = (CheckBox) findViewById(R.id.smoke);   // 습관
        final CheckBox exercise = (CheckBox) findViewById(R.id.exercise); // 습관
        Button btnChk = (Button) findViewById(R.id.btnChk); // 몸상태 측정 버튼
        final TextView text1 = (TextView) findViewById(R.id.text1);   // 결과1 혈액형,성별
        final TextView text2 = (TextView) findViewById(R.id.text2);   // 결과2 신체질량지수
        final Gallery gallery = (Gallery) findViewById(R.id.gallery); // 습관 이미지 갤러리
        ImageView image = (ImageView) findViewById(R.id.image); // 이미지?

        // 혈액형 타입 저장.
        // 혈액형 배열
        final String[] blood = {"A","B","AB","O"};
        // 혈액형 스피너 구현
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,blood);
        // 스피너에 어뎁터 꽂기
        spinner.setAdapter(adapter);
        // 해당 항목 클릭시, 정보 저장.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BloodT = blood[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 몸상태 측정 버튼 클릭
        btnChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 키와 체중 값 문자열로 저장
                String str1 =height.getText().toString();
                String str2 =weight.getText().toString();
                // 1.값이 입력되지 않을떄
                // dlgView에 인플레이트해서 뷰 저장
                dlgView = (View) View.inflate(MainActivity.this, R.layout.dlg, null);
                if(str1.equals("")||str2.equals("")) {
                    // 대화 상자 객체 생성.
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    // 대화 상자에 뷰 넣기
                    dlg.setView(dlgView);
                    // 대화 상자 출력
                    dlg.show();
                // 2.값이 입력될때,
                }else {
                    // 그림 배열을 한번 초기화
                    habitImg.clear();
                    // 습관 체크박스 정보 저장
                    if(alchol.isChecked()==true){
                        habitImg.add(R.drawable.drinking);
                    }
                    if(smoke.isChecked()){
                        habitImg.add(R.drawable.ciga);
                    }
                    if(exercise.isChecked()==false){
                        habitImg.add(R.drawable.running);
                    }
                    // 갤러리뷰 어뎁터
                    MyGalleryAdapter madapter =  new MyGalleryAdapter(getApplicationContext());
                    // 어뎁터 꽂기.
                    gallery.setAdapter(madapter);

                    // 성별 저장.체크 된 라디오 버튼에 따라서 여성과 남성을 저장.
                    if(rGp1.getCheckedRadioButtonId()==R.id.female){
                        gender = female.getText().toString();
                    }
                    if(rGp1.getCheckedRadioButtonId()==R.id.male){
                        gender = male.getText().toString();
                    }
                    // 키와 몸무게 입력 값 받기. 신체질량지수를 구하기 위해서. 공식(체증/((키/100)^2))
                    String h = height.getText().toString(); // 키 값
                    String w = weight.getText().toString(); // 체중 값
                    // 값들 모두 더블형으로
                    Double h1 = Double.valueOf(h)/100;
                    Double w1 = Double.valueOf(w);
                    // 신체질량지수 공식을 적용.
                    double result = (w1 / Math.pow(h1, 2));
                    // 소수점 표현을 위해서
                    DecimalFormat form = new DecimalFormat("#.#");
                    // 텍스트 영역에 출력
                    text1.setText("1. " +BloodT+"형 "+gender+ " 입니다!");
                    text2.setText("2. 신체 질량 지수는 " +form.format(result)  + " 입니다!");
                    // 갤러리 표시
                    gallery.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // 갤러리를 위해서 BaseAdapter를 상속받는 클래스 생성
    public class MyGalleryAdapter extends BaseAdapter{
        Context context;

        // 생성자 만들기
        public MyGalleryAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            // 갤러리에 나타낼 이미지 배열의 개수 만큼.
            return habitImg.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        // 뷰에 나타날 이미지 나타내기
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 이미지뷰 객체 생성.
            ImageView imageView = new ImageView(context);
            // 이미지소스는 배열에 넣은 정보를 얻어서, 이미지뷰에서 실행되도록.
            imageView.setImageResource(habitImg.get(position));
            // 이미지 뷰 객체 반환
            return imageView;
        }
    }
}