package no.ntnu.idatg2001.paths.model.goals;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The ScoreGoal class represents a goal that can be fulfilled by the player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen.
 * @version 2023.02.06
 */
@Entity
public class ScoreGoal extends Goal {
  private int minimumPoints;

  /**
   * Constructor for the ScoreGoal class.
   *
   * @param minimumPoints The minimum points the player must have to fulfill the goal.
   * @throws IllegalArgumentException if the minimum points is negative.
   */
  public ScoreGoal(int minimumPoints) {
    if (minimumPoints < 0) {
      throw new IllegalArgumentException("The score goal cannot be negative.");
    }
    this.minimumPoints = minimumPoints;
  }

  /** Default constructor for the ScoreGoal class. */
  protected ScoreGoal() {}

  /**
   * Adds points to the minimum points the player must have to fulfill the goal.
   *
   * @param points The number of points to add.
   * @throws IllegalArgumentException if the score goal is negative.
   */
  public void addPointsToScoreGoal(int points) {
    if (minimumPoints + points < 0) {
      throw new IllegalArgumentException("The score goal cannot be negative.");
    }
    minimumPoints += points;
  }

  /**
   * Removes points from the minimum points the player must have to fulfill the goal.
   *
   * @param points The number of points to remove.
   * @throws IllegalArgumentException if the score goal is negative.
   */
  public void removePointsFromScoreGoal(int points) {
    if (minimumPoints - points < 0) {
      throw new IllegalArgumentException("The score goal cannot be negative.");
    }

    minimumPoints -= points;
  }

  /**
   * Gets the minimum points the player must have to fulfill the goal.
   *
   * @return The minimum points the player must have to fulfill the goal.
   */
  public int getMinimumPoints() {
    return minimumPoints;
  }

  /**
   * Sets the minimum points the player must have to fulfill the goal.
   *
   * @param minimumPoints The amount of points to set.
   * @throws IllegalArgumentException if the score goal is negative.
   */
  public void setMinimumPoints(int minimumPoints) {
    if (minimumPoints < 0) {
      throw new IllegalArgumentException("The score goal cannot be negative.");
    }
    this.minimumPoints = minimumPoints;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isFulfilled(Player player) {
    int playerScore = player.getScore();
    return minimumPoints <= playerScore;
  }

  /** {@inheritDoc} */
  @Override
  public Object getGoalValue() {
    return getMinimumPoints();
  }

  /** {@inheritDoc} */
  @Override
  public void setGoalValue(Object goalValue) {
    if (!(goalValue instanceof Integer)) {
      throw new IllegalArgumentException("The goal value must be an integer.");
    }

    setMinimumPoints((int) goalValue);
  }
}
