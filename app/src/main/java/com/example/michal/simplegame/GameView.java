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
    Handler handler;

    int trump_width;
    int trump_height;
    long location_x = 0;
    long location_y = 0;
    long speed_x;
    long speed_y;
    long acc_x;
    long acc_y;

    public GameView(Context context, Handler handler) {
        super(context);
        holder = getHolder();
        this.handler = handler;

        speed_x = MyRandom.getRandomInt(0, 10);
        speed_y = MyRandom.getRandomInt(0, 10);
        acc_x = 0;
        acc_y = 0;
    }

    public void draw() {
        if(holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();

            canvas.drawColor(Color.WHITE);
            Bitmap trump = BitmapFactory.decodeResource(this.getResources(),R.drawable.trump_img);
            canvas.drawBitmap(trump, location_x, location_y, new Paint());

            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void accelerate(float x, float y) {
        acc_x = (long)((float)(x + trump_width/2 - location_x)*0.2);
        acc_y = (long)((float)(y + trump_height/2 - location_y)*0.2);
    }

    @Override
    public void run() {
        draw();

        // move trump
        location_x += speed_x;
        location_y += speed_y;

        // correct speed
        speed_x += acc_x;
        speed_y += acc_y;

        // slow down - tension
        speed_x = (long)((float)speed_x*0.7);
        speed_y = (long)((float)speed_y*0.7);

        // don't keep acceleration up
        acc_x = 0;
        acc_y = 0;

        if (isOutOfScreen()) {
            location_x = 0;
            location_y = 0;
            speed_x = 0;
            speed_y = 0;
        }

        handler.postDelayed(this,20);
    }

    private boolean isOutOfScreen() {
        return (location_x + trump_width) < 0 ||
                (location_y + trump_height) < 0 ||
                location_x > getWidth() ||
                location_y > getHeight();
    }
}
