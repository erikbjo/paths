package no.ntnu.idatg2001.paths.ui.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {
  private ResourceBundle resources;
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
      TableColumn<String, String> deadLinksTableColumn) {
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

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
            .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
            ResourceBundle.getBundle(
                    "home", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
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
