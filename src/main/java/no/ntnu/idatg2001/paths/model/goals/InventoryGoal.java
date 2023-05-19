package no.ntnu.idatg2001.paths.model.goals;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * InventoryGoal is a subclass of Goal. It is fulfilled when the player has certain items in their
 * inventory.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
@Entity
public class InventoryGoal extends Goal {
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "inventory_goal_id")
  private List<Item> mandatoryItems;

  /**
   * Constructor for the InventoryGoal class.
   *
   * @param mandatoryItems A list of items that the player must have in their inventory.
   */
  public InventoryGoal(List<Item> mandatoryItems) {
    this.mandatoryItems = new ArrayList<>();
    this.mandatoryItems.addAll(mandatoryItems);
  }

  /** Constructor for the InventoryGoal class. Used by JPA. */
  protected InventoryGoal() {}

  /**
   * Adds an item to the list of items that the player must have in their inventory.
   *
   * @param item The item to add.
   */
  public void addItemToInventoryGoal(Item item) {
    mandatoryItems.add(item);
  }

  /**
   * Removes an item from the list of items that the player must have in their inventory.
   *
   * @param item The item to remove.
   */
  public void removeItemFromInventoryGoal(Item item) {
    if (mandatoryItems.contains(item)) {
      mandatoryItems.remove(item);
    } else {
      throw new IllegalArgumentException("The item is not in the list.");
    }
  }

  /**
   * Returns the list of items that the player must have in their inventory.
   *
   * @return The list of items.
   */
  public List<Item> getMandatoryItems() {
    return mandatoryItems;
  }

  /**
   * Sets the list of items that the player must have in their inventory.
   *
   * @param mandatoryItems The list of items.
   */
  public void setMandatoryItems(List<Item> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isFulfilled(Player player) {
    if (mandatoryItems.isEmpty()) {
      return true;
    }
    for (Item nthMandatoryItem : mandatoryItems) {
      if (!player.getInventory().contains(nthMandatoryItem)) {
        return false;
      }
    }
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public Object getGoalValue() {
    return getMandatoryItems();
  }

  /** {@inheritDoc} */
  @Override
  public void setGoalValue(Object goalValue) {
    if (!(goalValue instanceof List)) {
      throw new IllegalArgumentException("The goal value must be a list of items.");
    }
    if (!((List<?>) goalValue).isEmpty() && !(((List<?>) goalValue).get(0) instanceof Item)) {
      throw new IllegalArgumentException("The goal value must be a list of items.");
    }

    setMandatoryItems((List<Item>) goalValue);
  }
}
