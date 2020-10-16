package com.example.clientnetdb.Network;

import com.example.clientnetdb.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {
    static public int getUserInfoJson(String response, ArrayList<UserInfo> userList) throws JSONException {
        String strId;
        String strName;
        String strPhone;
        String strGrade;
        String strWriteTime;

        JSONObject rootJSON = new JSONObject(response);
        // jsonArray를 받은뒤,
        JSONArray jsonArray = new JSONArray(rootJSON.getString("datas"));
        // for문을 이용해서, 배열안의 원소 하나 하나를 빼내어  따로 변수에 담는다.
        for(int i=0; i<jsonArray.length();i++){
            // json 객체에 넣는다. jsonArray의 순서대로
            JSONObject jsonObj = (JSONObject) jsonArray.get(i);
            // json 객체의 key를 찾는다. 그리고 그 key의 value 값을 저장.
            if(jsonObj.getString("ID").toString().equals("null"))
                strId="-";
            else
                strId = jsonObj.getString("ID").toString();

            if(jsonObj.getString("NAME").toString().equals("null"))
                strName="-";
            else
                strName = jsonObj.getString("NAME").toString();

            if(jsonObj.getString("PHONE").toString().equals("null"))
                strPhone="-";
            else
                strPhone = jsonObj.getString("PHONE").toString();

            if(jsonObj.getString("GRADE").toString().equals("null"))
                strGrade="-";
            else
                strGrade = jsonObj.getString("GRADE").toString();

            if(jsonObj.getString("WRITE_TIME").toString().equals("null"))
                strWriteTime="-";
            else {
                strWriteTime = jsonObj.getString("WRITE_TIME").toString();
                String[] temp = strWriteTime.split(" ");
                strWriteTime = temp[0] + "\n" + temp[1];
            }
            userList.add(new UserInfo(strId,strName,strPhone,strGrade,strWriteTime));
        }
        return jsonArray.length();
    }
    static public int getResultJson(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        JSONObject jsonObject = new JSONObject(jsonArray.getString(0));
        return Integer.parseInt(jsonObject.getString("RESULT_OK"));
    }
}
