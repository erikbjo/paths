package no.ntnu.idatg2001.paths.model.actions;

import no.ntnu.idatg2001.paths.model.actions.items.Item;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The InventoryAction class represents an action that can be performed by the player on an item in
 * the inventory.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public class InventoryAction implements Action {
  private final Item item;
  private final boolean isAdditionToInventory;

  /**
   * Constructor for the InventoryAction class.
   *
   * @param item the item to perform the action on
   * @param isAdditionToInventory true if the action is an addition to the inventory, false if it is
   *     a removal from the inventory
   */
  public InventoryAction(Item item, boolean isAdditionToInventory) {
    this.item = item;
    this.isAdditionToInventory = isAdditionToInventory;
  }

  /**
   * Executes the action.
   *
   * @param player the player who is performing the action
   */
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
