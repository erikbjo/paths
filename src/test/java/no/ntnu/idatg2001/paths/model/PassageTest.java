package no.ntnu.idatg2001.paths.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PassageTest {

  private Passage passage;
  private List<Link> links;

  @BeforeEach
  void setUp() {
    this.passage = new Passage("test", "123");
    this.links = new ArrayList<>();
    this.links.add(new Link("test1", "1"));
    this.links.add(new Link("test2", "2"));
  }

  @AfterEach
  void tearDown() {}

  @Test
  void assertThatSetTitleThrowsOnInvalidStrings() {
    assertThrows(IllegalArgumentException.class, () -> passage.setTitle(null));
    assertThrows(IllegalArgumentException.class, () -> passage.setTitle(""));
    assertThrows(IllegalArgumentException.class, () -> passage.setTitle(" "));
  }

  @Test
  void assertThatSetContentThrowsOnInvalidStrings() {
    assertThrows(IllegalArgumentException.class, () -> passage.setContent(null));
    assertThrows(IllegalArgumentException.class, () -> passage.setContent(""));
    assertThrows(IllegalArgumentException.class, () -> passage.setContent(" "));
  }

  @Test
  void assertThatAddLinkThrowsOnInvalidLink() {
    assertThrows(IllegalArgumentException.class, () -> passage.addLink(null));
  }

  @Test
  void assertThatAddLinkWorksOnGoodLink() {
    Link link = new Link("test", "123102318231231");
    passage.addLink(link);
    assertTrue(passage.getLinks().contains(link));
  }

  @Test
  void assertThatAddLinkThrowsOnDuplicateLink() {
    Link link = new Link("test", "123102318231231");
    passage.addLink(link);
    assertThrows(IllegalArgumentException.class, () -> passage.addLink(link));
  }

  @Test
  void assertThatSetLinksThrowsOnNull() {
    assertThrows(IllegalArgumentException.class, () -> passage.setLinks(null));
  }

  @Test
  void assertThatSetLinksThrowsOnEmptyList() {
    List<Link> emptyList = new ArrayList<>();
    assertThrows(IllegalArgumentException.class, () -> passage.setLinks(emptyList));
  }

  @Test
  void assertThatSetLinksWorksOnGoodList() {
    links.add(new Link("test3", "3"));
    passage.setLinks(links);
    assertTrue(passage.getLinks().containsAll(links));
  }

  @Test
  void assertThatSetLinksThrowsOnDuplicateLink() {
    links.add(new Link("test1", "1"));
    assertThrows(IllegalArgumentException.class, () -> passage.setLinks(links));
  }

  @Test
  void asserThatRemoveLinkThrowsOnNull() {
    assertThrows(IllegalArgumentException.class, () -> passage.removeLink(null));
  }

  @Test
  void asserThatRemoveLinkWorksOnGoodLink() {
    Link link = new Link("test", "123102318231231");
    passage.addLink(link);
    passage.removeLink(link);
    assertFalse(passage.getLinks().contains(link));
  }
}
