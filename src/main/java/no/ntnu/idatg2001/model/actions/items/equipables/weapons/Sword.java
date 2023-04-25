package no.ntnu.idatg2001.model.actions.items.equipables.weapons;

import no.ntnu.idatg2001.model.units.Player;

public class Sword extends Weapon {
  public Sword(String name, int itemScore, int goldValue) {
    super(name, itemScore, goldValue);
  }

  @Override
  public void equip(Player player) {
    player.getInventory().remove(this);
    player.getEquippedItems().add(this);
  }
}
