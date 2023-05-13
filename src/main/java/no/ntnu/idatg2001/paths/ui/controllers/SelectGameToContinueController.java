package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.ui.views.SelectGameToContinueView;

public class SelectGameToContinueController {
  private final SelectGameToContinueView view;

  public SelectGameToContinueController(Stage stage) {
    this.view = new SelectGameToContinueView(this, stage);
  }

  public SelectGameToContinueView getView() {
    return view;
  }

  public void configureGamesTableView(
      TableView<Game> ongoingGamesTableView,
      TableColumn<Game, String> ongoingGamesPlayerTableColumn,
      TableColumn<Game, String> ongoingGamesStoryTableColumn) {
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
  }

  public void updateGameTable(TableView<Game> ongoingGamesTableView) {
    ongoingGamesTableView.getItems().clear();
    List<Game> gameList = GameDAO.getInstance().getAll();
    // turn gameList into observable list
    ObservableList<Game> observableGameList = FXCollections.observableArrayList(gameList);
    ongoingGamesTableView.setItems(observableGameList);
  }
}
