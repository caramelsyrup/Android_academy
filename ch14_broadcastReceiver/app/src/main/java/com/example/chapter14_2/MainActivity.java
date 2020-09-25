package com.example.chapter14_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivBattery;
    EditText edtBattery;
    // 브로드캐스트리시버 객체 생성
    // 원래 문법과는 다른 형태임에 유의
    // 만들면서 객체에 넣는데, BroadcastReceiver는 추상클래스를 상속받음.
    // BroadcastReceiver는 운영체제에서 업데이트 사항을 받아옴
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 인텐트로 모든 정보들이 온다. 하지만 BroadcastReceiver가 아닌 Action으로 온다.
            // 맞는 액션에 따라서 기능이 작동되도록 하기 위해서 action 변수 만듦.
            String action = intent.getAction();
            // ACTION_BATTERY_CHANGED정보가 전송되어 온다면,
            if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                // remain변수에 배터리 레벨을 인텐트 통해서 받아 저장.
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                edtBattery.setText("현재 충전량 : "+remain+"%\n");
                 // reamin변수의 크기에 따라서 분기점.
                // reamin변수의 크기에 따라 이미지 변경
                if(remain>=90){
                    ivBattery.setImageResource(R.drawable.battery_100);
                }else if(remain>=70){
                    ivBattery.setImageResource(R.drawable.battery_80);
                }else if(remain>=50){
                    ivBattery.setImageResource(R.drawable.battery_60);
                }else  if(remain>=10){
                    ivBattery.setImageResource(R.drawable.battery_20);
                }else{
                    ivBattery.setImageResource(R.drawable.battery_0);
                }

                // 충전이 어느 루트로 되고 있는지 판단.
                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                switch (plug){
                    case 0:
                        edtBattery.append("전원 연결 : 안됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        edtBattery.append("전원 연결 : 어댑터 연결");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        edtBattery.append("전원 연결 : USB 연결");
                        break;
                }

                int state = intent.getIntExtra(BatteryManager.EXTRA_STATUS,BatteryManager.BATTERY_STATUS_UNKNOWN);
                switch (state){
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        Toast.makeText(getApplicationContext(),"현재 상태를 알수 없음",Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        Toast.makeText(getApplicationContext(),"현재 충전중이 아닙니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        Toast.makeText(getApplicationContext(),"현재 충전 중 입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        Toast.makeText(getApplicationContext(),"현재 충전을 해제 했습니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        Toast.makeText(getApplicationContext(),"현재 충전이 완료 되었습니다.",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        }
    };
    // 실행 메인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 바인딩
        ivBattery = (ImageView) findViewById(R.id.ivBattery);
        edtBattery = (EditText) findViewById(R.id.edtBattery);

    }
    // 중지 메소드
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 인텐트 필터 객체 생성.
        IntentFilter ifilter = new IntentFilter();
        // 인텐트필터에 액션을 추가
        ifilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        // 생성해놓은 br에 등록
        registerReceiver(br,ifilter);
    }
}