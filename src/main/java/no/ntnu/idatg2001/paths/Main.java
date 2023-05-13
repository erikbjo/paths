package no.ntnu.idatg2001.paths;

import no.ntnu.idatg2001.paths.model.database.GameDAO;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.database.StoryDAO;
import no.ntnu.idatg2001.paths.model.utilities.SettingsFileWriter;
import no.ntnu.idatg2001.paths.ui.handlers.MusicHandler;
import no.ntnu.idatg2001.paths.ui.Launcher;

public class Main {
  public static void main(String[] args) {
    MusicHandler.initialize();
    SettingsFileWriter.readSettings();

    // Initialize the DAO classes so that they are ready for use for later views
    GameDAO.getInstance();
    StoryDAO.getInstance();
    PlayerDAO.getInstance();

    Launcher.launch(args);
  }
}
