package no.ntnu.idatg2001.model.actions;

import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public class InventoryAction implements Action {
  private final Item item;
  private final boolean isAdditionToInventory;

  public InventoryAction(Item item, boolean isAdditionToInventory) {
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
