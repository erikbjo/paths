package no.ntnu.idatg2001.paths.model.units;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.items.equipables.Equipable;

/**
 * A class that represents a player with different actions that can be used in a story.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.03
 */
@Entity
@Table(name = "Player")
@PrimaryKeyJoinColumn(name = "ID")
public class Player extends Unit {
  @OneToMany(
      mappedBy = "player",
      cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
      orphanRemoval = true)
  private List<Item> inventory = new ArrayList<>();

  @OneToMany(
      mappedBy = "player",
      cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
      orphanRemoval = true)
  private List<Equipable> equippedItems = new ArrayList<>();

  @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  private Attributes attributes;

  private Player(PlayerBuilder builder) {
    super(builder);
    this.inventory = builder.inventory;
    this.equippedItems = builder.equippedItems;
    this.attributes = builder.attributes;
  }

  protected Player() {
    super();
  }

  /**
   * Gets the inventory.
   *
   * @return the inventory
   */
  public List<Item> getInventory() {
    return inventory;
  }

  /**
   * Sets the inventory.
   *
   * @param inventory the inventory
   */
  public void setInventory(List<Item> inventory) {
    this.inventory = inventory;
  }

  /**
   * Adds an item to the inventory.
   *
   * @param item the item to add
   */
  public void addToInventory(Item item) {
    inventory.add(item);
  }

  /**
   * Removes an item from the inventory.
   *
   * @param item the item to remove
   */
  public void removeFromInventory(Item item) {
    inventory.remove(item);
  }

  /**
   * Gets the equipped items.
   *
   * @return the equipped items
   */
  public List<Equipable> getEquippedItems() {
    return equippedItems;
  }

  /**
   * Sets the equipped items.
   *
   * @param inventory the equipped items
   */
  public void setEquippedItems(List<Equipable> inventory) {
    this.equippedItems = inventory;
  }

  /**
   * Adds an item to the equipped items.
   *
   * @param item the item to add
   */
  public void addToEquippedItems(Equipable item) {
    equippedItems.add(item);
  }

  /**
   * Removes an item from the equipped items.
   *
   * @param item the item to remove
   */
  public void removeFromEquippedItems(Equipable item) {
    equippedItems.remove(item);
  }

  public String dialog() {
    return "Hello, my name is " + super.getName();
  }

  /**
   * If the health is greater than 0, add it to the current health and return true. Otherwise,
   * return false
   *
   * @param health The amount of health to add to the player.
   * @return A boolean value.
   */
  public boolean addHealth(int health) {
    if (health > 0) {
      super.setHealth(super.getHealth() + health);
      return true;
    } else {
      return false;
    }
  }

  /**
   * If the mana is greater than 0, add it to the current mana and return true. Otherwise, return
   * false
   *
   * @param mana The amount of mana to add to the player.
   * @return A boolean value.
   */
  public boolean addMana(int mana) {
    if (mana > 0) {
      super.setMana(super.getMana() + mana);
      return true;
    } else {
      return false;
    }
  }

  /**
   * If the energy is greater than 0, add it to the current energy and return true. Otherwise,
   * return false
   *
   * @param energy The amount of energy to add to the robot.
   * @return The boolean value of the if statement.
   */
  public boolean addEnergy(int energy) {
    if (energy > 0) {
      super.setEnergy(super.getEnergy() + energy);
      return true;
    } else {
      return false;
    }
  }

  /**
   * If the gold is greater than or equal to zero, add the gold to the player's gold, and return
   * true. Otherwise, return false
   *
   * @param gold The amount of gold to add to the player's gold.
   * @return A boolean value.
   */
  public boolean addGold(int gold) {
    if (gold >= 0) {
      super.setGold(super.getGold() + gold);
      return true;
    } else {
      return false;
    }
  }

  public Long getId() {
    return super.getId();
  }

  /**
   * Gets the attributes of the unit.
   *
   * @return the attributes of the unit
   */
  public Attributes getAttributes() {
    return this.attributes;
  }

  /**
   * Sets the attributes of the unit.
   *
   * @param attributes the attributes of the unit
   */
  public void setAttributes(Attributes attributes) {
    this.attributes = attributes;
  }

  public static class PlayerBuilder extends UnitBuilder<PlayerBuilder> {
    private List<Item> inventory;
    private List<Equipable> equippedItems;
    private Attributes attributes;

    public PlayerBuilder withInventory(List<Item> inventory) {
      this.inventory = inventory;
      return this;
    }

    public PlayerBuilder withEquippedItems(List<Equipable> equippedItems) {
      this.equippedItems = equippedItems;
      return this;
    }

    public PlayerBuilder withAttributes(Attributes attributes) {
      this.attributes = attributes;
      return this;
    }

    @Override
    public Player build() {
      return new Player(this);
    }
  }
}
