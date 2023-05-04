package no.ntnu.idatg2001.paths.model.items.equipables.weapons;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.items.equipables.Equipable;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Weapon class represents a weapon in the game. It extends the Item class and implements the
 * equipable interface.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Weapon extends Equipable {

  @Column
  private int damage;

  /** Constructor for the Weapon class. */
  protected Weapon(String name, int itemScore, int goldValue, int damage) {
    super(name, itemScore, goldValue);
    this.damage = damage;
  }

  protected Weapon() {}

  /**
   * Gets the damage of the weapon.
   *
   * @return the damage of the weapon
   */
  protected int getDamage() {
    return damage;
  }

  /**
   * Sets the damage of the weapon.
   *
   * @param damage the damage of the weapon
   */
  protected void setDamage(int damage) {
    this.damage = damage;
  }
}
