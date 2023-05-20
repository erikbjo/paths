package no.ntnu.idatg2001.paths.model.units;

import jakarta.persistence.*;

/**
 * The Attributes class represents the attributes of a player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity
public class Attributes {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // S.P.E.C.I.A.L stats
  private int strength;
  private int perception;
  private int endurance;
  private int charisma;
  private int intelligence;
  private int agility;
  private int luck;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(
      name = "Attributes_player",
      joinColumns = @JoinColumn(name = "attributes_id"),
      inverseJoinColumns = @JoinColumn(name = "player_id"))
  private Player player;

  /**
   * Instantiates a new Attributes.
   *
   * @param strength the strength
   * @param perception the perception
   * @param endurance the endurance
   * @param charisma the charisma
   * @param intelligence the intelligence
   * @param agility the agility
   * @param luck the luck
   * @throws IllegalArgumentException if any of the attributes are negative
   */
  public Attributes(
      int strength,
      int perception,
      int endurance,
      int charisma,
      int intelligence,
      int agility,
      int luck) {
    if (strength < 0
        || perception < 0
        || endurance < 0
        || charisma < 0
        || intelligence < 0
        || agility < 0
        || luck < 0) throw new IllegalArgumentException("Attributes cannot be negative");
    this.strength = strength;
    this.perception = perception;
    this.endurance = endurance;
    this.charisma = charisma;
    this.intelligence = intelligence;
    this.agility = agility;
    this.luck = luck;
  }

  /** Used by DB */
  protected Attributes() {}

  /**
   * Instantiates a new Attributes from the enum DefaultAttributes.
   *
   * @param defaultAttributes the default attributes to copy
   * @throws IllegalArgumentException if defaultAttributes is null
   */
  public Attributes(DefaultAttributes defaultAttributes) {
    if (defaultAttributes == null) throw new IllegalArgumentException("DefaultAttributes is null");

    this.strength = defaultAttributes.getAttributes().getStrength();
    this.perception = defaultAttributes.getAttributes().getPerception();
    this.endurance = defaultAttributes.getAttributes().getEndurance();
    this.charisma = defaultAttributes.getAttributes().getCharisma();
    this.intelligence = defaultAttributes.getAttributes().getIntelligence();
    this.agility = defaultAttributes.getAttributes().getAgility();
    this.luck = defaultAttributes.getAttributes().getLuck();
  }

  /**
   * Gets player that owns the attributes.
   *
   * @return the player that owns the attributes
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Sets player that owns the attributes.
   *
   * @param player the player that owns the attributes
   * @throws IllegalArgumentException if player is null
   */
  public void setPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Player is null");
    }

    this.player = player;
  }

  /**
   * Gets strength.
   *
   * @return the strength
   */
  public int getStrength() {
    return strength;
  }

  /**
   * Sets strength.
   *
   * @param strength the strength
   * @throws IllegalArgumentException if strength is negative
   */
  public void setStrength(int strength) {
    if (strength < 0) {
      throw new IllegalArgumentException("Strength cannot be negative");
    }
    this.strength = strength;
  }

  /**
   * Gets perception.
   *
   * @return the perception
   */
  public int getPerception() {
    return perception;
  }

  /**
   * Sets perception.
   *
   * @param perception the perception
   * @throws IllegalArgumentException if perception is negative
   */
  public void setPerception(int perception) {
    if (perception < 0) {
      throw new IllegalArgumentException("Perception cannot be negative");
    }
    this.perception = perception;
  }

  /**
   * Gets endurance.
   *
   * @return the endurance
   */
  public int getEndurance() {
    return endurance;
  }

  /**
   * Sets endurance.
   *
   * @param endurance the endurance
   * @throws IllegalArgumentException if endurance is negative
   */
  public void setEndurance(int endurance) {
    if (endurance < 0) {
      throw new IllegalArgumentException("Endurance cannot be negative");
    }
    this.endurance = endurance;
  }

  /**
   * Gets charisma.
   *
   * @return the charisma
   */
  public int getCharisma() {
    return charisma;
  }

  /**
   * Sets charisma.
   *
   * @param charisma the charisma
   * @throws IllegalArgumentException if charisma is negative
   */
  public void setCharisma(int charisma) {
    if (charisma < 0) {
      throw new IllegalArgumentException("Charisma cannot be negative");
    }
    this.charisma = charisma;
  }

  /**
   * Gets intelligence.
   *
   * @return the intelligence
   */
  public int getIntelligence() {
    return intelligence;
  }

  /**
   * Sets intelligence.
   *
   * @param intelligence the intelligence
   * @throws IllegalArgumentException if intelligence is negative
   */
  public void setIntelligence(int intelligence) {
    if (intelligence < 0) {
      throw new IllegalArgumentException("Intelligence cannot be negative");
    }
    this.intelligence = intelligence;
  }

  /**
   * Gets agility.
   *
   * @return the agility
   */
  public int getAgility() {
    return agility;
  }

  /**
   * Sets agility.
   *
   * @param agility the agility
   * @throws IllegalArgumentException if agility is negative
   */
  public void setAgility(int agility) {
    if (agility < 0) {
      throw new IllegalArgumentException("Agility cannot be negative");
    }
    this.agility = agility;
  }

  /**
   * Gets luck.
   *
   * @return the luck
   */
  public int getLuck() {
    return luck;
  }

  /**
   * Sets luck.
   *
   * @param luck the luck
   * @throws IllegalArgumentException if luck is negative
   */
  public void setLuck(int luck) {
    if (luck < 0) {
      throw new IllegalArgumentException("Luck cannot be negative");
    }
    this.luck = luck;
  }

  /**
   * Merges two attributes together.
   *
   * @param attributes the attributes to merge
   * @throws IllegalArgumentException if the attributes are null
   */
  public void addAttributes(Attributes attributes) {
    if (attributes == null) {
      throw new IllegalArgumentException("Attributes cannot be null");
    }

    this.setStrength(this.getStrength() + attributes.getStrength());
    this.setPerception(this.getPerception() + attributes.getPerception());
    this.setEndurance(this.getEndurance() + attributes.getEndurance());
    this.setCharisma(this.getCharisma() + attributes.getCharisma());
    this.setIntelligence(this.getIntelligence() + attributes.getIntelligence());
    this.setAgility(this.getAgility() + attributes.getAgility());
    this.setLuck(this.getLuck() + attributes.getLuck());
  }

  /**
   * Subtracts two attributes from each other.
   *
   * @param attributes the attributes to subtract
   * @throws IllegalArgumentException if the attributes are null
   * @throws IllegalArgumentException if the attributes are greater than the current attributes
   */
  public void subtractAttributes(Attributes attributes) {
    if (attributes == null) {
      throw new IllegalArgumentException("Attributes cannot be null");
    }

    if (attributes.getStrength() > this.getStrength()
        || attributes.getPerception() > this.getPerception()
        || attributes.getEndurance() > this.getEndurance()
        || attributes.getCharisma() > this.getCharisma()
        || attributes.getIntelligence() > this.getIntelligence()
        || attributes.getAgility() > this.getAgility()
        || attributes.getLuck() > this.getLuck()) {
      throw new IllegalArgumentException("Attributes cannot be negative");
    }

    this.setStrength(this.getStrength() - attributes.getStrength());
    this.setPerception(this.getPerception() - attributes.getPerception());
    this.setEndurance(this.getEndurance() - attributes.getEndurance());
    this.setCharisma(this.getCharisma() - attributes.getCharisma());
    this.setIntelligence(this.getIntelligence() - attributes.getIntelligence());
    this.setAgility(this.getAgility() - attributes.getAgility());
    this.setLuck(this.getLuck() - attributes.getLuck());
  }
}
