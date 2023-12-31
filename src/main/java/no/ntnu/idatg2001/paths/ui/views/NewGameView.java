package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
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

public class NewGameView implements View {

  private final Stage primaryStage;
  private final Button startNewGameButton;
  private final Text informationText;
  private final NewGameController controller;
  private TableView<Player> playersTableView;
  private TableColumn<Player, String> playersTableColumn;
  private TableColumn<Player, Integer> playerScoreTableColumn;
  private TableView<Link> deadLinksTableView;
  private TableColumn<Link, String> deadLinksTableColumn;

  private TableColumn<Link, String> deadLinksStoryTableColumn;
  private TableColumn<Link, String> deadLinksPassageTableColumn;
  private Text deadLinksText;
  private Text playersText;
  private Text storiesText;
  private Button editStoryButton;
  private Button newStoryButton;
  private Button deleteStoryButton;
  private Button editPlayerButton;
  private Button newPlayerButton;
  private Button deletePlayerButton;
  private Button deleteLinkButton;
  private Button updateDeadLinksButton;
  private Button importStoryButton;
  private TableView<Story> storiesTableView;
  private TableColumn<Story, String> storiesTableColumn;

  private TableColumn<Story, Integer> passagesAmountTableColumn;
  private TableColumn<Story, Integer> linksAmountTableColumn;
  private TableColumn<Story, Integer> brokenLinksAmountTableColumn;
  private Button exportButton;

  public NewGameView(NewGameController controller, Stage primaryStage) {
    this.controller = controller;
    this.primaryStage = primaryStage;
    primaryStage.setTitle("New Game");

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    StandardMenuBar menuBar = new StandardMenuBar(primaryStage);
    root.setTop(menuBar);
    HBox mainHBox = new HBox();

    mainHBox.setAlignment(Pos.TOP_CENTER);
    AnchorPane.setTopAnchor(mainHBox, 0.0);
    AnchorPane.setBottomAnchor(mainHBox, 0.0);
    AnchorPane.setLeftAnchor(mainHBox, 0.0);
    AnchorPane.setRightAnchor(mainHBox, 0.0);

    HBox storiesAndPlayersHBox = new HBox();
    HBox startNewGameHBox = new HBox();

    // VBOXES
    VBox storiesVBox = createStoriesVBox();
    VBox playersVBox = createPlayersVBox();
    VBox deadLinksVBox = createDeadLinksVBox();
    VBox informationVBox = new VBox();

    // TEXTS
    informationText = new Text();

    // ADDING TO VBOXES
    informationVBox.getChildren().addAll();

    // ADDING TO HBOXES
    storiesAndPlayersHBox.getChildren().addAll(storiesVBox, playersVBox);

    // START NEW GAME BUTTON
    startNewGameButton = new Button();
    startNewGameHBox.getChildren().add(startNewGameButton);

    // ADD TO MAINVBOX
    mainHBox.getChildren().addAll(storiesAndPlayersHBox, startNewGameHBox);

    root.setRight(deadLinksVBox);
    root.setCenter(mainHBox);

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

    controller.configureDeadLinksColumns(
        deadLinksTableColumn, deadLinksStoryTableColumn, deadLinksPassageTableColumn);
    controller.configureStoryColumns(
        storiesTableColumn,
        passagesAmountTableColumn,
        linksAmountTableColumn,
        brokenLinksAmountTableColumn);
    controller.configurePlayerColumns(playersTableColumn, playerScoreTableColumn);

    controller.updateAllTables(storiesTableView, playersTableView, deadLinksTableView);

    mainHBox.setId("mainHBox");
    storiesVBox.setId("storiesVBox");
    playersVBox.setId("playersVBox");
    deadLinksVBox.setId("deadLinksVBox");
    startNewGameHBox.setId("startNewGameHBox");

    primaryStage.getScene().getStylesheets().add("css/newGame.css");
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
    passagesAmountTableColumn.setText(resources.getString("passagesAmountTableColumn"));
    linksAmountTableColumn.setText(resources.getString("linksAmountTableColumn"));
    brokenLinksAmountTableColumn.setText(resources.getString("brokenLinksAmountTableColumn"));
    playersTableColumn.setText(resources.getString("playersTableColumn"));
    playerScoreTableColumn.setText(resources.getString("playerScoreTableColumn"));
    deadLinksTableColumn.setText(resources.getString("deadLinksTableColumn"));
    deadLinksStoryTableColumn.setText(resources.getString("deadLinksStoryTableColumn"));
    deadLinksPassageTableColumn.setText(resources.getString("deadLinksPassageTableColumn"));
    informationText.setText(resources.getString("informationText"));
    deadLinksText.setText(resources.getString("deadLinksText"));
    playersText.setText(resources.getString("playersText"));
    storiesText.setText(resources.getString("storiesText"));
    editStoryButton.setText(resources.getString("editStoryButton"));
    newStoryButton.setText(resources.getString("newStoryButton"));
    deleteStoryButton.setText(resources.getString("deleteStoryButton"));
    importStoryButton.setText(resources.getString("importStoryButton"));
    exportButton.setText(resources.getString("exportButton"));
    editPlayerButton.setText(resources.getString("editPlayerButton"));
    newPlayerButton.setText(resources.getString("newPlayerButton"));
    deletePlayerButton.setText(resources.getString("deletePlayerButton"));
    deleteLinkButton.setText(resources.getString("deleteLinkButton"));
    updateDeadLinksButton.setText(resources.getString("updateDeadLinksButton"));
    startNewGameButton.setText(resources.getString("startNewGameButton"));
  }

  private VBox createStoriesVBox() {
    VBox storiesVBox = new VBox();

    storiesTableView = new TableView<>();
    storiesTableColumn = new TableColumn<>();
    passagesAmountTableColumn = new TableColumn<>();
    linksAmountTableColumn = new TableColumn<>();
    brokenLinksAmountTableColumn = new TableColumn<>();
    storiesTableView
        .getColumns()
        .addAll(
            storiesTableColumn,
            passagesAmountTableColumn,
            linksAmountTableColumn,
            brokenLinksAmountTableColumn);

    storiesText = new Text();

    editStoryButton = new Button();
    newStoryButton = new Button();
    deleteStoryButton = new Button();
    importStoryButton = new Button();
    importStoryButton.setOnAction(event -> controller.onImportStory(storiesTableView));
    exportButton = new Button();
    exportButton.setOnAction(event -> controller.onExportStory(storiesTableView));
    HBox storiesButtonsHBox = new HBox(editStoryButton, newStoryButton, deleteStoryButton);
    HBox importExportHBox = new HBox(importStoryButton, exportButton);
    VBox allStoryButtonsVBox = new VBox(storiesButtonsHBox, importExportHBox);

    storiesVBox.getChildren().addAll(storiesText, storiesTableView, allStoryButtonsVBox);

    return storiesVBox;
  }

  private VBox createPlayersVBox() {
    VBox playersVBox = new VBox();

    playersTableView = new TableView<>();
    playersTableColumn = new TableColumn<>();
    playerScoreTableColumn = new TableColumn<>();
    playersTableView.getColumns().addAll(playersTableColumn, playerScoreTableColumn);

    playersText = new Text();

    editPlayerButton = new Button();
    newPlayerButton = new Button();
    deletePlayerButton = new Button();
    HBox playersButtonsHBox = new HBox(editPlayerButton, newPlayerButton, deletePlayerButton);

    playersVBox.getChildren().addAll(playersText, playersTableView, playersButtonsHBox);

    return playersVBox;
  }

  private VBox createDeadLinksVBox() {
    VBox deadLinksVBox = new VBox();

    deadLinksTableView = new TableView<>();
    deadLinksTableColumn = new TableColumn<>();
    deadLinksStoryTableColumn = new TableColumn<>();
    deadLinksPassageTableColumn = new TableColumn<>();
    deadLinksTableView
        .getColumns()
        .addAll(deadLinksTableColumn, deadLinksStoryTableColumn, deadLinksPassageTableColumn);

    deadLinksText = new Text();

    deleteLinkButton = new Button();
    updateDeadLinksButton = new Button();
    HBox deadLinksButtonsHBox = new HBox(deleteLinkButton, updateDeadLinksButton);

    deadLinksVBox.getChildren().addAll(deadLinksText, deadLinksTableView, deadLinksButtonsHBox);

    return deadLinksVBox;
  }
}
