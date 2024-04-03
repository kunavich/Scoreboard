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
    public static final String MADRID = "Madrid";
    public static final String BARCELONA = "Barcelona";
    Scoreboard scoreboard;
    List<Match> finaleList;

    @Before
    public void init(){
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
        Assert.assertEquals(finaleList,scoreboard.getSummery());
    }

    @Test
    public void updateMatch_whenGivenTwoAbsoluteNumbers() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(),match.getAwayName());
        //when
        scoreboard.updateMatch(match,HOME_SCORE,AWAY_SCORE);

        match = setScores(match);
        finaleList.add(match);

        //then
        Assert.assertEquals(finaleList,scoreboard.getSummery());
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
        //given
        Match firstMatch = new Match(HOME, AWAY);
        scoreboard.startNewMatch(firstMatch.getHomeName(),firstMatch.getAwayName());
        Match secondMatch = new Match(MADRID, BARCELONA);
        scoreboard.startNewMatch(secondMatch.getHomeName(),secondMatch.getAwayName());
        finaleList.add(firstMatch);
        //when
        scoreboard.finishMatch(secondMatch);
        //then
        Assert.assertEquals(finaleList,scoreboard.getSummery());
    }

    @Test
    public void getSummery_whenListNeedToBeSorted() {
        //given
        Match firstMatch = new Match(HOME, AWAY);
        scoreboard.startNewMatch(firstMatch.getHomeName(),firstMatch.getAwayName());
        Match secondMatch = new Match(MADRID, BARCELONA);
        scoreboard.startNewMatch(secondMatch.getHomeName(),secondMatch.getAwayName());
        //when
        scoreboard.updateMatch(secondMatch,HOME_SCORE,AWAY_SCORE);

        secondMatch=setScores(secondMatch);
        finaleList.add(secondMatch);
        finaleList.add(firstMatch);
        //then
        Assert.assertEquals(finaleList,scoreboard.getSummery());
    }

    @Test
    public void getSummery_whenThereAreTwoSameSums() {
        //given
        Match firstMatch = new Match(HOME, AWAY);
        scoreboard.startNewMatch(firstMatch.getHomeName(),firstMatch.getAwayName());
        Match secondMatch = new Match(MADRID, BARCELONA);
        scoreboard.startNewMatch(secondMatch.getHomeName(),secondMatch.getAwayName());
        //when
        scoreboard.updateMatch(firstMatch,HOME_SCORE,AWAY_SCORE);
        scoreboard.updateMatch(secondMatch,HOME_SCORE,AWAY_SCORE);

        firstMatch=setScores(firstMatch);
        secondMatch=setScores(secondMatch);

        finaleList.add(secondMatch);
        finaleList.add(firstMatch);

        //then
        Assert.assertEquals(finaleList,scoreboard.getSummery());
    }

    private Match setScores(Match match){
        //TODO
        match.setHomeNameScore(HOME_SCORE);
        match.setAwayNameScore(AWAY_SCORE);
        return match;
    }

}