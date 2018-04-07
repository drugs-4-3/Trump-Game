package com.example.michal.simplegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    GameView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();
        gv = new GameView(this, handler);
        gv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                accelerate(x, y);
                return true;
            }

        });
        setContentView(gv);

        handler = new Handler();
        handler.postDelayed(gv,100);
    }

    private void accelerate(float x, float y) {
        gv.accelerate(x, y);
    }

}