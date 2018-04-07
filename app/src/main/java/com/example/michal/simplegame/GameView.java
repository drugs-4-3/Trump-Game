package com.example.michal.simplegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by michal on 06.04.18.
 */
public class GameView extends SurfaceView implements Runnable {

    SurfaceHolder holder;
    long X = 0;
    long Y = 0;
    Handler handler;

    public GameView(Context context, Handler handler) {
        super(context);
        holder = getHolder();
        this.handler = handler;
    }

    public void draw() {
        if(holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();

            canvas.drawColor(Color.WHITE);
            Bitmap but = BitmapFactory.decodeResource(this.getResources(),R.drawable.trump_img);
            canvas.drawBitmap(but,X,Y,new Paint());

            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void run() {
        draw();
        //zmiana parametwó
        //odczy pozycji gracza
        // .....
        Y+=5;
        if(Y>1000) {
            Y = 0;
            X = Math.round(Math.random()*600);
        }
        handler.postDelayed(this,50);
    }
}
