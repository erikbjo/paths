package no.ntnu.idatg2001.paths.model.units;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.items.consumables.BasePotion;
import no.ntnu.idatg2001.paths.model.items.equipables.Equipable;
import no.ntnu.idatg2001.paths.model.items.equipables.armor.Chest;
import no.ntnu.idatg2001.paths.model.items.equipables.weapons.Dagger;
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
  void addHealth_mustEndInPositiveHealth() {
    testPlayer.setHealth(20);
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.addHealth(-30);
        });
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

  @Test
  void testThatAddIntMethodsThrowsIfIntIsNegative() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.addEnergy(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.addGold(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.addHealth(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.addMana(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.addScore(-1);
        });
  }

  @Test
  void assertThatAddIntMethodsWorksAsExpected() {
    testPlayer.addEnergy(1);
    testPlayer.addGold(1);
    testPlayer.addHealth(1);
    testPlayer.addMana(1);
    testPlayer.addScore(1);

    assertEquals(6, testPlayer.getEnergy());
    assertEquals(6, testPlayer.getGold());
    assertEquals(6, testPlayer.getHealth());
    assertEquals(6, testPlayer.getMana());
    assertEquals(6, testPlayer.getScore());
  }

  @Test
  void assertThatSettersThrowsIfIntIsNegative() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.setEnergy(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.setGold(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.setHealth(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.setMana(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.setScore(-1);
        });
  }

  @Test
  void assertThatSettersWorksAsExpected() {
    testPlayer.setEnergy(1);
    testPlayer.setGold(1);
    testPlayer.setHealth(1);
    testPlayer.setMana(1);
    testPlayer.setScore(1);

    assertEquals(1, testPlayer.getEnergy());
    assertEquals(1, testPlayer.getGold());
    assertEquals(1, testPlayer.getHealth());
    assertEquals(1, testPlayer.getMana());
    assertEquals(1, testPlayer.getScore());
  }

  @Test
  void assertThatRemoveIntMethodsThrowsIfIntIsNegative() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.removeEnergy(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.removeGold(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.removeHealth(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.removeMana(-1);
        });
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          testPlayer.removeScore(-1);
        });
  }

  @Test
  void assertThatRemoveIntMethodsWorksAsExpected() {
    testPlayer.removeEnergy(1);
    testPlayer.removeGold(1);
    testPlayer.removeHealth(1);
    testPlayer.removeMana(1);
    testPlayer.removeScore(1);

    assertEquals(4, testPlayer.getEnergy());
    assertEquals(4, testPlayer.getGold());
    assertEquals(4, testPlayer.getHealth());
    assertEquals(4, testPlayer.getMana());
    assertEquals(4, testPlayer.getScore());
  }
}
