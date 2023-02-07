package no.ntnu.idatg2001.actions;

import no.ntnu.idatg2001.units.Player;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public class InventoryAction implements Action {
  private String item;
  private boolean positiveAction;

  public InventoryAction(String item, boolean positiveAction) {
    this.item = item;
    this.positiveAction = positiveAction;
  }

  @Override
  public void execute(Player player) {
    if (positiveAction) {
      player.addToInventory(item);
    } else {
      if (player.getInventory().contains(item)) {
        player.removeFromInventory(item);
      } else {
        System.err.println(item + "is not in player inventory");
      }
    }
  }
}
