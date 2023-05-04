/*
package no.ntnu.idatg2001.paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.PathActions;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.StoryFileHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoryFileHandlerTest {
    String filename;
    String fileContent;
    String expectedContent;
    BufferedReader reader;
    Path pathToFile;
    List<Story> storyList;
    Passage openingPassage;
    Passage testPassage;
    Link link1;
    Link link2;
    Link link3;
    Link link4;
    Story story1;
    PathActions action1;
    PathActions action2;
    PathActions action3;
    PathActions action4;

    @BeforeEach
    void setUp() throws IOException {
        filename = "testing.paths";
        pathToFile = Paths.get(filename);
        Files.createFile(pathToFile);
        reader = new BufferedReader(new FileReader(filename));
        storyList = new ArrayList<>();
        openingPassage = new Passage("Opening Passage", "The first passage in the game.");
        testPassage = new Passage("Test Passage", "The second passage in the game.");
        link1 = new Link("Go to passage 2", "Link 1");
        link2 = new Link("Go to passage 3", "Link 2");
        link3 = new Link("Go to passage 4", "Link 3");
        link4 = new Link("Go to passage 5", "Link 4");
        openingPassage.addLinks(link1);
        openingPassage.addLinks(link3);
        testPassage.addLinks(link2);
        testPassage.addLinks(link4);
        action1 = new PathActions("Attack the enemy", "Link 2");
        action2 = new PathActions("Find a diplomatic solution", "Link 4");
        action3 = new PathActions("Trade with the villager", "Link 1");
        action4 = new PathActions("Steal from the villager", "Link 3");
        link1.addPathAction(action3);
        link2.addPathAction(action1);
        link3.addPathAction(action4);
        link4.addPathAction(action2);
        story1 = new Story("Story 1", openingPassage);
        story1.addPassage(testPassage);
        story1.addPassage(openingPassage);
        storyList.add(story1);
        expectedContent =
            "Story 1\n\n"
                + "::Opening Passage\n"
                + "The first passage in the game.\n"
                + "[Go to passage 2](Link 1)\n"
                + "[Go to passage 4](Link 3)\n"
                + "<Attack the enemy>(Link 2)\n"
                + "<Find a diplomatic solution>(Link 4)\n"
                + "\n"
                + "::Test Passage\n"
                + "The second passage in the game.\n"
                + "[Go to passage 3](Link 2)\n"
                + "[Go to passage 5](Link 4)\n"
                + "<Trade with the villager>(Link 1)\n"
                + "<Steal from the villager>(Link 3)\n"
                + "\n\n";
    }

    @Test
    void writePathsFile() throws IOException {
        StoryFileHandler.writePathsFile(storyList, filename);
        fileContent = Files.readString(pathToFile);
        reader.close();
        assertEquals(expectedContent, fileContent);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(pathToFile);
    }
}*/
