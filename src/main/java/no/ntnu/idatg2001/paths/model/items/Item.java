package no.ntnu.idatg2001.paths.model.items;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.units.Player;

import java.io.Serializable;

/**
 * The Item class represents an item in the game.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  protected Long id;

  @ManyToOne
  @JoinColumn(name = "player_id")
  protected Player player;

  @Column
  protected String name;

  @Column
  protected int itemScore;

  @Column
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

  /** Used by DB */
  protected Item() {
  }
}
