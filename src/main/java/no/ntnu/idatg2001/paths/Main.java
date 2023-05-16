package no.ntnu.idatg2001.paths;

import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.model.utilities.PathsStoryFileReader;
import no.ntnu.idatg2001.paths.model.utilities.PathsStoryFileWriter;
import no.ntnu.idatg2001.paths.model.utilities.SettingsFileWriter;
import no.ntnu.idatg2001.paths.ui.handlers.MusicHandler;
import no.ntnu.idatg2001.paths.ui.Launcher;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    MusicHandler.initialize();
    SettingsFileWriter.readSettings();

    // Initialize the DAO classes so that they are ready for use for later views
    GameDAO.getInstance();
    StoryDAO.getInstance();
    PlayerDAO.getInstance();

    // TODO: Remove this when the game is finished
    // MAYBE SHIP THE DATABASE WITH THE STORY

    if (StoryDAO.getInstance().getAll().stream()
        .noneMatch(story -> story.getTitle().equals("Troll story"))) {

      createTrollStory();
    }

    PathsStoryFileReader.getInstance().readStoryFromFile("ForestStory.paths");
    PathsStoryFileWriter.writeStoryToFile(StoryDAO.getInstance().getAll().get(0));

    Launcher.launch(args);
  }

  private static void createTrollStory() {
    Passage startingPassage = new Passage("You see a troll", "");
    Story story = new Story("Troll story", startingPassage);
    StoryDAO.getInstance().add(story);

    Passage attackTrollPassage = new Passage("Attack the troll", "How do you want to attack?");
    Passage attackTrollWithSwordPassage =
        new Passage(
            "Attack the troll with your sword",
            "The sword only tickles the troll. It rips your arm off and eats it.");
    Passage attackTrollWithMagicSpellPassage =
        new Passage("Attack the troll with a magic spell", "The troll is defeated. You win!");
    Passage runAwayPassage = new Passage("Run away", "The troll sees you and runs after you.");
    Passage keepRunningPassage = new Passage("Keep running", "You run into a wall and die.");

    story.addPassage(attackTrollPassage);
    story.addPassage(attackTrollWithSwordPassage);
    story.addPassage(attackTrollWithMagicSpellPassage);
    story.addPassage(runAwayPassage);
    story.addPassage(keepRunningPassage);

    startingPassage.addLink(new Link("Attack the troll", attackTrollPassage.getTitle()));
    startingPassage.addLink(new Link("Run away", runAwayPassage.getTitle()));

    attackTrollPassage.addLink(
        new Link("Attack the troll with your sword", attackTrollWithSwordPassage.getTitle()));
    attackTrollPassage.addLink(
        new Link(
            "Attack the troll with a magic spell", attackTrollWithMagicSpellPassage.getTitle()));

    runAwayPassage.addLink(new Link("Keep running", keepRunningPassage.getTitle()));
    runAwayPassage.addLink(
        new Link(
            "Stop running and attack the troll with a magic spell",
            attackTrollWithMagicSpellPassage.getTitle()));

    StoryDAO.getInstance().update(story);
  }
}
