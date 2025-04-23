package com.example.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Bird {

    private Bitmap birdBitmap;
    private int x, y;
    private int width, height;
    private float velocity = 0;
    private final float gravity = 1.5f;
    private final float lift = -20f;

    public Bird(Context context) {
        birdBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        birdBitmap = Bitmap.createScaledBitmap(birdBitmap, 100, 100, false); // Resize to 100x100

        width = birdBitmap.getWidth();
        height = birdBitmap.getHeight();
        x = 200;  // fixed horizontal position
        y = 500;  // starting vertical position
    }

    public void update() {
        velocity += gravity;
        y += velocity;

        if (y < 0) {
            y = 0;
            velocity = 0;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(birdBitmap, x, y, null);
    }

    public void jump() {
        velocity = lift;
    }

    public Rect getBounds() {
        return new Rect(x, y, x + width, y + height);
    }

    public int getY() {
        return y;
    }

}
