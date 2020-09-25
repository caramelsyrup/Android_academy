package com.example.chapter6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.ViewFlipper;

@SuppressWarnings("deprecation")
public class MainActivity extends
//        AppCompatActivity {
        TabActivity {
        // 06_15
/*    Button btnPrev,btnNext;
    ViewFlipper viewFlipper;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* setContentView(R.layout.activity_main);
        setContentView(R.layout.ex06_01);
        setContentView(R.layout.ex06_02);
        setContentView(R.layout.ex06_03);
        setContentView(R.layout.ex06_10);
        setContentView(R.layout.ex06_12);
        setContentView(R.layout.ex06_13);
        setContentView(R.layout.ex06_14);
        setContentView(R.layout.ex06_15);*/
        setContentView(R.layout.ex06_17);

        // ex06_10
       /* // 배열을 만듦.
        String items[] = {
                "CSI-뉴욕","CSI-라스베가스","CSI-마이애미","Friends","Fringe","Lost"
        };
        // 바인딩. 다중선택이 안되는 기능.
        AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        // AutoCompleteTextView는 따로 어댑터가 필요하다.
        // 배열화하고, 아이템이 String형 이기에, 제네릭도 String으로 하여 배열 객체를 생성. 생성자 배열객체.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_dropdown_item_1line,items);
        // 어뎁터 세팅
        auto.setAdapter(adapter);
        // MultiAutoCompleteTextView 바인딩, 다중 선택 가능.
        MultiAutoCompleteTextView multi = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
        // 토큰나이저를 이용하여, 쉼표마다 문자열을 끊어서 데이터 저장. token 객체를 하나 만든다.
        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
        // 바인딩한 것에 토크나이저를 세팅. 어뎁터에 있는 정보를 선택할때마다 콤마가 자동 생성. 그리고 그 콤마가 기준.
        multi.setTokenizer(token);
        // 바인딩 한 것에 어댑터 장착
        multi.setAdapter(adapter);*/

        // ex06_15
/*     // 바인딩
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        // 이전화면
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //뷰플리퍼이용
                viewFlipper.showPrevious();
            }
        });
        // 다음화면
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });
*/
        // ex06_17
        // tabHost를 저장.
        TabHost tabhost = getTabHost();
        // tabSpecSong에 새로운 탭스펙을 저장.
        TabHost.TabSpec tabSpecSong = tabhost.newTabSpec("SONG").setIndicator("음악별");
        // id 가 tabsong인 것을 세팅
        tabSpecSong.setContent(R.id.tabsong);
        TabHost.TabSpec tabSpecArtist = tabhost.newTabSpec("ARTIST").setIndicator("가수별");
        tabSpecArtist.setContent(R.id.tabArtist);
        TabHost.TabSpec tabSpecAlbum = tabhost.newTabSpec("ALBUM").setIndicator("앨범별");
        tabSpecAlbum.setContent(R.id.tabAlbum);

        tabhost.addTab(tabSpecSong);
        tabhost.addTab(tabSpecArtist);
        tabhost.addTab(tabSpecAlbum);

        tabhost.setCurrentTab(0);

    }

}