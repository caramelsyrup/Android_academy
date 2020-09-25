package com.example.practice07_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // activity_main에 있는 요소들 변수 선언
    //TextView tvName,tvEmail;
    EditText tvName1,tvEmail1;
    //Button button1;
    Button button2;
    // dialog1에 있는 요소들
    EditText dlgEdt1,dlgEdt2;
    // toast1에 있는 요소
    TextView toastText1;
    // 두개의 xml파일 뷰를 선언
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 제목 설정
        setTitle("사용자 정보 입력");
        // 바인딩
        /*tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);*/
        tvName1 = (EditText) findViewById(R.id.tvName1);
        tvEmail1 = (EditText) findViewById(R.id.tvEmail1);

        // button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this,R.layout.dialog1,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력").setIcon(R.drawable.ic_menu_allfriends);
                // 반드시 해줘야함.
                dlg.setView(dialogView);

                final EditText dlgEdtName = (EditText)dialogView.findViewById(R.id.dlgEdt1);
                final EditText dlgEdtEmail = (EditText)dialogView.findViewById(R.id.dlgEdt2);

               // String str1 = tvName1.getText().toString();
                String str2 = tvEmail1.getText().toString();

                dlgEdtName.setText(tvName1.getText().toString());
                dlgEdtEmail.setText(str2);

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        tvName1.setText(dlgEdtName.getText().toString());
                        tvEmail1.setText(dlgEdtEmail.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);
                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int xOffset = (int) (Math.random()*display.getWidth());
                        int yOffset = (int) (Math.random()*display.getHeight());
                        toastView = (View) View.inflate(MainActivity.this,R.layout.toast1,null);
                        TextView toastText = (TextView) toastView.findViewById(R.id.toastText1);
                        toastText.setText("취소했습니다.");
                        toast.setGravity(Gravity.TOP|Gravity.LEFT,xOffset,yOffset);
                        // 이부분이 중요
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });
/*        // 버튼을 누를시 이벤트
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog1.xml을 열어서 대화상자로 만든다.
                dialogView = (View) View.inflate(MainActivity.this,R.layout.dialog1,null);
                // 대화 상자를 위한 dlg 객체 생성
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                // 대화 상자 제목 설정,아이콘 설정, *대화 상자 내의 뷰도 설정*
                dlg.setTitle("사용자 정보 입력").setIcon(R.drawable.ic_menu_allfriends).setView(dialogView);
                // 대화 상자에 버튼도 설정
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    // 버튼을 누르면, 입력한 텍스트가 activity_main화면에 출력되도록
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 바인딩.(inflate를 한 것을 참조해야함)
                       EditText dlgEdtName = (EditText)dialogView.findViewById(R.id.dlgEdt1);
                       EditText dlgEdtEmail = (EditText)dialogView.findViewById(R.id.dlgEdt2);
                        // activity_main화면에 텍스트 붙이기
                        tvName.setText(dlgEdtName.getText().toString());
                        tvEmail.setText(dlgEdtEmail.getText().toString());
                    }
                });
                // 대화 상자에 버튼 설정
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 토스트 객체를 만든다.
                        Toast toast = new Toast(MainActivity.this);
                        // 토스트뷰에 toast1.xml을 저장.
                        toastView = (View) View.inflate(MainActivity.this,R.layout.toast1,null);
                        TextView toastText = (TextView)toastView.findViewById(R.id.toastText1);
                        toastText.setText("취소했습니다.");
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                //대화상자 출력
                dlg.show();
            }
        });*/
    }
}