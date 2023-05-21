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

  /** Default constructor for the GoldGoal class. Used by JPA. */
  protected GoldGoal() {}

  /**
   * Adds gold to the minimum gold the player must have to fulfill the goal.
   *
   * @param gold The amount of gold to add.
   * @throws IllegalArgumentException if the gold is negative.
   */
  public void addGoldToGoldGoal(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("The gold cannot be negative.");
    }
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
   * Gets the minimum gold the player must have to fulfill the goal.
   *
   * @return The minimum gold the player must have to fulfill the goal.
   */
  public int getMinimumGold() {
    return minimumGold;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isFulfilled(Player player) {
    int playerGold = player.getGold();
    return minimumGold <= playerGold;
  }

  /** {@inheritDoc} */
  @Override
  public Object getGoalValue() {
    return getMinimumGold();
  }

  /** {@inheritDoc} */
  @Override
  public void setGoalValue(Object goalValue) {
    if (!(goalValue instanceof Integer)) {
      throw new IllegalArgumentException("The goal value must be an integer.");
    }

    setGoldToGoldGoal((int) goalValue);
  }
}
