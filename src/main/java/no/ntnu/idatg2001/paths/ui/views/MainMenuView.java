package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.database.GameDAO;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.database.StoryDAO;
import no.ntnu.idatg2001.paths.ui.controllers.MainMenuController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class MainMenuView implements View {
  private final MainMenuController controller;
  private final Stage stage;
  private final Button continueButton;
  private final Button newGameButton;
  private final Button settingsButton;
  private final Button exitButton;

  public MainMenuView(MainMenuController controller, Stage stage) {
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

    VBox buttonVBox = new VBox();
    buttonVBox.setAlignment(Pos.CENTER);
    buttonVBox.setPrefSize(100, 200);
    buttonVBox.setPadding(new Insets(5, 5, 5, 5));

    continueButton = new Button();
    continueButton.setPrefWidth(buttonVBox.getPrefWidth());
    newGameButton = new Button();
    newGameButton.setPrefWidth(buttonVBox.getPrefWidth());
    settingsButton = new Button();
    settingsButton.setPrefWidth(buttonVBox.getPrefWidth());
    exitButton = new Button();
    exitButton.setPrefWidth(buttonVBox.getPrefWidth());

    buttonVBox.getChildren().addAll(continueButton, newGameButton, settingsButton, exitButton);

    ImageView imageView =
        new ImageView(new Image("file:src/main/resources/images/placeholder.png"));
    imageView.setFitHeight(150);
    imageView.setFitWidth(200);
    imageView.setPreserveRatio(true);

    centerHBox.getChildren().addAll(buttonVBox, imageView);
    AnchorPane.setBottomAnchor(centerHBox, 0.0);
    AnchorPane.setTopAnchor(centerHBox, 0.0);
    AnchorPane.setLeftAnchor(centerHBox, 0.0);
    AnchorPane.setRightAnchor(centerHBox, 0.0);
    centerPane.getChildren().add(centerHBox);

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
