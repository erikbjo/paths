package no.ntnu.idatg2001.model.actions.items.equipables.armor;

import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;

/**
 * The Chest class represents a chest in the game. It extends the Armor class.
 *
 * @author Erik Bjørnsen
 */
public class Chest extends Armor {
  
  /**
   * Constructor for the Chest class.
   *
   * @param name the name of the item
   * @param itemScore the score of the item
   * @param goldValue the gold value of the item
   * @param attributes the attributes of the item
   */
  public Chest(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue, attributes);
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
