package no.ntnu.idatg2001.paths.model.items.equipables.weapons;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * The Axe class represents an axe in the game. It extends the Weapon class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity(name = "Axe")
@DiscriminatorValue("Axe")
public class Axe extends Weapon {

  /**
   * Constructor for the Axe class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   */
  public Axe(String name, int itemScore, int goldValue, int damage) {
    super(name, itemScore, goldValue, damage);
  }

  protected Axe() {}
}
