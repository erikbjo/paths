package no.ntnu.idatg2001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StoryFileHandler
{
    public static List<Story> readStoryFile(Path path)
    {
        List<Story> stories = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(path))
        {
            String lineOfText;
            while ((lineOfText = fileReader.readLine()) != null)
            {
                String[] words = lineOfText.split(",");
                stories.add(new Story("Test", new Passage("Start", "123")));
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return stories;
    }

    public static void writePathsFile(List<Story> stories, Path path)
    {
        try (BufferedWriter fileWriter = Files.newBufferedWriter(path))
        {
            for (Story story : stories)
            {
                fileWriter.write(story.getTitle() + "\n" + "" + "\n");
                fileWriter.write("::" + story.getOpeningPassage() + "\n");
                fileWriter.write(story.getOpeningPassage().getContent() + "\n");
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
