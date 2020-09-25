package com.example.practice08_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker datepick1;
    EditText diary1;
    Button btnW;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 바인딩
        datepick1 = (DatePicker) findViewById(R.id.datepick1);
        diary1 = (EditText) findViewById(R.id.diary1);
        btnW = (Button) findViewById(R.id.btnW);
        // 바인딩 8-8.8-9
        Button btnRead = (Button) findViewById(R.id.btnRead);
        final EditText edtRaw = (EditText) findViewById(R.id.edtRaw);

        // 캘린더 클래스를 이용하여, 변경된 날짜를 파일 이름으로 넣기
        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);
        // 데이트 피커 초기화, 파라미터는 연,월,일과 리스너(날짜가 변경되면 동작)로 설정
        datepick1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 현재 날짜에 해당하는 파일이름을 만든다.
                fileName = Integer.toString(year)+"_"+Integer.toString(monthOfYear+1)+"_"+Integer.toString(dayOfMonth)+".txt";
                // 현재 날짜의 일기 내용을 반환, 현재 날짜의 일기가 없다면 null을 반환.
                String str = readDiary(fileName);
                // 읽어온 일기내용을 받아서 출력.
                diary1.setText(str);
                btnW.setEnabled(true);
            }
        });
        // 쓰기 버튼
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // 읽기 파일을 쓰기 모드로 연다.
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    // EditText내용을 str변수에 저장.
                    String str = diary1.getText().toString();
                    // str변수를 byte[]형으로 쓰고
                    outFs.write(str.getBytes());
                    // 파일을 닫는다.
                    outFs.close();
                    // 토스트 메시지를 출력한다. 파일명을
                    Toast.makeText(getApplicationContext(),fileName+"이 저장됨",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // 8-8.8-9 예제, 버튼
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // raw폴더의 파일에 접글하기 위해서, Context 클래스의 정적 메소드인 getResources. 현재 패키지의 리소스 반환.
                    // openRawResource는 /res/raw리소스를 읽기용으로 연다. 반환은 InputStream형
                    InputStream inputS = getResources().openRawResource(R.raw.android);
                    // 입력스트림에서 읽을 수 있는 바이트 수를 반환해여 txt 저장.
                    byte[] txt = new byte[inputS.available()];
                    // 입력스트림에서 데이터를 읽어서 txt변수에 저장.
                    inputS.read(txt);
                    // txt 변수를 문자열로 변환하여 에디트텍스트에 출력.
                    edtRaw.setText(new String(txt));

                } catch (IOException e) {   // 파일이 없을 경우
                    e.printStackTrace();
                }

            }
        });
    }
    // 현재 날짜 파일을 읽어 일기내용을 반환하는 메소드.
    String readDiary(String fName){ // 파일이름을 파라미터로 받는다.
        // 읽어온 일기를 저장할 문자열 변수
        String diaryStr = null;
        // 입력 파일 스트림 변수 선언
        FileInputStream inFs;
        try {
            // 파일을 읽어서, 입력 파일 스트림에 저장.
            inFs = openFileInput(fName);
            // 바이트형 변수 선언
            byte[] txt = new byte[500];
            // 파일 내용을 txt에 읽는다
            inFs.read(txt);
            // 파일을 닫는다.
            inFs.close();
            // 읽어온 txt를 문자열로 변환하고 이것을 공백제거하고, 변수에 저장.
            diaryStr = (new String(txt)).trim();
            // 버튼 글자를 바꾼다.
            btnW.setText("수정하기");
            // 읽을 파일이 없다면 실행
        } catch (IOException e) {
            e.printStackTrace();
            // 파일이 없으므로, 내용에 표시
            diary1.setHint("일기 없음");
            // 버튼 변경
            btnW.setText("새로 저장");
        }
        // 파일이 있다면, 일기내용이 반환. 없다면, null 반환.
        return diaryStr;
    }
}