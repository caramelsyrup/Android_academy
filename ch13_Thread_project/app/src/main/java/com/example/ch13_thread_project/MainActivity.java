package com.example.ch13_thread_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 바인딩을 위해서 변수 선언
    ListView listViewmp3;
    Button btnPlay,btnStop;
    TextView tvMP3,tvTime;
    ProgressBar pbMP3;
    // mp3파일 재생을 위해서 변수 선언
    ArrayList<String> mp3List;
    String selectedMp3;
    String mp3Path = Environment.getExternalStorageDirectory().getPath()+"/";
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        mp3List = new ArrayList<String>();

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName,extName;
        for(File file : listFiles){
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);
            if(extName.equals((String)"mp3")) {
                mp3List.add(fileName);
            }
        }
        // 바인딩
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        tvMP3 = findViewById(R.id.tvMP3);
        tvTime = findViewById(R.id.tvTime);
        pbMP3 = findViewById(R.id.pbMP3);
        listViewmp3 = (ListView) findViewById(R.id.listViewmp3);    // 메뉴
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,mp3List);
        listViewmp3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewmp3.setAdapter(adapter);
        listViewmp3.setItemChecked(0,true);
        listViewmp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMp3 = mp3List.get(position);
            }
        });
        selectedMp3 = mp3List.get(0);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(mp3Path+selectedMp3);
                    mPlayer.prepare();
                    mPlayer.start();
                    btnPlay.setClickable(false);
                    btnStop.setClickable(true);
                    tvMP3.setText("실행중인 음악 : "+selectedMp3);
                    new Thread(){
                        SimpleDateFormat timeFormat = new SimpleDateFormat(" mm:ss ");
                        public void run(){
                            if(mPlayer == null) return;
                            pbMP3.setMax(mPlayer.getDuration());
                            while (mPlayer.isPlaying()){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pbMP3.setProgress(mPlayer.getCurrentPosition());
                                        tvTime.setText("진행 시간 : "+timeFormat.format(mPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(200);
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
                mPlayer.reset();
                btnStop.setClickable(false);
                btnPlay.setClickable(true);
                pbMP3.setProgress(0);
                tvTime.setText("진행 시간 : ");
            }
        });
        btnStop.setClickable(false);
    }
}