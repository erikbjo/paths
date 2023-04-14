package no.ntnu.idatg2001.model;

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

                /**
                 List<Link> openingPassageLinks = story.getOpeningPassage().getLinks();
                 for (Link link : openingPassageLinks) {
                 String text = link.getText();
                 String reference = link.getReference();
                 fileWriter.write("[" + text + "]" + "(" + reference + ")" + "\n");
                 List<PathActions> actions = new ArrayList<>();
                 for (PathActions action : actions) {
                 String actionName = action.getActionName();
                 String actionReference = action.getPathReference();
                 fileWriter.write("<" + actionName + ">" + "(" + actionReference + ")"
                 + "\n");
                 }
                 fileWriter.write("\n");
                 }*/

                for (Passage passage : story.getPassages()) {
                    List<Link> passageLinks = passage.getLinks();
                    passage.setLinks(passageLinks);
                    fileWriter.write(passage.getTitle() + "\n\n");
                    fileWriter.write("::" + passage.getTitle() + "\n");
                    fileWriter.write(passage.getContent() + "\n");
                    writeLinks(fileWriter, passageLinks);
                    fileWriter.write("\n");

                    /**
                     List<Link> passageLinks = passage.getLinks();
                     for (Link passageLink : passageLinks) {
                     String text = passageLink.getText();
                     String reference = passageLink.getReference();
                     fileWriter.write("[" + text + "]" + "(" + reference + ")" + "\n");
                     List<PathActions> actions = new ArrayList<>();
                     for (PathActions action : actions) {
                     String actionName = action.getActionName();
                     String actionReference = action.getPathReference();
                     fileWriter.write("<" + actionName + ">" + "(" + actionReference + ")"
                     + "\n");
                     }
                     fileWriter.write("\n");
                     }
                     fileWriter.write("\n");
                     */
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeLinks(BufferedWriter fileWriter, List<Link> links) throws IOException {
        for (Link link : links) {
            String text = link.getText();
            String reference = link.getReference();
            fileWriter.write("[" + text + "]" + "(" + reference + ")" + "\n");
            List<PathActions> actions = link.getPathActions();
            for (PathActions action : actions) {
                String actionName = action.getActionName();
                String actionReference = action.getPathReference();
                fileWriter.write("<" + actionName + ">" + "(" + actionReference + ")"
                    + "\n");
            }
            fileWriter.write("\n");
            fileWriter.close();
        }
    }

    public static List<Story> readStoryFile(String filename) {
        List<Story> storyParts = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Path.of(filename))) {
            String lineOfText;

            while ((lineOfText = fileReader.readLine()) != null) {
                String title = lineOfText.trim();
                String openingPassageTitle = fileReader.readLine();
                String openingPassageContent = fileReader.readLine();
                Passage openingPassage = new Passage(openingPassageTitle, openingPassageContent);
                List<Link> links = new ArrayList<>();
                while ((lineOfText = fileReader.readLine()) != null && !lineOfText.isEmpty()) {
                    if (lineOfText.startsWith("[")) {
                        String[] stringParts = lineOfText.split("\\]\\(");
                        String linkReference = lineOfText.substring(0, stringParts[1].length() - 1);
                        Link link = new Link(stringParts[0].substring(1).trim(),
                            linkReference);
                        //Link link = new Link(stringParts[0],stringParts[2]);
                        links.add(link);
                    } else if (lineOfText.startsWith("<")) {
                        String[] actionStringParts = lineOfText.split("\\>\\(");
                        String actionReference =
                            lineOfText.substring(0, actionStringParts[1].length() - 1);
                        Link link = new Link(actionStringParts[0].substring(1).trim(),
                            actionReference);
                        //Link link = new Link(actionStringParts[0],actionStringParts[2]);
                        links.add(link);
                    } else {
                        break;
                    }
                }
                openingPassage.setLinks(links);
                Story story = new Story(title, openingPassage);
                storyParts.add(story);
                /**
                 if (lineOfText.isEmpty()){ if (currentStory != null && currentPassage != null){
                 currentStory.addPassage(currentPassage); } currentPassage = null; } else if
                 (lineOfText.startsWith("::")) { if (currentStory != null && currentPassage != null) {
                 currentStory.addPassage(currentPassage); } String passageTitle = lineOfText.substring(2);
                 currentPassage = new Passage(passageTitle, currentPassage.getContent()); }

                 <p>if (currentStory == null){ currentStory = new Story() stories.add(currentStory); }
                 else if (){ currentStory.addPassage(currentPassage); } isPassageContent = true; }else {
                 if (currentStory == null){ currentStory = new Story() } }
                 */
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return storyParts;
    }
}
