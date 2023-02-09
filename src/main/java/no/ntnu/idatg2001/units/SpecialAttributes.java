package no.ntnu.idatg2001.units;

public interface SpecialAttributes {
  /**
  int strength = 0;
  int perception = 0;
  int endurance = 0;
  int charisma = 0;
  int intelligence = 0;
  int agility = 0;
  int luck = 0;
   */

  int getStrength();
  int getPerception();
  int getEndurance();
  int getCharisma();
  int getIntelligence();
  int getAgility();
  int getLuck();

  void setStrength(int strength);
  void setPerception(int perception);
  void setEndurance(int endurance);
  void setCharisma(int charisma);
  void setIntelligence(int intelligence);
  void setAgility(int agility);
  void setLuck(int luck);
}
