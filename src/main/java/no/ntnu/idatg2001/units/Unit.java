package no.ntnu.idatg2001.units;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public abstract class Unit {

  private HashSet<Attributes> standardAttributes;
  // Information
  protected String name;
  private int score;
  private int gold;
  private List<String> inventory;

  // Standard stats
  private int health;
  private int mana;
  private int energy;

  private Attributes attributes;

  public abstract void dialog();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public List<String> getInventory() {
    return inventory;
  }

  public void setInventory(List<String> inventory) {
    this.inventory = inventory;
  }

  public void addToInventory(String item) {
    inventory.add(item);
  }

  public void removeFromInventory(String item) {
    inventory.remove(item);
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getMana() {
    return mana;
  }

  public void setMana(int mana) {
    this.mana = mana;
  }

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public Attributes getAttributes() {
    return this.attributes;
  }

  public void setAttributes(Attributes attributes) {
    this.attributes = attributes;
  }
}
