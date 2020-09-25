package com.example.practice11_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("스피너 예제");

        String[] titleID={
                "써니","완득이","괴물","라디오스타","비열한 거리","왕의 남자",
                "아일랜드","웰컴투 동막골","헬보이","Back To the Future"
        };

        final Integer[] posterID ={
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,
                R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,
                R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,
                R.drawable.mov10
        };

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,titleID);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImageView image = (ImageView) findViewById(R.id.image);
                image.setImageResource(posterID[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}