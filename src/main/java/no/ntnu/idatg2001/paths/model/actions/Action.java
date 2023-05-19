package no.ntnu.idatg2001.paths.model.actions;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import no.ntnu.idatg2001.paths.model.Link;
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

  @ManyToOne
  @JoinColumn(name = "link_id")
  private Link link;

  /**
   * Executes the action.
   *
   * @param player the player who is performing the action
   */
  public abstract void execute(Player player);

  public abstract Object getActionValue();

  public abstract void setActionValue(Object actionValue);

  public abstract Boolean getActionIsPositive();
}
