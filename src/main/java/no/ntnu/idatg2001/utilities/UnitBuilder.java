package no.ntnu.idatg2001.utilities;

/**
 * Placed in utilities for later usage
 * Not used for now

import java.util.List;
import no.ntnu.idatg2001.units.Unit;

public abstract class UnitBuilder {
  private final String name;
  private int score;
  private int gold;
  private List<String> inventory;
  private int health;
  private int mana;
  private int energy;
  private int strength;
  private int perception;
  private int endurance;
  private int charisma;
  private int intelligence;
  private int agility;
  private int luck;

  public UnitBuilder(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public UnitBuilder setGold(int gold) {
    this.gold = gold;
    return this;
  }

  public UnitBuilder setInventory(List<String> inventory) {
    this.inventory = inventory;
    return this;
  }

  public UnitBuilder setHealth(int health) {
    this.health = health;
    return this;
  }

  public UnitBuilder setMana(int mana) {
    this.mana = mana;
    return this;
  }

  public UnitBuilder setEnergy(int energy) {
    this.energy = energy;
    return this;
  }

  public UnitBuilder setStrength(int strength) {
    this.strength = strength;
    return this;
  }

  public UnitBuilder setPerception(int perception) {
    this.perception = perception;
    return this;
  }

  public UnitBuilder setEndurance(int endurance) {
    this.endurance = endurance;
    return this;
  }

  public UnitBuilder setCharisma(int charisma) {
    this.charisma = charisma;
    return this;
  }

  public UnitBuilder setIntelligence(int intelligence) {
    this.intelligence = intelligence;
    return this;
  }

  public UnitBuilder setAgility(int agility) {
    this.agility = agility;
    return this;
  }

  public UnitBuilder setLuck(int luck) {
    this.luck = luck;
    return this;
  }

  public abstract Unit build();
}

 */