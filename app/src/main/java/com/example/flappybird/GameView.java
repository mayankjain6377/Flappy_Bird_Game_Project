package com.example.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



import java.util.ArrayList;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private boolean isGameOver = false;
    private Paint buttonPaint, buttonTextPaint;
    private Rect restartButtonRect;
    private GameThread thread;
    private Bitmap background;

    private Bird bird;
    private ArrayList<Pipe> pipes;
    private int pipeSpacing = 600;
    private int score = 0;
    private Paint scorePaint;

    public GameView(Context context) {
        super(context);
        int btnWidth = 500;
        int btnHeight = 150;
        int centerX = getResources().getDisplayMetrics().widthPixels / 2;
        int centerY = getResources().getDisplayMetrics().heightPixels / 2;

        restartButtonRect = new Rect(centerX - btnWidth/2, centerY - btnHeight/2,
                centerX + btnWidth/2, centerY + btnHeight/2);

        buttonPaint = new Paint();
        buttonPaint.setColor(Color.RED);
        buttonPaint.setStyle(Paint.Style.FILL);

        buttonTextPaint = new Paint();
        buttonTextPaint.setColor(Color.WHITE);
        buttonTextPaint.setTextSize(60);
        buttonTextPaint.setTextAlign(Paint.Align.CENTER);


        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);

        bird = new Bird(context);
        pipes = new ArrayList<>();
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(background, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels, false);


        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        pipes.clear();
        for (int i = 0; i < 3; i++) {
            pipes.add(new Pipe(getContext(), 1000 + i * pipeSpacing));
        }

        // thread is already initialized in the constructor, no need to reinitialize here
        thread.setRunning(true);
        thread.start();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false); // Stop the thread
        while (retry) {
            try {
                thread.join(); // Wait for the thread to finish
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (canvas != null) {
            // 1. Draw background
            canvas.drawBitmap(background, 0, 0, null);

            // 2. Draw bird
            bird.draw(canvas);

            // 3. Draw pipes
            for (Pipe pipe : pipes) {
                pipe.draw(canvas);
            }

            // 4. Draw score
            canvas.drawText("Score: " + score, 50, 150, scorePaint);
            if (isGameOver) {
                canvas.drawRect(restartButtonRect, buttonPaint);
                canvas.drawText("RESTART", restartButtonRect.centerX(), restartButtonRect.centerY() + 20, buttonTextPaint);
            }

        }
    }


    public void update() {
        bird.update();

        for (Pipe pipe : pipes) {
            pipe.update();

            if (pipe.isOffScreen()) {
                pipe.reset();
                score++;
            }

            if (pipe.collidesWith(bird)) {
                isGameOver = true;
                thread.setRunning(false);
            }

        }

        // Check if bird hits ground or flies off screen
        if (bird.getY() > getHeight() || bird.getY() < 0) {
            isGameOver = true;
            thread.setRunning(false);
        }



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isGameOver) {
                if (restartButtonRect.contains((int) event.getX(), (int) event.getY())) {
                    restartGame();
                }
            } else {
                bird.jump();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }
    private void restartGame() {
        bird = new Bird(getContext());
        pipes.clear();
        for (int i = 0; i < 3; i++) {
            pipes.add(new Pipe(getContext(), 1000 + i * pipeSpacing));
        }
        score = 0;
        isGameOver = false;

        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Not used
    }
}
