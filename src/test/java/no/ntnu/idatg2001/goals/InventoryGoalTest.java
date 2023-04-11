package no.ntnu.idatg2001.goals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.actions.items.equipables.weapons.Dagger;
import no.ntnu.idatg2001.model.actions.items.equipables.weapons.Sword;
import no.ntnu.idatg2001.model.goals.InventoryGoal;
import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryGoalTest {
  Attributes attributes;
  Player player;
  List<Item> listWithAllMandatoryItems;
  List<Item> listWithoutAllMandatoryItems;
  InventoryGoal inventoryGoalAchieved;
  InventoryGoal inventoryGoalNotAchieved;
  Dagger testDagger;
  Item testSword;
  Item testBelt;
  Item testChest;

  @BeforeEach
  void setUp() {
    this.attributes = new Attributes(1, 1, 1, 1, 1, 1, 1);
    this.player =
        new Player.PlayerBuilder()
            .withName("John")
            .withScore(5)
            .withGold(100)
            .withHealth(100)
            .withMana(50)
            .withEnergy(50)
            .withAttributes(attributes)
            .build();
    this.listWithAllMandatoryItems = new ArrayList<>();
    this.listWithoutAllMandatoryItems = new ArrayList<>();

    testDagger = new Dagger("dagger", 1, 1);
    testSword = new Sword("sword", 1, 1);
    listWithAllMandatoryItems.add(testDagger);
    listWithAllMandatoryItems.add(testSword);

    this.inventoryGoalAchieved = new InventoryGoal(listWithAllMandatoryItems);
    this.inventoryGoalNotAchieved = new InventoryGoal(listWithoutAllMandatoryItems);
  }

  @Test
  void inventoryGoalIsFulfilled() {
    player.setInventory(listWithAllMandatoryItems);

    System.out.println(player.getInventory());
    System.out.println(listWithAllMandatoryItems);

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
