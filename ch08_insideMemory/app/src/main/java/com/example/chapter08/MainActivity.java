package com.example.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnWrite = (Button) findViewById(R.id.btnWrite);
        Button btnRead = (Button) findViewById(R.id.btnRead);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        FileOutputStream outFs = openFileOutput("file.txt", Context.MODE_PRIVATE);
                        String str = "쿡북안드로이드";
                        try {
                            outFs.write(str.getBytes());
                            outFs.close();
                            Toast.makeText(MainActivity.this,"file.txt 가 생성됨.",Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream inFs = openFileInput("file.txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);
                    Toast.makeText(getApplicationContext(),str,0).show();
                    inFs.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"파일 없음",0).show();
                }
            }
        });
    }
}