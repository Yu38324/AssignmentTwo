package com.example.assignmenttwo;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    long MOLE_DISPLAY_TIME, GAME_DURATION;
    int currentScore, timeRemaining, currentMoleIndex;
    Handler moleHandler;
    Runnable moleRunnable;
    CountDownTimer gameTimer;
    Random random;
    ArrayList<Mole> moles;
    TextView scoreTextView, timerTextView;
    boolean isGameRunning;
    Context context;

    private static final long DEFAULT_MOLE_DISPLAY_TIME = 1000;
    private static final long DEFAULT_GAME_DURATION = 30000;

    public GameLogic(Context context, ArrayList<ImageView> moleViews, TextView scoreText, TextView timeText) {
        this.context = context;
        this.scoreTextView = scoreText;
        this.timerTextView = timeText;

        this.MOLE_DISPLAY_TIME = DEFAULT_MOLE_DISPLAY_TIME;
        this.GAME_DURATION = DEFAULT_GAME_DURATION;
        this.currentScore = 0;
        this.timeRemaining = (int) (GAME_DURATION / 1000);
        this.currentMoleIndex = -1;
        this.moleHandler = new Handler();
        this.random = new Random();
        this.isGameRunning = false;
        // initial moles
        this.moles = new ArrayList<>();
        for (int i = 0; i < moleViews.size(); i++) {
            moles.add(new Mole(i, moleViews.get(i)));
        }

        setupClickListeners();
    }

    public void startGame() {
        currentScore = 0;
        isGameRunning = true;
        updateScoreText();
        startTimer();
        startMoleLoop();
    }

    public void startTimer() {
        gameTimer = new CountDownTimer(GAME_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = (int) (millisUntilFinished / 1000);
                timerTextView.setText("Timer: " + timeRemaining);
            }

            @Override
            public void onFinish() {
                endGame();
            }
        };
        gameTimer.start();
    }

    private void startMoleLoop() {
        moleRunnable = new Runnable() {
            @Override
            public void run() {
                if (isGameRunning) {
                    showRandomMole();
                    moleHandler.postDelayed(this, 1000); // 每秒显示一个新地鼠
                }
            }
        };
        moleHandler.post(moleRunnable);
    }

    private void stopMoleLoop() {
        if (moleRunnable != null) {
            moleHandler.removeCallbacks(moleRunnable);
        }
    }

    private void showRandomMole() {

        hideMole();

        // random show mole
        int randomIndex = random.nextInt(moles.size());
        showMole(randomIndex);
    }

    private void showMole(int index) {
        if (index >= 0 && index < moles.size()) {
            Mole mole = moles.get(index);
            mole.setVisible(true);
            currentMoleIndex = index;

            mole.getImageView().setImageResource(R.drawable.img_with_mole);

            // hide mole in time
            moleHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hideMole();
                }
            }, MOLE_DISPLAY_TIME);
        }
    }

    private void hideMole() {
        if (currentMoleIndex != -1 && currentMoleIndex < moles.size()) {
            Mole mole = moles.get(currentMoleIndex);
            mole.setVisible(false);
            mole.getImageView().setImageResource(R.drawable.img_without_mole);
            currentMoleIndex = -1;
        }
    }

    private void updateScoreText() {
        scoreTextView.setText("Score: " + currentScore);
    }

    private void setupClickListeners() {
        for (int i = 0; i < moles.size(); i++) {
            final int index = i;
            moles.get(i).getImageView().setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    Mole clickedMole = moles.get(index);
                    if (clickedMole.isVisible()) {
                        // hit mole add score
                        currentScore += 10;
                        updateScoreText();
                        // reshow the hit mole
                        clickedMole.setVisible(false);
                        clickedMole.getImageView().setImageResource(R.drawable.img_without_mole);

                        if (currentMoleIndex == index) {
                            currentMoleIndex = -1;
                        }

                    }
                }
            });
        }
    }

    private void endGame() {
        isGameRunning = false;
        stopMoleLoop();
        hideMole();

        if (gameTimer != null) {
            gameTimer.cancel();
        }

        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra("SCORE", currentScore);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        if (context instanceof android.app.Activity) {
            ((android.app.Activity) context).finish();
        }
    }
}