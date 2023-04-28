package no.ntnu.idatg2001.paths.model.goals;

import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * HealthGoal class.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
public class HealthGoal implements Goal {
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

  /**
   * Sets the minimum health the player must have to fulfill the goal.
   *
   * @param health The amount of health to set.
   * @throws IllegalArgumentException if the health goal is negative.
   */
  public void setHealthToHealthGoal(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("The health goal cannot be negative.");
    }
    minimumHealth = health;
  }

  /**
   * Checks if the player has the minimum health to fulfill the goal.
   *
   * @param player The player to check.
   * @return A boolean value.
   */
  @Override
  public boolean isFulfilled(Player player) {
    int playerHealth = player.getHealth();
    return minimumHealth <= playerHealth;
  }
}
