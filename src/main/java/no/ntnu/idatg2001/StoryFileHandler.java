package no.ntnu.idatg2001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.actions.Action;
import no.ntnu.idatg2001.units.Attributes;
import no.ntnu.idatg2001.units.Player;

public class StoryFileHandler {
  public static void writePathsFile(List<Story> stories, String filename) {
    //Passage passage1 = new Passage("Beginning of story","You encounter a witch.");
    //Link link1 = new Link("What do you want to do?", "Witch actions");
    //passage1.addLinks(link1);
    //Story story1 = new Story("The Haunted House", passage1);
    //stories.add(story1);
    try (BufferedWriter fileWriter = Files.newBufferedWriter(Path.of(filename))) {
      for (Story story : stories) {
        fileWriter.write(story.getTitle() + "\n\n");
        fileWriter.write("::" + story.getOpeningPassage().getTitle() + "\n");
        fileWriter.write(story.getOpeningPassage().getContent() + "\n");

        List<Link> links = story.getOpeningPassage().getLinks();
        for (Link link : links) {
          String text = link.getText();
          String reference = link.getReference();
          fileWriter.write("[" + text + "]" + "(" + reference + ")" + "\n");

          List<Action> actions = link.getActions();
          for (Action action : actions) {
            Attributes attributes = new Attributes(1,1,1,
                1,1,1,1);
            Player player = new Player("Test", 9,7,9,6,
                12,attributes);
            action.execute(player);;
            fileWriter.write("<" + action.getClass().getSimpleName() + ">" + "\n");
          }
        }
        fileWriter.write("\n\n");
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static List<Story> readStoryFile(String filename) {
    List<Story> stories = new ArrayList<>();
    try (BufferedReader fileReader = Files.newBufferedReader(Path.of(filename))) {
      String lineOfText;
      while ((lineOfText = fileReader.readLine()) != null) {
        String[] words = lineOfText.split(",");
        stories.add(new Story("Test", new Passage("Start", "123")));
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return stories;
  }
}
