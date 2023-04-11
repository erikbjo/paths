package no.ntnu.idatg2001.model.actions.items.equipables.weapons;

import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.actions.items.equipables.Equipable;

public abstract class Weapon extends Item implements Equipable {
  public Weapon(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }
}
