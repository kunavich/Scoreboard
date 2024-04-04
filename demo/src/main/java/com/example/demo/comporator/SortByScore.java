package com.example.demo.comporator;

import com.example.demo.entity.Match;

import java.util.Comparator;

public class SortByScore implements Comparator<Match> {
    public int compare(Match a, Match b) {
        int ans = b.getHomeNameScore() + b.getAwayNameScore();
        ans = ans - a.getAwayNameScore() - a.getHomeNameScore();
        if(ans==0){
            return -1;
        }
        return ans;
    }
}
