package no.ntnu.idatg2001.paths.model.actions;

import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The HealthAction class represents an action that gives the player health.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class HealthAction implements Action {
  private final int health;

  /**
   * Constructor for the HealthAction class.
   *
   * @param health the amount of health to give the player
   */
  public HealthAction(int health) {
    this.health = health;
  }

  /**
   * Executes the action.
   *
   * @param player the player who is performing the action
   */
  @Override
  public void execute(Player player) {
    player.addHealth(health);
  }
}
