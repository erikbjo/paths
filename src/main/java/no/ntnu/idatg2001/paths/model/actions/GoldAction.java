package no.ntnu.idatg2001.paths.model.actions;

import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The GoldAction class represents an action that gives the player gold.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class GoldAction implements Action {
  private final int gold;

  /**
   * Constructor for the GoldAction class.
   *
   * @param gold the amount of gold to give the player
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }

  /**
   * Executes the action.
   *
   * @param player the player who is performing the action
   */
  @Override
  public void execute(Player player) {
    player.addGold(gold);
  }
}
