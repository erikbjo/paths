package no.ntnu.idatg2001.paths.model.actions;

import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The GoldAction class represents an action that gives the player gold.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class GoldAction implements Action {
  private int gold;
  private final boolean isPositive;

  /**
   * Constructor for the GoldAction class.
   *
   * @param gold the amount of gold to give the player
   */
  public GoldAction(int gold, boolean isPositive) {
    this.gold = gold;
    this.isPositive = isPositive;
  }

  /**
   * Returns whether the action is positive or not.
   *
   * @return whether the action is positive or not
   */
  public boolean getIsPositive() {
    return isPositive;
  }

  /**
   * Returns the amount of gold the action gives.
   *
   * @return the amount of gold the action gives
   */
  public int getGold() {
    return gold;
  }

  /**
   * Adds gold to the action.
   *
   * @param gold the amount of gold to add
   */
  public void addGold(int gold) {
    this.gold += gold;
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
