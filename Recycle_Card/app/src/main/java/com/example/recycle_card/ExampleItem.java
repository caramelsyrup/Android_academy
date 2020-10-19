package com.example.recycle_card;

public class ExampleItem {
    private int mImageResource;
    private String mtx1;
    private String mtx2;

    public ExampleItem(int mImageResource, String mtx1, String mtx2){
        this.mImageResource = mImageResource;
        this.mtx1 = mtx1;
        this.mtx2 = mtx2;
    }
    public void changeText1(String text){
        mtx1 = text;

    }
    public int getmImageResource() {
        return mImageResource;
    }

    public String getMtx1(){
        return mtx1;
    }

    public String getMtx2(){
        return mtx2;
    }

}
