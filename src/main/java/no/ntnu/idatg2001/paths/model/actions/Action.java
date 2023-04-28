package no.ntnu.idatg2001.paths.model.actions;

import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Action interface represents an action that can be performed by the player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
public interface Action {

  /**
   * Executes the action.
   *
   * @param player the player who is performing the action
   */
  void execute(Player player);
}
