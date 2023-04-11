package no.ntnu.idatg2001.model.goals;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class InventoryGoal implements Goal {
  private final List<Item> mandatoryItems;

  public InventoryGoal(List<Item> mandatoryItems) {
    this.mandatoryItems = new ArrayList<>();
    this.mandatoryItems.addAll(mandatoryItems);
  }

  @Override
  public boolean isFulfilled(Player player) {
    if (mandatoryItems.isEmpty()){
      return false;
    }
    for (Item nthMandatoryItem : mandatoryItems) {
      if (!player.getInventory().contains(nthMandatoryItem)) {
        return false;
      }
    }
    return true;
  }
}
