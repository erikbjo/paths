package no.ntnu.idatg2001.paths.ui.views;

import javafx.application.Application;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.database.GameDAO;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.database.StoryDAO;
import no.ntnu.idatg2001.paths.ui.controllers.MainMenuController;

public class Launcher extends Application {
  public static void launch(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    // Initialize the DAO classes so that they are ready for use for later views
    GameDAO.getInstance();
    StoryDAO.getInstance();
    PlayerDAO.getInstance();

    new MainMenuController(stage);
  }
}
