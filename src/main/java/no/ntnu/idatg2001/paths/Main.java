package no.ntnu.idatg2001.paths;

import no.ntnu.idatg2001.paths.model.*;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.goals.GoldGoal;
import no.ntnu.idatg2001.paths.model.goals.HealthGoal;
import no.ntnu.idatg2001.paths.model.goals.ScoreGoal;
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

    Passage firstPassage = new Passage("Start your journey.", "You standing in the middle of a forest");
    Passage secondPassage = new Passage("Forest ruins.", "You see ruins of an old castle.");

    Link link = new Link("Go to the forest ruins.", "goForestRuins");
    firstPassage.addLink(link);
    secondPassage.addLink(link);

    Story story = new Story("My first story", firstPassage);
    story.addPassage(secondPassage);

    GoldGoal goldGoal = new GoldGoal(100);
    HealthGoal healthGoal = new HealthGoal(100);
    ScoreGoal scoreGoal = new ScoreGoal(100);
    List<Goal> goals = List.of(goldGoal, healthGoal, scoreGoal);

    Game game = new Game(player, story, goals);

    Database.setCurrentGame(game);

    HomeView.mainApp(args);
  }
}
