package no.ntnu.idatg2001.units;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import no.ntnu.idatg2001.units.Player;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

  Attributes testAttributes = new Attributes(1,1,1,1,1,1,1);
  Player testPlayer = new Player("Test", 5,5,5,5,5,testAttributes);




  @Test
  void dialog() {
  }

  @Test
  void addHealth_mustEndInPositiveHealth() throws Exception {
    testPlayer.setHealth(20);
    testPlayer.addHealth(-19);

    System.out.println(testPlayer.getHealth());
    assertTrue(testPlayer.getHealth()>=0, "Yoyooyo");
  }

  @Test
  public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
    //Attributes testAttributes = new Attributes(1,1,1,1,1,1,1);
    //Player testPlayer = new Player("Test", 5,5,5,5,5,testAttributes);

    testPlayer.setEnergy(5);

    final Field field = testPlayer.getClass().getSuperclass().getDeclaredField("energy");
    field.setAccessible(true);
    assertEquals(5, field.get(testPlayer), "5");
  }
}
public class PlayerTest
{
    Attributes testAttributes =
        new Attributes(1,1,1,1,1,1,1);
    Player testPlayer = new Player("Test",
        5,5,5,5,5,testAttributes);

    @Test
    void dialog() {
    }

    @Test
    void addHealth_mustEndInPositiveHealth() throws Exception {
        testPlayer.setHealth(20);
        testPlayer.addHealth(-19);

        System.out.println(testPlayer.getHealth());
        assertTrue(testPlayer.getHealth()>=0, "Yoyooyo");
    }

    @Test
    public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //Attributes testAttributes = new Attributes(1,1,1,1,1,1,1);
        //Player testPlayer = new Player("Test", 5,5,5,5,5,testAttributes);

        testPlayer.setEnergy(5);

        final Field field = testPlayer.getClass().getSuperclass().getDeclaredField("energy");
        field.setAccessible(true);
        assertEquals(5, field.get(testPlayer), "5");
    }
}
