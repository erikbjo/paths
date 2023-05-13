package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.Scene;
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
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class SelectGameToContinueView implements View {
  private final SelectGameToContinueController controller;
  private final Stage stage;
  private final Text selectGameText;
  private final Button continueButton;

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

    TableView<Game> ongoingGamesTableView = new TableView<>();
    TableColumn<Game, String> ongoingGamesPlayerTableColumn = new TableColumn<>();
    TableColumn<Game, String> ongoingGamesStoryTableColumn = new TableColumn<>();
    ongoingGamesTableView
        .getColumns()
        .addAll(ongoingGamesPlayerTableColumn, ongoingGamesStoryTableColumn);

    HBox buttonHBox = new HBox();
    continueButton = new Button();
    buttonHBox.getChildren().add(continueButton);

    centerVBox.getChildren().addAll(ongoingGamesTableView, buttonHBox);

    // CONTROLLER
    controller.configureGamesTableView(
        ongoingGamesTableView, ongoingGamesPlayerTableColumn, ongoingGamesStoryTableColumn);
    controller.updateGameTable(ongoingGamesTableView);

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> updateLanguage());
    updateLanguage();

    stage.getScene().setRoot(root);
  }

  @Override
  public void updateLanguage() {
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "languages/selectGameToContinue",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    selectGameText.setText(resources.getString("selectGameText"));
    continueButton.setText(resources.getString("continueButton"));
  }
}
