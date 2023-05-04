package no.ntnu.idatg2001.paths.model.units;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.items.equipables.Equipable;

import java.util.List;

/**
 * A class that represents a player with different actions that can be used in a story.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.03
 */
@Entity
@Table(name = "player")
public class Player extends Unit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "player_id")
  private List<Item> inventory;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "player_id")
  private List<Equipable> equippedItems;

  private Player(PlayerBuilder builder) {
    super(builder);
    this.inventory = builder.inventory;
    this.equippedItems = builder.equippedItems;
  }

  protected Player() {
    super();
  }

  /**
   * Returns the player id.
   *
   * @return the id as a Long.
   */
  public Long getId() {
    return id;
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

  public static class PlayerBuilder extends UnitBuilder<PlayerBuilder> {
    private List<Item> inventory;
    private List<Equipable> equippedItems;

    public PlayerBuilder withInventory(List<Item> inventory) {
      this.inventory = inventory;
      return this;
    }

    public PlayerBuilder withEquippedItems(List<Equipable> equippedItems) {
      this.equippedItems = equippedItems;
      return this;
    }

    @Override
    public Player build() {
      return new Player(this);
    }
  }
}
