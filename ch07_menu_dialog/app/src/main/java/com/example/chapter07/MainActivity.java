package com.example.chapter07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button button1,button2,button3,button4,button5;
    final boolean[] checkArray = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("배경색 바꾸기");
        // 바인딩
        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        button1 = (Button) findViewById(R.id.button1);
        // 버튼4 등록
        button4 = (Button) findViewById(R.id.button4);
        // 버튼 5 등록
        button5 = findViewById(R.id.button5);
        // 컨텍스트 메뉴 : 버튼 - 뷰의 하나 - 메뉴사용을 위해 등록
        // 버튼2 등록
        button2 = (Button)findViewById(R.id.button2);
        registerForContextMenu(button2);
        // 버튼 3 등록
        button3 = (Button)findViewById(R.id.button3);
        registerForContextMenu(button3);

        // 원상복구 버튼
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setScaleX(1);
                button1.setRotation(0);
                button2.setScaleX(1);
                button2.setRotationX(0);
                button3.setScaleX(1);
                button3.setRotationY(0);
                baseLayout.setBackgroundColor(Color.rgb(255,255,255));
            }
        });


        // 토스트 띄우기 버튼
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tMsg에 저장.
               Toast tMsg = Toast.makeText(MainActivity.this,"토스트 연습",Toast.LENGTH_SHORT);
               // 토스트 메시지를 원하는 영역에다가 출력
                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                // 랜덤으로 너비 설정
                int xOffset = (int) (Math.random()*display.getWidth());
                // 랜덤으로 높이 설정
                int yOffset = (int) (Math.random()*display.getHeight());
                // 설정된 너비, 높이를 토스트에 적용
                tMsg.setGravity(Gravity.TOP,xOffset,yOffset);
                // 메시지 출력
                tMsg.show();
            }
        });

        // 대화 상자 띄우기 버튼
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 배열 만들기(목록을 위한)
                final String[] versionArray = new String[] {"오레오","파이","큐(10)"};

                // 다중 선택 가능한 목록
                // 대화 상자를 빌드하기 위하여 dlg에 객체 생성.
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                // 객체를 이용하여, 제목설정
                dlg.setTitle("좋아하는 버전은.");
                // 내용 설정
               // dlg.setMessage("내용 텍스트 입니다.");
                // 아이콘 그림 설정
                dlg.setIcon(R.mipmap.ic_launcher);
                // 목록 보이게 설정
                // 단순 목록 형태로 출력 되고, 클릭시 자동으로 닫힘.
          /*      dlg.setItems(versionArray, new DialogInterface.OnClickListener() {
                    // 목록 중 항목을 클릭하면 이벤트
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 버튼의 내용을 해당 항목으로 바꿈.
                        button5.setText(versionArray[which]);
                    }
                });*/
                // 라디오 버튼형태로 목록 출력. 클릭되어도 자동 닫힘 안됨.
 /*               dlg.setSingleChoiceItems(versionArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button5.setText(versionArray[which]);
                    }
                });*/
                // 여러개를 동시에 선택할 수 있는 체크 박스 설정.
                    dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            // 체크여부 판단
                                if(isChecked==true){
                                    checkArray[which]=true;
                                }
                                if(isChecked==false){
                                    checkArray[which]=false;
                                }
                                // 버튼에 글자를 쓰기위해서 문자열 변수
                                String str="";
                                // 체크되는 개수를 저장하기 위해서
                                int cnt=0;
                                // for문으로 반복으로 돌린다.
                                for(int i=0; i<checkArray.length;i++){
                                    // 만약에 i번째가 체크가 된 것이라면
                                    if(checkArray[i]){
                                        // 체크 개수 1 증가
                                        cnt++;
                                        // 개수를 기준으로 판단
                                       if(cnt!=1){
                                            // str에 ,를 누적
                                            str = str + ",";
                                        }
                                       // str에 텍스트를 누적.
                                        str = str+versionArray[i];
                                    }   // 결과적으로 xxx,xxx,xxx 로 출력이 됨.
                                }
                                // 체크가 모두 해제가 되면
                                if(str.isEmpty()){
                                    button5.setText("대화상자");
                                }else{
                                    // 체크가 되어 있는 상항
                                    button5.setText(str);
                                }
                        }
                    });
                // 버튼을 하단에 만들어서, 클릭시 이벤트 설정 가능.
               /* dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    // 클릭시 이벤트
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 토스트 메시지를 띄움
                        Toast.makeText(MainActivity.this,"확인버튼누르면 동작",Toast.LENGTH_SHORT).show();
                    }
                });*/
               // 버튼을 하단에 만듦. 이번에는 negative
                dlg.setNegativeButton("닫기",null);
                // 대화 상자가 보이게 설정.
                dlg.show();
            }
        });

    }


    // 컨텍스트메뉴를 사용하기 위한 메소드
    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,     // 버튼2, 버튼3는 View에 포함되는 요소
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();
        // 버튼과 메뉴가 대응 되도록 문장을 구성. 롱클릭을 해야 메뉴가 뜬다.
        if(v==button2){
            //XML로 작성

  // 메뉴 타이틀을 설정
            menu.setHeaderTitle("배경색 변경");
            // 메뉴에 아이콘 그림도 설정
/*            menu.setHeaderIcon(R.drawable.icon1);
            mInflater.inflate(R.menu.menu2,menu);*/


            //컨텍스트 메뉴 만들기 java버전
            menu.setHeaderTitle("배경색 변경(java)");
            menu.add(0,1,0,"빨강색");
            menu.add(0,2,0,"파랑색");
            menu.add(0,3,0,"초록색");
        }
        if(v==button3){

            menu.setHeaderTitle("버튼 변경");
/*            menu.setHeaderIcon(R.drawable.icon2);
            mInflater.inflate(R.menu.menu3,menu);*/


            //컨텍스트 메뉴 만들기 java버전
            menu.setHeaderTitle("버튼 변경(java)");
            menu.add(0,4,0,"버튼 45도 회전");
            menu.add(0,5,0,"버튼 2배 확대");
        }

    }


     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // menu1.xml 파일이 있을시,
/*        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1,menu);*/
        // java 파일에서 이용 방법
        menu.add(0,1,0,"배경색(빨강)");
        menu.add(0,2,0,"배경색(파랑)");
        menu.add(0,3,0,"배경색(초록)");

        SubMenu sMenu = menu.addSubMenu("버튼변경->>");
        sMenu.add(0,4,0,"버튼 45도 회전");
        sMenu.add(0,5,0,"버튼 2배 확대");

        return true;
    }
    // 일반 메뉴 클릭시 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            // xml파일 이용시.
/*            case R.id.me1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;

            case  R.id.me2:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;

            case  R.id.me3:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;

            case R.id.subRotate1:
                button1.setRotation(45);
                return true;

            case R.id.subSize1:
                button1.setScaleX(2);
                return true;*/

           // java에서 코딩 방법
            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 2:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 3:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 4:
                button1.setRotation(45);
                return true;
            case 5:
                button1.setScaleX(2);
                return true;
        }
        return false;
    }



    // 컨텍스트 메뉴 사용시
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()) {
            // xml버전에서 구동
   /*         case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.subRotate:
                button2.setRotationX(30);
                button3.setRotationY(30);
                return true;
            case R.id.subSize:
                button2.setScaleX(2);
                button3.setScaleX(2);
                return true;*/

           // java버전에서 구동
            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 2:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case 3:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case 4:
                button2.setRotationX(30);
                button3.setRotationY(30);
                return true;
            case 5:
                button2.setScaleX(2);
                button3.setScaleX(2);
                return true;
        }
        return false;
    }

}