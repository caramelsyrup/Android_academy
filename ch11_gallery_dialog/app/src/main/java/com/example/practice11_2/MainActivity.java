package com.example.practice11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 토스트를 위해서
    View toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("갤러리 예제");

        // gallery 바인딩
        Gallery gallery1 = (Gallery) findViewById(R.id.gallery1);
        // MyGalleryAdapter 의 객체 생성.
        MyGalleryAdapter mAdapter = new MyGalleryAdapter(this);
        // gallery바인딩 된것에 어뎁터 꽂기
        gallery1.setAdapter(mAdapter);
    }

    // BaseAdapter를 상속받는 MyGalleryAdapter클래스를 생성.
    public class MyGalleryAdapter extends BaseAdapter{
        // 이미지 파일 ID를 배열화
        Integer[] posterID ={
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,
                R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,
                R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,
                R.drawable.mov10
        };
        // 제목을 배열화
        String[] titleID={
                "써니","완득이","괴물","라디오스타","비열한 거리","왕의 남자",
                "아일랜드","웰컴투 동막골","헬보이","Back To the Future"
        };

        Context context;

        public MyGalleryAdapter(Context c) {
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
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(100,150));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);
            imageView.setImageResource(posterID[position]);

            final int pos = position;

            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[pos]);

                    // 제목 str에 저장.
                    final String str = titleID[pos];

                    // 출력되는 이미지를 클릭하면, 토스트 메시지 띄우기
                    ivPoster.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 만들어놓은 뷰 변수에 인플레이트 토스트를만듷어 놓은 xmnl파일 저장.
                            toastView = (View) View.inflate(MainActivity.this,R.layout.toast1,null);
                            // 토스트.xml파일에 있는 텍스트부분 바인딩
                            TextView text1 = (TextView) toastView.findViewById(R.id.text1);
                            text1.setText(str);

                            // 토스트 객체 생성
                            Toast toast = new Toast(MainActivity.this);
                            // 토스트 객체에 데이터들이 적용되어 저장된 뷰객체로 세팅
                            toast.setView(toastView);
                            //토스트 보이기
                            toast.show();
                        }
                    });

                    return false;
                }
            });

            return imageView;
       }
    }
}