package com.example.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private TextView tvTimerText, tvScoreText;
    private int score = 0;
    private CountDownTimer gameTimer;

    private static final long GAME_TIME = 30000; // 30秒游戏时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initializeViews();
        startGameTimer();
    }

    private void initializeViews() {
        // initial timer and score showing
        tvTimerText = findViewById(R.id.tv_timer_text);
        tvScoreText = findViewById(R.id.tv_score_text);

        updateScoreDisplay();
    }

    private void startGameTimer() {
        gameTimer = new CountDownTimer(GAME_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // renew timer
                long secondsRemaining = millisUntilFinished / 1000;
                tvTimerText.setText("Timer: " + secondsRemaining);
            }

            // game over and turn to playerActivity
            @Override
            public void onFinish() {
                navigateToPlayerActivity();
            }
        }.start();
    }

    private void updateScoreDisplay() {
        tvScoreText.setText("Score: " + score);
    }

    private void navigateToPlayerActivity() {
        Intent intent = new Intent(GameActivity.this, PlayerActivity.class);
        // score trans
        intent.putExtra("PLAYER_SCORE", score);

        startActivity(intent);

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (gameTimer != null) {
            gameTimer.cancel();
        }
    }
}