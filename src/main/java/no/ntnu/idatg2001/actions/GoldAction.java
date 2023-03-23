package no.ntnu.idatg2001.actions;

import no.ntnu.idatg2001.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public class GoldAction implements Action {
  private final int gold;

  public GoldAction(int gold) {
    this.gold = gold;
  }

  @Override
  public void execute(Player player) {
    player.addGold(gold);
  }
}
