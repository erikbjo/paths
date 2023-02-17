package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.units.Attributes;
import no.ntnu.idatg2001.units.Player;
import org.junit.jupiter.api.Test;

class HealthGoalTest
{

    @Test
    void isHealthGoalFulfilled()
    {

        Attributes attributes = new Attributes()
        {
        };
        HealthGoal healthGoal = new HealthGoal(0);
        Player player = new Player("Test", 9,7,9,6,
            12,attributes);
        assertTrue(healthGoal.isFulfilled(player));
    }

    @Test
    void healthGoalIsNotFulfilled()
    {
        Attributes attributes = new Attributes()
        {
        };
        HealthGoal healthGoal = new HealthGoal(0);
        Player player = new Player("Test", 2,10,-10,6,
            12,attributes);
        assertFalse(healthGoal.isFulfilled(player));
    }
}