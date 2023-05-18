package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The HealthAction class represents an action that gives the player health.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
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

  public HealthAction() {}

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
   * Returns the amount of health the action gives.
   *
   * @return the amount of health the action gives
   */
  public int getHealth() {
    return health;
  }

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

  /**
   * Executes the action.
   *
   * @param player the player who is performing the action
   */
  @Override
  public void execute(Player player) {
    player.addHealth(health);
  }

  @Override
  public Object getActionValue() {
    return getHealth();
  }

  @Override
  public void setActionValue(Object actionValue) {
    this.health = actionValue instanceof Integer ? (int) actionValue : 0;
  }

  @Override
  public Boolean getActionIsPositive() {
    return getIsPositive();
  }
}
