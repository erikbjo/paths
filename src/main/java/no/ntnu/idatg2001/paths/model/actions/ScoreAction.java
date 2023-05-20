package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.Entity;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The ScoreAction class represents an action that gives the player score.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023-04-19
 */
@Entity
public class ScoreAction extends Action {
  private boolean isPositive;
  private int points;

  /**
   * Constructor for the ScoreAction class.
   *
   * @param points the amount of points to give the player
   */
  public ScoreAction(int points, boolean isPositive) {
    setPoints(points);
    setIsPositive(isPositive);
  }

  /** Empty constructor for the ScoreAction class. Used by JPA. */
  public ScoreAction() {}

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
   * Returns the amount of points the action gives.
   *
   * @return the amount of points the action gives
   */
  public int getPoints() {
    return points;
  }

  /**
   * Sets the amount of points the action gives.
   *
   * @param points the amount of points the action gives
   * @throws IllegalArgumentException if the points value is negative
   */
  public void setPoints(int points) {
    if (points < 0) {
      throw new IllegalArgumentException("Points cannot be negative");
    }
    this.points = points;
  }

  /** {@inheritDoc} */
  @Override
  public void execute(Player player) {
    player.setScore(player.getScore() + points);
  }

  /** {@inheritDoc} */
  @Override
  public Object getActionValue() {
    return getPoints();
  }

  /** {@inheritDoc} */
  @Override
  public void setActionValue(Object actionValue) {
    if (!(actionValue instanceof Integer))
      throw new IllegalArgumentException("Action value must be an integer");

    setPoints((Integer) actionValue);
  }

  /** {@inheritDoc} */
  @Override
  public Boolean getActionIsPositive() {
    return getIsPositive();
  }
}
