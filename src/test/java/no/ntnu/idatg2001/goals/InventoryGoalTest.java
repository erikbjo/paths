package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.units.Attributes;
import no.ntnu.idatg2001.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryGoalTest {
  Attributes attributes;
  Player player;
  List<String> listWithAllMandatoryItems;
  List<String> listWithoutAllMandatoryItems;
  InventoryGoal inventoryGoalAchieved;
  InventoryGoal inventoryGoalNotAchieved;

  @BeforeEach
  void setUp() {
    this.attributes = new Attributes(1, 1, 1, 1, 1, 1, 1);
    this.player = new Player("Test", 9, 7, 9, 6, 12, attributes);
    this.listWithAllMandatoryItems = new ArrayList<>();
    this.listWithoutAllMandatoryItems = new ArrayList<>();
    listWithAllMandatoryItems.add("Sword");
    listWithAllMandatoryItems.add("Dagger");
    listWithAllMandatoryItems.add("Wand");
    this.inventoryGoalAchieved = new InventoryGoal(listWithAllMandatoryItems);
    this.inventoryGoalNotAchieved = new InventoryGoal(listWithoutAllMandatoryItems);
  }

  @Test
  void inventoryGoalIsFulfilled() {
    player.getInventory().add("Sword");
    player.getInventory().add("Dagger");
    player.getInventory().add("Wand");
    assertTrue(inventoryGoalAchieved.isFulfilled(player));
  }

  @Test
  void inventoryGoalIsNotFulfilled() {
    player.setInventory(listWithoutAllMandatoryItems);
    assertFalse(inventoryGoalNotAchieved.isFulfilled(player));
  }

  @AfterEach
  void tearDown() {}
}
