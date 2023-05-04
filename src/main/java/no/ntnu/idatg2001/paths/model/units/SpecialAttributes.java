package no.ntnu.idatg2001.paths.model.units;

public interface SpecialAttributes {

  int strength = 0;
  int perception = 0;
  int endurance = 0;
  int charisma = 0;
  int intelligence = 0;
  int agility = 0;
  int luck = 0;

  int getStrength();

  void setStrength(int strength);

  int getPerception();

  void setPerception(int perception);

  int getEndurance();

  void setEndurance(int endurance);

  int getCharisma();

  void setCharisma(int charisma);

  int getIntelligence();

  void setIntelligence(int intelligence);

  int getAgility();

  void setAgility(int agility);

  int getLuck();

  void setLuck(int luck);
}
