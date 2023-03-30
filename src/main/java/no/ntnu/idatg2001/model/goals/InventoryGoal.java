package no.ntnu.idatg2001.model.goals;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = new ArrayList<>();
  }

  @Override
  public boolean isFulfilled(Player player) {
    if (mandatoryItems.isEmpty()){
      return false;
    }
    for (String firstMandatoryItem : mandatoryItems) {
      mandatoryItems.add(firstMandatoryItem);
      if (!player.getInventory().contains(firstMandatoryItem)) {
        return false;
      }
    }
    return true;
  }
}
