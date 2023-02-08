package no.ntnu.idatg2001.actions;

import no.ntnu.idatg2001.units.Player;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
public interface Action {


  /**
   * This function takes a Player object as an argument and returns nothing.
   *
   * @param player The player who is executing the command.
   */
  public void execute(Player player);
}
