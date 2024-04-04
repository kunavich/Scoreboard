package com.example.demo;

import com.example.demo.comporator.SortByScore;
import com.example.demo.entity.Match;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private List<Match> board = new ArrayList();

    public void startNewMatch(String homeName, String awayName) {
        board.add(new Match(homeName, awayName));
    }

    public void updateMatch(Match match, int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore < 0) {
            throw new RuntimeException("Home Score is Negative");
        }
        if (awayTeamScore < 0) {
            throw new RuntimeException("Away Score is Negative");
        }
        if (! board.contains(match) ) {
            throw new RuntimeException("Match not Found");
        }
        int index = board.indexOf(match);
        board.get(index).setHomeNameScore(homeTeamScore);
        board.get(index).setAwayNameScore(awayTeamScore);
    }

    public void finishMatch(Match match) {
        if (! board.contains(match) ) {
            throw new RuntimeException("Match not Found");
        }
        board.remove(match);
    }

    public List<Match> getSummary() {
        board.sort(new SortByScore());
        return board;
    }
}