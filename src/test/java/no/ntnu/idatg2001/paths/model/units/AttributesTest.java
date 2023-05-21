package no.ntnu.idatg2001.paths.model.units;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttributesTest {

  private Attributes testAttributes1;
  private Attributes testAttributes2;
  private Attributes testAttributes3;

  @BeforeEach
  void setUp() {
    testAttributes1 = new Attributes(1, 1, 1, 1, 1, 1, 1);
    testAttributes2 = new Attributes(2, 2, 2, 2, 2, 2, 2);
    testAttributes3 = new Attributes(3, 3, 3, 3, 3, 3, 3);
  }

  @AfterEach
  void tearDown() {}

  @Test
  void assertThatAllSettersThrowsOnNegativeValues() {
    assertThrows(IllegalArgumentException.class, () -> testAttributes1.setStrength(-1));
    assertThrows(IllegalArgumentException.class, () -> testAttributes1.setPerception(-1));
    assertThrows(IllegalArgumentException.class, () -> testAttributes1.setEndurance(-1));
    assertThrows(IllegalArgumentException.class, () -> testAttributes1.setCharisma(-1));
    assertThrows(IllegalArgumentException.class, () -> testAttributes1.setIntelligence(-1));
    assertThrows(IllegalArgumentException.class, () -> testAttributes1.setAgility(-1));
    assertThrows(IllegalArgumentException.class, () -> testAttributes1.setLuck(-1));
  }

  @Test
  void assertThatAddAttributesWorksAsExpected() {
    testAttributes1.addAttributes(testAttributes2);
    assertEquals(3, testAttributes1.getStrength());
    assertEquals(3, testAttributes1.getPerception());
    assertEquals(3, testAttributes1.getEndurance());
    assertEquals(3, testAttributes1.getCharisma());
    assertEquals(3, testAttributes1.getIntelligence());
    assertEquals(3, testAttributes1.getAgility());
    assertEquals(3, testAttributes1.getLuck());
  }

  @Test
  void assertThatSubtractAttributesWorksAsExpected() {
    testAttributes2.subtractAttributes(testAttributes1);
    assertEquals(1, testAttributes2.getStrength());
    assertEquals(1, testAttributes2.getPerception());
    assertEquals(1, testAttributes2.getEndurance());
    assertEquals(1, testAttributes2.getCharisma());
    assertEquals(1, testAttributes2.getIntelligence());
    assertEquals(1, testAttributes2.getAgility());
    assertEquals(1, testAttributes2.getLuck());
  }

  @Test
  void assertThatSubtractAttributesThrowsIfTheAttributesAreNegative() {
    assertThrows(
        IllegalArgumentException.class, () -> testAttributes1.subtractAttributes(testAttributes2));
  }
}
