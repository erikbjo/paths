package no.ntnu.idatg2001.paths.model.units;

/**
 * The DefaultAttributes enum represents the default attributes of the different classes in the
 * game.
 *
 * @see Attributes
 * @author Erik Bjørnsen and Emil Klevgård-Slåtssveen
 */
public enum DefaultAttributes {
  HUNTER(3, 5, 2, 2, 6, 7, 3),
  WARRIOR(7, 3, 5, 4, 3, 4, 4),
  DRUID(4, 4, 4, 3, 6, 5, 4),
  ROGUE(3, 6, 3, 3, 5, 7, 3),
  MAGE(2, 5, 2, 3, 8, 4, 6),
  WARLOCK(2, 4, 3, 6, 7, 3, 5),
  PRIEST(3, 5, 3, 6, 7, 3, 3),
  PALADIN(6, 4, 6, 5, 4, 3, 4),
  SHAMAN(4, 6, 4, 4, 5, 4, 5);

  private final Attributes attributes;

  /**
   * Constructor for the DefaultAttributes enum.
   *
   * @param strength the strength of the class
   * @param perception the perception of the class
   * @param endurance the endurance of the class
   * @param charisma the charisma of the class
   * @param intelligence the intelligence of the class
   * @param agility the agility of the class
   * @param luck the luck of the class
   */
  DefaultAttributes(
      int strength,
      int perception,
      int endurance,
      int charisma,
      int intelligence,
      int agility,
      int luck) {
    attributes =
        new Attributes(strength, perception, endurance, charisma, intelligence, agility, luck);
  }

  /**
   * Gets the attributes of the class.
   *
   * @return the attributes of the class
   */
  public Attributes getAttributes() {
    return attributes;
  }
}
