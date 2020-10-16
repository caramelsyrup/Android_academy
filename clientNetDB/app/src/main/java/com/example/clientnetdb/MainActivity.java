package com.example.clientnetdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.clientnetdb.Network.NetworkGet;
import com.example.clientnetdb.Network.NetworkInsert;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 메인화면 버튼 변수
    private Button btnRefresh,btnAdd;
    // 메인화면 목록 변수
    private ListView listView1;
    // 목록변수에 꽂을 어뎁터 변수. 따로 클래스를 만들어서 변수를 만듦.
    private Custom_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 목록을 위한 리스트뷰 바인딩
        listView1 = (ListView) findViewById(R.id.listView1);
        // Custom_Adapter형의 변수에 객체 저장. 생성자는 현재 엑티비티,목록화면,목록내용인 배열을 매개변수로 가짐.
        adapter = new Custom_Adapter(MainActivity.this,R.layout.adapter_userinfo,new ArrayList<UserInfo>());
        // 리스트뷰에 어뎁터를 꽂음.
        listView1.setAdapter(adapter);

        // 새로고침 버튼
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 객체 생성.
                // NetworkGet생성자에 매개변수가 들어감. execute는 객체의 doInBackground 함수 실행. 매개변수 값은 없음.
                new NetworkGet((Custom_Adapter) listView1.getAdapter()).execute("");
            }
        });

        // 추가하기 버튼
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 뷰 참조변수에 뷰 생성을 위해서 getLayoutInflater에서 inflate메서드 실행. view를 만들고 싶은 레이아웃 파일을 매개변수.
                final View v = getLayoutInflater().inflate(R.layout.dialog_add,null);
                // 대화상자 창을 띄우기 위해서.
                new AlertDialog.Builder(MainActivity.this)
                        // 제목
                        .setTitle("멤버추가")
                        // 대화 상자에 쓸 뷰를 적용.
                        .setView(v)
                        // 대화 상자에 긍정 답변 버튼
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // dialog_add.xml 파일에 만들어 놓은 요소들을 변수에 저장.
                                String id = ((EditText)v.findViewById(R.id.edtID)).getText().toString();
                                String name = ((EditText)v.findViewById(R.id.edtName)).getText().toString();
                                String phone = ((EditText)v.findViewById(R.id.edtPhone)).getText().toString();
                                String grade = ((EditText)v.findViewById(R.id.edtGrade)).getText().toString();
                                // NetworkInsert클래스의 생성자를 실행하고, execute를 통해서 doInBackground 실행.
                                new NetworkInsert(adapter).execute(id,name,phone,grade);
                            }
                        })
                        // 대화 상자에서 부정 답변 버튼
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });
        // 전체불러오기
        new NetworkGet((Custom_Adapter) listView1.getAdapter()).execute("");
    }
}