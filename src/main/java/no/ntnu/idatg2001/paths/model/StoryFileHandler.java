/**
 * package no.ntnu.idatg2001.paths.model;
 * <p>
 * public class StoryFileHandler {
 * public static void writePathsFile(List<Story> storyParts, String filename) {
 * try (BufferedWriter fileWriter = Files.newBufferedWriter(Path.of(filename))) {
 * for (Story story : storyParts) {
 * fileWriter.write(story.getTitle() + "\n\n");
 * Passage openingPassage = story.getOpeningPassage();
 * fileWriter.write("::" + openingPassage.getTitle() + "\n");
 * fileWriter.write(openingPassage.getContent() + "\n");
 * writeLinks(fileWriter, openingPassage.getLinks());
 * fileWriter.write("\n");
 * <p>
 * for (Passage passage : story.getPassages()) {
 * if (openingPassage != passage) {
 * story.getPassages().add(passage);
 * fileWriter.write("::" + passage.getTitle() + "\n");
 * fileWriter.write(passage.getContent() + "\n");
 * writeLinks(fileWriter, passage.getLinks());
 * fileWriter.write("\n");
 * }
 * }
 * }
 * } catch (IOException e) {
 * System.out.println(e.getMessage());
 * }
 * }
 * <p>
 * public static void writeLinks(BufferedWriter fileWriter, List<Link> links) throws IOException {
 * int numberOfLinks = 0;
 * for (Link link : links) {
 * String text = link.getText();
 * String reference = link.getReference();
 * fileWriter.write("[" + text + "]" + "(" + reference + ")" + "\n");
 * if (!links.isEmpty() && links.contains(link)) {
 * writeActions(fileWriter, link.getPathActions());
 * }
 * numberOfLinks++;
 * }
 * if (numberOfLinks == links.size()) {
 * writeActions(fileWriter, links.get(0).getPathActions());
 * }
 * //fileWriter.close();
 * }
 * <p>
 * public static void writeActions(BufferedWriter fileWriter, List<PathActions> actions)
 * throws IOException {
 * for (PathActions action : actions) {
 * String actionName = action.getActionName();
 * String actionReference = action.getPathReference();
 * fileWriter.write("<" + actionName + ">" + "(" + actionReference + ")"
 * + "\n");
 * }
 * }
 * <p>
 * public static List<Story> readStoryFile(String filename) {
 * List<Story> storyParts = new ArrayList<>();
 * try (BufferedReader fileReader = Files.newBufferedReader(Path.of(filename))) {
 * String lineOfText;
 * <p>
 * while ((lineOfText = fileReader.readLine()) != null) {
 * String title = lineOfText.trim();
 * String openingPassageTitle = fileReader.readLine();
 * String openingPassageContent = fileReader.readLine();
 * Passage openingPassage = new Passage(openingPassageTitle, openingPassageContent);
 * List<Link> links = new ArrayList<>();
 * while ((lineOfText = fileReader.readLine()) != null && !lineOfText.isEmpty()) {
 * if (lineOfText.startsWith("[")) {
 * String[] stringParts = lineOfText.split("\\]\\(");
 * String linkReference = lineOfText.substring(0, stringParts[1].length() - 1);
 * Link link = new Link(stringParts[0].substring(1).trim(),
 * linkReference);
 * links.add(link);
 * } else if (lineOfText.startsWith("<")) {
 * String[] actionStringParts = lineOfText.split("\\>\\(");
 * String actionReference =
 * lineOfText.substring(0, actionStringParts[1].length() - 1);
 * Link link = new Link(actionStringParts[0].substring(1).trim(),
 * actionReference);
 * links.add(link);
 * } else {
 * break;
 * }
 * }
 * openingPassage.setLinks(links);
 * Story story = new Story(title, openingPassage);
 * storyParts.add(story);
 * }
 * } catch (IOException e) {
 * System.out.println(e.getMessage());
 * }
 * return storyParts;
 * }
 * }
 */
