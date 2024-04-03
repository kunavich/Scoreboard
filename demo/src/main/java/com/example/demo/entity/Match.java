package com.example.demo.entity;

import java.util.Objects;

public class Match {

    private String homeName;
    private String awayName;

    private int homeNameScore;
    private int awayNameScore;

    public Match(String homeName, String awayName) {
        this.homeName = homeName;
        this.awayName = awayName;

        this.awayNameScore = 0;
        this.homeNameScore = 0;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Match{" +
                "homeName='" + homeName + '\'' +
                ", awayName='" + awayName + '\'' +
                ", homeNameScore=" + homeNameScore +
                ", awayNameScore=" + awayNameScore +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Match match = (Match) object;
        return homeNameScore == match.homeNameScore && awayNameScore == match.awayNameScore && homeName.equals(match.homeName) && awayName.equals(match.awayName);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), homeName, awayName, homeNameScore, awayNameScore);
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    public String getHomeName() {
        return homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public int getHomeNameScore() {
        return homeNameScore;
    }

    public void setHomeNameScore(int homeNameScore) {
        this.homeNameScore = homeNameScore;
    }

    public int getAwayNameScore() {
        return awayNameScore;
    }

    public void setAwayNameScore(int awayNameScore) {
        this.awayNameScore = awayNameScore;
    }
}