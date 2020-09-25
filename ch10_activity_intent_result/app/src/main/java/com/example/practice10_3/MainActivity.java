package com.example.practice10_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("계산하기 예제");
        // 요소 바인딩
        final EditText edt1 = (EditText) findViewById(R.id.edt1);
        final EditText edt2 = (EditText) findViewById(R.id.edt2);
        // 라디오 그룹에서 바로 체크된 값을 가져오게 하기.
        final RadioGroup rGp = (RadioGroup) findViewById(R.id.rGp);
        // 일일이 라디오 버튼에서 값을 가져와도 되고,
        final RadioButton rbtAdd = (RadioButton) findViewById(R.id.rbtAdd);
        final RadioButton rbtSub = (RadioButton) findViewById(R.id.rbtSub);
        final RadioButton rbtMul = (RadioButton) findViewById(R.id.rbtMul);
        final RadioButton rbtDiv = (RadioButton) findViewById(R.id.rbtDiv);
        Button btn1 = (Button) findViewById(R.id.btn1);

        // 버튼 클릭시 이벤트
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 인텐트 설정.
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                // edt1,edt2의 데이터 값을 담아서 도착클래스에 전달.
                int num1 = Integer.parseInt(edt1.getText().toString());
                int num2 = Integer.parseInt(edt2.getText().toString());
                intent.putExtra("num1",num1);
                intent.putExtra("num2",num2);

                // 분기점 나누는 방식은 각자 취향 것
/*                // 라디오 그룹에서 체크 된것을 받아서 판단
                switch (rGp.getCheckedRadioButtonId()){
                    case R.id.rbtAdd:
                        intent.putExtra("function","A");
                        break;
                    case R.id.rbtSub:
                        intent.putExtra("function","B");
                        break;
                    case R.id.rbtMul:
                        intent.putExtra("function","C");
                        break;
                    case R.id.rbtDiv:
                        intent.putExtra("function","D");
                        break;
                }*/

                // 라디오 버튼에 따라 구분
                if(rbtAdd.isChecked()){
                    intent.putExtra("function","A");
                }else if(rbtSub.isChecked()){
                    intent.putExtra("function","B");
                }else if(rbtMul.isChecked()){
                    intent.putExtra("function","C");
                }else if(rbtDiv.isChecked()){
                    intent.putExtra("function","D");
                }
                // requestCode는 받았는지 확인을 위해서 설정.
                startActivityForResult(intent,0);
            }
        });
    }

    // 전송된 클래스에서 다시 반환되어 돌아올 때, 이벤트
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 반환 되어 올때 RESULT_OK로 세팅된게 맞다면,
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                // value에 받아 온 값을 저장하라.
                int value = data.getIntExtra("result", 0);
                // 토스트에 띄워라.
                Toast.makeText(getApplicationContext(), "계산 결과는 " + value, Toast.LENGTH_SHORT).show();
            }
    }
}