package no.ntnu.idatg2001.items.equipables;

import no.ntnu.idatg2001.units.Player;

public interface Equipable {
  int equipSlot = 0;
  void equip(Player player);

}
