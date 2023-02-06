package no.ntnu.idatg2001.actions;

import no.ntnu.idatg2001.actions.Action;

public class GoldAction implements Action
{
import no.ntnu.idatg2001.Player;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
public class GoldAction implements Action
{
  private int gold;

  public GoldAction(int gold) {
    this.gold = gold;
  }

  @Override
  public void execute(Player player) {

  }
}
