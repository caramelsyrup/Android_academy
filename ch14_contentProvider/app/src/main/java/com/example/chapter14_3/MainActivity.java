package com.example.chapter14_3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 접근 허용.
        ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_CALL_LOG},MODE_PRIVATE);
        // 요소들 바인딩
        Button btnCall = (Button) findViewById(R.id.btnCall);
        final EditText edtCall = (EditText) findViewById(R.id.edtCall);
        // 버튼 클릭 이벤트
        btnCall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // 메소드 반환값을 에딧텍스트에 작성
                edtCall.setText(getCallhisotry());
            }
        });
    }
    // getCallhisotry메소드 정의
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCallhisotry(){
        // callset 문자열 배열을 만들어서 저장.
        String[] callset = new String[]{
                CallLog.Calls.DATE, // 날짜
                CallLog.Calls.TYPE, // 수신,발신,미스 등
                CallLog.Calls.NUMBER,   // 번호
                CallLog.Calls.DURATION  // 통화시간
        };
        // 읽기전 위치 설정.
        // 컨텐트프로바이더를 따로 만들필요 없음. 만들어진 메소드를 사용.
        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI,callset,null,null);
        if(c==null)
            return "통화기록 없음";
        // 통화기록의 문자열을 저장할 StringBuffer 변수
        StringBuffer callBuff = new StringBuffer();
        callBuff.append("\n날짜 : 구분 : 전화번호 : 통화시간\n\n");
        c.moveToFirst();

        do{
            long callDate = c.getLong(0);
            SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = datePattern.format(new Date(callDate));
            callBuff.append(date_str + ":");
            if(c.getInt(1)== CallLog.Calls.INCOMING_TYPE)
                callBuff.append("착신 :");
            else
                callBuff.append("발신 :");
            callBuff.append(c.getString(2)+":");
            callBuff.append(c.getString(3)+"초\n");

        }while (c.moveToNext());    // 위치를

        c.close();
        return callBuff.toString();
    }
}