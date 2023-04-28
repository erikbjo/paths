package no.ntnu.idatg2001;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PassageTest {

  Passage passage;
  List<Link> links;

  @BeforeEach
  void setUp() {
    this.passage = new Passage("test", "123");
    this.links = new ArrayList<>();
    this.links.add(new Link("test1", "1"));
    this.links.add(new Link("test2", "2"));
  }

  @AfterEach
  void tearDown() {

  }
  @Test
  void addLinksShouldReturnTrue() {
    passage.setLinks(links);
    boolean resultTrue = passage.addLinks(new Link("test3", "3"));
    assertTrue(resultTrue);
  }

  @Test
  void addLinksShouldReturnFalse() {
    links.remove(0);
    passage.setLinks(links);
    boolean resultFalse = passage.addLinks(new Link("False test", "123"));
    assertFalse(resultFalse);
  }
}
