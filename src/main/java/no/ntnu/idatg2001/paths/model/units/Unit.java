package no.ntnu.idatg2001.paths.model.units;

import java.util.List;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.items.equipables.Equipable;

/** The Unit class represents a unit in the game. */
public abstract class Unit {

  // Information
  protected String name;
  private int score;
  private int gold;

  // Standard stats
  private int health;
  private int mana;
  private int energy;

  private Attributes attributes;

  protected Unit(UnitBuilder<?> builder) {
    this.name = builder.name;
    this.score = builder.score;
    this.gold = builder.gold;
    this.health = builder.health;
    this.mana = builder.mana;
    this.energy = builder.energy;
    this.attributes = builder.attributes;
  }

  /** Used by DB */
  protected Unit() {
  }

  /**
   * Method to get the dialog of the unit.
   *
   * @return String with the dialog of the unit
   */
  public abstract String dialog();


  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the score.
   *
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the score.
   *
   * @param score the score
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Gets the gold.
   *
   * @return the gold
   */
  public int getGold() {
    return gold;
  }

  /**
   * Sets the gold.
   *
   * @param gold the gold
   */
  public void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * Gets the health of the unit.
   *
   * @return the health of the unit
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the health of the unit.
   *
   * @param health the health of the unit
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Gets the mana of the unit.
   *
   * @return the mana of the unit
   */
  public int getMana() {
    return mana;
  }

  /**
   * Sets the mana of the unit.
   *
   * @param mana the mana of the unit
   */
  public void setMana(int mana) {
    this.mana = mana;
  }

  /**
   * Gets the energy of the unit.
   *
   * @return the energy of the unit
   */
  public int getEnergy() {
    return energy;
  }

  /**
   * Sets the energy of the unit.
   *
   * @param energy the energy of the unit
   */
  public void setEnergy(int energy) {
    this.energy = energy;
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
   * The UnitBuilder class is used to build a Unit object.
   *
   * @param <T> the type of the UnitBuilder
   */
  public abstract static class UnitBuilder<T extends UnitBuilder<T>> {
    // Builder fields
    private String name;
    private int score;
    private int gold;
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
