package com.example.assignmenttwo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity {

    TextView tv_playerscore;
    EditText et_playername;
    RadioGroup rg_avatar;
    int finalScore;
    Leaderboard leaderboardInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // initialize
        tv_playerscore = findViewById(R.id.tv_playerscore);
        et_playername = findViewById(R.id.et_playername);
        rg_avatar = findViewById(R.id.rg_avator);

        finalScore = getIntent().getIntExtra("SCORE", 0);
        tv_playerscore.setText("Score: " + finalScore);

        // gain leaderboard instance
        leaderboardInstance = Leaderboard.getInstance();
    }

    public void onclickSubmit(View view){
        String name = ((EditText)findViewById(R.id.et_playername)).getText().toString();
        if (!name.isEmpty()) {
            // get the chosen avatar
            String color = "Grey";
            RadioGroup rg = findViewById(R.id.rg_avator);
            int selectedId = rg.getCheckedRadioButtonId();
            if (selectedId != -1) {
                color = ((RadioButton)findViewById(selectedId)).getText().toString();
            }

            Drawable avatar = getResources().getDrawable(getAvatarResourceId(color));
            Player player = new Player(name, avatar, finalScore);
            Leaderboard.getInstance().updateLeaderboard(player);

            startActivity(new Intent(this, LeaderboardActivity.class));
            finish();
        }
    }

    private int getAvatarResourceId(String color) {
        switch (color.toLowerCase()) {
            case "blue": return R.drawable.img_blue_mole;
            case "orange": return R.drawable.img_orange_mole;
            case "green": return R.drawable.img_green_mole;
            case "purple": return R.drawable.img_purple_mole;
            case "pink": return R.drawable.img_pink_mole;
            default: return R.drawable.img_grey_mole;
        }
    }
}