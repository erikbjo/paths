package no.ntnu.idatg2001.paths.model.goals;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * GoldGoal is a goal that is fulfilled when the player has a certain amount of gold.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
@Entity
public class GoldGoal extends Goal {
  private int minimumGold;

  /**
   * Constructor for the GoldGoal class.
   *
   * @param minimumGold The minimum gold the player must have to fulfill the goal.
   * @throws IllegalArgumentException if the minimum gold is negative.
   */
  public GoldGoal(int minimumGold) {
    if (minimumGold < 0) {
      throw new IllegalArgumentException("The gold goal cannot be negative.");
    }
    this.minimumGold = minimumGold;
  }

  protected GoldGoal() {}

  /**
   * Adds gold to the minimum gold the player must have to fulfill the goal.
   *
   * @param gold The amount of gold to add.
   */
  public void addGoldToGoldGoal(int gold) {
    minimumGold += gold;
  }

  /**
   * Removes gold from the minimum gold the player must have to fulfill the goal.
   *
   * @param gold The amount of gold to remove.
   * @throws IllegalArgumentException if the gold goal is negative.
   */
  public void removeGoldFromGoldGoal(int gold) {
    if (minimumGold - gold < 0) {
      throw new IllegalArgumentException("The gold goal cannot be negative.");
    }
    minimumGold -= gold;
  }

  /**
   * Sets the minimum gold the player must have to fulfill the goal.
   *
   * @param gold The amount of gold to set.
   * @throws IllegalArgumentException if the gold goal is negative.
   */
  public void setGoldToGoldGoal(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("The gold goal cannot be negative.");
    }
    minimumGold = gold;
  }

  /**
   * Checks if the player has fulfilled the goal.
   *
   * @param player The player to check.
   * @return True if the player has fulfilled the goal, false otherwise.
   */
  public boolean isFulfilled(Player player) {
    int playerGold = player.getGold();
    return minimumGold <= playerGold;
  }
}
