package no.ntnu.idatg2001.paths.model.database;

import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.units.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class StoryDAOTest {
  private StoryDAO storyDAO;
  private Story testStory;

  @BeforeEach
  void setUp() {
    storyDAO = new StoryDAO();
    testStory = new Story("Test", new Passage("Test", "Test passage"));
    storyDAO.add(testStory);
  }

  @AfterEach
  void tearDown() {
    storyDAO.close();
    storyDAO = new StoryDAO();
    if (storyDAO.getAll().contains(testStory)) {
      storyDAO.remove(testStory);
    }
    storyDAO.close();
  }

  @Test
  void testAddAndFind() {
    Optional<Story> foundStory = storyDAO.find(testStory.getId());
    assertTrue(foundStory.isPresent());
    assertEquals(testStory, foundStory.get());
  }

  @Test
  void testRemove() {
    storyDAO.remove(testStory);
    Optional<Story> foundStory = storyDAO.find(testStory.getId());
    assertFalse(foundStory.isPresent());
  }

  @Test
  void testUpdate() {
    testStory.setCurrentPassage(new Passage("Ozzy", "Ozzy passage"));
    storyDAO.update(testStory);
    Optional<Story> foundStory = storyDAO.find(testStory.getId());
    assertTrue(foundStory.isPresent());
    assertEquals("Ozzy", foundStory.get().getCurrentPassage().getTitle());
  }

  @Test
  void testThatIteratorReturnsAGoodIterator() {
    Iterator<Story> iterator = storyDAO.iterator();

    Story newStory = new Story("New test", new Passage("New test", "New test passage"));
    storyDAO.add(newStory);

    assertTrue(iterator.hasNext());

    storyDAO.remove(newStory);
  }
}
