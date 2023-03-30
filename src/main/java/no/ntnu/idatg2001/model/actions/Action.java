package no.ntnu.idatg2001.model.actions;

import no.ntnu.idatg2001.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
public interface Action {

  void execute(Player player);
}
