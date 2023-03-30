package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.model.goals.GoldGoal;
import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoldGoalTest
{
    Attributes attributes;
    Player playerTrue;
    Player playerFalse;
    GoldGoal goldGoal;

    @BeforeEach
    void setUp(){
        attributes = new Attributes(1,1,1,1,
            1,1,1);
        goldGoal = new GoldGoal(0);
        playerTrue = new Player("Test", 2,7,9,6,
            12,attributes);
        playerFalse = new Player("Test", 2,-2,9,6,
            12,attributes);
    }
    @Test
    void goldGoalIsFulfilled()
    {
        assertTrue(goldGoal.isFulfilled(playerTrue));
    }

    @Test
    void goldGoalIsNotFulfilled()
    {
        assertFalse(goldGoal.isFulfilled(playerFalse));
    }
}