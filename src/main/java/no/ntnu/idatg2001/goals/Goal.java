package no.ntnu.idatg2001.goals;

import no.ntnu.idatg2001.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public interface Goal {
  boolean isFulfilled(Player player);
}
