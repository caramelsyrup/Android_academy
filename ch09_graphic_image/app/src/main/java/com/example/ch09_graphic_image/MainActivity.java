package com.example.ch09_graphic_image;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // xml을 사용하지 않음. 대신 View를 사용한다.
        setContentView(new MyGraphicView(this));
    }

    // View 클래스를 상속받아 재정의해서 만듦.
    private static class MyGraphicView extends View{

        public MyGraphicView(Context context) {
            super(context);
        }

        // canvas를 매개변수로 한다. onDraw메소드. 그리는 기능.
        @Override
        protected void onDraw(Canvas canvas) {
            // canvas는 도화지 즉, 그리는 바탕이 되는 판
            super.onDraw(canvas);
            // canvas에 채워 넣기 위해서 paint객체를 생성.
            Paint paint = new Paint();
            // 화면이 확대 되면 픽셀(모자이크)현상이 나타남. 즉 계단현상이 드러나게 됨. 이것을 보정
            paint.setAntiAlias(true);
            // 붓에 색상을 초록색으로 정의.
            paint.setColor(Color.GREEN);
            // canvas와 paint를 연결. 선 그리기
            canvas.drawLine(10,10,300,10,paint);

            // 색상을 파랑색
            paint.setColor(Color.BLUE);

            paint.setStrokeWidth(5);
            // canvas와 paint를 연결. 선그리기
            canvas.drawLine(10,30,300,30,paint);
            // 색상을 파랑색으로
            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);
            paint.setStyle(Paint.Style.FILL);
            Rect rect1 = new Rect(10,50,10+100,50+100);
            canvas.drawRect(rect1,paint);

            paint.setStyle(Paint.Style.STROKE);
            Rect rect2 = new Rect(130,50,130+100,50+100);
            canvas.drawRect(rect2,paint);

            RectF rect3 = new RectF(250,50,250+100,50+100);

            canvas.drawRoundRect(rect3,20,20,paint);

            canvas.drawCircle(60,220,50,paint);

            paint.setStrokeWidth(5);

            Path path1 = new Path();
            path1.moveTo(10,290);
            path1.lineTo(10+50,290+50);
            path1.lineTo(10+100,290);
            path1.lineTo(10+150,290+50);
            path1.lineTo(10+200,290);
            canvas.drawPath(path1,paint);

            paint.setStrokeWidth(0);
            paint.setTextSize(30);
            canvas.drawText("안드로이드",10,390,paint);

        }
    }
}