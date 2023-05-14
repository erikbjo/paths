package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.alerts.ConfirmationAlert;
import no.ntnu.idatg2001.paths.ui.alerts.ExceptionAlert;
import no.ntnu.idatg2001.paths.ui.alerts.WarningAlert;
import no.ntnu.idatg2001.paths.ui.dialogs.NewPlayerDialog;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.NewGameView;
import no.ntnu.idatg2001.paths.ui.views.View;

public class NewGameController implements Controller {
    private final NewGameView view;
    private final Stage stage;

    public NewGameController(Stage stage) {
        this.view = new NewGameView(this, stage);
        this.stage = stage;
        LanguageHandler.getObservableIntegerCounter()
            .addListener((a, b, c) -> view.updateLanguage());
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
                throw new NullPointerException("No story or player selected");
            }

            Game game = new Game(player, story, new ArrayList<>());
            GameDAO gameDAO = GameDAO.getInstance();
            gameDAO.add(game);

            CurrentGameHandler.setCurrentGame(game);

            new GameController(stage);
        } catch (NullPointerException e) {
            ExceptionAlert exceptionAlert = new ExceptionAlert(e);
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
                        throw new NullPointerException("No story selected");
                    }
                    new EditStoryController(stage, story);
                } catch (NullPointerException e) {
                    ExceptionAlert exceptionAlert = new ExceptionAlert(e);
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

                        updateStoryTable(storiesTableView);
                    } else {
                        confirmationAlert.close();
                    }
                } else {
                    WarningAlert warningAlert =
                        new WarningAlert("Please select a player to delete");
                    warningAlert.showAndWait();
                }
            });
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
                        throw new NullPointerException("No player selected");
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
                            "Delete player",
                            "Are you sure you want to delete this player?\n"
                                + selectedPlayer.getName()
                                + "\nThis will also delete any games this player is part of.");

                    Optional<ButtonType> result = confirmationAlert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {

                        PlayerDAO.getInstance().remove(selectedPlayer);

                        updatePlayerTable(playersTableView);
                    } else {
                        confirmationAlert.close();
                    }
                } else {
                    WarningAlert warningAlert =
                        new WarningAlert("Please select a player to delete");
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
                            "Delete link",
                            "Are you sure you want to delete this link?\n" + deadLink);

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
}
