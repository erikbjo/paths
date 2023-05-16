package no.ntnu.idatg2001.paths.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.ui.alerts.ExceptionAlert;
import no.ntnu.idatg2001.paths.ui.controllers.MainMenuController;

import java.io.IOException;

public class Launcher extends Application {
  public static void launch(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) {
    try {
      new MainMenuController(stage);
    } catch (IOException e) {
      new ExceptionAlert(e).showAndWait();
    }
  }
}
