package no.ntnu.idatg2001.ui.views;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.model.Database;
import no.ntnu.idatg2001.ui.controllers.SettingsController;
import no.ntnu.idatg2001.ui.standardObjects.StandardMenuBar;

public class HomeView extends Application {
  private SettingsController settingsController;
  private ResourceBundle resources;
  private Button startNewGameButton;
  private Text pathsGameText;
  private Text storiesText;
  private Text deadLinksText;

  public static void mainApp(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // Observes when the language in Database is changed, then calls updateLanguage()
    Database.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "home", Locale.forLanguageTag(Database.getCurrentLanguage().getLocalName()));

    BorderPane borderPane = new BorderPane();
    borderPane.setTop(new StandardMenuBar());
    AnchorPane anchorPane = new AnchorPane();
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    VBox storiesVBox = new VBox();
    VBox deadLinksVBox = new VBox();
    HBox middleHBox = new HBox();

    storiesVBox.setSpacing(10);
    deadLinksVBox.setSpacing(10);
    middleHBox.setSpacing(10);

    pathsGameText = new Text(resources.getString("title"));
    storiesText = new Text(resources.getString("storiesText"));
    deadLinksText = new Text(resources.getString("deadLinksText"));

    pathsGameText.setFont(Font.font("Comic sans", 50));

    TextArea storiesTextArea = new TextArea("storiesTextArea");
    TextArea deadLinksTextArea = new TextArea("deadLinksTextArea");

    storiesTextArea.setEditable(false);
    deadLinksTextArea.setEditable(false);

    storiesVBox.getChildren().add(storiesText);
    storiesVBox.getChildren().add(storiesTextArea);

    deadLinksVBox.getChildren().add(deadLinksText);
    deadLinksVBox.getChildren().add(deadLinksTextArea);

    middleHBox.getChildren().add(storiesVBox);
    middleHBox.getChildren().add(deadLinksVBox);

    startNewGameButton = new Button(resources.getString("startNewGameButton"));
    startNewGameButton.setOnAction(
        event -> {
          // Launch the Player Information View in a new window
          PlayerInformationView playerInfoView = new PlayerInformationView();
          playerInfoView.start(primaryStage);
        });

    gridPane.add(pathsGameText, 0, 0);
    gridPane.add(middleHBox, 0, 1);
    gridPane.add(startNewGameButton, 0, 2);

    gridPane.setAlignment(Pos.CENTER);

    anchorPane.getChildren().add(gridPane);
    anchorPane.getChildren().add(borderPane);

    AnchorPane.setTopAnchor(gridPane, 10.0);
    AnchorPane.setLeftAnchor(gridPane, 10.0);
    AnchorPane.setRightAnchor(gridPane, 10.0);
    AnchorPane.setBottomAnchor(gridPane, 10.0);

    Scene scene = new Scene(anchorPane, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
    playMusic("src/main/resources/music/relaxing-145038.mp3");
  }

  public void updateLanguage() {
    resources =
        ResourceBundle.getBundle(
            "home", Locale.forLanguageTag(Database.getCurrentLanguage().getLocalName()));
    startNewGameButton.setText(resources.getString("startNewGameButton"));
    pathsGameText.setText(resources.getString("storiesText"));
    storiesText.setText(resources.getString("storiesText"));
    deadLinksText.setText(resources.getString("deadLinksText"));
  }


  // Should maybe be static like Database
  private void playMusic(String musicFilePath) {
    try {
      Media media = new Media(new File(musicFilePath).toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setAutoPlay(true);
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    } catch (Exception e) {
      System.out.println("Error playing music: " + e.getMessage());
    }
  }
}
