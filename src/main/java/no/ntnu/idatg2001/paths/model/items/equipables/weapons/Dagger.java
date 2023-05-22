package no.ntnu.idatg2001.paths.model.items.equipables.weapons;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * The Dagger class represents a dagger in the game. It extends the Weapon class.
 *
 * @see Weapon
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity(name = "Dagger")
@DiscriminatorValue("Dagger")
public class Dagger extends Weapon {

  /**
   * Constructor for the Dagger class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   */
  public Dagger(String name, int itemScore, int goldValue, int damage) {
    super(name, itemScore, goldValue, damage);
  }

  /** Used by DB */
  protected Dagger() {}
}
