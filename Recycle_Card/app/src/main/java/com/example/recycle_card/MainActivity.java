package com.example.recycle_card;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 목록 아이템들을 담는 제네릭 어레이리스트 참조변수 선언.
    ArrayList<ExampleItem> examList;
    // 리사이클뷰 변수
    private RecyclerView mRecyclerView;
    // 리사이클뷰 어뎁터 변수
    private ExampleAdapter mAdapter;
    // 리사이클뷰 레이아웃매니저 변수
    private RecyclerView.LayoutManager mlayoutManager;
    // 버튼과 에딧 텍스트 변수
    private EditText eInsert;
    private Button btn_insert;
    private EditText eDelete;
    private Button btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 목록 만드는 함수
        createExampleList();
        // 리사이클뷰를 화면에 장착하여 뿌리기 함수
        buildRecyclerView();
        // 버튼이 많아서 버튼 모으기
        setbuttons();

    }   // onCreate

    public void insertItem(int position){
        examList.add(position,new ExampleItem(R.drawable.ic_android,"New Item At"+position,"Line2"));
        mAdapter.notifyItemInserted(position);
    }
    public void deleteItem(int position){
        examList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
    public void changeItem(int position, String text){
        examList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }

    public void createExampleList(){
        examList = new ArrayList<>();
        // 어레이리스트 변수에 추가. ExampleItem의 생성자를
        examList.add(new ExampleItem(R.drawable.ic_android,"Line 1","Line 2"));
        examList.add(new ExampleItem(R.drawable.ic_moon,"Line 3","Line 4"));
        examList.add(new ExampleItem(R.drawable.ic_cloud,"Line 5","Line 6"));
    }

    public void buildRecyclerView(){
        // 화면xml파일에서 리사이클뷰영역을 찾아서 저장.
        mRecyclerView = findViewById(R.id.recycleView);
        // 고정된 사이즈를 설정
        mRecyclerView.setHasFixedSize(true);
        mlayoutManager = new LinearLayoutManager(this);
        // 어뎁터에 어레이리스트 참조변수를 생성자의 매개변수로 하여 반환 값 저장.
        mAdapter = new ExampleAdapter(examList);

        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position,"Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                deleteItem(position);
            }
        });
    }
    // 버튼 기능 함수모음
    public void setbuttons(){
        // 버튼과 에딧 텍스트 바인딩
        eInsert = findViewById(R.id.eInsert);
        eDelete = findViewById(R.id.eDelete);
        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        // 추가버튼
        // 이것은 이미 생성된 목록 사이에 추가하는 것.
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(eInsert.getText().toString());
                insertItem(position);
            }
        });
        // 삭제버튼
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(eDelete.getText().toString());
                deleteItem(position);
            }
        });
    }
}