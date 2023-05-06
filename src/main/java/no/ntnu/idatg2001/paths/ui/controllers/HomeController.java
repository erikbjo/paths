package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.database.GameDAO;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.database.StoryDAO;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.goals.GoldGoal;
import no.ntnu.idatg2001.paths.model.goals.HealthGoal;
import no.ntnu.idatg2001.paths.model.goals.ScoreGoal;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.alerts.ConfirmationAlert;
import no.ntnu.idatg2001.paths.ui.alerts.ExceptionAlert;
import no.ntnu.idatg2001.paths.ui.alerts.WarningAlert;
import no.ntnu.idatg2001.paths.ui.dialogs.NewPlayerDialog;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.EditPlayerView;
import no.ntnu.idatg2001.paths.ui.views.EditStoryView;
import no.ntnu.idatg2001.paths.ui.views.NewStoryView;
import no.ntnu.idatg2001.paths.ui.views.StoryView;

public class HomeController {
  // TEXTS
  private final Text pathsGameText;
  private final Text storiesText;
  private final Text playersText;
  private final Text deadLinksText;
  private final Text ongoingGamesText;
  // BUTTONS
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
  private final Button startNewGameButton;

  // TABLE VIEWS

  private final TableView<Story> storiesTableView;
  private final TableView<Player> playersTableView;
  private final TableView<Link> deadLinksTableView;
  private final TableView<Game> ongoingGamesTableView;
  // TABLE COLUMNS
  private final TableColumn<Game, String> ongoingGamesPlayerTableColumn;
  private final TableColumn<Game, String> ongoingGamesStoryTableColumn;
  private final TableColumn<Story, String> storiesTableColumn;
  private final TableColumn<Player, String> playersTableColumn;
  private final TableColumn<Link, String> deadLinksTableColumn;

  private final Stage primaryStage;
  private ResourceBundle resources;

  public HomeController(
      Text pathsGameText,
      Text storiesText,
      Text playersText,
      Text deadLinksText,
      Text ongoingGamesText,
      Button editStoryButton,
      Button newStoryButton,
      Button deleteStoryButton,
      Button editPlayerButton,
      Button newPlayerButton,
      Button deletePlayerButton,
      Button deleteLinkButton,
      Button updateDeadLinksButton,
      Button continueButton,
      Button deleteButton,
      Button startNewGameButton,
      TableView<Story> storiesTableView,
      TableView<Player> playersTableView,
      TableView<Link> deadLinksTableView,
      TableView<Game> ongoingGamesTableView,
      TableColumn<Story, String> storiesTableColumn,
      TableColumn<Player, String> playersTableColumn,
      TableColumn<Link, String> deadLinksTableColumn,
      TableColumn<Game, String> ongoingGamesPlayerTableColumn,
      TableColumn<Game, String> ongoingGamesStoryTableColumn,
      Stage primaryStage) {
    // TEXTS
    this.pathsGameText = pathsGameText;
    this.storiesText = storiesText;
    this.playersText = playersText;
    this.deadLinksText = deadLinksText;
    this.ongoingGamesText = ongoingGamesText;

    // BUTTONS
    this.editStoryButton = editStoryButton;
    this.newStoryButton = newStoryButton;
    this.deleteStoryButton = deleteStoryButton;

    this.editPlayerButton = editPlayerButton;
    this.newPlayerButton = newPlayerButton;
    this.deletePlayerButton = deletePlayerButton;

    this.deleteLinkButton = deleteLinkButton;
    this.updateDeadLinksButton = updateDeadLinksButton;

    this.continueButton = continueButton;
    this.deleteButton = deleteButton;

    this.startNewGameButton = startNewGameButton;

    // TABLE VIEWS
    this.storiesTableView = storiesTableView;
    this.playersTableView = playersTableView;
    this.deadLinksTableView = deadLinksTableView;
    this.ongoingGamesTableView = ongoingGamesTableView;

    // TABLE COLUMNS
    this.ongoingGamesPlayerTableColumn = ongoingGamesPlayerTableColumn;
    this.ongoingGamesStoryTableColumn = ongoingGamesStoryTableColumn;
    this.storiesTableColumn = storiesTableColumn;
    this.playersTableColumn = playersTableColumn;

    this.deadLinksTableColumn = deadLinksTableColumn;
    this.primaryStage = primaryStage;

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "home", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  }

  public void configureButtons() {
    configureStoryButtons();
    configurePlayerButtons();
    configureDeadLinksButtons();
    configureOngoingGamesButtons();

    startNewGameButton.setOnAction(
        event -> {
          try {
            Story story = storiesTableView.getSelectionModel().getSelectedItem();
            Player player = playersTableView.getSelectionModel().getSelectedItem();

            if (story == null || player == null) {
              throw new NullPointerException("No story or player selected");
            }

            Game game = new Game(player, story, new ArrayList<>());
            GameDAO gameDAO = GameDAO.getInstance();
            gameDAO.add(game);

            CurrentGameHandler.setCurrentGame(game);

            StoryView storyView = new StoryView();
            storyView.start(primaryStage);
          } catch (NullPointerException e) {
            ExceptionAlert exceptionAlert = new ExceptionAlert(e);
            exceptionAlert.showAndWait();
          }
        });
  }

  public void configureStoryButtons() {
    editStoryButton.setOnAction(
        event -> {
          try {
            Story story = storiesTableView.getSelectionModel().getSelectedItem();
            if (story == null) {
              throw new NullPointerException("No story selected");
            }
            EditStoryView editStoryView = new EditStoryView();
            editStoryView.start(primaryStage, story);
          } catch (NullPointerException e) {
            ExceptionAlert exceptionAlert = new ExceptionAlert(e);
            exceptionAlert.showAndWait();
          }
        });

    newStoryButton.setOnAction(
        event -> {
          NewStoryView newStoryView = new NewStoryView();
          newStoryView.start(primaryStage);
        });

    deleteStoryButton.setOnAction(
        event -> {
          if (storiesTableView
              .getSelectionModel()
              .isSelected(storiesTableView.getSelectionModel().getSelectedIndex())) {
            Story selectedStory =
                storiesTableView
                    .getItems()
                    .get(storiesTableView.getSelectionModel().getSelectedIndex());

            ConfirmationAlert confirmationAlert =
                new ConfirmationAlert(
                    "Delete story",
                    "Are you sure you want to delete this story?\n"
                        + selectedStory.getTitle()
                        + "\nThis will also delete any games this story is part of.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
              storiesTableView
                  .getItems()
                  .removeAll(storiesTableView.getSelectionModel().getSelectedItems());
              StoryDAO.getInstance().remove(selectedStory);

              updateStoryTable();
              updateGameTable();
            } else {
              confirmationAlert.close();
            }
          } else {
            WarningAlert warningAlert = new WarningAlert("Please select a player to delete");
            warningAlert.showAndWait();
          }
        });
  }

  public void configurePlayerButtons() {
    editPlayerButton.setOnAction(
        event -> {
          try {
            Player player = playersTableView.getSelectionModel().getSelectedItem();
            if (player == null) {
              throw new NullPointerException("No player selected");
            }
            EditPlayerView editPlayerView = new EditPlayerView();
            editPlayerView.start(primaryStage, player);
          } catch (NullPointerException e) {
            ExceptionAlert exceptionAlert = new ExceptionAlert(e);
            exceptionAlert.showAndWait();
          }
        });

    newPlayerButton.setOnAction(
        event -> {
          NewPlayerDialog newPlayerDialog = new NewPlayerDialog();
          newPlayerDialog.initOwner(primaryStage);

          Optional<Player> result = newPlayerDialog.showAndWait();
          result.ifPresent(
              player -> {
                PlayerDAO.getInstance().add(player);
                updatePlayerTable();
              });
        });

    deletePlayerButton.setOnAction(
        event -> {
          if (playersTableView
              .getSelectionModel()
              .isSelected(playersTableView.getSelectionModel().getSelectedIndex())) {
            Player selectedPlayer =
                playersTableView
                    .getItems()
                    .get(playersTableView.getSelectionModel().getSelectedIndex());

            ConfirmationAlert confirmationAlert =
                new ConfirmationAlert(
                    "Delete player",
                    "Are you sure you want to delete this player?\n"
                        + selectedPlayer.getName()
                        + "\nThis will also delete any games this player is part of.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

              PlayerDAO.getInstance().remove(selectedPlayer);

              updatePlayerTable();
              updateGameTable();
            } else {
              confirmationAlert.close();
            }
          } else {
            WarningAlert warningAlert = new WarningAlert("Please select a player to delete");
            warningAlert.showAndWait();
          }
        });
  }

  public void configureDeadLinksButtons() {
    deleteLinkButton.setOnAction(
        event -> {
          if (deadLinksTableView
              .getSelectionModel()
              .isSelected(deadLinksTableView.getSelectionModel().getSelectedIndex())) {
            Link deadLink =
                deadLinksTableView
                    .getItems()
                    .get(deadLinksTableView.getSelectionModel().getSelectedIndex());

            ConfirmationAlert confirmationAlert =
                new ConfirmationAlert(
                    "Delete link", "Are you sure you want to delete this link?\n" + deadLink);

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
              // Remove the link from the story
              // @TODO Fix this
              deadLinksTableView
                  .getItems()
                  .removeAll(deadLinksTableView.getSelectionModel().getSelectedItems());
              // Remove the link from the database
              // Or update the story in the database
            } else {
              confirmationAlert.close();
            }
          } else {
            WarningAlert warningAlert = new WarningAlert("Please select a link to delete");
            warningAlert.showAndWait();
          }
        });

    updateDeadLinksButton.setOnAction(event -> updateDeadLinkTable());
  }

  public void configureOngoingGamesButtons() {
    continueButton.setOnAction(
        event -> {
          if (ongoingGamesTableView
              .getSelectionModel()
              .isSelected(ongoingGamesTableView.getSelectionModel().getSelectedIndex())) {
            CurrentGameHandler.setCurrentGame(
                ongoingGamesTableView.getSelectionModel().getSelectedItem());

            StoryView storyView = new StoryView();
            storyView.start(primaryStage);

            createTestItems();
          } else {
            WarningAlert warningAlert = new WarningAlert("Please select a game to continue");
            warningAlert.showAndWait();
          }
        });

    deleteButton.setOnAction(
        event -> {
          if (ongoingGamesTableView
              .getSelectionModel()
              .isSelected(ongoingGamesTableView.getSelectionModel().getSelectedIndex())) {
            Game selectedGame =
                ongoingGamesTableView
                    .getItems()
                    .get(ongoingGamesTableView.getSelectionModel().getSelectedIndex());

            StringBuilder contentText =
                new StringBuilder("Are you sure you want to delete this game?\n");

            if (selectedGame.getStory() != null) {
              contentText.append("Story: ").append(selectedGame.getStory().getTitle()).append("\n");
            } else {
              contentText.append("No story\n");
            }

            if (selectedGame.getPlayer() != null) {
              contentText
                  .append("Player: ")
                  .append(selectedGame.getPlayer().getName())
                  .append("\n");
            } else {
              contentText.append("No player\n");
            }

            ConfirmationAlert confirmationAlert =
                new ConfirmationAlert("Delete game", contentText.toString());

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

              GameDAO.getInstance().remove(selectedGame);

              updateStoryTable();
              updatePlayerTable();
              updateGameTable();
            } else {
              confirmationAlert.close();
            }
          } else {
            WarningAlert warningAlert = new WarningAlert("Please select a game to delete");
            warningAlert.showAndWait();
          }
        });
  }

  public void updateLanguage() {
    resources =
        ResourceBundle.getBundle(
            "home", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    pathsGameText.setText(resources.getString("title"));
    storiesText.setText(resources.getString("storiesText"));
    playersText.setText(resources.getString("playersText"));
    deadLinksText.setText(resources.getString("deadLinksText"));
    ongoingGamesText.setText(resources.getString("ongoingGamesText"));

    editStoryButton.setText(resources.getString("editStoryButton"));
    newStoryButton.setText(resources.getString("newStoryButton"));
    deleteStoryButton.setText(resources.getString("deleteStoryButton"));

    editPlayerButton.setText(resources.getString("editPlayerButton"));
    newPlayerButton.setText(resources.getString("newPlayerButton"));
    deletePlayerButton.setText(resources.getString("deletePlayerButton"));

    deleteLinkButton.setText(resources.getString("deleteLinkButton"));
    updateDeadLinksButton.setText(resources.getString("updateDeadLinksButton"));
    continueButton.setText(resources.getString("continueButton"));
    deleteButton.setText(resources.getString("deleteButton"));
    startNewGameButton.setText(resources.getString("startNewGameButton"));

    ongoingGamesPlayerTableColumn.setText(resources.getString("ongoingGamesPlayerTableColumn"));
    ongoingGamesStoryTableColumn.setText(resources.getString("ongoingGamesStoryTableColumn"));

    storiesTableColumn.setText(resources.getString("storiesTableColumn"));
    playersTableColumn.setText(resources.getString("playersTableColumn"));
    deadLinksTableColumn.setText(resources.getString("deadLinksTableColumn"));
  }

  public void updateAllTables() {
    updatePlayerTable();
    updateStoryTable();
    updateGameTable();
    updateDeadLinkTable();
  }

  public void updatePlayerTable() {
    playersTableView.getItems().clear();
    List<Player> playerList = PlayerDAO.getInstance().getAll();
    // turn playerList into observable list
    ObservableList<Player> observablePlayerList = FXCollections.observableArrayList(playerList);
    playersTableView.setItems(observablePlayerList);
  }

  public void updateStoryTable() {
    storiesTableView.getItems().clear();
    List<Story> storyList = StoryDAO.getInstance().getAll();
    // turn storyList into observable list
    ObservableList<Story> observableStoryList = FXCollections.observableArrayList(storyList);
    storiesTableView.setItems(observableStoryList);
  }

  public void updateGameTable() {
    ongoingGamesTableView.getItems().clear();
    List<Game> gameList = GameDAO.getInstance().getAll();
    // turn gameList into observable list
    ObservableList<Game> observableGameList = FXCollections.observableArrayList(gameList);
    ongoingGamesTableView.setItems(observableGameList);
  }

  public void updateDeadLinkTable() {
    deadLinksTableView.getItems().clear();
    // do something
    StoryDAO.getInstance()
        .getAll()
        .forEach(story -> deadLinksTableView.getItems().addAll(story.getBrokenLinks()));
  }

  public void configureTableColumns() {
    ongoingGamesTableView.setPrefWidth(250);
    ongoingGamesPlayerTableColumn.setCellValueFactory(
        cellData -> {
          Game game = cellData.getValue();
          String playerName;
          if (game.getPlayer() == null) {
            playerName = "No player";
          } else {
            playerName = game.getPlayer().getName();
          }
          return new ReadOnlyStringWrapper(playerName);
        });
    ongoingGamesPlayerTableColumn.setPrefWidth(ongoingGamesTableView.getPrefWidth() / 2);

    ongoingGamesStoryTableColumn.setCellValueFactory(
        cellData -> {
          Game game = cellData.getValue();
          String storyTitle;
          if (game.getStory() == null) {
            storyTitle = "No story";
          } else {
            storyTitle = game.getStory().getTitle();
          }
          return new ReadOnlyStringWrapper(storyTitle);
        });
    ongoingGamesStoryTableColumn.setPrefWidth(ongoingGamesTableView.getPrefWidth() / 2);

    storiesTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    storiesTableColumn.setPrefWidth(250);

    playersTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    playersTableColumn.setPrefWidth(250);

    deadLinksTableColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
    deadLinksTableColumn.setPrefWidth(250);
  }

  // FOR TESTING


  private void createTestItems() {
    Random random = new Random();

    PlayerDAO.getInstance()
        .add(
            new Player.PlayerBuilder()
                .withName("Test" + random.nextInt(1000))
                .withMana(5)
                .withHealth(5)
                .withEnergy(5)
                .withGold(5)
                .withGold(5)
                .withScore(5)
                .withAttributes(new Attributes(1, 1, 1, 1, 1, 1, 1))
                .build());
    updatePlayerTable();

    Story newStory =
        new Story(
            "Test" + random.nextInt(1000), new Passage("Title" + random.nextInt(1000), "Text"));

    StoryDAO.getInstance().add(newStory);
    updateStoryTable();
  }
}
