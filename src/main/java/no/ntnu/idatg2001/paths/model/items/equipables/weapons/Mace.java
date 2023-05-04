package no.ntnu.idatg2001.paths.model.items.equipables.weapons;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * The Mace class represents a mace in the game. It extends the Weapon class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity(name = "Mace")
@DiscriminatorValue("Mace")
public class Mace extends Weapon {

  /**
   * Constructor for the Mace class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   */
  public Mace(String name, int itemScore, int goldValue, int damage) {
    super(name, itemScore, goldValue, damage);
  }

  protected Mace() {}
}
