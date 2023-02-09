package no.ntnu.idatg2001.goals;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class InventoryGoal implements Goal {
    private List<String> mandatoryItems;

    public InventoryGoal(List<String> mandatoryItems){
        this.mandatoryItems = new ArrayList<>();
    }

    /**
    @Override
    public boolean isFulfilled(Player player)
    {
        int i = 0;
        String inventory = player.getInventory().get(i);
        mandatoryItems.add(inventory);
        for (i; mandatoryItems.size(); i++)
        {
            return true;
        }
    }*/
}
