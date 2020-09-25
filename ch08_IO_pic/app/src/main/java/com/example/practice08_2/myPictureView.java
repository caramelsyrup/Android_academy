package com.example.practice08_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class myPictureView extends View {
    // 해당 클래스에 보여줄 이미지 파일의 경로 및 파일 이름을 저장할 변수 선언
    String imagePath = null;
    public myPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // imagePath값이 있으면, 그림파일 출력.
        if(imagePath!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            canvas.scale(2, 2, 0, 0);
            canvas.drawBitmap(bitmap,0,0,null);
            bitmap.recycle();
        }
    }
}

