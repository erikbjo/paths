package no.ntnu.idatg2001.units.classes;

import no.ntnu.idatg2001.units.Attributes;
import no.ntnu.idatg2001.units.Unit;

public class Hunter extends no.ntnu.idatg2001.units.Unit {

  public Hunter(String name, int score, int gold, int health, int mana, int energy, Attributes attributes) {
    super.setName(name);
    super.setScore(score);
    super.setGold(gold);
    super.setHealth(health);
    super.setMana(mana);
    super.setEnergy(energy);
    super.setAttributes(attributes);
  }
  @Override
  public void dialog() {
    System.out.println("Yo I am a hunter");
  }
}
