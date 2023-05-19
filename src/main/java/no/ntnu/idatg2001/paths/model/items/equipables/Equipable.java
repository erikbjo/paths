package no.ntnu.idatg2001.paths.model.items.equipables;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Equipable class represents an item that can be equipped by the player. It is a subclass of
 * Item.
 *
 * @see Item
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Equipable extends Item {
  /**
   * Constructor for the Item class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   */
  protected Equipable(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }

  /** Used by DB */
  protected Equipable() {}

  /**
   * Equips the item to the player.
   *
   * @param player the player who is equipping the item
   */
  public void equip(Player player) {
    player.getInventory().remove(this);
    player.getEquippedItems().add(this);
  }

  /**
   * Uneqiups the item from the player.
   *
   * @param player the player who is unequipping the item
   */
  public void unEquip(Player player) {
    player.getEquippedItems().remove(this);
    player.getInventory().add(this);
  }
}
