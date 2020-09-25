package com.example.chapter11_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 동적인 리스트뷰 만들기
        // 먼제 arryalist 객체 만들기
        final ArrayList<String> items = new ArrayList<>();
  /*      // 리스트뷰 만들기
        // 먼저 내용을 배열화
        final String items[] = {
                "히어로즈","24시","로스트","로스트롬","스몰빌","탐정몽크",
                "빅뱅이론","프렌즈","덱스터","글리","가쉽걸","테이큰","슈퍼내추럴","브이"
        };

   */
        // 리스트뷰 바인딩
        ListView list = (ListView) findViewById(R.id.listView1);
        // 배열 어뎁터 객체 생성
        // 단순한 어뎁터(위치,디자인,데이터), 디자인은 한줄의 디자인.
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,items);
        // 리스트뷰에서 다중 선택(체크박스)구현
 /*       ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,items);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);*/

        // 생성된 어뎁터 객체를 리스트뷰에 꽂기.
        list.setAdapter(adapter);

        // editText 바인딩
        final EditText edtItem = (EditText) findViewById(R.id.edtItem);
        // 버튼 바인딩
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        // 버튼 기능 이벤트
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 배열에 추가하는 기능이기떄문에, 그리고 editText 내용이 추가가 되어야 한다.
                items.add(edtItem.getText().toString());
                // 어뎁터 객체에서 목록이 변화가 있으면, 그것을 적용하는 기능의 메소드를 실행.
                adapter.notifyDataSetChanged();
            }
        });
        // 클릭을 길게 하면, 해당 항목이 삭제 됨.
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        // 리스트 내의 목록 들을 클릭할때 이벤트
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),items.get(position),Toast.LENGTH_SHORT).show();
            }
        });





    }
}