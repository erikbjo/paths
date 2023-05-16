package no.ntnu.idatg2001.paths.ui.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.controllers.NewGameController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.standardobjects.StandardMenuBar;

import java.util.Locale;
import java.util.ResourceBundle;

public class NewGameView implements View {

  private final Stage primaryStage;
  private final Button startNewGameButton;
  private final TableView<Story> storiesTableView;
  private final TableColumn<Story, String> storiesTableColumn;
  private final TableView<Player> playersTableView;
  private final TableColumn<Player, String> playersTableColumn;
  private final TableView<Link> deadLinksTableView;
  private final TableColumn<Link, String> deadLinksTableColumn;
  private final Text informationText;
  private final Text deadLinksText;
  private final Text playersText;
  private final Text storiesText;
  private final Text pathsGameText;
  private final Button editStoryButton;
  private final Button newStoryButton;
  private final Button deleteStoryButton;
  private final Button editPlayerButton;
  private final Button newPlayerButton;
  private final Button deletePlayerButton;
  private final Button deleteLinkButton;
  private final Button updateDeadLinksButton;
  private final Button continueButton;
  private final Button deleteButton;
  private final NewGameController controller;
  private final Button importStoryButton;

  public NewGameView(NewGameController controller, Stage primaryStage) {
    this.controller = controller;
    this.primaryStage = primaryStage;
    primaryStage.setTitle("New Game");

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    StandardMenuBar menuBar = new StandardMenuBar(primaryStage);
    root.setTop(menuBar);
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
    HBox deadLinksAndInformationHBox = new HBox();
    deadLinksAndInformationHBox.setAlignment(Pos.CENTER);

    // VBOXES
    VBox storiesVBox = new VBox();
    VBox playersVBox = new VBox();
    VBox deadLinksVBox = new VBox();
    VBox informationVBox = new VBox();

    // TABLEVIEWS
    storiesTableView = new TableView<>();
    storiesTableColumn = new TableColumn<>();
    storiesTableView.getColumns().add(storiesTableColumn);

    playersTableView = new TableView<>();
    playersTableColumn = new TableColumn<>();
    playersTableView.getColumns().add(playersTableColumn);

    deadLinksTableView = new TableView<>();
    deadLinksTableColumn = new TableColumn<>();
    deadLinksTableView.getColumns().add(deadLinksTableColumn);

    // TEXTS
    pathsGameText = new Text();
    storiesText = new Text();
    playersText = new Text();
    deadLinksText = new Text();
    informationText = new Text();

    // BUTTONS
    editStoryButton = new Button();
    newStoryButton = new Button();
    deleteStoryButton = new Button();
    importStoryButton = new Button();
    importStoryButton.setOnAction(
        event -> controller.onImportStory(storiesTableView)
    );
    HBox storiesButtonsHBox = new HBox(editStoryButton, newStoryButton, deleteStoryButton, importStoryButton);

    editPlayerButton = new Button();
    newPlayerButton = new Button();
    deletePlayerButton = new Button();
    HBox playersButtonsHBox = new HBox(editPlayerButton, newPlayerButton, deletePlayerButton);

    deleteLinkButton = new Button();
    updateDeadLinksButton = new Button();
    HBox deadLinksButtonsHBox = new HBox(deleteLinkButton, updateDeadLinksButton);

    continueButton = new Button();
    deleteButton = new Button();
    HBox ongoingGamesButtonsHBox = new HBox(continueButton, deleteButton);

    // ADDING TO VBOXES
    storiesVBox.getChildren().addAll(storiesText, storiesTableView, storiesButtonsHBox);
    playersVBox.getChildren().addAll(playersText, playersTableView, playersButtonsHBox);
    deadLinksVBox.getChildren().addAll(deadLinksText, deadLinksTableView, deadLinksButtonsHBox);
    informationVBox.getChildren().addAll();

    // ADDING TO HBOXES
    storiesAndPlayersHBox.getChildren().addAll(storiesVBox, playersVBox);
    deadLinksAndInformationHBox.getChildren().addAll(deadLinksVBox, informationVBox);

    // START NEW GAME BUTTON
    startNewGameButton = new Button();
    startNewGameHBox.getChildren().add(startNewGameButton);

    // ADD TO MAINVBOX
    mainVBox
        .getChildren()
        .addAll(
            pathsGameText, storiesAndPlayersHBox, startNewGameHBox, deadLinksAndInformationHBox);

    root.setCenter(mainVBox);

    updateLanguage();

    // CONTROLLER
    startNewGameButton.setOnAction(
        event -> controller.onNewGame(storiesTableView, playersTableView));
    controller.configureStoryButtons(
        editStoryButton, newStoryButton, deleteStoryButton, storiesTableView);
    controller.configurePlayerButtons(
        editPlayerButton, newPlayerButton, deletePlayerButton, playersTableView);
    controller.configureDeadLinksButtons(
        deleteLinkButton, updateDeadLinksButton, deadLinksTableView);

    controller.configureTableColumns(storiesTableColumn, playersTableColumn, deadLinksTableColumn);
    controller.updateAllTables(storiesTableView, playersTableView, deadLinksTableView);


    primaryStage.getScene().setRoot(root);
  }

  @Override
  public void updateLanguage() {
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "languages/newGame",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    storiesTableView.setPlaceholder(new Text(resources.getString("noStories")));
    playersTableView.setPlaceholder(new Text(resources.getString("noPlayers")));
    deadLinksTableView.setPlaceholder(new Text(resources.getString("noDeadLinks")));
    storiesTableColumn.setText(resources.getString("storiesTableColumn"));
    playersTableColumn.setText(resources.getString("playersTableColumn"));
    deadLinksTableColumn.setText(resources.getString("deadLinksTableColumn"));
    informationText.setText(resources.getString("informationText"));
    deadLinksText.setText(resources.getString("deadLinksText"));
    playersText.setText(resources.getString("playersText"));
    storiesText.setText(resources.getString("storiesText"));
    pathsGameText.setText(resources.getString("pathsGameText"));
    editStoryButton.setText(resources.getString("editStoryButton"));
    newStoryButton.setText(resources.getString("newStoryButton"));
    deleteStoryButton.setText(resources.getString("deleteStoryButton"));
    importStoryButton.setText(resources.getString("importStoryButton"));
    editPlayerButton.setText(resources.getString("editPlayerButton"));
    newPlayerButton.setText(resources.getString("newPlayerButton"));
    deletePlayerButton.setText(resources.getString("deletePlayerButton"));
    deleteLinkButton.setText(resources.getString("deleteLinkButton"));
    updateDeadLinksButton.setText(resources.getString("updateDeadLinksButton"));
    continueButton.setText(resources.getString("continueButton"));
    deleteButton.setText(resources.getString("deleteButton"));
    startNewGameButton.setText(resources.getString("startNewGameButton"));
  }
}
