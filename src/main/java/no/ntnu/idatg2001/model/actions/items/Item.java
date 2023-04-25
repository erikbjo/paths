package no.ntnu.idatg2001.model.actions.items;

/**
 * The Item class represents an item in the game.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public abstract class Item {
  // Information
  protected String name;
  protected int itemScore;
  protected int goldValue;

  /**
   * Constructor for the Item class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   */
  protected Item(String name, int itemScore, int goldValue) {
    this.name = name;
    this.itemScore = itemScore;
    this.goldValue = goldValue;
  }
}
