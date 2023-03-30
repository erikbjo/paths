package no.ntnu.idatg2001.model.actions.items.equipables;

import no.ntnu.idatg2001.model.units.Player;

public interface Equipable {
  int equipSlot = 0;

  void equip(Player player);
}
