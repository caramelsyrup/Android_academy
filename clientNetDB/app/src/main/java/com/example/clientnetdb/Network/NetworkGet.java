package com.example.clientnetdb.Network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.clientnetdb.Custom_Adapter;
import com.example.clientnetdb.UserInfo;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NetworkGet extends AsyncTask<String,Void,String> {
    private URL Url;
    // 서버의 페이지를 설정.
    private String URL_Adress = "http://10.100.206.16:8888/testDB/testDB.jsp";
    // 커스텀어뎁터 클래스형의 변수 선언.
    private Custom_Adapter adapter;

    public NetworkGet(Custom_Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // execute메소드를 실행하면, 해당 함수 실행.
    @Override
    protected String doInBackground(String... strings) {    // String배열을 표현. String... Strings
        String res = "";
        try {
            // jsp페이지를 접속한다.
            Url = new URL(URL_Adress);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            // 전송모드 설정
            conn.setDefaultUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            // content-type 설정
            conn.setRequestProperty("Content-type","application/x-www-form-urlencoded; charset=utf-8");
            // 전송값 설정
            StringBuffer buffer = new StringBuffer();
            buffer.append("id").append("=").append(strings[0]);
            // 서버로 전송
            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            // 서버로부터 온 값을 받음.
            StringBuilder builder = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line=in.readLine())!=null){
                builder.append(line+"\n");
            }
            res = builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("Get result",res);
        // res값을 반환. json형
        return res;
    }

    // 매개변수 s는 위의 res 값을 받는다.
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // 데이터를 받을 배열객체 생성.
        ArrayList<UserInfo> userList = new ArrayList<UserInfo>();
        int count = 0;
        try {
            // res값이 저장된 s를 userList배열 객체에 넣는다.
            // getUserInfoJson함수를 정의 할때 json형의 데이터를 userList배열에 넣을 수 있도록 설계.
            count = JsonParser.getUserInfoJson(s,userList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(count==0){
        }else{
            adapter.setDatas(userList);
            adapter.notifyDataSetInvalidated();
        }
    }
}
