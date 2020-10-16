package com.example.clientnetdb.Network;

import android.os.AsyncTask;

import com.example.clientnetdb.Custom_Adapter;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkInsert extends AsyncTask<String,Void,String> {
    private URL Url;
    private String URL_Adress="http://10.100.206.16:8888/testDB/testDB3_insert.jsp";
    private Custom_Adapter adapter;

    public NetworkInsert(Custom_Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    // 추가하기 버튼 이벤트를 통해서 execute함수를 통하여, doInBackground메서드를 실행 시킴.
    // 매개변수는 id, name, phone, grade 등을 받음.
    @Override
    protected String doInBackground(String... strings) {
        String res = "";

        try {
            // DB와 연결된 jsp 서버에 접속
            Url = new URL(URL_Adress);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            // 접속 모드
            conn.setDefaultUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            // 인코딩
            conn.setRequestProperty("Content-type","application/x-www-form-urlencoded; charset=utf-8");
            // 서버에 전송
            StringBuffer buffer = new StringBuffer();
            buffer.append("id").append("=").append(strings[0]);
            buffer.append("&name").append("=").append(strings[1]);
            buffer.append("&phone").append("=").append(strings[2]);
            buffer.append("&grade").append("=").append(strings[3]);

            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();
            // 서버에서 결과 받기
            StringBuilder builder = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line=in.readLine())!=null){
                builder.append(line+"\n");
            }
            res = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        int res = 0;

        try {
            res = JsonParser.getResultJson(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 추가가 제대로 되면 res는 0이 될 수 없다.
        // res가 0이 아니라면 NetworkGet의 생성자를 통해서 실행. list가 뿌려짐.
        if(res==0){
        }else{
            new NetworkGet(adapter).execute("");
        }

    }
}
