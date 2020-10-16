package com.example.clientnetdb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clientnetdb.Network.NetworkDelete;
import com.example.clientnetdb.Network.NetworkUpdate;

import java.util.ArrayList;

public class Custom_Adapter extends BaseAdapter {
    private Activity mAct;
    LayoutInflater mInflater;
    ArrayList<UserInfo> mUserInfoObjArr;
    int mListLayout;

    public Custom_Adapter(Activity a, int listLayout,ArrayList<UserInfo> userInfoObjArrayListT) {
        mAct = a;
        mListLayout = listLayout;
        mUserInfoObjArr = userInfoObjArrayListT;
        mInflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setDatas(ArrayList<UserInfo>arrayList){
        mUserInfoObjArr = arrayList;
    }

    @Override
    public int getCount() {
        return mUserInfoObjArr.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserInfoObjArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView = mInflater.inflate(mListLayout,parent,false);

        final TextView tvID = (TextView) convertView.findViewById(R.id.tv_id);
        tvID.setText(mUserInfoObjArr.get(position).id);

        final TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        tvName.setText(mUserInfoObjArr.get(position).name);

        final TextView tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
        tvPhone.setText(mUserInfoObjArr.get(position).phone);

        final TextView tvGrade = (TextView) convertView.findViewById(R.id.tv_grade);
        tvGrade.setText(mUserInfoObjArr.get(position).grade);

        final TextView tvWriteTime = (TextView) convertView.findViewById(R.id.tv_write_time);
        tvWriteTime.setText(mUserInfoObjArr.get(position).writeTime);

        Button btnUpdate = (Button) convertView.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = mInflater.inflate(R.layout.dialog_update,null);

                ((EditText)view.findViewById(R.id.edtID)).setText(mUserInfoObjArr.get(position).id);
                ((EditText)view.findViewById(R.id.edtName)).setText(mUserInfoObjArr.get(position).name);
                ((EditText)view.findViewById(R.id.edtPhone)).setText(mUserInfoObjArr.get(position).phone);
                ((EditText)view.findViewById(R.id.edtGrade)).setText(mUserInfoObjArr.get(position).grade);

                AlertDialog.Builder ad = new AlertDialog.Builder(mAct);
                ad.setTitle("수정하기");
                ad.setView(view);
                ad.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = ((EditText)view.findViewById(R.id.edtID)).getText().toString();
                        String name = ((EditText)view.findViewById(R.id.edtName)).getText().toString();
                        String phone = ((EditText)view.findViewById(R.id.edtPhone)).getText().toString();
                        String grade = ((EditText)view.findViewById(R.id.edtGrade)).getText().toString();

                        new NetworkUpdate(Custom_Adapter.this).execute(id,name,phone,grade);
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mAct,"취소하셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.show();
            }
        });

        Button btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = tvID.getText().toString();
                AlertDialog.Builder ad = new AlertDialog.Builder(mAct);
                ad.setTitle("삭제하기");
                ad.setMessage("사용자 ID: "+userID+"를(을) 정말 삭제하시겠습니까?");

                ad.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new NetworkDelete(Custom_Adapter.this).execute(tvID.getText().toString());
                    }
                });
                ad.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mAct,"취소하셨습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.show();
            }
        });
        return convertView;
    }
}
