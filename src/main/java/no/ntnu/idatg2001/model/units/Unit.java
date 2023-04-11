package no.ntnu.idatg2001.model.units;

import java.util.List;
import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.actions.items.equipables.Equipable;

public abstract class Unit {

  // Information
  protected String name;
  private int score;
  private int gold;
  private List<Item> inventory;
  private List<Equipable> equippedItems;

  // Standard stats
  private int health;
  private int mana;
  private int energy;

  private Attributes attributes;

  protected Unit(UnitBuilder<?> builder) {
    this.name = builder.name;
    this.score = builder.score;
    this.gold = builder.gold;
    this.inventory = builder.inventory;
    this.equippedItems = builder.equippedItems;
    this.health = builder.health;
    this.mana = builder.mana;
    this.energy = builder.energy;
    this.attributes = builder.attributes;
  }

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

  public List<Item> getInventory() {
    return inventory;
  }

  public void setInventory(List<Item> inventory) {
    this.inventory = inventory;
  }

  public void addToInventory(Item item) {
    inventory.add(item);
  }

  public void removeFromInventory(Item item) {
    inventory.remove(item);
  }

  public List<Equipable> getEquippedItems() {
    return equippedItems;
  }

  public void setEquippedItems(List<Equipable> inventory) {
    this.equippedItems = inventory;
  }

  public void addToEquippedItems(Equipable item) {
    equippedItems.add(item);
  }

  public void removeFromEquippedItems(Equipable item) {
    equippedItems.remove(item);
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

  public abstract static class UnitBuilder<T extends UnitBuilder<T>> {
    // Builder fields
    private String name;
    private int score;
    private int gold;
    private List<Item> inventory;
    private List<Equipable> equippedItems;
    private int health;
    private int mana;
    private int energy;
    private Attributes attributes;

    public T withName(String name) {
      this.name = name;
      return (T) this;
    }

    public T withScore(int score) {
      this.score = score;
      return (T) this;
    }

    public T withGold(int gold) {
      this.gold = gold;
      return (T) this;
    }

    public T withInventory(List<Item> inventory) {
      this.inventory = inventory;
      return (T) this;
    }

    public T withEquippedItems(List<Equipable> equippedItems) {
      this.equippedItems = equippedItems;
      return (T) this;
    }

    public T withHealth(int health) {
      this.health = health;
      return (T) this;
    }

    public T withMana(int mana) {
      this.mana = mana;
      return (T) this;
    }

    public T withEnergy(int energy) {
      this.energy = energy;
      return (T) this;
    }

    public T withAttributes(Attributes attributes) {
      this.attributes = attributes;
      return (T) this;
    }

    public abstract Unit build();
  }
}
