package no.ntnu.idatg2001.units;

public class Enemy extends Unit {

  public Enemy(
      String name, int score, int gold, int health, int mana, int energy, Attributes attributes) {
    super.setName(name);
    super.setScore(score);
    super.setGold(gold);
    super.setHealth(health);
    super.setMana(mana);
    super.setEnergy(energy);
    super.setAttributes(attributes);
  }

  @Override
  public void dialog() {}
}
