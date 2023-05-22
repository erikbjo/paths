package no.ntnu.idatg2001.paths.model.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Objects;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PathsStoryFileReaderTest {

  private String path;
  private File file;

  @BeforeEach
  void setUp() {
    path = Objects.requireNonNull(this.getClass().getResource("/AlienStoryTest.paths")).getPath();
    file = new File(path);
  }

  @AfterEach
  void tearDown() {
    StoryDAO.getInstance().getAll().stream()
        .filter(story -> story.getTitle().equals("AlienStoryTest"))
        .forEach(StoryDAO.getInstance()::remove);
  }

  @Test
  void readStoryFromFileAddsStoryToDB() {
    assertDoesNotThrow(() -> PathsStoryFileReader.readStoryFromFile(file));
    assertTrue(
        StoryDAO.getInstance().getAll().stream()
            .anyMatch(story -> story.getTitle().equals("AlienStoryTest")));
  }

  @Test
  void assertReadStoryFromFileThrowsExceptionWhenFileDoesNotExist() {
    File badFile = new File("badFile");
    assertThrows(
        // Throws IOException AND FileNotFoundException
        Exception.class, () -> PathsStoryFileReader.readStoryFromFile(badFile));
  }

  @Test
  void assertThatReadStoryFromFileThrowsOnInvalidFile() {
    String pathToBad =
        Objects.requireNonNull(this.getClass().getResource("/AlienStoryTestBad.paths")).getPath();
    assertThrows(
        // Throws lots of exceptions
        Exception.class, () -> PathsStoryFileReader.readStoryFromFile(new File(pathToBad)));
  }

  @Test
  void assertThatReadStoryImportsTheCorrectAmountOfPassagesAndLinks() {
    assertDoesNotThrow(() -> PathsStoryFileReader.readStoryFromFile(file));
    assertEquals(
        12,
        StoryDAO.getInstance().getAll().stream()
            .filter(story -> story.getTitle().equals("AlienStoryTest"))
            .findFirst()
            .orElseThrow()
            .getPassages()
            .size());

    assertEquals(
        17,
        StoryDAO.getInstance().getAll().stream()
            .filter(story -> story.getTitle().equals("AlienStoryTest"))
            .findFirst()
            .orElseThrow()
            .getPassages()
            .stream()
            .mapToInt(passage -> passage.getLinks().size())
            .sum());
  }
}
