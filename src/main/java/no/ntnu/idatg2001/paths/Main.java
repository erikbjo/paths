package no.ntnu.idatg2001.paths;

import no.ntnu.idatg2001.paths.model.utilities.SettingsFileWriter;
import no.ntnu.idatg2001.paths.ui.controllers.MainMenuController;
import no.ntnu.idatg2001.paths.ui.handlers.MusicHandler;
import no.ntnu.idatg2001.paths.ui.views.Launcher;
import no.ntnu.idatg2001.paths.ui.views.MainMenuView;

public class Main {
  public static void main(String[] args) {
    MusicHandler.initialize();
    SettingsFileWriter.readSettings();

    Launcher.launch(args);
  }
}
