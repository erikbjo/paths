package no.ntnu.idatg2001.paths.ui.controllers;

import javafx.application.Platform;
import javafx.scene.control.Button;
import no.ntnu.idatg2001.paths.model.database.GameDAO;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.database.StoryDAO;
import no.ntnu.idatg2001.paths.ui.views.MainMenuView;

public class MainMenuController {
  private final MainMenuView view;

  public MainMenuController(MainMenuView view) {
    this.view = view;
  }

  public void configureContinueButton(Button continueButton) {
    continueButton.setOnAction(
        event -> new SelectGameToContinueController(view.getStage()));
  }

  public void configureNewGameButton(Button newGameButton) {
    newGameButton.setOnAction(
        event -> {
          view.getStage().close();
          new SelectGameToContinueController(view.getStage());
        });
  }

  public void configureSettingsButton(Button settingsButton) {
    settingsButton.setOnAction(SettingsController::new);
  }

  public void configureExitButton(Button exitButton) {
    exitButton.setOnAction(
        event -> {
          GameDAO.getInstance().close();
          StoryDAO.getInstance().close();
          PlayerDAO.getInstance().close();
          Platform.exit();
        });
  }
}
