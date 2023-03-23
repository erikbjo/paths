package no.ntnu.idatg2001.goals;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.units.Player;

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
    for (String firstMandatoryItem : mandatoryItems) {
      if (!player.getInventory().contains(firstMandatoryItem)) {
        return false;
      }
    }
    return true;
  }
}
