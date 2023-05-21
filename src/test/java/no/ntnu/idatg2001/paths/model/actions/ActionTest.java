package no.ntnu.idatg2001.paths.model.actions;

import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.items.Item;
import no.ntnu.idatg2001.paths.model.items.equipables.armor.Belt;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

  private GoldAction goldAction;
  private HealthAction healthAction;
  private InventoryAction inventoryAction;
  private Item item;
  private ScoreAction scoreAction;
  private Player player;
  private Link link;

  @BeforeEach
  void setUp() {
    goldAction = new GoldAction(20, true);
    healthAction = new HealthAction(20, true);
    item = new Belt("Test", 20, 20, new Attributes(20, 20, 20, 20, 20, 20, 20));
    inventoryAction = new InventoryAction(item, true);
    scoreAction = new ScoreAction(20, true);
    link = new Link("Test", "Test reference");
    player = new Player.PlayerBuilder().withName("Test").build();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void assertSetNullLinkDoesNotThrow() {
    // Needed by DB
    assertDoesNotThrow(() -> goldAction.setLink(null));
    assertDoesNotThrow(() -> healthAction.setLink(null));
    assertDoesNotThrow(() -> inventoryAction.setLink(null));
    assertDoesNotThrow(() -> scoreAction.setLink(null));
  }

  @Test
  void assertExecuteAddsHealthToPlayer() {
    healthAction.execute(player);
    assertEquals(healthAction.getHealth(), player.getHealth());
  }

  @Test
  void assertExecuteRemovesHealthFromPlayer() {
    player.setHealth(healthAction.getHealth() * 2);
    healthAction.setIsPositive(false);
    healthAction.execute(player);
    assertEquals(healthAction.getHealth(), player.getHealth());
  }

  @Test
  void getActionValueReturnsCorrectClass() {
    assertEquals(goldAction.getActionValue(), goldAction.getGold());
    assertEquals(healthAction.getActionValue(), healthAction.getHealth());
    assertEquals(inventoryAction.getActionValue(), inventoryAction.getItem());
    assertEquals(scoreAction.getActionValue(), scoreAction.getPoints());
  }

  @Test
  void setActionValueWorksWithExpectedClasses() {
    goldAction.setActionValue(20);
    healthAction.setActionValue(20);
    inventoryAction.setActionValue(item);
    scoreAction.setActionValue(20);
  }

  @Test
  void assertThatSetActionValueThrowsOnWrongClass() {
    assertThrows(IllegalArgumentException.class, () -> goldAction.setActionValue(item));
    assertThrows(IllegalArgumentException.class, () -> healthAction.setActionValue(item));
    assertThrows(IllegalArgumentException.class, () -> inventoryAction.setActionValue(20));
    assertThrows(IllegalArgumentException.class, () -> scoreAction.setActionValue(item));
  }

  @Test
  void assertThatSetActionForIntDoesNotAcceptNegativeValues() {
    assertThrows(IllegalArgumentException.class, () -> goldAction.setActionValue(-20));
    assertThrows(IllegalArgumentException.class, () -> healthAction.setActionValue(-20));
    assertThrows(IllegalArgumentException.class, () -> scoreAction.setActionValue(-20));
  }

  @Test
  void getActionIsPositiveReturnsTrueValue() {
    assertTrue(goldAction.getActionIsPositive());
    assertTrue(healthAction.getActionIsPositive());
    assertTrue(inventoryAction.getActionIsPositive());
    assertTrue(scoreAction.getActionIsPositive());
  }
}
