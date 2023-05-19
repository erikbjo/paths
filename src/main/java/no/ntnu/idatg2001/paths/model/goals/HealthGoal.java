package no.ntnu.idatg2001.paths.model.goals;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * HealthGoal class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
@Entity
public class HealthGoal extends Goal {
  private int minimumHealth;

  /**
   * Constructor for the HealthGoal class.
   *
   * @param minimumHealth The minimum health the player must have to fulfill the goal.
   * @throws IllegalArgumentException if the minimum health is negative.
   */
  public HealthGoal(int minimumHealth) {
    if (minimumHealth < 0) {
      throw new IllegalArgumentException("The health goal cannot be negative.");
    }
    this.minimumHealth = minimumHealth;
  }

  protected HealthGoal() {}

  /**
   * Adds health to the minimum health the player must have to fulfill the goal.
   *
   * @param health The amount of health to add.
   */
  public void addHealthToHealthGoal(int health) {
    minimumHealth += health;
  }

  /**
   * Removes health from the minimum health the player must have to fulfill the goal.
   *
   * @param health The amount of health to remove.
   * @throws IllegalArgumentException if the health goal is negative.
   */
  public void removeHealthFromHealthGoal(int health) {
    if (minimumHealth - health < 0) {
      throw new IllegalArgumentException("The health goal cannot be negative.");
    }
    minimumHealth -= health;
  }

  public int getMinimumHealth() {
    return minimumHealth;
  }

  public void setMinimumHealth(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  /**
   * Sets the minimum health the player must have to fulfill the goal.
   *
   * @param health The amount of health to set.
   */
  public void setHealthToHealthGoal(int health) {
    minimumHealth = health;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isFulfilled(Player player) {
    int playerHealth = player.getHealth();
    return minimumHealth <= playerHealth;
  }

  /** {@inheritDoc} */
  @Override
  public Object getGoalValue() {
    return getMinimumHealth();
  }

  /** {@inheritDoc} */
  @Override
  public void setGoalValue(Object goalValue) {
    if (!(goalValue instanceof Integer)) {
      throw new IllegalArgumentException("The goal value must be an integer.");
    }

    setMinimumHealth((int) goalValue);
  }
}
