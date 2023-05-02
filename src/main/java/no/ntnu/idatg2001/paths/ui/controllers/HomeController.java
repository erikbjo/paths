package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.ui.alerts.ConfirmationAlert;
import no.ntnu.idatg2001.paths.ui.dialogs.NewPlayerDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.NewStoryDialog;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.EditPlayerView;
import no.ntnu.idatg2001.paths.ui.views.EditStoryView;
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
  private final Button editPlayerButton;
  private final Button newPlayerButton;
  private final Button deleteLinkButton;
  private final Button continueButton;
  private final Button deleteButton;
  // TABLE COLUMNS
  private final TableColumn<String, String> ongoingGamesTableColumn;
  private final TableColumn<String, String> storiesTableColumn;
  private final TableColumn<String, String> playersTableColumn;
  private final TableColumn<String, String> deadLinksTableColumn;

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
      Button editPlayerButton,
      Button newPlayerButton,
      Button deleteLinkButton,
      Button continueButton,
      Button deleteButton,
      TableColumn<String, String> ongoingGamesTableColumn,
      TableColumn<String, String> storiesTableColumn,
      TableColumn<String, String> playersTableColumn,
      TableColumn<String, String> deadLinksTableColumn,
      Stage primaryStage) {
    this.pathsGameText = pathsGameText;
    this.storiesText = storiesText;
    this.playersText = playersText;
    this.deadLinksText = deadLinksText;
    this.ongoingGamesText = ongoingGamesText;
    this.editStoryButton = editStoryButton;
    this.newStoryButton = newStoryButton;
    this.editPlayerButton = editPlayerButton;
    this.newPlayerButton = newPlayerButton;
    this.deleteLinkButton = deleteLinkButton;
    this.continueButton = continueButton;
    this.deleteButton = deleteButton;
    this.ongoingGamesTableColumn = ongoingGamesTableColumn;
    this.storiesTableColumn = storiesTableColumn;
    this.playersTableColumn = playersTableColumn;
    this.deadLinksTableColumn = deadLinksTableColumn;
    this.primaryStage = primaryStage;

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "home", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  }

  public void configureButtons() {
    editStoryButton.setOnAction(
        event -> {
          EditStoryView editStoryView = new EditStoryView();
          editStoryView.start(primaryStage);
        });

    newStoryButton.setOnAction(
        event -> {
          NewStoryDialog newStoryDialog = new NewStoryDialog();
          newStoryDialog.showAndWait();
        });

    editPlayerButton.setOnAction(
        event -> {
          EditPlayerView editPlayerView = new EditPlayerView();
          editPlayerView.start(primaryStage);
        });

    newPlayerButton.setOnAction(
        event -> {
          NewPlayerDialog newPlayerDialog = new NewPlayerDialog();
          newPlayerDialog.showAndWait();
        });

    deleteLinkButton.setOnAction(
        event -> {
          ConfirmationAlert confirmationAlert =
              new ConfirmationAlert("Delete Link", "Are you sure you want to delete this link?");
          confirmationAlert.showAndWait();
        });

    continueButton.setOnAction(
        event -> {
          StoryView storyView = new StoryView();
          storyView.start(primaryStage);
        });

    deleteButton.setOnAction(
        event -> {
          ConfirmationAlert confirmationAlert =
              new ConfirmationAlert("Delete Game", "Are you sure you want to delete this game?");
          confirmationAlert.showAndWait();
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
    editPlayerButton.setText(resources.getString("editPlayerButton"));
    newPlayerButton.setText(resources.getString("newPlayerButton"));
    deleteLinkButton.setText(resources.getString("deleteLinkButton"));
    continueButton.setText(resources.getString("continueButton"));
    deleteButton.setText(resources.getString("deleteButton"));
    ongoingGamesTableColumn.setText(resources.getString("ongoingGamesTableColumn"));
    storiesTableColumn.setText(resources.getString("storiesTableColumn"));
    playersTableColumn.setText(resources.getString("playersTableColumn"));
    deadLinksTableColumn.setText(resources.getString("deadLinksTableColumn"));
  }
}
