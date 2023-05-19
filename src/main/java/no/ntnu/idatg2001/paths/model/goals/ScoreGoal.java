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
   */
  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  protected ScoreGoal() {}

  /**
   * Adds points to the minimum points the player must have to fulfill the goal.
   *
   * @param points The amount of points to add.
   */
  public void addPointsToScoreGoal(int points) {
    minimumPoints += points;
  }

  /**
   * Removes points from the minimum points the player must have to fulfill the goal.
   *
   * @param points The amount of points to remove.
   */
  public void removePointsFromScoreGoal(int points) {
    minimumPoints -= points;
  }

  /**
   * Sets the minimum points the player must have to fulfill the goal.
   *
   * @param points The amount of points to set.
   */
  public void setPointsToScoreGoal(int points) {
    minimumPoints = points;
  }

  public int getMinimumPoints() {
    return minimumPoints;
  }

  public void setMinimumPoints(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  /**
   * Checks if the player has fulfilled the goal.
   *
   * @param player The player to check.
   * @return True if the player has fulfilled the goal, false otherwise.
   */
  @Override
  public boolean isFulfilled(Player player) {
    int playerScore = player.getScore();
    return minimumPoints <= playerScore;
  }

  @Override
  public Object getGoalValue() {
    return getMinimumPoints();
  }

  @Override
  public void setGoalValue(Object goalValue) {
    setMinimumPoints((int) goalValue);
  }
}
