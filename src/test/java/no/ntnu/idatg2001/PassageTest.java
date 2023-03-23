package no.ntnu.idatg2001;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PassageTest
{
    @Test
    void addLinksShouldReturnTrue()
    {
        /**
        Passage passage = new Passage("test","123");
        List<Link> links = new ArrayList<>();
        links.add(new Link("test1","1"));
        links.add(new Link("test2","2"));
        links.add(new Link("test3","3"));
        assertTrue(links.size() >= 2,"Test success!");
        assertTrue(passage.addLinks(new Link("True test", "123")));
         */

        Passage passage = new Passage("test","123");
        List<Link> links = new ArrayList<>();
        links.add(new Link("test1","1"));
        links.add(new Link("test2","2"));
        boolean resultTrue = passage.addLinks(new Link("testTrue","3"));
        assertTrue(resultTrue);
    }

    @Test
    void addLinksShouldReturnFalse()
    {
        Passage passage = new Passage("test","123");
        List<Link> links = new ArrayList<>();
        links.add(new Link("test4","4"));
        boolean resultFalse = passage.addLinks(new Link("False test", "123"));
        assertFalse(resultFalse);
    }
}
