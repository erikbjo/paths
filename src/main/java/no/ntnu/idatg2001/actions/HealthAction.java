package no.ntnu.idatg2001.actions;

import no.ntnu.idatg2001.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public class HealthAction implements Action {
  private final int health;

  public HealthAction(int health) {
    this.health = health;
  }

  @Override
  public void execute(Player player) {
    player.addHealth(health);
  }
}
