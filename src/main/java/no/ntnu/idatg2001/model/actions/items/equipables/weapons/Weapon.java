package no.ntnu.idatg2001.model.actions.items.equipables.weapons;

import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.actions.items.equipables.Equipable;
import no.ntnu.idatg2001.model.units.SpecialAttributes;

public abstract class Weapon extends Item implements SpecialAttributes, Equipable {
  public Weapon(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }
}
