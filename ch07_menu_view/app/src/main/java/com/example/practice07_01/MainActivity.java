package com.example.practice07_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText num;
    ImageView image;
    String Num;
    float f2 =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("제주도 사진 보기");

        // 바인딩
        num = (EditText) findViewById(R.id.num);
        image = (ImageView) findViewById(R.id.image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.rotate:
                // EditText에 적힌 텍스트를 받기.
                Num = num.getText().toString();
                // Num을 Float형으로
                Float Num1 = Float.parseFloat(Num);
                // 회전버튼을 계속 누르면 각도를 계속 누적
                f2 = Num1+f2;
                // image를 회전시키기
                image.setRotation(f2);
                return true;

            case R.id.item1:
                image.setImageResource(R.drawable.jeju2);
                item.setChecked(true);
                return true;

            case R.id.item2:
                image.setImageResource(R.drawable.jeju8);
                item.setChecked(true);
                return true;

            case R.id.item3:
                image.setImageResource(R.drawable.jeju6);
                item.setChecked(true);
                return true;
        }
        return false;
    }
}