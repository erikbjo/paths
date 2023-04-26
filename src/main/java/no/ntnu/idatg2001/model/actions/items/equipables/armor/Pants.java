package no.ntnu.idatg2001.model.actions.items.equipables.armor;

import no.ntnu.idatg2001.model.units.Attributes;

/**
 * The Pants class represents a pants in the game. It extends the Armor class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class Pants extends Armor {

  /**
   * Constructor for the Pants class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   * @param attributes the attributes of the item
   */
  public Pants(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue, attributes);
  }
}
