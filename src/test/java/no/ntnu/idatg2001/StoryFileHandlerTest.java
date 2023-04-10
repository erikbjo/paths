package no.ntnu.idatg2001;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.model.Link;
import no.ntnu.idatg2001.model.Passage;
import no.ntnu.idatg2001.model.PathActions;
import no.ntnu.idatg2001.model.Story;
import no.ntnu.idatg2001.model.StoryFileHandler;
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
    Passage passage1;
    Passage passage2;
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
        fileContent = Files.readString(pathToFile);
        storyList = new ArrayList<>();
        passage1 = new Passage("Test Passage 1", "The first passage in the game.");
        passage2 = new Passage("Test Passage 2", "The second passage in the game.");
        link1 = new Link("Go to Passage 2", "Test Passage 2");
        link2 = new Link("Go to Passage 1", "Test Passage 1");
        link3 = new Link("Go to passage 4", "Test Passage 4");
        link4 = new Link("Go to passage 3", "Test Passage 3");
        passage1.addLinks(link1);
        passage1.addLinks(link3);
        passage2.addLinks(link2);
        passage2.addLinks(link4);
        action1 = new PathActions("Attack the enemy", "Test Passage 2");
        action2 = new PathActions("Find a diplomatic solution", "Test Passage 4");
        action3 = new PathActions("Trade with the villager", "Test Passage 1");
        action4 = new PathActions("Steal from the villager", "Test Passage 3");
        link1.addPathAction(action1);
        link2.addPathAction(action3);
        link3.addPathAction(action2);
        link4.addPathAction(action4);
        story1 = new Story("Story 1", passage1);
        expectedContent =
            "Story 1\n\n"
                + "::Test Passage 1\n"
                + "The first passage in the game.\n"
                + "[Go to Passage 2](Test Passage 2)\n"
                + "[Go to Passage 4](Test Passage 4)\n"
                + "<Attack the enemy>(Test Passage 2)\n"
                + "<Find a diplomatic solution>(Test Passage 4)\n"
                + "\n"
                + "::Test Passage 2\n"
                + "The second passage in the game.\n"
                + "[Go to Passage 1](Test Passage 1)\n"
                + "[Go to Passage 3](Test Passage 3)\n"
                + "<Trade with the villager>(Test Passage 1)\n"
                + "<Steal from the villager>(Test Passage 3)\n"
                + "\n\n";
    }

    @Test
    void writeAndReadPathsFile() throws IOException {
        story1.addPassage(passage1);
        storyList.add(story1);

        StoryFileHandler.writePathsFile(storyList, filename);
        fileContent = Files.readString(pathToFile);
        assertEquals(expectedContent, fileContent);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(pathToFile);
    }
}