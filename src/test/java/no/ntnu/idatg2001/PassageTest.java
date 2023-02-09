package no.ntnu.idatg2001;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PassageTest
{
    @Test
    void testAddLinks()
    {
        var passage = new Passage("test","123");
        assertTrue(passage.addLinks(new Link("test", "123")));
    }
}
