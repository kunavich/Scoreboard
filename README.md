# Scoreboard library
It's a sports data project, implementing a new Live Football World Cup Scoreboard library that shows all the ongoing matches and their scores.

The scoreboard supports the following operations:

1. Start a new match, assuming the initial score is 0 – 0, and add it to the scoreboard. 
This should capture the following parameters:

    a. Home team
   
    b. Away team
3. Update score. This should receive a pair of absolute scores: home team score and away team score.
4. Finish match currently in progress. This removes a match from the scoreboard.
5. Get a summary of matches in progress ordered by their total score. 
The matches with the same total score will be returned ordered by the most recently started match on the scoreboard.
