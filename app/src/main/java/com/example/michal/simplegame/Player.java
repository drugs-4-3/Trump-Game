package com.example.michal.simplegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by michal on 07.04.18.
 */

public class Player extends Movable {

    public Player (
            int x,
            int y,
            int speedX,
            int speedY,
            int accX,
            int accY,
            Bitmap img) {
        super(x, y, speedX, speedY, accX, accY, img);
    }
}
