package com.example.assignmenttwo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard {
    private static Leaderboard leaderboardInstance;
    private ArrayList<Player> leaderboard;
    private static final int MAX_LEADERBOARD_SIZE = 10;

    private Leaderboard(){
        leaderboard = new ArrayList<>();
    }

    public static Leaderboard getInstance() {
        if (leaderboardInstance == null) {
            leaderboardInstance = new Leaderboard();
        }
        return leaderboardInstance;
    }

    public void updateLeaderboard(Player newPlayer){
        leaderboard.add(newPlayer);

        Collections.sort(leaderboard, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getPlayerScore(), p1.getPlayerScore());
            }
        });

        if (leaderboard.size() > MAX_LEADERBOARD_SIZE) {
            leaderboard = new ArrayList<>(leaderboard.subList(0, MAX_LEADERBOARD_SIZE));
        }
    }

    public ArrayList<Player> getLeaderboard(){
        return new ArrayList<>(leaderboard);
    }
}