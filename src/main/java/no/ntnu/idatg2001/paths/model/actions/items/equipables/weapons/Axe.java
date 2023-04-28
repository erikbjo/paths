package no.ntnu.idatg2001.paths.model.actions.items.equipables.weapons;


/**
 * The Axe class represents an axe in the game. It extends the Weapon class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
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
}