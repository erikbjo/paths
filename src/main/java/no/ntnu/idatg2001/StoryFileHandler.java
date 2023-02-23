package no.ntnu.idatg2001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class StoryFileHandler
{
    public static ArrayList<Story> readStoryFile(Path path){
        ArrayList<Story> stories = new ArrayList<>();

        try (BufferedReader fileReader = Files.newBufferedReader(path)){
            String lineOfText;
            while((lineOfText = fileReader.readLine()) != null){
                String[] words = lineOfText.split(",");
                stories.add(new Story("Test", new Passage("Start","123")));
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return stories;
    }

    public static void writePathsFile(ArrayList<Story> stories, Path path){
        try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
            for (Story story : stories){
                stories.add(new Story())
            }
        }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
