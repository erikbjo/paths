package no.ntnu.idatg2001.paths.model;

import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.goals.ScoreGoal;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  private Player testPlayer;
  private Story testStory;
  private Game testGame;
  private List<Goal> goals;
  private Passage testPassage1;
  private Passage testPassage2;
  private Link link1to2;

  @BeforeEach
  void setUp() {
    testPlayer = new Player.PlayerBuilder().withName("Test").build();
    testPassage1 = new Passage("TestPassageTitle", "TestPassageContent");
    testPassage2 = new Passage("TestPassageTitle2", "TestPassageContent2");
    link1to2 = new Link("TestLink", testPassage2.getTitle());
    testPassage1.addLink(link1to2);
    testStory = new Story("TestStoryTitle", testPassage1);
    testStory.addPassage(testPassage2);
    goals = new ArrayList<>(List.of(new ScoreGoal(1)));
    testGame = new Game(testPlayer, testStory, goals);
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getPlayer() {
    assertEquals(testPlayer, testGame.getPlayer());
  }

  @Test
  void setPlayer() {
    Player newPlayer = new Player.PlayerBuilder().withName("NewTest").build();
    testGame.setPlayer(newPlayer);
    assertEquals(newPlayer, testGame.getPlayer());
  }

  @Test
  void getStory() {
    assertEquals(testStory, testGame.getStory());
  }

  @Test
  void setStory() {
    Story newStory =
        new Story("NewTestStoryTitle", new Passage("NewTestPassageTitle", "NewTestPassageContent"));
    testGame.setStory(newStory);
    assertEquals(newStory, testGame.getStory());
  }

  @Test
  void getGoals() {
    assertEquals(goals, testGame.getGoals());
  }

  @Test
  void testThatBeginReturnTheFirstPassage() {
    assertEquals(testPassage1, testGame.begin());
  }

  @Test
  void testThatGoReturnThePassageThatIsLinked() {
    assertEquals(testPassage2, testGame.go(link1to2));
  }

  @Test
  void testGetCurrentPassageShouldReturnFirstPassage() {
    assertEquals(testGame.getCurrentPassage(), testPassage1);
  }

  @Test
  void getCurrentPassage() {
    assertEquals(testGame.getCurrentPassage(), testPassage1);
  }

  @Test
  void setCurrentPassage() {
    testGame.setCurrentPassage(testPassage2);
    assertEquals(testGame.getCurrentPassage(), testPassage2);
  }
}
