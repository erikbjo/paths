package no.ntnu.idatg2001.model.actions;

import no.ntnu.idatg2001.model.units.Player;

/**
 * The ScoreAction class represents an action that gives the player score.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class ScoreAction implements Action {
  private final int points;

  /**
   * Constructor for the ScoreAction class.
   *
   * @param points the amount of points to give the player
   */
  public ScoreAction(int points) {
    this.points = points;
  }

  /**
   * Executes the action.
   *
   * @param player the player who is performing the action
   */
  @Override
  public void execute(Player player) {
    player.setScore(player.getScore() + points);
  }
}
