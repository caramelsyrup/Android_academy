package com.example.chapter08_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 버튼 선언
        Button btnSDread;
        // 에딧텍스트 선언
        final EditText edtSD;
        // 바인딩
        btnSDread = (Button) findViewById(R.id.btnSDread);
        edtSD = (EditText) findViewById(R.id.edtSD);
        // 이 앱에게 파일 액세스 작업을 허용할지 묻는 창이 뜬다. 허용 해야함.
        ActivityCompat.requestPermissions(this,new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        // sd카드에 폴더 생성 제거 버튼 바인딩
        Button btnMkdir = (Button) findViewById(R.id.btnMkdir);
        Button btnRmdir = (Button) findViewById(R.id.btnRmdir);
        // 시스템폴더에서 폴더경로,파일목록 주는 버튼 바인딩
        Button btnFilelist = (Button) findViewById(R.id.btnFilelist);
        // EditText 바인딩
        final EditText edtFilelist = (EditText) findViewById(R.id.edtFilelist);
        // SD카드의 절대경로 저장. Environment.getRootDirectory(),Environment.getDataDirectory() 등의 메소드도 이용가능.
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        // mydir 폴더를 생성하기 위해서 File형 변수 설정.
        final File myDir = new File(strSDpath+"/mydir");
        // 폴더 생성 버튼
        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 폴더 생성
                myDir.mkdir();
            }
        });
        // 폴더 제거 버튼
        btnRmdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 폴더 제거
                myDir.delete();
            }
        });
        // sd 카드 파일 읽기
        btnSDread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // 파일 입력 스트림을 객체 생성할 때, SD카드의 절대 경로를 생성자에 지정한다.
                    FileInputStream inFS = new FileInputStream("/storage/emulated/0/android.txt");
                    // 스트림으로 들어 온것을 바이트 배열로 만듦.반환
                    byte[] txt = new byte[inFS.available()]; // 빈 용기
                    // 읽어서 용기를 채움.
                    inFS.read(txt);
                    // 다 채운 용기(txt)를 세팅.
                    edtSD.setText(new String(txt));
                    // 닫기
                    inFS.close();
                } catch (IOException e) {   // 파일이 없을 경우.
                    e.printStackTrace();
                }
            }
        });
        // 시스템 폴더의 폴더,파일 목록 띄우는 버튼
        btnFilelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 안드로이드 시스템 폴더 경로를 반환.
                String sysDir = Environment.getRootDirectory().getAbsolutePath();
                // sysFiles배열에 시스템 폴더의 폴더/파일 목록을 구하여 저장.
                File[] sysFiles = new File(sysDir).listFiles();
                // 경로 복사를 위해서 문자열 변수 선언.
                String strFname;
                // 시스템 폴더의 폴더/파일 개수만큼 반복
                for(int i=0; i<sysFiles.length;i++){
                    // 현재 파일이 폴더인가 아닌가를 구분.
                    if(sysFiles[i].isDirectory()== true){
                        // 폴더면 폴더를 붙인다.
                        strFname = "<폴더>"+sysFiles[i].toString();
                    }else{
                        // 파일이면 파일을 붙인다.
                        strFname = "<파일>"+sysFiles[i].toString();
                    }
                    // 에딧텍스트영역에 출력.
                    edtFilelist.setText(edtFilelist.getText()+"\n"+strFname);
                }
            }
        });

    }
}