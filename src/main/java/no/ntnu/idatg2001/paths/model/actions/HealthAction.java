package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The HealthAction class represents an action that gives the player health.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023-04-19
 */
@Entity
public class HealthAction extends Action {
  private int health;
  private boolean isPositive;

  /**
   * Constructor for the HealthAction class.
   *
   * @param health the amount of health to give the player
   * @param isPositive whether the action is positive or not
   * @throws IllegalArgumentException if the health value is negative
   */
  public HealthAction(int health, boolean isPositive) {
    setHealth(health);
    setIsPositive(isPositive);
  }

  /** Empty constructor for the HealthAction class. Used by JPA. */
  public HealthAction() {}

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
   * Returns the amount of health the action gives.
   *
   * @return the amount of health the action gives
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the amount of health the action gives.
   *
   * @param health the amount of health the action gives
   * @throws IllegalArgumentException if the health value is negative
   */
  public void setHealth(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("Health cannot be negative");
    }
    this.health = health;
  }

  /** {@inheritDoc} */
  @Override
  public void execute(Player player) {
    player.addHealth(health);
  }

  /** {@inheritDoc} */
  @Override
  public Object getActionValue() {
    return getHealth();
  }

  /** {@inheritDoc} */
  @Override
  public void setActionValue(Object actionValue) {
    if (!(actionValue instanceof Integer)) {
      throw new IllegalArgumentException("Action value must be an integer");
    }

    setHealth((Integer) actionValue);
  }

  /** {@inheritDoc} */
  @Override
  public Boolean getActionIsPositive() {
    return getIsPositive();
  }
}
