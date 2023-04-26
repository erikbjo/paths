package no.ntnu.idatg2001.model.actions.items.equipables.weapons;


/**
 * The Mace class represents a mace in the game. It extends the Weapon class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
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
}
