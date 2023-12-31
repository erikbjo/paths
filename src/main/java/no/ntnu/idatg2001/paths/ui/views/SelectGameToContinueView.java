package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.ui.controllers.SelectGameToContinueController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.standardobjects.StandardMenuBar;

public class SelectGameToContinueView implements View {
  private final SelectGameToContinueController controller;
  private final Stage stage;
  private final Text selectGameText;
  private final TableView<Game> ongoingGamesTableView;
  private final TableColumn<Game, String> ongoingGamesPlayerTableColumn;
  private final TableColumn<Game, String> ongoingGamesStoryTableColumn;
  private final TableColumn<Game, String> ongoingGamesCurrentPassageTableColumn;
  private final TableColumn<Game, String> ongoingGamesGoalsTableColumn;
  private Button continueButton;
  private Button changePlayerButton;
  private Button deleteGameButton;

  public SelectGameToContinueView(SelectGameToContinueController controller, Stage stage) {
    this.controller = controller;
    this.stage = stage;

    stage.setTitle("Select game");

    BorderPane root = new BorderPane();
    root.setPrefWidth(1200);
    root.setPrefHeight(800);
    root.setTop(new StandardMenuBar(stage));

    VBox centerVBox = new VBox();
    root.setCenter(centerVBox);

    selectGameText = new Text();

    ongoingGamesTableView = new TableView<>();
    ongoingGamesPlayerTableColumn = new TableColumn<>();
    ongoingGamesStoryTableColumn = new TableColumn<>();
    ongoingGamesCurrentPassageTableColumn = new TableColumn<>();
    ongoingGamesGoalsTableColumn = new TableColumn<>();
    ongoingGamesTableView
        .getColumns()
        .addAll(
            ongoingGamesPlayerTableColumn,
            ongoingGamesStoryTableColumn,
            ongoingGamesCurrentPassageTableColumn,
            ongoingGamesGoalsTableColumn);

    HBox buttonHBox = createButtonsHBox();

    centerVBox.getChildren().addAll(ongoingGamesTableView, buttonHBox);

    // CONTROLLER
    controller.configureGamesTableView(
        ongoingGamesTableView,
        ongoingGamesPlayerTableColumn,
        ongoingGamesStoryTableColumn,
        ongoingGamesCurrentPassageTableColumn,
        ongoingGamesGoalsTableColumn);
    controller.updateGameTable(ongoingGamesTableView);

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> updateLanguage());
    updateLanguage();

    centerVBox.setId("centerVBox");
    ongoingGamesTableView.setId("ongoingGamesTableView");
    buttonHBox.setId("buttonHBox");

    stage.getScene().setRoot(root);
    stage.getScene().getStylesheets().add("/css/selectGame.css");
  }

  @Override
  public void updateLanguage() {
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "languages/selectGameToContinue",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    selectGameText.setText(resources.getString("selectGameText"));
    continueButton.setText(resources.getString("continueButton"));
    changePlayerButton.setText(resources.getString("changePlayerButton"));
    deleteGameButton.setText(resources.getString("deleteGameButton"));

    ongoingGamesPlayerTableColumn.setText(resources.getString("ongoingGamesPlayerTableColumn"));
    ongoingGamesStoryTableColumn.setText(resources.getString("ongoingGamesStoryTableColumn"));
    ongoingGamesCurrentPassageTableColumn.setText(
        resources.getString("ongoingGamesCurrentPassageTableColumn"));
    ongoingGamesGoalsTableColumn.setText(resources.getString("ongoingGamesGoalsTableColumn"));
  }

  private HBox createButtonsHBox() {
    HBox buttonHBox = new HBox();

    continueButton = new Button();
    continueButton.setOnAction(e -> controller.onContinueGame(ongoingGamesTableView));

    changePlayerButton = new Button();
    changePlayerButton.setOnAction(e -> controller.onChangePlayer(ongoingGamesTableView));

    deleteGameButton = new Button();
    deleteGameButton.setOnAction(e -> controller.onDeleteGame(ongoingGamesTableView));

    buttonHBox.getChildren().addAll(continueButton, changePlayerButton, deleteGameButton);
    return buttonHBox;
  }
}
