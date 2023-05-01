package no.ntnu.idatg2001.paths.model.goals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.06
 */
@Entity
public interface Goal {
  @Id
  @GeneratedValue
  Long id = null;
  boolean isFulfilled(Player player);
}
