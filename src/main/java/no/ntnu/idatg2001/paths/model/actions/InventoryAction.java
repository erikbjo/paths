package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The InventoryAction class represents an action that can be performed by the player on an item in
 * the inventory.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
@Entity
public class InventoryAction extends Action {
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "inventory_action_item_id")
  private Item item;

  private boolean isAdditionToInventory;

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

  protected InventoryAction() {}

  /**
   * Returns the item to perform the action on.
   *
   * @return the item to perform the action on
   */
  public Item getItem() {
    return item;
  }

  /**
   * Returns true if the action is an addition to the inventory, false if it is a removal from the
   * inventory.
   *
   * @return true if the action is an addition to the inventory, false if it is a removal from the
   *     inventory
   */
  public boolean getIsAdditionToInventory() {
    return isAdditionToInventory;
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
