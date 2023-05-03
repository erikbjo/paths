package no.ntnu.idatg2001.paths.model.database;

import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class PlayerDAOTest {
  private PlayerDAO playerDAO;
  private Player testPlayer;

  @BeforeEach
  void setUp() {
    playerDAO = PlayerDAO.getInstance();
    testPlayer = new Player.PlayerBuilder().withName("Test").build();
    playerDAO.add(testPlayer);
  }

  @AfterEach
  void tearDown() {
    if (playerDAO.getAll().contains(testPlayer)) {
      playerDAO.remove(testPlayer);
    }
  }

  @Test
  void testAddAndFind() {
    Optional<Player> foundPlayer = playerDAO.find(testPlayer.getId());
    assertTrue(foundPlayer.isPresent());
    assertEquals(testPlayer, foundPlayer.get());
  }

  @Test
  void testAddAlreadyAddedAccount() {
    assertThrows(IllegalArgumentException.class, () -> playerDAO.add(testPlayer));
  }

  @Test
  void testRemove() {
    playerDAO.remove(testPlayer);
    Optional<Player> foundPlayer = playerDAO.find(testPlayer.getId());
    assertFalse(foundPlayer.isPresent());
  }

  @Test
  void testUpdate() {
    testPlayer.setName("Ozzy");
    playerDAO.update(testPlayer);
    Optional<Player> foundPlayer = playerDAO.find(testPlayer.getId());
    assertTrue(foundPlayer.isPresent());
    assertEquals("Ozzy", foundPlayer.get().getName());
  }

  @Test
  void testThatIteratorReturnsAGoodIterator() {
    Iterator<Player> iterator = playerDAO.iterator();

    Player newPlayer = new Player.PlayerBuilder().withName("Test2").build();
    playerDAO.add(newPlayer);

    assertTrue(iterator.hasNext());

    playerDAO.remove(newPlayer);
  }
}
