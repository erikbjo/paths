package no.ntnu.idatg2001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StoryFileHandler {
    public static void writePathsFile(List<Story> storyParts, String filename) {
        try (BufferedWriter fileWriter = Files.newBufferedWriter(Path.of(filename))) {
            for (Story story : storyParts) {
                fileWriter.write(story.getTitle() + "\n\n");
                fileWriter.write("::" + story.getOpeningPassage().getTitle() + "\n");
                fileWriter.write(story.getOpeningPassage().getContent() + "\n");

                List<Link> links = story.getOpeningPassage().getLinks();
                for (Link link : links) {
                    String text = link.getText();
                    String reference = link.getReference();
                    fileWriter.write("[" + text + "]" + "(" + reference + ")" + "\n");

                    /**
                     * List<Action> actions = link.getActions(); for (Action action : actions) { Attributes
                     * attributes = new Attributes(1,1,1, 1,1,1,1); Player player = new Player("Test",
                     * 9,7,9,6, 12,attributes); action.execute(player); fileWriter.write("<" +
                     * action.getClass().getSimpleName() + ">" + "\n"); }
                     */
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
                String title = lineOfText.trim();
                fileReader.readLine();
                String openingPassageTitle = fileReader.readLine();
                String openingPassageContent = fileReader.readLine();
                Passage openingPassage = new Passage(openingPassageTitle, openingPassageContent);
                List<Link> links = new ArrayList<>();
                while ((lineOfText = fileReader.readLine()) != null && !lineOfText.isEmpty()) {
                    String[] stringParts = lineOfText.split("\\]\\(");
                    //String linkText = stringParts[0].substring(0, stringParts[1].length() - 1);
                    String linkReference = lineOfText.substring(0, stringParts[1].length() - 1);
                    Link link = new Link(stringParts[0].substring(1).trim(),
                        linkReference);
                    String nextLine;

                    /** while ((nextLine = fileReader.readLine()) != null && nextLine.startsWith("<")){ } */
                    links.add(link);
                }
                openingPassage.setLinks(links);
                Story story = new Story(title, openingPassage);
                stories.add(story);
                /**
                 * if (lineOfText.isEmpty()){ if (currentStory != null && currentPassage != null){
                 * currentStory.addPassage(currentPassage); } currentPassage = null; } else if
                 * (lineOfText.startsWith("::")) { if (currentStory != null && currentPassage != null) {
                 * currentStory.addPassage(currentPassage); } String passageTitle = lineOfText.substring(2);
                 * currentPassage = new Passage(passageTitle, currentPassage.getContent()); }
                 *
                 * <p>if (currentStory == null){ currentStory = new Story() stories.add(currentStory); }
                 * else if (){ currentStory.addPassage(currentPassage); } isPassageContent = true; }else {
                 * if (currentStory == null){ currentStory = new Story() } }
                 */
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return stories;
    }

    private static void createAction(String actionName) {
        //
    }
}
