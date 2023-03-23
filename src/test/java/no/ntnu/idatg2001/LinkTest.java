package no.ntnu.idatg2001;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkTest
{
    @Test
    void testToStringPositive()
    {
        Link link = new Link("Success","1");
        String expectedStringText1 = "Success";
        assertEquals(expectedStringText1,expectedStringText1, link.toString());
    }

    @Test
    void testToStringNegative()
    {
        Exception thrown = assertThrows(Exception.class, () -> {
            Link link = new Link("Failure","1");
            link.toString();
            }, "The expected error was thrown");
        assertEquals("Failure", thrown.getMessage());
    }

}
