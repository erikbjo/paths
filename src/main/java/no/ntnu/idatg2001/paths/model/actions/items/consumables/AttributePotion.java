package no.ntnu.idatg2001.paths.model.actions.items.consumables;

import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The AttributePotion class represents a potion that can be used by the player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class AttributePotion extends Potion implements Consumable {
  private Attributes attributes;

  /**
   * Constructor for the AttributePotion class.
   *
   * @param name the name of the potion
   * @param itemScore the score of the potion
   * @param goldValue the gold value of the potion
   * @param attributes the attributes the potion gives
   */
  public AttributePotion(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue);
    this.attributes = attributes;
  }

  /**
   * Use the potion. Merges the attributes of the potion with the players attributes.
   *
   * @param player the player that uses the potion
   */
  @Override
  public void use(Player player) {
    player.getAttributes().addAttributes(attributes);
  }
}
