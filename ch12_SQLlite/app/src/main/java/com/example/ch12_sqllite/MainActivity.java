package com.example.ch12_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 변수 선언
    MyDBHelper myHelper;    // DBopenHelper 클래스 (DDL) Create,Drop,Alter
    EditText edtName,edtNumber,edtNameResult,edtNumberResult;
    Button btnInit,btnInsert,btnSelect,btnUpdate,btnDelete;
    SQLiteDatabase sqlDB;   // SQLilteDB 클래스 (DML) select , insert, update, delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그룹 가수 관리 DB");

        // 바인딩
        edtName = (EditText) findViewById(R.id.edtName);    // 이름입력
        edtNumber = (EditText) findViewById(R.id.edtNumber);    // 인원 입력
        edtNameResult = (EditText) findViewById(R.id.edtNameResult);    // 결과 표시
        edtNumberResult = (EditText) findViewById(R.id.edtNumberResult);    // 결과 표시
        btnInit = (Button) findViewById(R.id.btnInit);  // 초기화
        btnInsert = (Button) findViewById(R.id.btnInsert);  // 입력
        btnSelect = (Button) findViewById(R.id.btnSelect);  // 조회
        btnUpdate = (Button) findViewById(R.id.btnUpdate);  // 수정
        btnDelete = (Button) findViewById(R.id.btnDelete);  // 삭제

        // 객체를 만들면서, DB만들고, 테이블 만듦.
        myHelper = new MyDBHelper(this);
        // 버튼 이벤트 초기화
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SQLiteDatabase형 변수에 SQLiteOpenHelper를 상속받은 클래스의 변수를 참조하여 메소드를 실행된 결과를 저장(DB를 반환).
                // 데이터베이스를 만들거나 여는 메소드(이메소드는 쓰기모드 DB), 첫 실행에서 onCreate과 onUpgrade이 실행됨.
                // 테이블 역시 저장됨.
                sqlDB = myHelper.getWritableDatabase();
                // 테이블이 변경되어 새버전의 테이블이 될때 실행. 예외가 생기면, 알아서 롤백 됨.
                myHelper.onUpgrade(sqlDB,1,2);
                // 실행되는 메소드를 닫아준다.(안드로이드에서 권장)
                sqlDB.close();
            }
        });

        // 버튼 이벤트 입력
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // 쓰기모드 메소드
                    sqlDB = myHelper.getWritableDatabase();
                    // 해당 sql문을 실행.
                    sqlDB.execSQL("INSERT INTO groupTBL VALUES('"+edtName.getText().toString()+"',"+edtNumber.getText().toString()+");");
                    // 실행되는 메소드 닫기(안드로이드에서 권장)
                    sqlDB.close();
                    // 토스트 메시지 띄우기
                    Toast.makeText(getApplicationContext(),"추가되었습니다.",Toast.LENGTH_SHORT).show();
                    // 입력칸 초기화
                    edtName.setText("");
                    edtNumber.setText("");
                    // 조회버튼의 기능을 실행.
                    btnSelect.callOnClick();
                }catch (Exception e){   // 예외 발생시
                    e.printStackTrace();    // 예외 기록
                    // 토스트 메시지 띄우기
                    Toast.makeText(getApplicationContext(),"데이터를 확인하세요!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 버튼 이벤트 조회
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SQLiteDatabase형 변수에 SQLiteOpenHelper를 상속받은 클래스의 변수를 참조하여 메소드를 실행.
                // 데이터베이스를 만들거나 여는 메소드(읽기), 첫 실행에서 onCreate과 onUpgrade이 실행됨.
                sqlDB = myHelper.getReadableDatabase();
                // 프롬프트라고도 하는 것. 일반적으로 보는 커서. DB에서는 현재 위치.
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;",null);

                String strNames = "그룹 이름"+"\r\n"+"---------------"+"\r\n";
                String strNumbers = "그룹 인원"+"\r\n"+"---------------"+"\r\n";

                // 커서가 움직이는 동안
                while(cursor.moveToNext()){
                    // strNames에 해당 열의 자료를 누적
                    strNames += cursor.getString(0)+"\r\n";
                    // strNumbers에 해당 열의 자료를 누적
                    strNumbers += cursor.getString(1)+"\r\n";
                }
                // 누적된 자료를 출력될수 있도록 영역에 세팅
                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close(); // 커서 닫아주고
                sqlDB.close();  // 디비도 닫아준다.(안드로이드에서 권장)
            }
        });

        // 버튼이벤트 수정
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //sqlDB 객체에 저장. 반환되는 쓰기모드 DB(계정)와 테이블.
                    sqlDB = myHelper.getWritableDatabase();
                    // sql문 문장을 query에 저장
                    String query="";
                    query= "UPDATE groupTBL SET gNumber=";
                    query+= edtNumber.getText().toString();
                    query+= " WHERE gName='";
                    query+= edtName.getText().toString();
                    query+= "';";
                    // sql문을 실행 되도록.
                    sqlDB.execSQL(query);
                    // sql문 실행이 끝나면 닫아주기.
                    sqlDB.close();
                    // 토스트 띄우기
                    Toast.makeText(getApplicationContext(),"수정되었습니다.",Toast.LENGTH_SHORT).show();
                    // 입력칸 초기화
                    edtName.setText("");
                    edtNumber.setText("");
                    // select실행 메소드 불러와서 실행
                    btnSelect.callOnClick();
                }catch (SQLiteException s){ // 예외 발생시
                    s.printStackTrace();    // 출력
                    // 토스트 띄우기
                    Toast.makeText(getApplicationContext(),"데이터를 확인하세요!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 버튼이벤트 삭제
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    sqlDB = myHelper.getWritableDatabase();
                    sqlDB.execSQL("DELETE FROM groupTBL WHERE gName='"+edtName.getText().toString()+"';");
                    sqlDB.close();
                    Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtNumber.setText("");
                    btnSelect.callOnClick();
                }catch (SQLiteException s){
                    s.printStackTrace();
                    Toast.makeText(getApplicationContext(),"데이터를 확인하세요!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // SQLiteHelper 이용. 안드로이드에서 권장하는 방법.
    public class MyDBHelper extends SQLiteOpenHelper {
        // 생성자 - 액티비티(context)를 받아서 설정.
        public MyDBHelper(Context context) {
            // 액티비티,DB이름설정(계정설정),
            super(context, "groupDB", null, 1);
        }
        // 테이블을 생성하는 메소드
        @Override
        public void onCreate(SQLiteDatabase db) {
            // SQL문 실행
            db.execSQL("CREATE TABLE groupTBL(gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }
        // 테이블을 삭제한 후 다시 생성하는 메소드
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // 존재한다면 삭제해라.
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            // 생성메소드 실행
            onCreate(db);
        }
    }
}