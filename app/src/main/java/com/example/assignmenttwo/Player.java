package com.example.assignmenttwo;

import android.graphics.drawable.Drawable;

public class Player {
    String name;
    Drawable avatar;
    int score;

    public Player(String name, Drawable avatar, int score) {
        this.name = name;
        this.avatar = avatar;
        this.score = score;
    }

    public String getPlayerName(){
        return name;
    }

    public Drawable getPlayerAvatar(){
        return avatar;
    }

    public int getPlayerScore(){
        return score;
    }
}
