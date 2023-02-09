package no.ntnu.idatg2001.items.consumables;


import no.ntnu.idatg2001.units.Player;

public class BasePotion extends Potion {

  private int health;
  private int mana;
  private int energy;

  public BasePotion(int health, int mana, int energy) {
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
