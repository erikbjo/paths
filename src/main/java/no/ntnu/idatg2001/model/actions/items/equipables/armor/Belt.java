package no.ntnu.idatg2001.model.actions.items.equipables.armor;

import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;

public class Belt extends Armor {

  private final int equipSlot = 2;

  protected Belt(String name, int itemScore, int goldValue, Attributes attributes) {
    super(name, itemScore, goldValue, attributes);
  }

  @Override
  public void equip(Player player) {}
}
