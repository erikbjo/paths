package no.ntnu.idatg2001.paths.ui.views;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.ui.controllers.MainMenuController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class MainMenuView implements View {
  private final MainMenuController controller;
  private final Stage stage;
  private final Button continueButton;
  private final Button newGameButton;
  private final Button settingsButton;
  private final Button exitButton;

  public MainMenuView(MainMenuController controller, Stage stage) throws IOException {
    this.controller = controller;
    this.stage = stage;

    stage.setTitle("Main menu");
    stage.setOnCloseRequest(
        event -> {
          event.consume();
          GameDAO.getInstance().close();
          StoryDAO.getInstance().close();
          PlayerDAO.getInstance().close();
          Platform.exit();
        });

    BorderPane root = new BorderPane();
    root.setPrefSize(1200, 800);

    AnchorPane centerPane = new AnchorPane();

    HBox centerHBox = new HBox();
    centerHBox.setAlignment(Pos.CENTER_LEFT);
    centerHBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

    HBox buttonsHBox = new HBox();
    buttonsHBox.setId("buttonsHBox");
    buttonsHBox.setAlignment(Pos.CENTER);
    buttonsHBox.setPrefSize(100, 200);
    buttonsHBox.setPadding(new Insets(5, 5, 5, 5));

    continueButton = new Button();
    newGameButton = new Button();
    settingsButton = new Button();
    exitButton = new Button();

    buttonsHBox.getChildren().addAll(continueButton, newGameButton, settingsButton, exitButton);

    AnchorPane.setBottomAnchor(centerHBox, 0.0);
    AnchorPane.setTopAnchor(centerHBox, 0.0);
    AnchorPane.setLeftAnchor(centerHBox, 0.0);
    AnchorPane.setRightAnchor(centerHBox, 0.0);
    centerPane.getChildren().add(centerHBox);

    root.setBottom(buttonsHBox);
    root.setCenter(centerPane);

    // CONTROLLER
    controller.configureContinueButton(continueButton);
    controller.configureNewGameButton(newGameButton);
    controller.configureSettingsButton(settingsButton);
    controller.configureExitButton(exitButton);

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> updateLanguage());
    updateLanguage();

    Scene scene = new Scene(root, 1200, 800);
    scene.getStylesheets().add("css/mainMenu.css");

    Image image =
        new Image(
            (Objects.requireNonNull(getClass().getResource("/images/PathsTitleScreen.png")))
                .openStream());
    BackgroundImage backgroundImage =
        new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false));
    root.setBackground(new Background(backgroundImage));

    stage.setScene(scene);
    stage.show();
  }

  public Stage getStage() {
    return stage;
  }

  @Override
  public void updateLanguage() {
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "languages/mainMenu",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    // BUTTONS
    continueButton.setText(resources.getString("continueButton"));
    newGameButton.setText(resources.getString("newGameButton"));
    settingsButton.setText(resources.getString("settingsButton"));
    exitButton.setText(resources.getString("exitButton"));
  }
}
