package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import no.ntnu.idatg2001.Link;
import no.ntnu.idatg2001.units.Attributes;
import no.ntnu.idatg2001.units.Player;
import org.junit.jupiter.api.Test;

class InventoryGoalTest
{

    @Test
    void isInventoryGoalFulfilled()
    {
        Attributes attributes = new Attributes(1,1,1,1,
            1,1,1);
        List<String> items = new ArrayList<>();
        items.add("Sword");
        items.add("Dagger");
        items.add("Wand");
        InventoryGoal inventoryGoal = new InventoryGoal(items);
        Player player = new Player("Test", 9,7,9,6,
            12,attributes);
        player.getInventory().addAll(items);
        assertTrue(inventoryGoal.isFulfilled(player));
    }

    @Test
    void inventoryGoalIsNotFulfilled()
    {
        Attributes attributes = new Attributes(1,1,1,1,
            1,1,1);
        List<String> items = new ArrayList<>();
        InventoryGoal inventoryGoal = new InventoryGoal(items);
        Player player = new Player("Test", 9,7,9,6,
            12,attributes);
        player.getInventory().addAll(items);
        assertFalse(inventoryGoal.isFulfilled(player));
    }
}