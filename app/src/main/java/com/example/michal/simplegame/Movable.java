package com.example.michal.simplegame;

import android.graphics.Bitmap;

/**
 * Created by michal on 07.04.18.
 */

public abstract class Movable {

    protected int width;
    protected int height;
    protected long location_x = 0;
    protected long location_y = 0;
    protected long speed_x;
    protected long speed_y;
    protected long acc_x;
    protected long acc_y;
    protected Bitmap image;

    public Movable (
            int x,
            int y,
            int speedX,
            int speedY,
            int accX,
            int accY,
            Bitmap img) {
        location_x = x;
        location_y = y;
        speed_x = MyRandom.getRandomInt(0, 10);
        speed_y = MyRandom.getRandomInt(0, 10);
        acc_x = 0;
        acc_y = 0;
        image = img;
    }

    public long getX() {
        return location_x;
    }

    public long getY() {
        return location_y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getSpeed_x() {
        return speed_x;
    }

    public long getSpeed_y() {
        return speed_y;
    }


    public Bitmap getImage() {
        return image;
    }

    public void accelerate(float x, float y) {
        acc_x = (long)((float)(x + width/2 - location_x)*0.2);
        acc_y = (long)((float)(y + height/2 - location_y)*0.2);
    }

    public void update() {
        // move object
        location_x += speed_x;
        location_y += speed_y;

        // adjust speed with acceleration
        speed_x += acc_x;
        speed_y += acc_y;

        // slow down (friction)
        speed_x = (long)((float)speed_x*0.7);
        speed_y = (long)((float)speed_y*0.7);

        // don't keep acceleration up
        acc_x = 0;
        acc_y = 0;
    }

    public void setLocation(long x, long y) {
        location_x = x;
        location_y = y;
    }

    public void setSpeed(long x, long y) {
        speed_x = x;
        speed_y = y;
    }
}
