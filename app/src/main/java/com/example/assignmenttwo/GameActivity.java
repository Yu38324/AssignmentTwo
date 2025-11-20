package com.example.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private TextView tvTimerText, tvScoreText;
    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initializeViews();
        setupGameLogic();
        startGame();
    }

    private void initializeViews() {
        // Initialize timer and score display
        tvTimerText = findViewById(R.id.tv_timer_text);
        tvScoreText = findViewById(R.id.tv_score_text);
    }

    private void setupGameLogic() {
        // Get all mole hole ImageViews
        ArrayList<ImageView> moleViews = new ArrayList<>();
        int[] moleIds = {
                R.id.iv_without_mole_01, R.id.iv_without_mole_02, R.id.iv_without_mole_03,
                R.id.iv_without_mole_04, R.id.iv_without_mole_05, R.id.iv_without_mole_06,
                R.id.iv_without_mole_07, R.id.iv_without_mole_08, R.id.iv_without_mole_09
        };

        for (int id : moleIds) {
            moleViews.add(findViewById(id));
        }

        // Create game logic instance
        gameLogic = new GameLogic(this, moleViews, tvScoreText, tvTimerText);
    }

    private void startGame() {
        gameLogic.startGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}