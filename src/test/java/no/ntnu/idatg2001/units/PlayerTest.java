package no.ntnu.idatg2001.units;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import no.ntnu.idatg2001.model.actions.items.Item;
import no.ntnu.idatg2001.model.actions.items.consumables.BasePotion;
import no.ntnu.idatg2001.model.actions.items.equipables.Equipable;
import no.ntnu.idatg2001.model.actions.items.equipables.armor.Chest;
import no.ntnu.idatg2001.model.actions.items.equipables.weapons.Dagger;
import no.ntnu.idatg2001.model.units.Attributes;
import no.ntnu.idatg2001.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

  Attributes testAttributes;
  Player testPlayer;
  ArrayList<Item> inventory;
  ArrayList<Equipable> equippedInventory;
  Dagger testDagger;
  Chest testChest;
  BasePotion testPotion;

  @BeforeEach
  void setUp() {
    testAttributes = new Attributes(1, 1, 1, 1, 1, 1, 1);
    inventory = new ArrayList<>();
    equippedInventory = new ArrayList<>();
    testDagger = new Dagger("test", 1, 1, 1);
    testChest = new Chest("test", 1, 1, testAttributes);
    testPlayer =
        new Player.PlayerBuilder()
            .withName("Test")
            .withScore(5)
            .withGold(5)
            .withHealth(5)
            .withMana(5)
            .withEnergy(5)
            .withInventory(inventory)
            .withEquippedItems(equippedInventory)
            .withAttributes(testAttributes)
            .build();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void testThatEquipPutsItemInEquippedInventory() {
    testDagger.equip(testPlayer);

    System.out.println(testPlayer.getEquippedItems());

    assertTrue(testPlayer.getEquippedItems().contains(testDagger));
  }

  @Test
  void addHealth_mustEndInPositiveHealth() throws Exception {
    testPlayer.setHealth(20);
    testPlayer.addHealth(-29);

    System.out.println(testPlayer.getHealth());
    assertTrue(testPlayer.getHealth() >= 0);
  }

  @Test
  public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
    // Attributes testAttributes = new Attributes(1,1,1,1,1,1,1);
    // Player testPlayer = new Player("Test", 5,5,5,5,5,testAttributes);

    testPlayer.setEnergy(5);

    final Field field = testPlayer.getClass().getSuperclass().getDeclaredField("energy");
    field.setAccessible(true);
    assertEquals(5, field.get(testPlayer), "5");
  }
}
