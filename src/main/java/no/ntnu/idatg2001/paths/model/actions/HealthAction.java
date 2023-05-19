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
   */
  public HealthAction(int health, boolean isPositive) {
    this.health = health;
    this.isPositive = isPositive;
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
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Adds health to the action.
   *
   * @param health the amount of health to add
   */
  public void addHealth(int health) {
    this.health += health;
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
