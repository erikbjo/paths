package no.ntnu.idatg2001.items.consumables;

import no.ntnu.idatg2001.items.Item;

public abstract class Potion extends Item implements Consumable {
  protected Potion(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }
}
