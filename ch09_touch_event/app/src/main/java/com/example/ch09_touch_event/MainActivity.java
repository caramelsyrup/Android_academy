package com.example.ch09_touch_event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Paint paint = new Paint();
    // 선은 1, 원은 2, 사각형은 3으로 저장. 상수화
    final static int Line=1, Circle=2, Rect=3;
    // curShape변수에 위의 변수들을 담을수 있도록.
    static int curShape = Line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("간단 그림판");
    }

    private class MyGraphicView extends View{
        // 좌표 초기화
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {    // 어떤 이벤트?
            switch ((event.getAction())){   // 이벤트 종류를 반환
                case MotionEvent.ACTION_DOWN:
                    // 초기 x,y값 세팅
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;

                case MotionEvent.ACTION_MOVE:   // Move든
                case MotionEvent.ACTION_UP:     // Up이든 모두 동일 기능 수행.
                    // 마지막 x,y 값을 저장.
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    // 무효화. 가장 앞부분에 실행되고 있기에, 이전 실행된 코드를 출력하기 위해서.
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            switch (curShape){
                case Line:
                    // 시작점과 끝점의 값으로 선을 그림.
                    canvas.drawLine(startX,startY,stopX,stopY,paint);
                    break;
                case Circle:
                    // 반지름 길이 구하기.
                    int radius = (int) Math.sqrt(Math.pow(stopX-startX,2)+Math.pow(stopY-startY,2));
                    // 중점에서 반지름값 구하고, 원을 그림.
                    canvas.drawCircle(startX,startY,radius,paint);
                    break;
                case Rect:
                    // 사각형 그리기.
                    canvas.drawRect(startX,startY,stopX,stopY,paint);
                    break;
            }
        }
    }
    // 메뉴 만들기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"선 그리기");
        menu.add(0,2,0,"원 그리기");
        menu.add(0,3,0,"사각형 그리기");

        SubMenu subMenu = menu.addSubMenu("색상 변경>>>");
        subMenu.add(1,1,0,"빨강");
        subMenu.add(1,2,0,"초록");
        subMenu.add(1,3,0,"파랑");

        return true;
    }
    // 메뉴 클릭시 이벤트

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getGroupId()){
            case 0:
                switch (item.getItemId()){
                    case 1:
                        // 선
                        curShape = Line;
                        return true;
                    case 2:
                        // 원
                        curShape = Circle;
                        return true;
                    case 3:
                        // 사각형
                        curShape = Rect;
                        return true;
                }
            case 1:
                switch (item.getItemId()){
                    case 1:
                        paint.setColor(Color.RED);

                        return true;
                    case 2:
                        paint.setColor(Color.GREEN);

                        return true;
                    case 3:
                        paint.setColor(Color.BLUE);

                        return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

}