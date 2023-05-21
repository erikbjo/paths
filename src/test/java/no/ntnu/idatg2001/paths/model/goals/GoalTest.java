package no.ntnu.idatg2001.paths.model.goals;

import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.items.consumables.AttributePotion;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoalTest {

  private GoldGoal testGoldGoal;
  private HealthGoal testHealthGoal;
  private InventoryGoal testInventoryGoal;
  private Item goalItem;
  private ScoreGoal testScoreGoal;
  private Player testPlayer;

  @BeforeEach
  void setUp() {
    testGoldGoal = new GoldGoal(100);
    testHealthGoal = new HealthGoal(100);
    goalItem = new AttributePotion("test", 1, 1, new Attributes(1, 1, 1, 1, 1, 1, 1));
    testInventoryGoal = new InventoryGoal(new ArrayList<>(List.of(goalItem)));
    testScoreGoal = new ScoreGoal(100);
    testPlayer =
        new Player.PlayerBuilder()
            .withName("Test")
            .withGold(101)
            .withHealth(101)
            .withScore(101)
            .withInventory(new ArrayList<>(List.of(goalItem)))
            .build();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void assertThatAllGoalsAreFulfilled() {
    assertTrue(testGoldGoal.isFulfilled(testPlayer));
    assertTrue(testHealthGoal.isFulfilled(testPlayer));
    assertTrue(testInventoryGoal.isFulfilled(testPlayer));
    assertTrue(testScoreGoal.isFulfilled(testPlayer));
  }

  @Test
  void assertThatGetGoalValueReturnsTheCorrectClass() {
    assertEquals(100, testGoldGoal.getGoalValue());
    assertEquals(100, testHealthGoal.getGoalValue());
    assertEquals(new ArrayList<>(List.of(goalItem)), testInventoryGoal.getGoalValue());
    assertEquals(100, testScoreGoal.getGoalValue());
  }

  @Test
  void assertThatSetGoalValueWorksAsExpectedWithGoodValues() {
    testGoldGoal.setGoalValue(200);
    testHealthGoal.setGoalValue(200);
    testInventoryGoal.setGoalValue(new ArrayList<>(List.of(goalItem)));
    testScoreGoal.setGoalValue(200);

    assertEquals(200, testGoldGoal.getGoalValue());
    assertEquals(200, testHealthGoal.getGoalValue());
    assertEquals(new ArrayList<>(List.of(goalItem)), testInventoryGoal.getGoalValue());
    assertEquals(200, testScoreGoal.getGoalValue());
  }

  @Test
  void assertThatSetGoalValueThrowsIfWrongClass() {
    assertThrows(IllegalArgumentException.class, () -> testGoldGoal.setGoalValue(goalItem));
    assertThrows(IllegalArgumentException.class, () -> testHealthGoal.setGoalValue(goalItem));
    assertThrows(IllegalArgumentException.class, () -> testScoreGoal.setGoalValue(goalItem));
    assertThrows(IllegalArgumentException.class, () -> testScoreGoal.setGoalValue(goalItem));
  }

  @Test
  void assertThatSetGoalValueOfIntegerGoalsToNegativeIntThrows() {
    assertThrows(IllegalArgumentException.class, () -> testGoldGoal.setGoalValue(-1));
    assertThrows(IllegalArgumentException.class, () -> testHealthGoal.setGoalValue(-1));
    assertThrows(IllegalArgumentException.class, () -> testScoreGoal.setGoalValue(-1));
  }
}
