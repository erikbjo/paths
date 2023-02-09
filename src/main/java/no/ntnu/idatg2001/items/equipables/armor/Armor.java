package no.ntnu.idatg2001.items.equipables.armor;

import no.ntnu.idatg2001.items.Item;
import no.ntnu.idatg2001.items.equipables.Equipable;
import no.ntnu.idatg2001.units.Attributes;

public abstract class Armor extends Item implements Equipable {

  final Attributes attributes;

  protected Armor(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue);
    this.attributes = attributes;
  }
}
