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
        Player player = new Player("Emil", 2,7,9,6,
            12,attributes);
        assertTrue(goldGoal.isFulfilled(player));
    }

    @Test
    void goldGoalIsNotFulfilled()
    {
        /**
        Exception thrown = assertThrows(Exception.class, () -> {
            Attributes attributes = new Attributes()
            {
            };
            GoldGoal goldGoal = new GoldGoal(-1);
            Player player = new Player("Emil", 2,-1,9,6,
                12,attributes);
            goldGoal.isFulfilled(player);
        }, "The expected error was thrown");
        assertEquals(-1,-1, thrown.getMessage());
         */

        Attributes attributes = new Attributes()
        {
        };
        GoldGoal goldGoal = new GoldGoal(0);
        Player player = new Player("Emil", 2,-2,9,6,
            12,attributes);
        assertFalse(goldGoal.isFulfilled(player));

    }
}