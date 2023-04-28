package no.ntnu.idatg2001.paths.model.actions.items.equipables.armor;

import no.ntnu.idatg2001.paths.model.units.Attributes;

/**
 * The Belt class represents a belt in the game. It extends the Armor class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class Belt extends Armor {

  /**
   * Constructor for the Belt class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   * @param attributes the attributes of the item
   */
  public Belt(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue, attributes);
  }
}
