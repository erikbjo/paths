package no.ntnu.idatg2001.paths.model.items.equipables.armor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Attributes;

/**
 * The Chest class represents a chest in the game. It extends the Armor class.
 *
 * @see Armor
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity(name = "Chest")
@DiscriminatorValue("Chest")
public class Chest extends Armor {

  /**
   * Constructor for the Chest class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   * @param attributes the attributes of the item
   */
  public Chest(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue, attributes);
  }

  /** Used by DB */
  protected Chest() {}
}
