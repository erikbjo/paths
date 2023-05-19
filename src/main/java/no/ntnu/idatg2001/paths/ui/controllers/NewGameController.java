package no.ntnu.idatg2001.paths.ui.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.model.utilities.PathsStoryFileReader;
import no.ntnu.idatg2001.paths.model.utilities.PathsStoryFileWriter;
import no.ntnu.idatg2001.paths.ui.alerts.ConfirmationAlert;
import no.ntnu.idatg2001.paths.ui.alerts.ExceptionAlert;
import no.ntnu.idatg2001.paths.ui.alerts.WarningAlert;
import no.ntnu.idatg2001.paths.ui.dialogs.CreateGoalsForNewGameDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.NewPlayerDialog;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.NewGameView;
import no.ntnu.idatg2001.paths.ui.views.View;

public class NewGameController implements Controller {
  private final NewGameView view;
  private final Stage stage;
  private final ResourceBundle confirmationResources;
  private final ResourceBundle warningResources;
  private final ResourceBundle exceptionResources;
  private final ResourceBundle newGameResources;

  public NewGameController(Stage stage) {
    this.view = new NewGameView(this, stage);
    this.stage = stage;
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> view.updateLanguage());
    this.newGameResources =
        ResourceBundle.getBundle(
            "languages/newGame",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    this.confirmationResources =
        ResourceBundle.getBundle(
            "languages/confirmations",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    this.warningResources =
        ResourceBundle.getBundle(
            "languages/warnings",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    this.exceptionResources =
        ResourceBundle.getBundle(
            "languages/exceptions",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  }

  public View getView() {
    return view;
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  public void onNewGame(TableView<Story> storiesTableView, TableView<Player> playersTableView) {
    try {
      Story story = storiesTableView.getSelectionModel().getSelectedItem();
      Player player = playersTableView.getSelectionModel().getSelectedItem();

      if (story == null || player == null) {
        throw new NullPointerException(exceptionResources.getString("noStoryOrPlayerException"));
      }

      Game game = new Game(player, story, new ArrayList<>());
      GameDAO gameDAO = GameDAO.getInstance();
      gameDAO.add(game);

      CreateGoalsForNewGameDialog createGoalsForNewGameDialog =
          new CreateGoalsForNewGameDialog(game);
      Optional<List<Goal>> result = createGoalsForNewGameDialog.showAndWait();
      if (result.isPresent() && !result.get().isEmpty()) {
        game.setGoals(result.get());
        gameDAO.update(game);
        CurrentGameHandler.setCurrentGame(game);
        new GameController(stage);
      } else {
        gameDAO.remove(game);
      }
    } catch (NullPointerException e) {
      ExceptionAlert exceptionAlert = new ExceptionAlert(e);
      exceptionAlert.setTitle(exceptionResources.getString("alertTitle"));
      ButtonType okButtonType =
          new ButtonType(exceptionResources.getString("okButton"), ButtonBar.ButtonData.OK_DONE);
      exceptionAlert.getButtonTypes().setAll(okButtonType);
      exceptionAlert.showAndWait();
    }
  }

  public void configureStoryButtons(
      Button editStoryButton,
      Button newStoryButton,
      Button deleteStoryButton,
      TableView<Story> storiesTableView) {
    editStoryButton.setOnAction(
        event -> {
          try {
            Story story = storiesTableView.getSelectionModel().getSelectedItem();
            if (story == null) {
              throw new NullPointerException(exceptionResources.getString("noStoryException"));
            }
            new EditStoryController(stage, story);
          } catch (NullPointerException e) {
            ExceptionAlert exceptionAlert = new ExceptionAlert(e);
            exceptionAlert.setTitle(exceptionResources.getString("alertTitle"));
            ButtonType okButtonType =
                new ButtonType(
                    exceptionResources.getString("okButton"), ButtonBar.ButtonData.OK_DONE);
            exceptionAlert.getButtonTypes().setAll(okButtonType);
            exceptionAlert.showAndWait();
          }
        });

    newStoryButton.setOnAction(event -> new NewStoryController(stage));

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
                    confirmationResources.getString("deleteStoryTitle"),
                    confirmationResources.getString("deleteStoryContentStart")
                        + "\n"
                        + selectedStory.getTitle()
                        + "\n"
                        + confirmationResources.getString("deleteStoryContentEnd"));

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
              storiesTableView
                  .getItems()
                  .removeAll(storiesTableView.getSelectionModel().getSelectedItems());
              StoryDAO.getInstance().remove(selectedStory);

              updateStoryTable(storiesTableView);
            } else {
              confirmationAlert.close();
            }
          } else {
            WarningAlert warningAlert =
                new WarningAlert(warningResources.getString("deleteStoryContentText"));
            warningAlert.setTitle(warningResources.getString("dialogTitle"));
            warningAlert.setHeaderText(warningResources.getString("dialogHeader"));
            ButtonType okButtonType =
                new ButtonType(
                    warningResources.getString("okButton"), ButtonBar.ButtonData.OK_DONE);
            warningAlert.getButtonTypes().setAll(okButtonType);
            warningAlert.showAndWait();
          }
        });
  }

  public void onImportStory(TableView<Story> storiesTableView) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PATHS", "*.paths"));
    fileChooser.setTitle(newGameResources.getString("fileChooserTitle"));
    Optional<File> result = Optional.ofNullable(fileChooser.showOpenDialog(stage));
    if (result.isPresent()) {
      try {
        PathsStoryFileReader.readStoryFromFile(result.get());
      } catch (IOException e) {
        ExceptionAlert fileWriterExceptionAlert = new ExceptionAlert(e);
        fileWriterExceptionAlert.showAndWait();
      }
      updateStoryTable(storiesTableView);
    } else {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle(exceptionResources.getString("alertTitle"));
      alert.setHeaderText(exceptionResources.getString("alertTitle"));
      alert.setContentText(exceptionResources.getString("fileReaderException"));
      ButtonType okButtonType =
          new ButtonType(exceptionResources.getString("okButton"), ButtonBar.ButtonData.OK_DONE);
      alert.getButtonTypes().setAll(okButtonType);
      alert.showAndWait();
    }
  }

  public void configurePlayerButtons(
      Button editPlayerButton,
      Button newPlayerButton,
      Button deletePlayerButton,
      TableView<Player> playersTableView) {
    editPlayerButton.setOnAction(
        event -> {
          try {
            Player player = playersTableView.getSelectionModel().getSelectedItem();
            if (player == null) {
              throw new NullPointerException(exceptionResources.getString("noPlayerException"));
            }
            new EditPlayerController(stage, player);
          } catch (NullPointerException e) {
            ExceptionAlert exceptionAlert = new ExceptionAlert(e);
            exceptionAlert.showAndWait();
          }
        });

    newPlayerButton.setOnAction(
        event -> {
          NewPlayerDialog newPlayerDialog = new NewPlayerDialog();
          newPlayerDialog.initOwner(stage);

          Optional<Player> result = newPlayerDialog.showAndWait();
          result.ifPresent(
              player -> {
                PlayerDAO.getInstance().add(player);
                updatePlayerTable(playersTableView);
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
                    confirmationResources.getString("deletePlayerTitle"),
                    confirmationResources.getString("deletePlayerContentTextStart")
                        + "\n"
                        + selectedPlayer.getName()
                        + "\n"
                        + confirmationResources.getString("deletePlayerContentTextEnd"));

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

              PlayerDAO.getInstance().remove(selectedPlayer);

              updatePlayerTable(playersTableView);
            } else {
              confirmationAlert.close();
            }
          } else {
            WarningAlert warningAlert =
                new WarningAlert(warningResources.getString("deletePlayerContentText"));
            warningAlert.setTitle(warningResources.getString("dialogTitle"));
            warningAlert.setHeaderText(warningResources.getString("dialogHeader"));
            ButtonType okButtonType =
                new ButtonType(
                    warningResources.getString("okButton"), ButtonBar.ButtonData.OK_DONE);
            warningAlert.getButtonTypes().setAll(okButtonType);
            warningAlert.showAndWait();
          }
        });
  }

  public void configureDeadLinksButtons(
      Button deleteLinkButton, Button updateDeadLinksButton, TableView<Link> deadLinksTableView) {
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
                    confirmationResources.getString("deleteLinkTitle"),
                    confirmationResources.getString("deleteLinkContentText") + "\n" + deadLink);
            confirmationAlert.setHeaderText(confirmationResources.getString("confirmationText"));
            ButtonType okButtonType =
                new ButtonType(
                    confirmationResources.getString("okButton"), ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType =
                new ButtonType(
                    confirmationResources.getString("cancelButton"),
                    ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationAlert.getButtonTypes().setAll(okButtonType, cancelButtonType);

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
            WarningAlert warningAlert =
                new WarningAlert(warningResources.getString("deleteLinkContentText"));
            warningAlert.setTitle(warningResources.getString("dialogTitle"));
            warningAlert.setHeaderText(warningResources.getString("dialogHeader"));
            ButtonType okButtonType =
                new ButtonType(
                    warningResources.getString("okButton"), ButtonBar.ButtonData.OK_DONE);
            warningAlert.getButtonTypes().setAll(okButtonType);
            warningAlert.showAndWait();
          }
        });

    updateDeadLinksButton.setOnAction(event -> updateDeadLinkTable(deadLinksTableView));
  }

  public void updateAllTables(
      TableView<Story> storiesTableView,
      TableView<Player> playersTableView,
      TableView<Link> deadLinksTableView) {
    updateStoryTable(storiesTableView);
    updatePlayerTable(playersTableView);
    updateDeadLinkTable(deadLinksTableView);
  }

  public void updatePlayerTable(TableView<Player> playersTableView) {
    playersTableView.getItems().clear();
    List<Player> playerList = PlayerDAO.getInstance().getAll();
    // turn playerList into observable list
    ObservableList<Player> observablePlayerList = FXCollections.observableArrayList(playerList);
    playersTableView.setItems(observablePlayerList);
  }

  public void updateStoryTable(TableView<Story> storiesTableView) {
    storiesTableView.getItems().clear();
    List<Story> storyList = StoryDAO.getInstance().getAll();
    // turn storyList into observable list
    ObservableList<Story> observableStoryList = FXCollections.observableArrayList(storyList);
    storiesTableView.setItems(observableStoryList);
  }

  public void updateGameTable(TableView<Game> ongoingGamesTableView) {
    ongoingGamesTableView.getItems().clear();
    List<Game> gameList = GameDAO.getInstance().getAll();
    // turn gameList into observable list
    ObservableList<Game> observableGameList = FXCollections.observableArrayList(gameList);
    ongoingGamesTableView.setItems(observableGameList);
  }

  public void updateDeadLinkTable(TableView<Link> deadLinksTableView) {
    deadLinksTableView.getItems().clear();
    // do something
    StoryDAO.getInstance()
        .getAll()
        .forEach(story -> deadLinksTableView.getItems().addAll(story.getBrokenLinks()));
  }

  public void configureTableColumns(
      TableColumn<Story, String> storiesTableColumn,
      TableColumn<Player, String> playersTableColumn,
      TableColumn<Link, String> deadLinksTableColumn) {
    storiesTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    storiesTableColumn.setPrefWidth(250);

    playersTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    playersTableColumn.setPrefWidth(250);

    deadLinksTableColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
    deadLinksTableColumn.setPrefWidth(250);
  }

  public void onExportStory(TableView<Story> storiesTableView) {
    if (!storiesTableView.getSelectionModel().isEmpty()) {
      DirectoryChooser directoryChooser = new DirectoryChooser();
      // TODO: MAKE THIS LANGUAGE INDEPENDENT
      directoryChooser.setTitle("Select a directory to export to");

      Optional<File> result = Optional.ofNullable(directoryChooser.showDialog(stage));
      if (result.isPresent()) {
        // TODO: MAKE IT SO THAT THE FILE IS NOT OVERWRITTEN AND THAT THE USER IS WARNED
        // ALSO MAKE IT WRITE TO THE DIRECTORY
        File directory = result.get();
        Story selectedStory = storiesTableView.getSelectionModel().getSelectedItem();
        PathsStoryFileWriter.writeStoryToFile(selectedStory);
      }
    } else {
      // TODO: warning
      System.out.println("no story selected");
    }
  }
}
