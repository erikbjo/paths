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

  /**
   * Constructor for the InventoryGoal class.
   *
   * @param mandatoryItems A list of items that the player must have in their inventory.
   */
  public InventoryGoal(List<Item> mandatoryItems) {
    this.mandatoryItems = new ArrayList<>();
    this.mandatoryItems.addAll(mandatoryItems);
  }

  /**
   * Adds an item to the list of items that the player must have in their inventory.
   *
   * @param item The item to add.
   */
  public void addItemToInventoryGoal(Item item){
    mandatoryItems.add(item);
  }

  /**
   * Removes an item from the list of items that the player must have in their inventory.
   *
   * @param item The item to remove.
   */
  public void removeItemFromInventoryGoal(Item item){
    if (mandatoryItems.contains(item)) {
      mandatoryItems.remove(item);
    } else {
      throw new IllegalArgumentException("The item is not in the list.");
    }
  }

  /**
   * Checks if the player has all the items in the list.
   *
   * @param player The player to check.
   * @return A boolean value.
   */
  @Override
  public boolean isFulfilled(Player player) {
    if (mandatoryItems.isEmpty()){
      return true;
    }
    for (Item nthMandatoryItem : mandatoryItems) {
      if (!player.getInventory().contains(nthMandatoryItem)) {
        return false;
      }
    }
    return true;
  }
}
