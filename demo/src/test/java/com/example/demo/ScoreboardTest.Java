package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Match;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ScoreboardTest {

    public static final String HOME = "home";
    public static final String AWAY = "away";
    public static final int HOME_SCORE = 2;
    public static final int AWAY_SCORE = 4;
    Scoreboard scoreboard;
    List<Match> finaleList;

    @Before
    private void init(){
        scoreboard = new Scoreboard();
        finaleList = new ArrayList<>();

    }

    @Test
    public void startNewMatch_whenGivenTwoNames() {
        //given
        Match match = new Match(HOME,AWAY);
        finaleList.add(match);
        //when
        scoreboard.startNewMatch(match.getHomeName(),match.getAwayName());
        //then
        Assert.assertEquals(scoreboard.getSummery(),finaleList);
    }

    @Test
    public void updateMatch_whenGivenTwoAbsoluteNumbers() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(),match.getAwayName());
        //when
        scoreboard.updateMatch(match,HOME_SCORE,AWAY_SCORE);

        match.setHomeNameScore(HOME_SCORE);
        match.setAwayNameScore(AWAY_SCORE);
        finaleList.add(match);

        //then
        Assert.assertEquals(scoreboard.getSummery(),finaleList);
    }

    @Test
    public void updateMatch_updateMatch_whenGivenNegativeHomeNumber() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(),match.getAwayName());
        //when
        RuntimeException exception = Assert.assertThrows(NullPointerException.class, () -> {
            scoreboard.updateMatch(match,-3,AWAY_SCORE);
        });
        //then
        Assert.assertEquals("Home Score is Negative", exception.getMessage());
    }

    @Test
    public void updateMatch_updateMatch_whenGivenNegativeAwayNumber() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(),match.getAwayName());
        //when
        RuntimeException exception = Assert.assertThrows(NullPointerException.class, () -> {
            scoreboard.updateMatch(match,HOME_SCORE,-3);
        });
        //then
        Assert.assertEquals("Away Score is Negative", exception.getMessage());
    }

    @Test
    public void finishMatch_whenGivenValidMatch() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(),match.getAwayName());
        //when
        scoreboard.finishMatch(match);
        //then
        Assert.assertTrue(scoreboard.getSummery().isEmpty());
    }

    @Test
    public void finishMatch_whenMultipleMatchesOnBoard() {
        //TODO
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(),match.getAwayName());
        //when
        scoreboard.finishMatch(match);
        //then
        Assert.assertTrue(scoreboard.getSummery().isEmpty());
    }

    @Test
    public void getSummery_whenListNeedToBeSorted() {
        //TODO
        //given
        Match match = new Match(HOME,AWAY);
        finaleList.add(match);
        scoreboard.startNewMatch(HOME,AWAY);
        //when

        //then
        Assert.assertEquals(scoreboard.getSummery(),finaleList);
    }

    @Test
    public void getSummery_whenThereAreTwoSameSums() {
        //TODO
        //given
        Match match = new Match(HOME,AWAY);
        finaleList.add(match);
        scoreboard.startNewMatch(HOME,AWAY);
        //when

        //then
        Assert.assertEquals(scoreboard.getSummery(),finaleList);
    }
    
}