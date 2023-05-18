package no.ntnu.idatg2001.paths.model.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.actions.Action;
import no.ntnu.idatg2001.paths.model.actions.GoldAction;
import no.ntnu.idatg2001.paths.model.actions.HealthAction;
import no.ntnu.idatg2001.paths.model.actions.ScoreAction;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;

public class PathsStoryFileReader {
  private static final PathsStoryFileReader instance = new PathsStoryFileReader();

  private PathsStoryFileReader() {}

  public static PathsStoryFileReader getInstance() {
    return instance;
  }

  public static void readStoryFromFile(File file) throws IOException {
    try {
      Scanner fileScanner = new Scanner(file);
      Story story = new Story();
      StoryDAO.getInstance().add(story);
      Map<Link, Passage> passages = story.getPassagesHashMap();
      boolean firstLine = true;
      boolean firstPassage = true;

      // Blocks of text between :: and ::, or :: and end of file
      String pattern = "(?s)::(.*?)(?=::|$)";

      if (firstLine) {
        String title = fileScanner.next();
        story.setTitle(title);
        StoryDAO.getInstance().update(story);
        firstLine = false;
      }

      while (fileScanner.findWithinHorizon(pattern, 0) != null) {
        StringBuilder content = new StringBuilder();
        Passage passage = new Passage(null, null);
        Link link = null;

        MatchResult matchResult = fileScanner.match();
        String passageBlock = matchResult.group(0);
        String[] lines = passageBlock.split("\n");
        for (String line : lines) {
          switch (line.substring(0, 1)) {
            case ":" -> passage.setTitle(line.substring(2).trim());
            case "[" -> {
              int endBracket = line.indexOf(']');
              int startParenthesis = line.indexOf('(');
              int endParenthesis = line.indexOf(')');
              if (endBracket != -1 && startParenthesis != -1 && endParenthesis != -1) {
                String linkText = line.substring(1, endBracket);
                String linkReference = line.substring(startParenthesis + 1, endParenthesis);

                link = new Link(linkText, linkReference);

                passage.getLinks().add(link);
              }
            }
            case "{" -> {
              int endCurlyBracket = line.indexOf('}');
              int startCrocodile = line.indexOf('<');
              int endCrocodile = line.indexOf('>');
              int startBracket = line.indexOf('(');
              int endBracket = line.indexOf(')');

              if (endCurlyBracket != -1
                  && startCrocodile != -1
                  && endCrocodile != -1
                  && startBracket != -1
                  && endBracket != -1) {
                String actionClass = line.substring(1, endCurlyBracket);
                String actionValue = line.substring(startCrocodile + 1, endCrocodile);
                String actionIsPositive = line.substring(startBracket + 1, endBracket);

                Action action = null;
                switch (actionClass) {
                  case "HealthAction" -> {
                    action =
                        new HealthAction(
                            Integer.parseInt(actionValue), Boolean.getBoolean(actionIsPositive));
                  }
                  case "ScoreAction" -> {
                    action =
                        new ScoreAction(
                            Integer.parseInt(actionValue), Boolean.getBoolean(actionIsPositive));
                  }
                  case "item" -> {
                    // TODO: FIX THIS
                    // action = new InventoryAction(actionValue,
                    // Boolean.getBoolean(actionIsPositive));
                  }
                  case "GoldAction" -> {
                    action =
                        new GoldAction(
                            Integer.parseInt(actionValue), Boolean.getBoolean(actionIsPositive));
                  }
                  default -> System.out.println("Could not find action class");
                }

                if (action != null && link != null) {
                  link.addAction(action);
                } else {
                  System.out.println("Could not add action to link");
                }
              }
            }
            case "\n" -> {
              // Do nothing
            }
            default -> content.append(line).append("\n");
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
    } catch (IOException e) {
      throw new IOException("Could not read file: " + file, e);
    }
  }
}

/*
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
 */
