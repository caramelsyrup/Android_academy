package com.example.practice06_19;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

    // Fragment를 상속받는 MyTabFragment 클래스를 만든다.(내용)
    // View 보다 Activity급으로 기능을 가짐.
   public class MyTabFragment extends Fragment {
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
    }
