package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.paths.model.goals.GoldGoal;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoldGoalTest {
  Attributes attributes;
  Player playerTrue;
  Player playerFalse;
  GoldGoal goldGoal;

  @BeforeEach
  void setUp() {
    attributes = new Attributes(1, 1, 1, 1, 1, 1, 1);
    goldGoal = new GoldGoal(0);
    playerTrue = new Player.PlayerBuilder()
            .withGold(5)
            .build();
    playerFalse = new Player.PlayerBuilder()
            .withGold(-5)
            .build();
  }

  @Test
  void goldGoalIsFulfilled() {
    assertTrue(goldGoal.isFulfilled(playerTrue));
  }

  @Test
  void goldGoalIsNotFulfilled() {
    assertFalse(goldGoal.isFulfilled(playerFalse));
  }
}
