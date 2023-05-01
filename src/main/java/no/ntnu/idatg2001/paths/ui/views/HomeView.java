package no.ntnu.idatg2001.paths.ui.views;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.controllers.SettingsController;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class HomeView extends Application {
  private SettingsController settingsController;
  private ResourceBundle resources;
  private Button startNewGameButton;
  private Text pathsGameText;
  private Text storiesText;
  private Text deadLinksText;

  public static void mainApp(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) {
    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "home", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    BorderPane borderPane = new BorderPane();
    borderPane.setTop(new StandardMenuBar());

    AnchorPane rootAnchorPane = new AnchorPane();
    rootAnchorPane.getChildren().add(borderPane);
    VBox mainVBox = new VBox(50);

    mainVBox.setAlignment(Pos.TOP_CENTER);
    AnchorPane.setTopAnchor(mainVBox, 0.0);
    AnchorPane.setBottomAnchor(mainVBox, 0.0);
    AnchorPane.setLeftAnchor(mainVBox, 0.0);
    AnchorPane.setRightAnchor(mainVBox, 0.0);

    HBox storiesAndPlayersHBox = new HBox();
    storiesAndPlayersHBox.setAlignment(Pos.CENTER);
    HBox startNewGameHBox = new HBox();
    startNewGameHBox.setAlignment(Pos.CENTER);
    HBox deadLinksAndOngoingGamesHBox = new HBox();
    deadLinksAndOngoingGamesHBox.setAlignment(Pos.CENTER);

    // VBOXES
    VBox storiesVBox = new VBox();
    VBox playersVBox = new VBox();
    VBox deadLinksVBox = new VBox();
    VBox ongoingGamesVBox = new VBox();

    // TABLEVIEWS
    TableView<String> storiesTableView = new TableView<>();
    TableColumn<String, String> storiesTableColumn = new TableColumn<>(resources.getString("storiesTableColumn"));
    storiesTableView.getColumns().add(storiesTableColumn);

    TableView<String> playersTableView = new TableView<>();
    TableColumn<String, String> playersTableColumn = new TableColumn<>(resources.getString("playersTableColumn"));
    playersTableView.getColumns().add(playersTableColumn);

    TableView<String> deadLinksTableView = new TableView<>();
    TableColumn<String, String> deadLinksTableColumn = new TableColumn<>(resources.getString("deadLinksTableColumn"));
    deadLinksTableView.getColumns().add(deadLinksTableColumn);

    TableView<String> ongoingGamesTableView = new TableView<>();
    TableColumn<String, String> ongoingGamesTableColumn = new TableColumn<>(resources.getString("ongoingGamesTableColumn"));
    ongoingGamesTableView.getColumns().add(ongoingGamesTableColumn);

    // TEXTS
    Text pathsGameText = new Text(resources.getString("title"));
    Text storiesText = new Text(resources.getString("storiesText"));
    Text playersText = new Text(resources.getString("playersText"));
    Text deadLinksText = new Text(resources.getString("deadLinksText"));
    Text ongoingGamesText = new Text(resources.getString("ongoingGamesText"));

    // BUTTONS
    Button editStoryButton = new Button(resources.getString("editStoryButton"));
    Button newStoryButton = new Button(resources.getString("newStoryButton"));
    HBox storiesButtonsHBox = new HBox(editStoryButton, newStoryButton);

    Button editPlayerButton = new Button(resources.getString("editPlayerButton"));
    Button newPlayerButton = new Button(resources.getString("newPlayerButton"));
    HBox playersButtonsHBox = new HBox(editPlayerButton, newPlayerButton);

    Button deleteLinkButton = new Button(resources.getString("deleteLinkButton"));
    HBox deadLinksButtonsHBox = new HBox(deleteLinkButton);

    Button continueButton = new Button(resources.getString("continueButton"));
    Button deleteButton = new Button(resources.getString("deleteButton"));
    HBox ongoingGamesButtonsHBox = new HBox(continueButton, deleteButton);

    // ADDING TO VBOXES
    storiesVBox.getChildren().addAll(storiesText, storiesTableView, storiesButtonsHBox);
    playersVBox.getChildren().addAll(playersText, playersTableView, playersButtonsHBox);
    deadLinksVBox.getChildren().addAll(deadLinksText, deadLinksTableView, deadLinksButtonsHBox);
    ongoingGamesVBox
        .getChildren()
        .addAll(ongoingGamesText, ongoingGamesTableView, ongoingGamesButtonsHBox);

    // ADDING TO HBOXES
    storiesAndPlayersHBox.getChildren().addAll(storiesVBox, playersVBox);
    deadLinksAndOngoingGamesHBox.getChildren().addAll(deadLinksVBox, ongoingGamesVBox);

    // START NEW GAME BUTTON
    startNewGameButton = new Button(resources.getString("startNewGameButton"));
    startNewGameButton.setOnAction(
        event -> {
          // Launch the Player Information View in a new window
          PlayerInformationView playerInfoView = new PlayerInformationView();
          playerInfoView.start(primaryStage);
        });

    // ADD TO MAINVBOX
    mainVBox
        .getChildren()
        .addAll(
            pathsGameText, storiesAndPlayersHBox, startNewGameHBox, deadLinksAndOngoingGamesHBox);

    rootAnchorPane.getChildren().add(mainVBox);

    Scene scene = new Scene(rootAnchorPane, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
    playMusic("/music/relaxing-145038.mp3");
  }

  public void updateLanguage() {
    resources =
        ResourceBundle.getBundle(
            "home", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    startNewGameButton.setText(resources.getString("startNewGameButton"));
    pathsGameText.setText(resources.getString("storiesText"));
    storiesText.setText(resources.getString("storiesText"));
    deadLinksText.setText(resources.getString("deadLinksText"));
  }

  // Should maybe be static like Database
  private void playMusic(String musicFilePath) {
    try {
      URL resource = getClass().getResource(musicFilePath);
      Media media = new Media(resource.toString());
      // Media media = new Media(new File(musicFilePath).toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setAutoPlay(true);
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    } catch (Exception e) {
      System.out.println("Error playing music: " + e.getMessage());
    }
  }
}
