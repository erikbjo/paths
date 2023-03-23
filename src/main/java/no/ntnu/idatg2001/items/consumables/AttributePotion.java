package no.ntnu.idatg2001.items.consumables;

import no.ntnu.idatg2001.units.SpecialAttributes;
import no.ntnu.idatg2001.units.Player;

public class AttributePotion extends Potion implements SpecialAttributes, Consumable {
  private int strength;
  private int perception;
  private int endurance;
  private int charisma;
  private int intelligence;
  private int agility;
  private int luck;

  protected AttributePotion(String name, int itemScore, int goldValue)
  {
    super(name, itemScore, goldValue);
  }

  /**
  public AttributePotion(int strength, int perception, int endurance,
                         int charisma, int intelligence, int agility, int luck) {
    this.strength = strength;
    this.perception = perception;
    this.endurance = endurance;
    this.charisma = charisma;
    this.intelligence = intelligence;
    this.agility = agility;
    this.luck = luck;
  }*/

  @Override
  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  @Override
  public int getPerception() {
    return perception;
  }

  public void setPerception(int perception) {
    this.perception = perception;
  }

  @Override
  public int getEndurance() {
    return endurance;
  }

  public void setEndurance(int endurance) {
    this.endurance = endurance;
  }

  @Override
  public int getCharisma() {
    return charisma;
  }

  public void setCharisma(int charisma) {
    this.charisma = charisma;
  }

  @Override
  public int getIntelligence() {
    return intelligence;
  }

  public void setIntelligence(int intelligence) {
    this.intelligence = intelligence;
  }

  @Override
  public int getAgility() {
    return agility;
  }

  public void setAgility(int agility) {
    this.agility = agility;
  }

  @Override
  public int getLuck() {
    return luck;
  }

  public void setLuck(int luck) {
    this.luck = luck;
  }



  @Override
  public void use(Player player) {
    player.getAttributes().addAttributes(this);
  }
}
