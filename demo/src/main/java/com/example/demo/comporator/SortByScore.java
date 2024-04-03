package com.example.demo.comporator;

import com.example.demo.entity.Match;

import java.util.Comparator;

public class SortByScore implements Comparator<Match> {
    public int compare(Match a, Match b) {
        return a.getAwayNameScore() + a.getHomeNameScore() - b.getHomeNameScore() - b.getAwayNameScore();
    }
}
