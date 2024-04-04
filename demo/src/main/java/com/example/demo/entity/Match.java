package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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

    @Override
    public java.lang.String toString() {
        return "Match{" +
                "homeName='" + homeName + '\'' +
                ", awayName='" + awayName + '\'' +
                ", homeNameScore=" + homeNameScore +
                ", awayNameScore=" + awayNameScore +
                '}';
    }

    public boolean equals(Object object) {
        Match match = (Match) object;
        return homeNameScore == match.homeNameScore && awayNameScore == match.awayNameScore && homeName.equals(match.homeName) && awayName.equals(match.awayName);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), homeName, awayName, homeNameScore, awayNameScore);
    }
}