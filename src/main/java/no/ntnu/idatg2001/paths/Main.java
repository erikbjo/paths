package no.ntnu.idatg2001.paths;

import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.goals.GoldGoal;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.views.HomeView;

import java.util.List;

public class Main {
  public static void main(String[] args) {

    //        List<Story> stories = new ArrayList<>();
    //        StoryFileHandler.writePathsFile(stories, "Story.paths");
    // StoryFileHandler.readStoryFile("Story.paths");

    // create all the components needed to test the application

    Player player =
        new Player.PlayerBuilder()
            .withName("Erik")
            .withAttributes(new Attributes(10, 10, 10, 10, 10, 10, 10))
            .withEnergy(10)
            .withGold(100)
            .withHealth(100)
            .withMana(100)
            .withScore(0)
            .build();

    Passage firstPassage = new Passage("Start your journey.", "Choose your player class.");
    Passage secondPassage = new Passage("Choose your player class.", "Which class do you choose?");

    Link link = new Link("I want to be normally", "firstLink");
    firstPassage.addLinks(link);
    secondPassage.addLinks(link);

    Story story = new Story("My first story", firstPassage);
    story.addPassage(secondPassage);

    GoldGoal goldGoal = new GoldGoal(100);

    Game game = new Game(player, story, (List<Goal>) goldGoal);

    HomeView.mainApp(args);
  }
}
