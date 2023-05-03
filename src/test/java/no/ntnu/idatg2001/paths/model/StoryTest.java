package no.ntnu.idatg2001.paths.model;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoryTest {
  private Story story;
  private Passage passage1;
  private Passage passage2;
  private Passage passage3;
  private Passage passage4;
  private Player player;
  private Link link1;
  private Link link2;
  private Link link3;

  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder().withName("Erik").build();

    passage1 = new Passage("Passage 1", "This is passage 1");
    passage2 = new Passage("Passage 2", "This is passage 2");
    passage3 = new Passage("Passage 3", "This is passage 3");
    passage4 = new Passage("Passage 4", "This is passage 4");

    link1 = new Link("Link 1", "link1");
    link2 = new Link("Link 2", "link2");
    link3 = new Link("Link 3", "link3");

    passage1.addLink(link1);
    passage2.addLink(link1);

    passage2.addLink(link2);
    passage3.addLink(link2);

    passage2.addLink(link3);
    passage4.addLink(link3);

    story = new Story("Test story", passage1);
    story.addPassage(passage2);
    story.addPassage(passage3);
    story.addPassage(passage4);
  }

  @AfterEach
  void tearDown() {}

  @Test
  void testGetCurrentPassageShouldReturnFirstPassage() {
    assertEquals(story.getCurrentPassage(), passage1);
  }

  @Test
  void testGetPassagesConnectedWithLink1IsGood() {
    assertEquals(2, story.getPassagesConnectedWithLink(link1).size());
  }

  @Test
  void testGetPassagesConnectedWithLink2IsGood() {
    assertEquals(2, story.getPassagesConnectedWithLink(link2).size());
  }

  @Test
  void getTitle() {}

  @Test
  void getOpeningPassage() {}

  @Test
  void addPassage() {}
}
