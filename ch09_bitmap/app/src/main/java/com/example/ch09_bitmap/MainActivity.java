package com.example.ch09_bitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageButton ibZoomin,ibZoomout,ibRotate,ibBright,ibDark,ibGray,ibBlur,ibEmboss;
    MyGraphicView graphicView;
    // 확대,축소를 위해서 변수 선언
    static float scaleX=1,scaleY=1;
    // 회전을 위해서 변수 선언
    static float angle = 0;
    // 밝기 조절 위한 변수 선언
    static float color = 1;
    // 채도 조절을 위한 변수 선언
    static float satur = 1;
    // 블러싱 조절
    static boolean bBlur = false;
    // 엠보싱 조절
    static boolean eEmboss = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니 포토샵");
        LinearLayout picLinearLayout  = (LinearLayout) findViewById(R.id.pictureLayout);
        graphicView = (MyGraphicView) new MyGraphicView(this);
        picLinearLayout.addView(graphicView);
    }

    private class MyGraphicView extends View{
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),R.drawable.pic4);

            int cenX = this.getWidth()/2;
            int cenY = this.getHeight()/2;
            int picX = (this.getWidth()-picture.getWidth())/2;
            int picY = (this.getHeight()-picture.getHeight())/2;


            // 밝기를 위해서 paint객체 선언.
            Paint paint = new Paint();

            // 배열을 만듦.
/*            float[] array = { color,    0,  0,    0,    0,
                                0,  color,  0,    0,    0,
                                0,      0,color,  0,    0,
                                0,      0,  0,    1,    0       };*/
            // 색배열 객체에 위의 배열을 인자값으로 함.
            ColorMatrix cm = new ColorMatrix();
            // 아이콘 실행 메소드
            clickIcons();
            // 채도
            cm.setSaturation(satur);
/*           if(satur == 0)
                cm.setSaturation(satur);*/

            // paint객체에 컬러 필터메소드 실행.
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            // 블러싱을 위해서
            // 블러싱을 위해서 변수 선언
            if(bBlur){
                BlurMaskFilter bMask;
                bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
                paint.setMaskFilter(bMask);
            }

            // 엠보싱을 위해서
            if(eEmboss){
                EmbossMaskFilter eMask;
                eMask = new EmbossMaskFilter(new float[]{3,3,3},0.5f,8,10);
                paint.setMaskFilter(eMask);
            }

            // 확대,축소
            canvas.scale(scaleX,scaleY,cenX,cenY);
            // 회전
            canvas.rotate(angle,cenX,cenY);
            // 그리기
            canvas.drawBitmap(picture,picX,picY,paint);

            picture.recycle();

        }
    }
    private void clickIcons(){
        ibZoomin = (ImageButton) findViewById(R.id.ibZoomin);
        ibZoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();
            }
        });

        ibZoomout = (ImageButton) findViewById(R.id.ibZoomout);
        ibZoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
            }
        });

        ibRotate = (ImageButton) findViewById(R.id.ibRotate);
        ibRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angle = angle + 20;
                graphicView.invalidate();
            }
        });

        ibBright = (ImageButton) findViewById(R.id.ibBright);
        ibBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*color = color+0.2f;*/
                satur = satur + 20.0f;
                graphicView.invalidate();
            }
        });

        ibDark = (ImageButton) findViewById(R.id.ibDark);
        ibDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*color = color - 0.2f;*/
                satur = satur - 20.0f;
                graphicView.invalidate();
            }
        });

/*        ibGray = (ImageButton) findViewById(R.id.ibGray);
        ibGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(satur==0)
                    satur = 1;
                else
                    satur = 0;
                graphicView.invalidate();
            }
        });*/

        ibBlur = (ImageButton) findViewById(R.id.ibBlur);
        ibBlur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bBlur == false){
                    bBlur = true;
                }else{
                    bBlur = false;
                }
                graphicView.invalidate();
            }
        });

        ibEmboss = (ImageButton) findViewById(R.id.ibEmboss);
        ibEmboss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eEmboss == false){
                    eEmboss = true;
                }else{
                    eEmboss = false;
                }
                graphicView.invalidate();
            }
        });

    }


}