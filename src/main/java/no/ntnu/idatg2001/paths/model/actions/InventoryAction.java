package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The InventoryAction class represents an action that can be performed by the player on an item in
 * the inventory.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.04.19
 */
@Entity
public class InventoryAction extends Action {
  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne
  @JoinColumn(name = "link_id")
  private Link link;

  @Column(name = "is_addition_to_inventory")
  private boolean isAdditionToInventory;

  /**
   * Constructor for the InventoryAction class.
   *
   * @param item the item to perform the action on
   * @param isAdditionToInventory true if the action is an addition to the inventory, false if it is
   *     a removal from the inventory
   * @throws IllegalArgumentException if the item is null
   */
  public InventoryAction(Item item, boolean isAdditionToInventory) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    setItem(item);
    setIsAdditionToInventory(isAdditionToInventory);
  }

  /** Default constructor for the InventoryAction class. This is required by JPA. */
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
   * Sets the item to perform the action on.
   *
   * @param item the item to perform the action on
   * @throws IllegalArgumentException if the item is null
   */
  public void setItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
    this.item = item;
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
   * Sets whether the action is an addition to the inventory or a removal from the inventory.
   *
   * @param isAdditionToInventory true if the action is an addition to the inventory, false if it is
   *     a removal from the inventory
   */
  public void setIsAdditionToInventory(boolean isAdditionToInventory) {
    this.isAdditionToInventory = isAdditionToInventory;
  }

  /**
   * {@inheritDoc}
   *
   * @throws IllegalArgumentException if the item is not in the inventory
   */
  @Override
  public void execute(Player player) {
    if (isAdditionToInventory) {
      player.addToInventory(item);
    } else {
      if (player.getInventory().contains(item)) {
        player.removeFromInventory(item);
      } else {
        throw new IllegalArgumentException("Item not in inventory");
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public Object getActionValue() {
    return getItem();
  }

  /** {@inheritDoc} */
  @Override
  public void setActionValue(Object actionValue) {
    if (!(actionValue instanceof Item)) {
      throw new IllegalArgumentException("Action value must be an Item");
    }

    setItem((Item) actionValue);
  }

  /** {@inheritDoc} */
  @Override
  public Boolean getActionIsPositive() {
    return getIsAdditionToInventory();
  }
}
