package no.ntnu.idatg2001.paths.model.goals;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * Abstract class for the different types of goals. A goal is fulfilled when a player has reached a
 * certain value.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Goal {
  @Id @GeneratedValue private Long id;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  /**
   * Returns the id of the goal.
   *
   * @return the id of the goal
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id of the goal.
   *
   * @param id the id of the goal
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Returns the game that the goal is held under.
   *
   * @return the game that the goal is held under
   */
  public Game getGame() {
    return game;
  }

  /**
   * Sets the game that the goal is held under.
   *
   * @param game the game that the goal is held under
   */
  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * Returns whether the goal is fulfilled.
   *
   * @param player the player to check if the goal is fulfilled for
   * @return whether the goal is fulfilled
   */
  public abstract boolean isFulfilled(Player player);

  /**
   * Returns the value of the goal. This varies depending on the type of goal.
   *
   * @return the value of the goal
   */
  public abstract Object getGoalValue();

  /**
   * Sets the value of the goal. This varies depending on the type of goal.
   *
   * @throws IllegalArgumentException if the goal value is not of the correct type
   * @param goalValue the value of the goal
   */
  public abstract void setGoalValue(Object goalValue);
}
