package no.ntnu.idatg2001.model.goals;

import no.ntnu.idatg2001.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class GoldGoal implements Goal {
  private int minimumGold;

  public GoldGoal(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  public boolean isFulfilled(Player player) {
    int playerGold = player.getGold();
    return minimumGold <= playerGold;
  }
}
