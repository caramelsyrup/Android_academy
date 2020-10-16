package com.example.ch13_audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewmp3;
    Button btnPlay,btnStop;
    TextView tvMP3;
    ProgressBar pbMP3;

    ArrayList<String> mp3List;
    String selectedMp3;

    String mp3Path = Environment.getExternalStorageDirectory().getPath()+"/";
    MediaPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 mp3플레이어");

        ActivityCompat.requestPermissions(this,new String[]{
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        // 파일명을 읽어서 배열에 저장.
        mp3List = new ArrayList<String>();
        // 파일클래스로 폴더? 파일? 다루기
        // 경로에 있는 파일들을 배열에 저장.
        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        // 파일 배열에 있는 값
        for(File file : listFiles){
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);
            if(extName.equals((String)"mp3")){
                mp3List.add(fileName);
            }
        }

        listViewmp3 = (ListView) findViewById(R.id.listViewmp3);
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

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        tvMP3 = (TextView) findViewById(R.id.tvMP3);
        pbMP3 = (ProgressBar) findViewById(R.id.pbMP3);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path+selectedMp3);
                    mPlayer.prepare();
                    mPlayer.start();
                    btnPlay.setClickable(false);
                    btnStop.setClickable(true);
                    tvMP3.setText("실행중인 음악: "+selectedMp3);
                    pbMP3.setVisibility(View.VISIBLE);
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
                btnPlay.setClickable(true);
                btnStop.setClickable(false);
                tvMP3.setText("실행중인 음악: ");
                pbMP3.setVisibility(View.INVISIBLE);
            }
        });
        btnStop.setClickable(false);
    }
}