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
 * @version 2023.04.19
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

  /**
   * Creates a new player. This constructor is used by the PlayerBuilder.
   *
   * @param builder the player builder
   */
  private Player(PlayerBuilder builder) {
    super(builder);
    this.inventory = builder.inventory;
    this.equippedItems = builder.equippedItems;
    this.attributes = builder.attributes;
  }

  /** Used by DB */
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

  /**
   * Dialog for the player.
   *
   * @return A string with the dialog.
   */
  public String dialog() {
    return "Hello, my name is " + super.getName();
  }

  /**
   * Adds health to the player.
   *
   * @param health The amount of health to add to the player.
   * @throws IllegalArgumentException if the health is negative.
   */
  public void addHealth(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("Health cannot be negative");
    }
    super.setHealth(super.getHealth() + health);
  }

  /**
   * Removes health from the player.
   *
   * @param health The amount of health to remove from the player.
   * @throws IllegalArgumentException if the health is negative.
   */
  public void removeHealth(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("Health cannot be negative");
    }
    super.setHealth(super.getHealth() - health);
  }

  /**
   * Adds mana to the player.
   *
   * @param mana The amount of mana to add to the player.
   * @throws IllegalArgumentException if the mana is negative.
   */
  public void addMana(int mana) {
    if (mana < 0) {
      throw new IllegalArgumentException("Mana cannot be negative");
    }
    super.setMana(super.getMana() + mana);
  }

  /**
   * Removes mana from the player.
   *
   * @param mana The amount of mana to remove from the player.
   * @throws IllegalArgumentException if the mana is negative.
   */
  public void removeMana(int mana) {
    if (mana < 0) {
      throw new IllegalArgumentException("Mana cannot be negative");
    }
    super.setMana(super.getMana() - mana);
  }

  /**
   * Adds energy to the player.
   *
   * @param energy The amount of energy to add to the player.
   * @throws IllegalArgumentException if the energy is negative.
   */
  public void addEnergy(int energy) {
    if (energy < 0) {
      throw new IllegalArgumentException("Energy cannot be negative");
    }
    super.setEnergy(super.getEnergy() + energy);
  }

  /**
   * Removes energy from the player.
   *
   * @param energy The amount of energy to remove from the player.
   * @throws IllegalArgumentException if the energy is negative.
   */
  public void removeEnergy(int energy) {
    if (energy < 0) {
      throw new IllegalArgumentException("Energy cannot be negative");
    }
    super.setEnergy(super.getEnergy() - energy);
  }

  /**
   * Adds gold to the player.
   *
   * @param gold The amount of gold to add to the player.
   * @throws IllegalArgumentException if the gold is negative.
   */
  public void addGold(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("Gold cannot be negative");
    }
    super.setGold(super.getGold() + gold);
  }

  /**
   * Removes gold from the player.
   *
   * @param gold The amount of gold to remove from the player.
   * @throws IllegalArgumentException if the gold is negative.
   */
  public void removeGold(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("Gold cannot be negative");
    }
    super.setGold(super.getGold() - gold);
  }

  /**
   * Gets the id of the player.
   *
   * @return the id of the player
   */
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

  /**
   * The Class PlayerBuilder. This is used to build a player. It extends the UnitBuilder.
   *
   * @see UnitBuilder
   * @see Player
   * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
   */
  public static class PlayerBuilder extends UnitBuilder<PlayerBuilder> {
    private List<Item> inventory;
    private List<Equipable> equippedItems;
    private Attributes attributes;

    /**
     * Sets the inventory.
     *
     * @param inventory the inventory
     * @return the player builder
     */
    public PlayerBuilder withInventory(List<Item> inventory) {
      this.inventory = inventory;
      return this;
    }

    /**
     * Sets the equipped items.
     *
     * @param equippedItems the equipped items
     * @return the player builder
     */
    public PlayerBuilder withEquippedItems(List<Equipable> equippedItems) {
      this.equippedItems = equippedItems;
      return this;
    }

    /**
     * Sets the attributes of the player.
     *
     * @param attributes the attributes of the player
     * @return the player builder
     */
    public PlayerBuilder withAttributes(Attributes attributes) {
      this.attributes = attributes;
      return this;
    }

    /** {@inheritDoc} */
    @Override
    public Player build() {
      return new Player(this);
    }
  }
}
