package no.ntnu.idatg2001.paths.ui.controllers;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.database.GameDAO;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.database.StoryDAO;
import no.ntnu.idatg2001.paths.ui.views.MainMenuView;
import no.ntnu.idatg2001.paths.ui.views.SelectGameToContinueView;

public class MainMenuController {
  private final Stage stage;
  private final MainMenuView view;

  public MainMenuController(MainMenuView view) {
    this.view = view;
    this.stage = view.getStage();
  }

  public MainMenuController(Stage stage) {
    this.stage = stage;
    this.view = new MainMenuView(this, stage);
  }

  public void configureContinueButton(Button continueButton) {
    continueButton.setOnAction(event -> new SelectGameToContinueController(stage));
  }

  public void configureNewGameButton(Button newGameButton) {
    newGameButton.setOnAction(event -> new NewGameController(stage));
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
