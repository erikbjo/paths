package no.ntnu.idatg2001.model.goals;

import no.ntnu.idatg2001.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class ScoreGoal implements Goal {
  private int minimumPoints;

  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  @Override
  public boolean isFulfilled(Player player) {
    int playerScore = player.getScore();
    return minimumPoints <= playerScore;
  }
}
