package com.example.practice06_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.main);

        TabHost tabhost = getTabHost();

        TabHost.TabSpec tabSpecCat = tabhost.newTabSpec("cat").setIndicator("고양이");
        tabSpecCat.setContent(R.id.cat);
        tabhost.addTab(tabSpecCat);

        TabHost.TabSpec tabSpecDog = tabhost.newTabSpec("dog").setIndicator("강아지");
        tabSpecDog.setContent(R.id.dog);
        tabhost.addTab(tabSpecDog);

        TabHost.TabSpec tabSpecrabbit = tabhost.newTabSpec("rabbit").setIndicator("토끼");
        tabSpecrabbit.setContent(R.id.rabbit);
        tabhost.addTab(tabSpecrabbit);

        TabHost.TabSpec tabSpechorse = tabhost.newTabSpec("horse").setIndicator("말");
        tabSpechorse.setContent(R.id.horse);
        tabhost.addTab(tabSpechorse);

        tabhost.setCurrentTab(0);

    }
}