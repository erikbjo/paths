package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.model.goals.ScoreGoal;
import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreGoalTest
{

    Attributes attributes;
    Player playerTrue;
    Player playerFalse;
    ScoreGoal scoreGoal;
    @BeforeEach
    void setUp(){
     attributes = new Attributes(1,1,1,1,
            1,1,1);
     playerTrue = new Player("Test", 9,7,9,6,
            12,attributes);
     playerFalse = new Player("Test", -6,12,9,6,
            12,attributes);
     scoreGoal = new ScoreGoal(0);

    }
    @Test
    void isScoreGoalFulfilled()
    {
        assertTrue(scoreGoal.isFulfilled(playerTrue));
    }

    @Test
    void scoreGoalIsNotFulfilled()
    {
        assertFalse(scoreGoal.isFulfilled(playerFalse));
    }
    @AfterEach
    void tearDown(){

    }
}