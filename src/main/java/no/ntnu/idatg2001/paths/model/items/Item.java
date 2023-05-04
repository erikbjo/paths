package no.ntnu.idatg2001.paths.model.items;

import jakarta.persistence.*;

/**
 * The Item class represents an item in the game.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
  // Information
  protected String name;
  protected int itemScore;
  protected int goldValue;

  @Id
  @GeneratedValue
  private Long id;

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

  /** Used by DB */
  protected Item() {
  }
}
