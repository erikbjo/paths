package no.ntnu.idatg2001.paths.model.actions.items.equipables.weapons;

import no.ntnu.idatg2001.paths.model.actions.items.Item;
import no.ntnu.idatg2001.paths.model.actions.items.equipables.Equipable;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Weapon class represents a weapon in the game. It extends the Item class and implements the
 * equipable interface.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public abstract class Weapon extends Item implements Equipable {

  private int damage;

  /** Constructor for the Weapon class. */
  protected Weapon(String name, int itemScore, int goldValue, int damage) {
    super(name, itemScore, goldValue);
    this.damage = damage;
  }

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

  /**
   * Equips the item to the player
   *
   * @param player the player who is equipping the item
   */
  @Override
  public void equip(Player player) {
    player.getInventory().remove(this);
    player.getEquippedItems().add(this);
  }

  /**
   * Uneqiups the weapon from the player.
   *
   * @param player the player who is unequipping the weapon
   */
  @Override
  public void unEquip(Player player) {
    player.getEquippedItems().remove(this);
    player.getInventory().add(this);
  }
}
