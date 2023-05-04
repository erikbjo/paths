package no.ntnu.idatg2001.paths;

import no.ntnu.idatg2001.paths.model.*;
import no.ntnu.idatg2001.paths.model.database.GameDAO;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.database.StoryDAO;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.goals.GoldGoal;
import no.ntnu.idatg2001.paths.model.goals.HealthGoal;
import no.ntnu.idatg2001.paths.model.goals.ScoreGoal;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.model.utilities.SettingsFileWriter;
import no.ntnu.idatg2001.paths.ui.handlers.MusicHandler;
import no.ntnu.idatg2001.paths.ui.views.HomeView;

import java.util.List;

public class Main {
  public static void main(String[] args) {

    //        List<Story> stories = new ArrayList<>();
    //        StoryFileHandler.writePathsFile(stories, "Story.paths");
    // StoryFileHandler.readStoryFile("Story.paths");
    //

    //System.out.println("GameDAO: " + GameDAO.getInstance().getAll());
    //System.out.println("PlayerDAO: " + PlayerDAO.getInstance().getAll());
    //System.out.println("StoryDAO: " + StoryDAO.getInstance().getAll());

    MusicHandler.initialize();
    SettingsFileWriter.readSettings();
    HomeView.mainApp(args);
  }
}
