package com.example.huanghg.twentyfourtyeight;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeListener implements View.OnTouchListener {
    private static final String TAG = "OnSwipeListener";

    private GestureDetector gd;

    public OnSwipeListener(Context context) {
        gd = new GestureDetector(context, new GestureListener());
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "onTouch: touched");
       // Log.d(TAG, "onTouch: " + event.toString());
        return gd.onTouchEvent(event);
    }



    private class GestureListener extends GestureDetector.SimpleOnGestureListener{



        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "onFling: detected Fling");
            //Log.d(TAG, "onFling: " + "\n" + e1.toString() + "\n" + e2.toString() + "\n" + velocityX + "<-x y->" +velocityY);
            
            //find which difference is bigger
            
            float absDiffX = Math.abs(e1.getX() - e2.getX());
            float absDiffY = Math.abs(e1.getY() - e2.getY());
            
            if(absDiffX > absDiffY){
                if(velocityX > 0){
                    Log.d(TAG, "onFling: swipe right");
                    OnSwipeRight();
                }else{
                    Log.d(TAG, "onFling: swipe left");
                    OnSwipeLeft();
                }

            }else{
                if(velocityY > 0){
                    Log.d(TAG, "onFling: swipe down");
                    OnSwipeDown();
                }else{
                    Log.d(TAG, "onFling: swipe up");
                    OnSwipeUp();
                }
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    public void OnSwipeUp(){

    }

    public void OnSwipeDown(){

    }

    public void OnSwipeLeft(){

    }

    public void OnSwipeRight(){

    }




}
