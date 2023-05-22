package no.ntnu.idatg2001.paths.model;

import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class StoryTest {
  private Story story;
  private Passage passage1;
  private Passage passage2;
  private Passage passage3;
  private Passage passage4;
  private Player player;
  private Link link1to2;
  private Link link1to3;
  private Link link2to3;
  private Link link3to4;

  @BeforeEach
  void setUp() {
    player = new Player.PlayerBuilder().withName("Erik").build();

    passage1 = new Passage("Passage 1", "This is passage 1");
    passage2 = new Passage("Passage 2", "This is passage 2");
    passage3 = new Passage("Passage 3", "This is passage 3");
    passage4 = new Passage("Passage 4", "This is passage 4");

    link1to2 = new Link("Link 1-2", passage2.getTitle());
    link1to3 = new Link("Link 1-3", passage3.getTitle());
    link2to3 = new Link("Link 2-3", passage3.getTitle());
    link3to4 = new Link("Link 3-4", passage4.getTitle());

    passage1.addLink(link1to2);
    passage1.addLink(link1to3);
    passage2.addLink(link2to3);
    passage3.addLink(link3to4);

    story = new Story("Test story", passage1);
    story.addPassage(passage2);
    story.addPassage(passage3);
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getOpeningPassage() {
    assertEquals(story.getOpeningPassage(), passage1);
  }

  @Test
  void setOpeningPassage() {
    story.setOpeningPassage(passage2);
    assertEquals(story.getOpeningPassage(), passage2);
  }

  @Test
  void assertThatSetOpeningPassageThrowsOnNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          story.setOpeningPassage(null);
        });
  }

  @Test
  void addPassage() {
    story.addPassage(passage4);
    assertTrue(story.getPassages().contains(passage4));
  }

  @Test
  void addPassageThrowsOnNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          story.addPassage(null);
        });
  }

  @Test
  void getPassages() {
    List<Passage> passages = story.getPassages();
    assertTrue(passages.contains(passage2));
    assertTrue(passages.contains(passage3));
  }

  @Test
  void removePassage() {
    story.removePassage(link1to2);
    assertFalse(story.getPassages().contains(passage2));
  }

  @Test
    void removePassageThrowsOnNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
            story.removePassage(null);
            });
    }

  @Test
  void getBrokenLinksReturnsABrokenLink() {
    Link brokenLink = new Link("Broken link", "This is a broken link");
    story.addPassage(passage4);
    passage4.addLink(brokenLink);
    System.out.println(story.getBrokenLinks());
    assertTrue(story.getBrokenLinks().contains(brokenLink));
  }

  @Test
    void getBrokenLinksReturnsNoBrokenLinks() {
        story.addPassage(passage4);
        assertFalse(story.getBrokenLinks().contains(link3to4));
    }

    @Test
  void assertGetAmountOfLinksToPassages() {
    assertEquals(2, story.shortestPathFromOpeningPassage(passage2));
    assertEquals(2, story.shortestPathFromOpeningPassage(passage3));
    story.addPassage(passage4);
    assertEquals(3, story.shortestPathFromOpeningPassage(passage4));
    }

    @Test
  void assertShortestPathThrowsOnNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          story.shortestPathFromOpeningPassage(null);
        });
  }
}
