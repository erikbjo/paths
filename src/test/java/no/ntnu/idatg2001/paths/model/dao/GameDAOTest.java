package no.ntnu.idatg2001.paths.model.dao;

import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.goals.ScoreGoal;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameDAOTest {
  private GameDAO gameDAO;
  private Game testGame;
  private Player testPlayer;
  private Story testStory;
  private List<Goal> testGoals;

  @BeforeEach
  void setUp() {
    gameDAO = GameDAO.getInstance();
    testGame = new Game();
    gameDAO.add(testGame);

    testPlayer = new Player.PlayerBuilder().withName("Test").build();
    testStory = new Story("Test", new Passage("Test", "Test"));
    testGoals = new ArrayList<Goal>(List.of(new ScoreGoal(5)));

    testGame.setPlayer(testPlayer);
    testGame.setStory(testStory);
    testGame.setGoals(testGoals);

    gameDAO.update(testGame);
  }

  @AfterEach
  void tearDown() {
    if (gameDAO.getAll().contains(testGame)) {
      gameDAO.remove(testGame);
    }
  }

  @Test
  void testAddAndFind() {
    Optional<Game> foundGame = gameDAO.find(testGame.getId());
    assertTrue(foundGame.isPresent());
    assertEquals(testGame, foundGame.get());
  }

  @Test
  void testAddAlreadyAddedGame() {
    assertThrows(Exception.class, () -> gameDAO.add(testGame));
  }

  @Test
  void testRemove() {
    gameDAO.remove(testGame);
    Optional<Game> foundGame = gameDAO.find(testGame.getId());
    assertFalse(foundGame.isPresent());
  }

  @Test
  void testUpdate() {
    testGame.getPlayer().setName("Ozzy");
    gameDAO.update(testGame);
    Optional<Game> foundGame = gameDAO.find(testGame.getId());
    assertTrue(foundGame.isPresent());
    assertEquals("Ozzy", foundGame.get().getPlayer().getName());
  }

  @Test
  void testThatIteratorReturnsAGoodIterator() {
    Iterator<Game> iterator = gameDAO.iterator();

    Game newGame = new Game();
    gameDAO.add(newGame);

    assertTrue(iterator.hasNext());

    gameDAO.remove(newGame);
  }
}
