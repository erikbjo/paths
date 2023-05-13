package no.ntnu.idatg2001.paths.ui;

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
    new MainMenuController(stage);
  }
}
