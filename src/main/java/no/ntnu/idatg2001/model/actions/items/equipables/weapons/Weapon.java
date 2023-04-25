package no.ntnu.idatg2001.model.actions.items.equipables.weapons;

import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.actions.items.equipables.Equipable;

/**
 * The Weapon class represents a weapon in the game. It extends the Item class and implements the
 * equipable interface.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public abstract class Weapon extends Item implements Equipable {

  /** Constructor for the Weapon class. */
  public Weapon(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }
}
