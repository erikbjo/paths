package no.ntnu.idatg2001.paths.model.items.equipables;

import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Equipable interface represents an item that can be equipped by the player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public interface Equipable {
  int equipSlot = 0;

  /**
   * Equips the item to the player.
   *
   * @param player the player who is equipping the item
   */
  void equip(Player player);


  /**
   * Uneqiups the item from the player.
   *
   * @param player the player who is unequipping the item
   */
  void unEquip(Player player);
}
