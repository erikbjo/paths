package no.ntnu.idatg2001.paths.model.utilities;

import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

public class PathsStoryFileReader {
  private static final PathsStoryFileReader instance = new PathsStoryFileReader();

  private PathsStoryFileReader() {}

  public static PathsStoryFileReader getInstance() {
    return instance;
  }

  public Story readStoryFromFile(String fileName) throws IOException {
    try {
      File file = new File(fileName);

      Scanner fileScanner = new Scanner(file);
      Story story = new Story();
      StoryDAO.getInstance().add(story);
      Map<Link, Passage> passages = story.getPassagesHashMap();
      boolean firstLine = true;
      boolean firstPassage = true;

      // Blocks of text between :: and )
      String pattern = "(?s)::(.*?)\\)";

      if (firstLine) {
        String title = fileScanner.next();
        story.setTitle(title);
        StoryDAO.getInstance().update(story);
        firstLine = false;
      }

      while (fileScanner.findWithinHorizon(pattern, 0) != null) {
        StringBuilder content = new StringBuilder();
        Passage passage = new Passage();

        MatchResult matchResult = fileScanner.match();
        String passageBlock = matchResult.group(1);
        String[] lines = passageBlock.split("\n");
        for (String line : lines) {
          if (line.startsWith("::")) {
            passage.setTitle(line.substring(2).trim());
          } else if (line.startsWith("[")) {
            int endBracket = line.indexOf(']');
            int startParenthesis = line.indexOf('(');
            int endParenthesis = line.indexOf(')');

            if (endBracket != -1 && startParenthesis != -1 && endParenthesis != -1) {
              String linkText = line.substring(1, endBracket);
              String linkReference = line.substring(startParenthesis + 1, endParenthesis);

              Link link = new Link();
              link.setText(linkText);
              link.setReference(linkReference);

              passage.getLinks().add(link);
            }
          } else {
            content.append(line).append("\n");
          }
        }
        passage.setContent(content.toString().trim());

        if (firstPassage) {
          story.setOpeningPassage(passage);
          firstPassage = false;
          StoryDAO.getInstance().update(story);
        } else {
          story.addPassage(passage);
          StoryDAO.getInstance().update(story);
        }
      }

      return story;
    } catch (IOException e) {
      throw new IOException("Could not read file: " + fileName, e);
    }
  }
}
