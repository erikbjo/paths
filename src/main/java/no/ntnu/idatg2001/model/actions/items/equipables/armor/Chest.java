package no.ntnu.idatg2001.model.actions.items.equipables.armor;

import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;

public class Chest extends Armor {
  public Chest(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue, attributes);
  }

  @Override
  public void equip(Player player) {}
}
