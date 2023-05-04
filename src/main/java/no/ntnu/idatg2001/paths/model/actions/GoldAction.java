package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The GoldAction class represents an action that gives the player gold.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity
public class GoldAction extends Action {
  private boolean isPositive;
  private int gold;

  /**
   * Constructor for the GoldAction class.
   *
   * @param gold the amount of gold to give the player
   */
  public GoldAction(int gold, boolean isPositive) {
    this.gold = gold;
    this.isPositive = isPositive;
  }

  public GoldAction() {}

  /**
   * Returns whether the action is positive or not.
   *
   * @return whether the action is positive or not
   */
  public boolean getIsPositive() {
    return isPositive;
  }

  public void setIsPositive(boolean isPositive) {
    this.isPositive = isPositive;
  }

  /**
   * Returns the amount of gold the action gives.
   *
   * @return the amount of gold the action gives
   */
  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
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
