package no.ntnu.idatg2001.paths.model.actions.items.consumables;

import no.ntnu.idatg2001.paths.model.actions.items.Item;

/**
 * The Potion class represents a potion in the game. It extends the Item class and implements the
 * consumable interface.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public abstract class Potion extends Item implements Consumable {

  /**
   * Constructor for the Potion class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   */
  protected Potion(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }
}
