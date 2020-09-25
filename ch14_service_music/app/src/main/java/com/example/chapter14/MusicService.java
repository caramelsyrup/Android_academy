package com.example.chapter14;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;
// 해당 클래스는 Service를 상속받음.
public class MusicService extends Service {
    // MediaPlayer형 전역변수 선언
    MediaPlayer mp;


    // 구동 중에도 연결해서 사용 가능하도록 하는 메소드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // 로그찍기
        android.util.Log.i("서비스테스트","onCreate()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // 로그찍기
        android.util.Log.i("서비스테스트","onDestroy()");
        // MediaPlayer 중단
        mp.stop();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 로그찍기
        android.util.Log.i("서비스테스트","onStartCommand()");
        // 변수에 raw폴더에 있는 음악 파일을 저장.
        mp = MediaPlayer.create(getApplicationContext(),R.raw.song4);
        // MediaPlayer는 쓰레드에 안전하지 않음. 여러 접속과 생성은 같은 쓰레드를 소비함.
        mp.setLooping(true);
        // MediaPlayer시작
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
