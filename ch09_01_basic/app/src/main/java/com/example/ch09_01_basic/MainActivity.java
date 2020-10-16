package com.example.ch09_01_basic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
}

private static class MyGraphicView extends View {

    public MyGraphicView(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        // 붓이 좀더 세밀하게 표현
        paint.setAntiAlias(true);
        // 붓의 색깔은 검정
        paint.setColor(Color.BLACK);
        // 붓의 두께는 30
        paint.setStrokeWidth(30);
        // 캔버스에 그리기.
        canvas.drawLine(30,30,300,30,paint);
        // 끝을 둥글게 설정.
        paint.setStrokeCap(Paint.Cap.ROUND);
        // 캔버스에 그리기
        canvas.drawLine(30,70,300,70,paint);
        // 타원형 그리기
        canvas.drawOval(60,110,270,200,paint);
        // 부채꼴 그리기
        // 사각형 객체 생성.
        RectF rect = new RectF();
        // 사각형의 시작점과 끝점을 설정하여, 크기 결정.
        rect.set(60,200,140,280);
        // 부채꼴 그리기. (사각형 객체, 시작 각도는 45도 위치, +90도,부채꼴,붓)
        canvas.drawArc(rect,45,90,true,paint);
        // 사각형 겹치기
        // 파랑 사각형
        // 붓에 물감 바르기
        paint.setColor(Color.argb(100,0,54,252));
        // 사각형 그리기
        canvas.drawRect(60,300,180,420,paint);
        // 빨간 사각형
        paint.setColor(Color.argb(70,252,0,67));
        // 그리기
        canvas.drawRect(90,330,210,450,paint);
    }
}
}