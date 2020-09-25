package com.example.chapter10_20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("암시적 인텐트");

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        // 버튼 바인딩
        Button btnCall = (Button) findViewById(R.id.btnCall);
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnMap =  (Button) findViewById(R.id.btnMap);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        Button btnMsg = (Button) findViewById(R.id.btnMsg);
        Button btnPhoto = (Button) findViewById(R.id.btnPhoto);

        //버튼 기능 이벤트
        // 전화걸기
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:01012345678");
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });
        // 웹페이지 열기
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.naver.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        // 구글 맵 열기
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.co.kr/maps/@35.1558434,129.0596516,18.5z?hl=ko");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        // 구글 검색창 열기
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"안드로이드");
                startActivity(intent);
            }
        });
        // 문자메시지 보내기
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body","안녕하세요?");
                intent.setData(Uri.parse("smsto:"+Uri.encode("010-1234-5678")));
                startActivity(intent);
            }
        });
        // 사진 보기
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath()+"/Download"));
                intent.setDataAndType(uri,"image/png");
                startActivity(intent);
            }
        });


    }
}