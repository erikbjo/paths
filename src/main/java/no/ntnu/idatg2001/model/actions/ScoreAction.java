package no.ntnu.idatg2001.model.actions;

import no.ntnu.idatg2001.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public class ScoreAction implements Action {
  private final int points;

  public ScoreAction(int points) {
    this.points = points;
  }

  @Override
  public void execute(Player player) {
    player.setScore(player.getScore() + points);
  }
}
