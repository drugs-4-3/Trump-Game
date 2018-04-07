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
    int trump_width;
    int trump_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Bitmap trump = BitmapFactory.decodeResource(this.getResources(),R.drawable.trump_img);
        trump_width = trump.getWidth();
        trump_height = trump.getHeight();

        gv = new GameView(this);
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

    public class GameView extends SurfaceView implements Runnable {

        SurfaceHolder holder;
        long location_x = 0;
        long location_y = 0;

        long speed_x;
        long speed_y;
        long acc_x;
        long acc_y;

        public GameView(Context context) {
            super(context);
            holder = getHolder();

            speed_x = MyRandom.getRandomInt(0, 10);
            speed_y = MyRandom.getRandomInt(0, 10);
            acc_x = 0;
            acc_y = 0;
        }

        public void draw() {
            if(holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();

                canvas.drawColor(Color.WHITE);
                Bitmap but = BitmapFactory.decodeResource(this.getResources(),R.drawable.trump_img);
                canvas.drawBitmap(but,location_x, location_y, new Paint());

                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void accelerate(float x, float y) {
            Log.i("myapp", "acc_x: "+ (long)((float)(x - location_x)*0.01));
            Log.i("myapp", "acc_y: "+ (long)((float)(y - location_y)*0.01));
//            acc_x = (long)(x - location_x);
//            acc_y = (long)(y - location_y);
            acc_x = (long)((float)(x - location_x)*0.2);
            acc_y = (long)((float)(y - location_y)*0.2);
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

//            Log.i("myapp", "location_x: " + location_x);
//            Log.i("myapp", "location_y: " + location_y);
//            Log.i("myapp", "location_x - trump: " + location_y - );

            return (location_x + trump_width) < 0 ||
                    (location_y + trump_height) < 0 ||
                    location_x > getWidth() ||
                    location_y > getHeight();
        }
    }

}