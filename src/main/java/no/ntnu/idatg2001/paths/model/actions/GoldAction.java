package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The GoldAction class represents an action that gives the player gold.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023-04-19
 */
@Entity
public class GoldAction extends Action {
  private boolean isPositive;
  private int gold;

  /**
   * Constructor for the GoldAction class.
   *
   * @param gold the amount of gold to give the player
   * @param isPositive whether the action is positive or not
   * @throws IllegalArgumentException if the gold value is negative
   */
  public GoldAction(int gold, boolean isPositive) {
    setGold(gold);
    setIsPositive(isPositive);
  }

  /** Empty constructor for the GoldAction class. Used by JPA. */
  public GoldAction() {}

  /**
   * Returns whether the action is positive or not.
   *
   * @return whether the action is positive or not
   */
  public boolean getIsPositive() {
    return isPositive;
  }

  /**
   * Sets whether the action is positive or not.
   *
   * @param isPositive whether the action is positive or not
   */
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

  /**
   * Sets the amount of gold the action gives.
   *
   * @param gold the amount of gold the action gives
   * @throws IllegalArgumentException if the gold is negative
   */
  public void setGold(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("Gold cannot be negative");
    }

    this.gold = gold;
  }

  /** {@inheritDoc} */
  @Override
  public void execute(Player player) {
    if (getIsPositive()) {
      player.addGold(getGold());
    } else {
      player.removeGold(getGold());
    }
  }

  /** {@inheritDoc} */
  @Override
  public Object getActionValue() {
    return getGold();
  }

  /** {@inheritDoc} */
  @Override
  public void setActionValue(Object actionValue) {
    if (!(actionValue instanceof Integer)) {
      throw new IllegalArgumentException("Action value must be an integer");
    }

    setGold((int) actionValue);
  }

  /** {@inheritDoc} */
  @Override
  public Boolean getActionIsPositive() {
    return getIsPositive();
  }
}
