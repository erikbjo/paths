package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.Link;
import no.ntnu.idatg2001.units.Attributes;
import no.ntnu.idatg2001.units.Player;
import org.junit.jupiter.api.Test;

class GoldGoalTest
{
    @Test
    void goldGoalIsFulfilled()
    {
        Attributes attributes = new Attributes()
        {
        };
        GoldGoal goldGoal = new GoldGoal(2);
        Player player = new Player("Test", 2,7,9,6,
            12,attributes);
        assertTrue(goldGoal.isFulfilled(player));
    }

    @Test
    void goldGoalIsNotFulfilled()
    {
        Attributes attributes = new Attributes()
        {
        };
        GoldGoal goldGoal = new GoldGoal(0);
        Player player = new Player("Test", 2,-2,9,6,
            12,attributes);
        assertFalse(goldGoal.isFulfilled(player));
    }
}