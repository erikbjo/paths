package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.model.goals.HealthGoal;
import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HealthGoalTest
{

    Attributes attributes;
    Player playerTrue;
    Player playerFalse;
    HealthGoal healthGoal;
    @BeforeEach
    void setUp(){
        attributes = new Attributes(1,1,1,1,
            1,1,1);
        healthGoal = new HealthGoal(0);
        playerTrue = new Player("Test", 9,7,9,6,
            12,attributes);
        playerFalse = new Player("Test", 2,10,-10,6,
            12,attributes);
    }
    @Test
    void isHealthGoalFulfilled()
    {
        assertTrue(healthGoal.isFulfilled(playerTrue));
    }

    @Test
    void healthGoalIsNotFulfilled()
    {
        assertFalse(healthGoal.isFulfilled(playerFalse));
    }
}