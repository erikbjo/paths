package no.ntnu.idatg2001.model.actions.items.consumables;

import no.ntnu.idatg2001.model.units.Player;

/**
 * The Consumable interface represents an item that can be consumed by the player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public interface Consumable {

  /**
   * Uses the consumable.
   *
   * @param player the player who is using the item
   */
  void use(Player player);
}
