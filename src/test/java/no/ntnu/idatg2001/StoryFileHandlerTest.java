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
    Story story1;
    Story story2;

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
        story1 = new Story("Story 1", passage1);
        story2 = new Story("Story 2", passage2);
        expectedContent = "Story 1\n\n"
            + "::Test Passage 1\n"
            + "The first passage in the game.\n"
            + "[Go to Passage 2](Test Passage 2)\n"
            + "\n\n"
            + "Story 2\n\n"
            + "::Test Passage 2\n"
            + "The second passage in the game.\n"
            + "[Go to Passage 1](Test Passage 1)\n"
            + "\n\n";
    }

    @Test
    void writeAndReadPathsFile() throws IOException {
        passage1.addLinks(link1);
        passage2.addLinks(link2);
        story1.addPassage(passage1);
        story2.addPassage(passage2);
        storyList.add(story1);
        storyList.add(story2);

        StoryFileHandler.writePathsFile(storyList, filename);
        fileContent = Files.readString(pathToFile);
        //fileContent = StoryFileHandler.readStoryFile(filename).toString();
        assertEquals(expectedContent, fileContent);
        reader.close();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.delete(pathToFile);
    }
}