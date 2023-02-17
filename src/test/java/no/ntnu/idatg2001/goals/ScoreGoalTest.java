package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.units.Attributes;
import no.ntnu.idatg2001.units.Player;
import org.junit.jupiter.api.Test;

class ScoreGoalTest
{

    @Test
    void isScoreGoalFulfilled()
    {
        Attributes attributes = new Attributes()
        {
        };
        ScoreGoal scoreGoal = new ScoreGoal(0);
        Player player = new Player("Test", 9,7,9,6,
            12,attributes);
        assertTrue(scoreGoal.isFulfilled(player));
    }

    @Test
    void scoreGoalIsNotFulfilled()
    {
        Attributes attributes = new Attributes()
        {
        };
        ScoreGoal scoreGoal = new ScoreGoal(0);
        Player player = new Player("Test", -6,12,9,6,
            12,attributes);
        assertFalse(scoreGoal.isFulfilled(player));
    }
}