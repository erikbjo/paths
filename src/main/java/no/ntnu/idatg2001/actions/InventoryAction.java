package no.ntnu.idatg2001.actions;

import no.ntnu.idatg2001.units.Player;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public class InventoryAction implements Action {
  private String item;
  private boolean isAdditionToInventory;

  public InventoryAction(String item, boolean isAdditionToInventory) {
    this.item = item;
    this.isAdditionToInventory = isAdditionToInventory;
  }

  @Override
  public void execute(Player player) {
    if (isAdditionToInventory) {
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
