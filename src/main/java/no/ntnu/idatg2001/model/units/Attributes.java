package no.ntnu.idatg2001.model.units;

public class Attributes implements SpecialAttributes {
  // S.P.E.C.I.A.L stats
  private int strength;
  private int perception;
  private int endurance;
  private int charisma;
  private int intelligence;
  private int agility;
  private int luck;

  public Attributes(
      int strength,
      int perception,
      int endurance,
      int charisma,
      int intelligence,
      int agility,
      int luck) {
    this.strength = strength;
    this.perception = perception;
    this.endurance = endurance;
    this.charisma = charisma;
    this.intelligence = intelligence;
    this.agility = agility;
    this.luck = luck;
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getPerception() {
    return perception;
  }

  public void setPerception(int perception) {
    this.perception = perception;
  }

  public int getEndurance() {
    return endurance;
  }

  public void setEndurance(int endurance) {
    this.endurance = endurance;
  }

  public int getCharisma() {
    return charisma;
  }

  public void setCharisma(int charisma) {
    this.charisma = charisma;
  }

  public int getIntelligence() {
    return intelligence;
  }

  public void setIntelligence(int intelligence) {
    this.intelligence = intelligence;
  }

  public int getAgility() {
    return agility;
  }

  public void setAgility(int agility) {
    this.agility = agility;
  }

  public int getLuck() {
    return luck;
  }

  public void setLuck(int luck) {
    this.luck = luck;
  }

  public void addAttributes(SpecialAttributes attributes) {
    this.setStrength(this.getStrength() + attributes.getStrength());
    this.setPerception(this.getPerception() + attributes.getPerception());
    this.setEndurance(this.getEndurance() + attributes.getEndurance());
    this.setCharisma(this.getCharisma() + attributes.getCharisma());
    this.setIntelligence(this.getIntelligence() + attributes.getIntelligence());
    this.setAgility(this.getAgility() + attributes.getAgility());
    this.setLuck(this.getLuck() + attributes.getLuck());
  }

  public void subtractAttributes(SpecialAttributes attributes) {
    this.setStrength(this.getStrength() - attributes.getStrength());
    this.setPerception(this.getPerception() - attributes.getPerception());
    this.setEndurance(this.getEndurance() - attributes.getEndurance());
    this.setCharisma(this.getCharisma() - attributes.getCharisma());
    this.setIntelligence(this.getIntelligence() - attributes.getIntelligence());
    this.setAgility(this.getAgility() - attributes.getAgility());
    this.setLuck(this.getLuck() - attributes.getLuck());
  }
}
