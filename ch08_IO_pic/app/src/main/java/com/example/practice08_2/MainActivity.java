package com.example.practice08_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    int curNum;
    File[] imageFiles;
    String imageFname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        // 요소들 바인딩
        Button btnPrev = (Button) findViewById(R.id.btnPrev);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        final myPictureView myPictureView1 = (myPictureView) findViewById(R.id.myPictureView1);

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        imageFname = imageFiles[0].toString();
        myPictureView1.imagePath = imageFname;

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum<=0){
                    //curNum = imageFiles.length-1;
                    Toast.makeText(getApplicationContext(),"첫번째 그립입니다.",Toast.LENGTH_SHORT).show();
                }else{
                    curNum --;
                    imageFname = imageFiles[curNum].toString();
                    myPictureView1.imagePath=imageFname;
                    myPictureView1.invalidate();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum>=imageFiles.length-1){
                    //curNum=0;
                    Toast.makeText(getApplicationContext(),"마지막 그림입니다.",Toast.LENGTH_SHORT).show();
                }else{
                    curNum ++;
                    imageFname = imageFiles[curNum].toString();
                    myPictureView1.imagePath=imageFname;
                    myPictureView1.invalidate();
                }
            }
        });
    }
}