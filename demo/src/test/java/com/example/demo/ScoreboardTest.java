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
    public void init() {
        scoreboard = new Scoreboard();
        finaleList = new ArrayList<>();
    }

    @Test
    public void startNewMatch_whenGivenTwoNames() {
        //given
        Match match = new Match(HOME, AWAY);
        finaleList.add(match);
        //when
        scoreboard.startNewMatch(match.getHomeName(), match.getAwayName());
        //then
        Assert.assertEquals(finaleList, scoreboard.getSummery());
    }

    @Test
    public void updateMatch_whenGivenTwoAbsoluteNumbers() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(), match.getAwayName());
        //when
        scoreboard.updateMatch(match, HOME_SCORE, AWAY_SCORE);

        match = setScores(match, HOME_SCORE, AWAY_SCORE);
        finaleList.add(match);

        //then
        Assert.assertEquals(finaleList, scoreboard.getSummery());
    }

    @Test
    public void updateMatch_whenGivenNegativeHomeNumber() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(), match.getAwayName());
        //when
        RuntimeException exception = Assert.assertThrows(RuntimeException.class, () -> {
            scoreboard.updateMatch(match, -3, AWAY_SCORE);
        });
        //then
        Assert.assertEquals("Home Score is Negative", exception.getMessage());
    }

    @Test
    public void updateMatch_whenGivenNegativeAwayNumber() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(), match.getAwayName());
        //when
        RuntimeException exception = Assert.assertThrows(RuntimeException.class, () -> {
            scoreboard.updateMatch(match, HOME_SCORE, -3);
        });
        //then
        Assert.assertEquals("Away Score is Negative", exception.getMessage());
    }

    @Test
    public void updateMatch_whenGivenInvalidMatch() {
        //given
        Match match = new Match(HOME, AWAY);
        Match invalid = new Match(MADRID, BARCELONA);
        scoreboard.startNewMatch(match.getHomeName(), match.getAwayName());
        //when
        RuntimeException exception = Assert.assertThrows(RuntimeException.class, () -> {
            scoreboard.updateMatch(invalid, HOME_SCORE, AWAY_SCORE);
        });
        //then
        Assert.assertEquals("Match not Found", exception.getMessage());
    }

    @Test
    public void finishMatch_whenGivenInvalidMatch() {
        //given
        Match match = new Match(HOME, AWAY);
        Match invalid = new Match(MADRID, BARCELONA);
        scoreboard.startNewMatch(match.getHomeName(), match.getAwayName());
        //when
        RuntimeException exception = Assert.assertThrows(RuntimeException.class, () -> {
            scoreboard.finishMatch(invalid);
        });
        //then
        Assert.assertEquals("Match not Found", exception.getMessage());
    }

    @Test
    public void finishMatch_whenGivenValidMatch() {
        //given
        Match match = new Match(HOME, AWAY);
        scoreboard.startNewMatch(match.getHomeName(), match.getAwayName());
        //when
        scoreboard.finishMatch(match);
        //then
        Assert.assertTrue(scoreboard.getSummery().isEmpty());
    }

    @Test
    public void finishMatch_whenMultipleMatchesOnBoard() {
        //given
        Match firstMatch = new Match(HOME, AWAY);
        scoreboard.startNewMatch(firstMatch.getHomeName(), firstMatch.getAwayName());
        Match secondMatch = new Match(MADRID, BARCELONA);
        scoreboard.startNewMatch(secondMatch.getHomeName(), secondMatch.getAwayName());
        finaleList.add(firstMatch);
        //when
        scoreboard.finishMatch(secondMatch);
        //then
        Assert.assertEquals(finaleList, scoreboard.getSummery());
    }

    @Test
    public void getSummery_whenListNeedToBeSorted() {
        //given
        Match firstMatch = new Match(HOME, AWAY);
        scoreboard.startNewMatch(firstMatch.getHomeName(), firstMatch.getAwayName());
        Match secondMatch = new Match(MADRID, BARCELONA);
        scoreboard.startNewMatch(secondMatch.getHomeName(), secondMatch.getAwayName());
        //when
        scoreboard.updateMatch(secondMatch, HOME_SCORE, AWAY_SCORE);

        secondMatch = setScores(secondMatch, HOME_SCORE, AWAY_SCORE);
        finaleList.add(secondMatch);
        finaleList.add(firstMatch);
        //then
        Assert.assertEquals(finaleList, scoreboard.getSummery());
    }

    @Test
    public void getSummery_whenThereAreTwoSameSums() {
        //given
        Match firstMatch = new Match(HOME, AWAY);
        scoreboard.startNewMatch(firstMatch.getHomeName(), firstMatch.getAwayName());
        Match secondMatch = new Match(MADRID, BARCELONA);
        scoreboard.startNewMatch(secondMatch.getHomeName(), secondMatch.getAwayName());
        //when
        scoreboard.updateMatch(firstMatch, HOME_SCORE, AWAY_SCORE);
        scoreboard.updateMatch(secondMatch, HOME_SCORE, AWAY_SCORE);

        firstMatch = setScores(firstMatch, HOME_SCORE, AWAY_SCORE);
        secondMatch = setScores(secondMatch, HOME_SCORE, AWAY_SCORE);

        finaleList.add(secondMatch);
        finaleList.add(firstMatch);

        //then
        Assert.assertEquals(finaleList, scoreboard.getSummery());
    }

    @Test
    public void getSummery_whenThereAreManyMatches() {
        //given
        Match firstMatch = new Match("Mexico", "Canada");
        scoreboard.startNewMatch(firstMatch.getHomeName(), firstMatch.getAwayName());
        Match secondMatch = new Match("Spain", "Brazil");
        scoreboard.startNewMatch(secondMatch.getHomeName(), secondMatch.getAwayName());
        Match thirdMatch = new Match("Germany", "France");
        scoreboard.startNewMatch(thirdMatch.getHomeName(), thirdMatch.getAwayName());
        Match forthMatch = new Match("Uruguay", "Italy");
        scoreboard.startNewMatch(forthMatch.getHomeName(), forthMatch.getAwayName());
        Match fifthMatch = new Match("Argentina", "Australia");
        scoreboard.startNewMatch(fifthMatch.getHomeName(), fifthMatch.getAwayName());

        //when
        scoreboard.updateMatch(firstMatch, 0, 5);
        scoreboard.updateMatch(secondMatch, 10, 2);
        scoreboard.updateMatch(thirdMatch, 2, 2);
        scoreboard.updateMatch(forthMatch, 6, 6);
        scoreboard.updateMatch(fifthMatch, 3, 1);

        firstMatch = setScores(firstMatch, 0, 5);
        secondMatch = setScores(secondMatch, 10, 2);
        thirdMatch = setScores(thirdMatch, 2, 2);
        forthMatch = setScores(forthMatch, 6, 6);
        fifthMatch = setScores(fifthMatch, 3, 1);

        finaleList.add(forthMatch);
        finaleList.add(secondMatch);
        finaleList.add(firstMatch);
        finaleList.add(fifthMatch);
        finaleList.add(thirdMatch);

        //then
        Assert.assertEquals(finaleList, scoreboard.getSummery());
    }

    private Match setScores(Match match, int home, int away) {
        //TODO
        match.setHomeNameScore(home);
        match.setAwayNameScore(away);
        return match;
    }

}