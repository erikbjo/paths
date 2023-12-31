package no.ntnu.idatg2001.paths.model.units;

import jakarta.persistence.*;

/**
 * The Unit class represents a unit in the game.
 *
 * @see UnitBuilder
 * @author Erik Bjørsen and Emil Klevgård-Slåttsveen
 */
@Entity
@Table(name = "Unit")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Unit {
  protected String name;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int score;
  private int gold;
  // Standard stats
  private int health;
  private int mana;
  private int energy;

  /**
   * Constructor for the Unit class.
   *
   * @param builder the builder to build the unit
   */
  protected Unit(UnitBuilder<?> builder) {
    this.name = builder.name;
    this.score = builder.score;
    this.gold = builder.gold;
    this.health = builder.health;
    this.mana = builder.mana;
    this.energy = builder.energy;
  }

  /** Used by DB */
  protected Unit() {}

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
   * @param name the name of the unit
   * @throws IllegalArgumentException if the name is null or blank/empty
   */
  public void setName(String name) {
    if (name == null || name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null");
    }
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
   * @throws IllegalArgumentException if the score is negative
   */
  public void setScore(int score) {
    if (score < 0) {
      throw new IllegalArgumentException("Score cannot be negative");
    }
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
   * @param gold the gold of the unit
   * @throws IllegalArgumentException if the gold is negative
   */
  public void setGold(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("Gold cannot be negative");
    }
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
   * @throws IllegalArgumentException if the health is negative
   */
  public void setHealth(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("Health cannot be negative");
    }
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
   * @throws IllegalArgumentException if the mana is negative
   */
  public void setMana(int mana) {
    if (mana < 0) {
      throw new IllegalArgumentException("Mana cannot be negative");
    }
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
   * @throws IllegalArgumentException if the energy is negative
   */
  public void setEnergy(int energy) {
    if (energy < 0) {
      throw new IllegalArgumentException("Energy cannot be negative");
    }
    this.energy = energy;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  protected Long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * ToString method for the Unit class.
   *
   * @return String with the information of the unit
   */
  @Override
  public String toString() {
    return name + ", score: " + score + ", gold: " + gold;
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

    /**
     * Sets the name of the unit.
     *
     * @param name the name of the unit
     * @return the unit builder
     * @throws IllegalArgumentException if the name is null or blank/empty
     */
    public T withName(String name) {
      if (name == null || name.isBlank() || name.isEmpty()) {
        throw new IllegalArgumentException("Name cannot be null");
      }
      this.name = name;
      return (T) this;
    }

    /**
     * Sets the score of the unit.
     *
     * @param score the score of the unit
     * @return the unit builder
     * @throws IllegalArgumentException if the score is negative
     */
    public T withScore(int score) {
      if (score < 0) {
        throw new IllegalArgumentException("Score cannot be negative");
      }
      this.score = score;
      return (T) this;
    }

    /**
     * Sets the gold of the unit.
     *
     * @param gold the gold of the unit
     * @return the unit builder
     * @throws IllegalArgumentException if the gold is negative
     */
    public T withGold(int gold) {
      if (gold < 0) {
        throw new IllegalArgumentException("Gold cannot be negative");
      }
      this.gold = gold;
      return (T) this;
    }

    /**
     * Sets the health of the unit.
     *
     * @param health the health of the unit
     * @return the unit builder
     * @throws IllegalArgumentException if the health is negative
     */
    public T withHealth(int health) {
      if (health < 0) {
        throw new IllegalArgumentException("Health cannot be negative");
      }
      this.health = health;
      return (T) this;
    }

    /**
     * Sets the mana of the unit.
     *
     * @param mana the mana of the unit
     * @return the unit builder
     * @throws IllegalArgumentException if the mana is negative
     */
    public T withMana(int mana) {
      if (mana < 0) {
        throw new IllegalArgumentException("Mana cannot be negative");
      }
      this.mana = mana;
      return (T) this;
    }

    /**
     * Sets the energy of the unit.
     *
     * @param energy the energy of the unit
     * @return the unit builder
     * @throws IllegalArgumentException if the energy is negative
     */
    public T withEnergy(int energy) {
      if (energy < 0) {
        throw new IllegalArgumentException("Energy cannot be negative");
      }
      this.energy = energy;
      return (T) this;
    }

    /**
     * Builds the unit.
     *
     * @return the unit
     */
    public abstract Unit build();
  }
}
