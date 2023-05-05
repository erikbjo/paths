package no.ntnu.idatg2001.paths.model.units;

// @TODO: ADD LANGUAGE SUPPORT FOR THIS
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

  public Attributes getAttributes() {
    return attributes;
  }
}
