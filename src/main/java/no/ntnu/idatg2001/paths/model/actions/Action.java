package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Action interface represents an action that can be performed by the player.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Action {
  @Id @GeneratedValue private Long id;

  /**
   * Executes the action.
   *
   * @param player the player who is performing the action
   */
  public abstract void execute(Player player);
}
