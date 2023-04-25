package no.ntnu.idatg2001.model.goals;

import no.ntnu.idatg2001.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class HealthGoal implements Goal {
  private int minimumHealth;

  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  @Override
  public boolean isFulfilled(Player player) {
    int playerHealth = player.getHealth();
    return minimumHealth <= playerHealth;
  }
}
