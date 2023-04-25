package no.ntnu.idatg2001.model.actions.items.equipables.armor;

import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.actions.items.equipables.Equipable;
import no.ntnu.idatg2001.model.units.Attributes;

/**
 * The Armor class represents an armor in the game. It extends the Item class and implements the
 * equipable interface.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public abstract class Armor extends Item implements Equipable {

  final Attributes attributes;

  /**
   * Constructor for the Armor class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   * @param attributes the attributes of the item
   */
  protected Armor(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue);
    this.attributes = attributes;
  }
}
