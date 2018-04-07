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
    Player player;

    public GameView(Context context, Handler handl) {
        super(context);
        holder = getHolder();
        handler = handl;
        player = new Player(
                0,
                0,
                4,
                4,
                3,
                3,
                BitmapFactory.decodeResource(this.getResources(),R.drawable.trump_img));
    }

    public void draw() {
        if(holder.getSurface().isValid()) {
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.WHITE);

            // draw player
            canvas.drawBitmap(player.getImage(), player.getX(), player.getY(), new Paint());

            // draw enemies


            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void accelerate(float x, float y) {
        player.accelerate(x, y);
    }

    @Override
    public void run() {
        draw();

        player.update();
        if (isOutOfScreen(player)) {
            // handle out of boundaries
            player.setLocation(0, 0);
            player.setSpeed(0, 0);
        }


        handler.postDelayed(this,20);
    }

    private boolean isOutOfScreen(Movable object) {
        return (object.getX() + object.getWidth()) < 0 ||
                (object.getY() + object.getWidth()) < 0 ||
                object.getX() > getWidth() ||
                object.getY() > getHeight();
    }
}
