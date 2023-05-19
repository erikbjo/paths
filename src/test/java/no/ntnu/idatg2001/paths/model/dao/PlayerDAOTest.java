package no.ntnu.idatg2001.paths.model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Optional;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerDAOTest {
  private PlayerDAO playerDAO;
  private Player testPlayer;

  @BeforeEach
  void setUp() {
    playerDAO = new PlayerDAO();
    testPlayer = new Player.PlayerBuilder().withName("Test").build();
    playerDAO.add(testPlayer);
  }

  @AfterEach
  void tearDown() {
    playerDAO.close();
    playerDAO = new PlayerDAO();
    if (playerDAO.getAll().contains(testPlayer)) {
      playerDAO.remove(testPlayer);
    }

    playerDAO.getAll().stream()
        .filter(player -> player.getName().equals("Ozzy"))
        .forEach(playerDAO::remove);
    playerDAO.getAll().stream()
        .filter(player -> player.getName().equals("Test"))
        .forEach(playerDAO::remove);

    playerDAO.close();
  }

  @Test
  void testAddAndFind() {
    Optional<Player> foundPlayer = playerDAO.find(testPlayer.getId());
    assertTrue(foundPlayer.isPresent());
    assertEquals(testPlayer, foundPlayer.get());
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
