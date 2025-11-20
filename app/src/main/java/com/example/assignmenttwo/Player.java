package com.example.assignmenttwo;

public class Player {
    String name;
    int avatarResId; // 改为存储资源ID
    int score;

    public Player(String name, int avatarResId, int score) {
        this.name = name;
        this.avatarResId = avatarResId;
        this.score = score;
    }

    public String getPlayerName(){
        return name;
    }

    public int getAvatarResId(){
        return avatarResId;
    }

    public int getPlayerScore(){
        return score;
    }
}