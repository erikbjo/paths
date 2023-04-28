package no.ntnu.idatg2001.paths.goals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.paths.model.actions.items.Item;
import no.ntnu.idatg2001.paths.model.actions.items.equipables.armor.Belt;
import no.ntnu.idatg2001.paths.model.actions.items.equipables.armor.Chest;
import no.ntnu.idatg2001.paths.model.actions.items.equipables.weapons.Dagger;
import no.ntnu.idatg2001.paths.model.actions.items.equipables.weapons.Sword;
import no.ntnu.idatg2001.paths.model.goals.InventoryGoal;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryGoalTest {
  private Attributes attributes;
  private Player player;
  private List<Item> listWithAllMandatoryItems;
  private List<Item> listWithoutAllMandatoryItems;
  private InventoryGoal inventoryGoalAchieved;
  private InventoryGoal inventoryGoalNotAchieved;
  private Dagger testDagger;
  private Item testSword;
  private Item testBelt;
  private Item testChest;

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

    testDagger = new Dagger("dagger", 1, 1, 1);
    testSword = new Sword("sword", 1, 1, 1);
    listWithAllMandatoryItems.add(testDagger);
    listWithAllMandatoryItems.add(testSword);

    testBelt = new Belt("belt", 1, 1, attributes);
    testChest = new Chest("chest", 1, 1, attributes);
    listWithoutAllMandatoryItems.add(testBelt);
    listWithoutAllMandatoryItems.add(testChest);

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
    assertFalse(inventoryGoalAchieved.isFulfilled(player));
  }

  @AfterEach
  void tearDown() {}
}
