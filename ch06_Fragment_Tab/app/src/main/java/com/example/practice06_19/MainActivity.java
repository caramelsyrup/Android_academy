package com.example.practice06_19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {
    // 액션바 탭형으로 변수 선언
    ActionBar.Tab tabSong;
    ActionBar.Tab tabArtist;
    ActionBar.Tab tabAlbum;

    // MyTabFragment클래스형 배열 선언. Fragment를 지속적으로 중첩시키지 않고, 재사용을 위해서.
    MyTabFragment myFrags[] = new MyTabFragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // 상단에 만들 액션바 준비(메뉴)
        ActionBar bar = getSupportActionBar();
        // 탭호스트와 비슷하게 탭의 모양을 가지도록 설정(바를 만듦)
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // 탭을 액션바에 생성
        tabSong = bar.newTab();
        // 탭의 글자 설정
        tabSong.setText("음악별");
        // 탭 터치하면 작동하는 리스너. 이미 자기 자신이 리스너를 implements 했기때문에
        tabSong.setTabListener(this);
        // 탭을 액션바에 추가
        bar.addTab(tabSong);

        tabArtist = bar.newTab();
        // 탭의 글자 설정
        tabArtist.setText("가수별");
        // 탭 터치하면 작동하는 리스너
        tabArtist.setTabListener(this);
        // 탭을 액션바에 추가
        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        // 탭의 글자 설정
        tabAlbum.setText("앨범별");
        // 탭 터치하면 작동하는 리스너
        tabAlbum.setTabListener(this);
        // 탭을 액션바에 추가
        bar.addTab(tabAlbum);
    }

    // 탭을 선택하면 작동하는 메소드
    @Override
    public void onTabSelected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {
        // myTabFrag 선언, 초기화. 계속 새로운 객체를 생성하면 안된다. 없을때만 생성.
        MyTabFragment myTabFrag = null;
        // 선택된 탭에서 객체가 없다면, 새로 객체를 생성한다.
        if(myFrags[tab.getPosition()]==null){   // getPosition으로 배열내의 주소를 읽음.
            // 새로운 객체를 생성.
            myTabFrag = new MyTabFragment();
            // Bundle 객체도 새로이 생성. 해당 기기의 정보를 얻은 뒤에, Fragment에서 처리뒤 View가 보여짐
            Bundle data = new Bundle();
            // Bundle의 객체인 data에 문자열 정보를 넣는다.(키값,벨류값)
            data.putString("tabName",tab.getText().toString());
            // 번들 정보를 클래스에 넣는다.
            myTabFrag.setArguments(data);
            // 배열에도 추가한다.
            myFrags[tab.getPosition()] = myTabFrag;

        }else{
            // 이미 myFrags 배열에 존재 할때, 주소값을 얻어서 저장
            myTabFrag = myFrags[tab.getPosition()];
        }
        // Fragment를 교체
        ft.replace(android.R.id.content, myTabFrag);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, androidx.fragment.app.FragmentTransaction ft) {

    }


    /*
    // Fragment를 상속받는 MyTabFragment 클래스를 만든다.(내용)
    // View 보다 Activity급으로 기능을 가짐.
    public static class MyTabFragment extends Fragment{
        //  Bundle을 통해서 받은 값을 넣을 변수 선언. 클래스 전역변수로 설정.
        // 이후에 View에서도 사용함.
        String tabName;

        // Fragment 생성 메소드
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // 안드로이드 운영체제(OS)와 통신하기 위해 연결. 기기의 해상도,OS 등등의 정보.
            Bundle data = getArguments();
            // 위젯에 있는 tabName을 가져와서 Fragment에 있는 tabName에 저장(연결).
            tabName = data.getString("tabName");
        }

        // Fragment안에 들어가는 화면(View) 메소드. XML과 같은 역할
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            // 전체 레이아웃부터 먼저 설정
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    // 높이,넓이
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            // 생성자 매개변수(위치->액티비티). View를 만듦.
            LinearLayout baseLayout = new LinearLayout(super.getActivity());//MainActivity.this와 동일.
            // 순서설정
            baseLayout.setOrientation(LinearLayout.VERTICAL);
            // 높이,넓이등 설정해놓은 레이아웃을 꽂음.
            baseLayout.setLayoutParams(params);

            // 각 Fragment의 내용이 탭에 따라 변화 하도록
            if(tabName.equals("음악별")) baseLayout.setBackgroundColor(Color.RED);
            if(tabName.equals("가수별")) baseLayout.setBackgroundColor(Color.GREEN);
            if(tabName.equals("앨범별")) baseLayout.setBackgroundColor(Color.BLUE);

            return baseLayout;
        }
    }*/



}