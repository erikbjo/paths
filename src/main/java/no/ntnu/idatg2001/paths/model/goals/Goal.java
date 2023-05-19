package no.ntnu.idatg2001.paths.model.goals;

import jakarta.persistence.*;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Goal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public abstract boolean isFulfilled(Player player);

  public abstract Object getGoalValue();

  public abstract void setGoalValue(Object goalValue);
}
