package no.ntnu.idatg2001.paths.model.goals;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * HealthGoal class. Represents a goal that can be fulfilled by the player.
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

  /** Default constructor for the HealthGoal class. Used by DB */
  protected HealthGoal() {}

  /**
   * Adds health to the minimum health the player must have to fulfill the goal.
   *
   * @param health The amount of health to add.
   * @throws IllegalArgumentException if the health goal is negative.
   */
  public void addHealthToHealthGoal(int health) {
    if (minimumHealth + health < 0) {
      throw new IllegalArgumentException("The health goal cannot be negative.");
    }
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

  /**
   * Gets the minimum health the player must have to fulfill the goal.
   *
   * @return The minimum health the player must have to fulfill the goal.
   */
  public int getMinimumHealth() {
    return minimumHealth;
  }

  /**
   * Sets the minimum health the player must have to fulfill the goal.
   *
   * @param minimumHealth The amount of health to set.
   * @throws IllegalArgumentException if the health goal is negative.
   */
  public void setMinimumHealth(int minimumHealth) {
    if (minimumHealth < 0) {
      throw new IllegalArgumentException("The health goal cannot be negative.");
    }
    this.minimumHealth = minimumHealth;
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
