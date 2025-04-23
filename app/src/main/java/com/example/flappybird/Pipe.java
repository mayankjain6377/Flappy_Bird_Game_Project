package com.example.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Pipe {

    private Bitmap topPipeBitmap, bottomPipeBitmap;
    private int x;
    private int topHeight;
    private final int gap = 400; // vertical gap between top and bottom pipes
    private final int speed = 10;
    private int screenHeight,screenWidth;
    private final int width;
    private Random random;

    public Pipe(Context context, int startX) {
        topPipeBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe_top);
        bottomPipeBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe_bottom);
        this.screenWidth = screenWidth;

// Scale pipes
        topPipeBitmap = Bitmap.createScaledBitmap(topPipeBitmap, 200, 800, false);
        bottomPipeBitmap = Bitmap.createScaledBitmap(bottomPipeBitmap, 200, 800, false);
        width = topPipeBitmap.getWidth();

        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        x = startX;

        random = new Random();
        resetHeight();
    }

    private void resetHeight() {
        topHeight = 100 + random.nextInt(screenHeight / 2); // random height for the top pipe
    }

 // You could increase this as the score increases

    public void update() {
        x -= speed;
        if (x < 0) {
            x = screenWidth + new Random().nextInt(300);
            resetHeight();
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(topPipeBitmap, x, topHeight - topPipeBitmap.getHeight(), null);
        canvas.drawBitmap(bottomPipeBitmap, x, topHeight + gap, null);

    }

    public void reset() {
        x = screenHeight + random.nextInt(300); // reset off-screen
        resetHeight();
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public boolean collidesWith(Bird bird) {
        Rect birdRect = bird.getBounds();
        Rect topRect = new Rect(x, 0, x + width, topHeight);
        Rect bottomRect = new Rect(x, topHeight + gap, x + width, screenHeight);
        return Rect.intersects(birdRect, topRect) || Rect.intersects(birdRect, bottomRect);
    }


    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
}
