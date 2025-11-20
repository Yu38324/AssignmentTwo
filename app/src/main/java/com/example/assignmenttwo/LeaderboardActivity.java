package com.example.assignmenttwo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    private Leaderboard leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // Get leaderboard instance
        leaderboard = Leaderboard.getInstance();

        // Display leaderboard data
        displayLeaderboard();
    }

    private void displayLeaderboard() {
        ArrayList<Player> players = leaderboard.getLeaderboard();

        // Display top 5 players (or less if not enough players)
        for (int i = 0; i < 5; i++) {
            int avatarId = getResources().getIdentifier("iv_leaderboard_avatar" + (i + 1), "id", getPackageName());
            int nameId = getResources().getIdentifier("tv_leaderboard_name" + (i + 1), "id", getPackageName());
            int scoreId = getResources().getIdentifier("tv_leaderboard_score" + (i + 1), "id", getPackageName());

            ImageView avatar = findViewById(avatarId);
            TextView name = findViewById(nameId);
            TextView score = findViewById(scoreId);

            if (i < players.size()) {
                // Set player data
                Player player = players.get(i);
                avatar.setImageResource(player.getAvatarResId());
                name.setText(player.getPlayerName());
                score.setText(String.valueOf(player.getPlayerScore()));
            } else {
                // Clear unused slots
                avatar.setImageDrawable(null);
                name.setText("");
                score.setText("");
            }
        }
    }
}