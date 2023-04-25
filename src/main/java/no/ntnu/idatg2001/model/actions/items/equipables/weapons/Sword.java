package no.ntnu.idatg2001.model.actions.items.equipables.weapons;

import no.ntnu.idatg2001.model.units.Player;

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
  public Sword(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
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
}
