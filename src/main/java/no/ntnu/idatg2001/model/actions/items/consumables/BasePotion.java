package no.ntnu.idatg2001.model.actions.items.consumables;

import no.ntnu.idatg2001.model.units.Player;

/**
 * The BasePotion class represents a potion that can be used by the player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class BasePotion extends Potion {

  private final int energy;
  private final int health;
  private final int mana;
  private String name;

  /**
   * Constructor for the BasePotion class.
   *
   * @param name the name of the potion
   * @param itemScore the score of the potion
   * @param goldValue the gold value of the potion
   * @param health the health the potion gives
   * @param mana the mana the potion gives
   * @param energy the energy the potion gives
   */
  public BasePotion(String name, int itemScore, int goldValue, int health, int mana, int energy) {
    super(name, itemScore, goldValue);
    this.health = health;
    this.mana = mana;
    this.energy = energy;
  }
  
  /**
   * Use the potion.
   *
   * @param player the player that uses the potion
   */
  @Override
  public void use(Player player) {
    player.addHealth(health);
    player.addMana(mana);
    player.addEnergy(energy);
  }
}
