package no.ntnu.idatg2001.paths.model.items.equipables;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.units.Player;

import java.io.Serializable;

/**
 * The Equipable interface represents an item that can be equipped by the player.
 *
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
  public Equipable(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }

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
