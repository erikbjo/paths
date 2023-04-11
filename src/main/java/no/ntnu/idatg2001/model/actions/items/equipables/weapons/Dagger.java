package no.ntnu.idatg2001.model.actions.items.equipables.weapons;

import no.ntnu.idatg2001.model.units.Player;

public class Dagger extends Weapon {
  public Dagger(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }

  @Override
  public void equip(Player player) {}
}
