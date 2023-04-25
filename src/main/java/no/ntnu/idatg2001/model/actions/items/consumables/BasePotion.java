package no.ntnu.idatg2001.model.actions.items.consumables;

import no.ntnu.idatg2001.model.units.Player;

public class BasePotion extends Potion {

  private final int energy;
  private String name;
  private int health;
  private int mana;

  public BasePotion(String name, int itemScore, int goldValue ,int health, int mana, int energy) {
    super(name, itemScore, goldValue);
    this.health = health;
    this.mana = mana;
    this.energy = energy;
  }

  @Override
  public void use(Player player) {
    player.addHealth(health);
    player.addMana(mana);
    player.addEnergy(energy);
  }
}
