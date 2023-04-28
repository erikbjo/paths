package no.ntnu.idatg2001.paths.model.actions.items.equipables.weapons;


/**
 * The Sword class represents a sword in the game. It extends the Weapon class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class Sword extends Weapon {

  /**
   * Constructor for the Sword class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   */
  public Sword(String name, int itemScore, int goldValue, int damage) {
    super(name, itemScore, goldValue, damage);
  }
}
