package no.ntnu.idatg2001;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkTest
{
    Link successLink;
    Link failureLink;

    @BeforeEach
    void setUp(){
        this.successLink = new Link("Success","1");
        this.failureLink = new Link("Failure","1");
    }

    @Test
    void testToStringPositive()
    {
        String expectedStringText1 = "Success";
        assertEquals(expectedStringText1,expectedStringText1, successLink.toString());
    }

    @Test
    void testToStringNegative()
    {
        assertNotEquals("Test", failureLink.toString());
    }

    @AfterEach
    void tearDown(){

    }
}
