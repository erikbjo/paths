package no.ntnu.idatg2001.paths;

import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.model.utilities.SettingsFileWriterReader;
import no.ntnu.idatg2001.paths.ui.Launcher;
import no.ntnu.idatg2001.paths.ui.handlers.MusicHandler;
import no.ntnu.idatg2001.paths.ui.handlers.VolumeHandler;

public class Main {
  public static void main(String[] args) {
    MusicHandler.initialize();
    VolumeHandler.initialize();
    SettingsFileWriterReader.readSettings();

    // Initialize the DAO classes so that they are ready for use for later views
    GameDAO.getInstance();
    StoryDAO.getInstance();
    PlayerDAO.getInstance();

    Launcher.launch(args);
  }
}
