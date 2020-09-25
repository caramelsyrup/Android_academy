package com.example.practice11_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화포스터보기");
        // 그리드뷰 바인딩
        GridView gridView1 = (GridView) findViewById(R.id.gridView1);
        // gAdapter객체를 만들지만, MyGridAdapter클래스를 먼저 만들고 나서 해야한다.
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        // 그리드뷰에 어뎁터 세팅
        gridView1.setAdapter(gAdapter);

    }
    // 그리드뷰에 세팅할 어뎁터를 위해서 BaseAdapter를 상속받는 클래스를 만듦.
    public class MyGridAdapter extends BaseAdapter{
        // 그림 파일 ID를 배열로 지정
        Integer[] posterID={
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,
                R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,
                R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,
                R.drawable.mov10,R.drawable.mov11,R.drawable.mov12,
                R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,
                R.drawable.mov16,R.drawable.mov17,R.drawable.mov18,
                R.drawable.mov19,R.drawable.mov20,R.drawable.mov21,
                R.drawable.mov22,R.drawable.mov23,R.drawable.mov24,
                R.drawable.mov25,R.drawable.mov26,R.drawable.mov27
        };
        // 포스터의 제목도 배열로 지정
        String[] titleID={
                "써니","완득이","괴물","라디오스타","비열한 거리","왕의 남자",
                "아일랜드","웰컴투 동막골","헬보이","Back To the Future","여인의 향기","쥬라기 공원",
                "포레스트검프","GroundhogDay","혹성탈출","아름다운 비행","내 이름은 칸","해리포터",
                "마더","킹콩을 들다","쿵푸팬더2","짱구는 못말려","아저씨","아바타",
                "대부","국가대표","토이스토리3"
        };

        Context context;
        // 위의 문장과 동일
        // Activity act;
        // 생성자에서
        // public MyGridAdapter( Activity act){
        //      this.act = act;
        // }
        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // 이미지 뷰 객체를 생성. Activity가 필요한데,
            ImageView imageView = new ImageView(context);
            //  이미지 뷰에서 레이아웃 크기 설정.
            imageView.setLayoutParams(new LinearLayout.LayoutParams(100,150));
            // 이미지 뷰에서 해당 컨텐츠의 위치가 중앙에 맞게
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            // 이미지 뷰에서 패딩 설정.
            imageView.setPadding(5,5,5,5);
            // 이미지 파일가져오기. 배열순서나 이미지뷰의 순서나 같음.
            imageView.setImageResource(posterID[position]);
            // 클릭하면 대화상자가 나오고 영화 포스터의 원래 크기가 보여지도록.
            // 순서정보를 pos에 저장.
            final int pos = position;
            // 이미지뷰를 클릭했을때 이벤트
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 뷰 객체를 만들어서 dialog.xml파일을 인플레이트 함. 대화 상자에 띄울
                    View dialogView = View.inflate(MainActivity.this,R.layout.dialog,null);
                    // MainActivity현재 위치를 매개변수로 Builder객체를 새로이 하나 만듬.
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    // 이미지뷰 객체를 만드는데, 대화상자에서 바인딩.
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    // 이미지뷰에 그림파일 세팅.
                    ivPoster.setImageResource(posterID[pos]);
                    // 대화상자에 제목 설정.
                    dlg.setTitle(titleID[pos]);
                    // 대화상자에 아이콘 설정.
                    dlg.setIcon(R.drawable.movie_icon);
                    // 대화상자에 VIew 설정
                    dlg.setView(dialogView);
                    // 대화 상자에 닫기 버튼
                    dlg.setNegativeButton("닫기",null);
                    // 대화 상자 보이기
                    dlg.show();
                }
            });
            // 설정된 이미지뷰를 반환
            return imageView;
        }



    }
}