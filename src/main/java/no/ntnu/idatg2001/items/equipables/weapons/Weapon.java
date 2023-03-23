package no.ntnu.idatg2001.items.equipables.weapons;

import no.ntnu.idatg2001.items.Item;
import no.ntnu.idatg2001.items.equipables.Equipable;
import no.ntnu.idatg2001.units.SpecialAttributes;

public abstract class Weapon extends Item implements SpecialAttributes, Equipable {
  public Weapon(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }
}
