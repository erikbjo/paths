package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Action interface represents an action that can be performed by the player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023-04-19
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Action {
  @Id @GeneratedValue private Long id;

  @ManyToOne
  @JoinColumn(name = "link_id")
  private Link link;

  /**
   * Returns the link that the action is held under.
   *
   * @return the link that the action is held under
   */
  public Link getLink() {
    return link;
  }

  /**
   * Sets the link that the action is held under.
   *
   * @param link the link that the action is held under
   */
  public void setLink(Link link) {
    this.link = link;
  }

  /**
   * Executes the action. This takes the action value and isPositive boolean, and performs the
   * action on the player.
   *
   * @param player the player is having the action performed on
   */
  public abstract void execute(Player player);

  /**
   * Returns the value of the action. This varies depending on the type of action.
   *
   * @return the value of the action
   */
  public abstract Object getActionValue();

  /**
   * Sets the value of the action. This varies depending on the type of action.
   *
   * @throws IllegalArgumentException if the action value is not of the correct type
   * @param actionValue the value of the action
   */
  public abstract void setActionValue(Object actionValue);

  /**
   * Returns if the action is positive or negative. Positive actions are actions that add something
   * to the player, while negative actions are actions that remove something.
   *
   * @return true if the action is positive, false if it is negative
   */
  public abstract Boolean getActionIsPositive();
}
