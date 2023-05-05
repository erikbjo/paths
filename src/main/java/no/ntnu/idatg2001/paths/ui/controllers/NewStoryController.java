package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class NewStoryController {
  private final Stage primaryStage;
  private final Text newStoryText;
  private final Text titleText;
  private final TextField titleTextField;
  private final Button newPassageButton;
  private final Button newLinkButton;
  private final Button addToStoryButton;
  private final Button createStoryButton;
  private final Button cancelButton;
  private final TableView<Link> linkCreationTableView;
  private final TableColumn<Link, String> linkColumn;
  private final TableView<Passage> passageCreationTableView;
  private final TableColumn<Passage, String> passageColumn;
  private final TableColumn<Passage, String> startingPassageColumn;
  private final TableView<Passage> startingPassageTableView;
  private ResourceBundle resources;

  public NewStoryController(
      Stage primaryStage,
      Text newStoryText,
      Text titleText,
      TextField titleTextField,
      Button newPassageButton,
      Button newLinkButton,
      Button addToStoryButton,
      Button createStoryButton,
      Button cancelButton,
      TableView<Link> linkCreationTableView,
      TableColumn<Link, String> linkColumn,
      TableView<Passage> passageCreationTableView,
      TableColumn<Passage, String> passageColumn,
      TableColumn<Passage, String> startingPassageColumn,
      TableView<Passage> startingPassageTableView) {
    this.primaryStage = primaryStage;
    this.newStoryText = newStoryText;
    this.titleText = titleText;
    this.titleTextField = titleTextField;
    this.newPassageButton = newPassageButton;
    this.newLinkButton = newLinkButton;
    this.addToStoryButton = addToStoryButton;
    this.createStoryButton = createStoryButton;
    this.cancelButton = cancelButton;
    this.linkCreationTableView = linkCreationTableView;
    this.linkColumn = linkColumn;
    this.passageCreationTableView = passageCreationTableView;
    this.passageColumn = passageColumn;
    this.startingPassageColumn = startingPassageColumn;
    this.startingPassageTableView = startingPassageTableView;

    // Observes when the language in Database is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());
  }

  public void updateLanguage() {
    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "newStory", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    newStoryText.setText(resources.getString("newStoryText"));
    titleText.setText(resources.getString("titleText"));
    titleTextField.setPromptText(resources.getString("titleTextField"));
    newPassageButton.setText(resources.getString("newPassageButton"));
    newLinkButton.setText(resources.getString("newLinkButton"));
    addToStoryButton.setText(resources.getString("addToStoryButton"));
    createStoryButton.setText(resources.getString("createStoryButton"));
    cancelButton.setText(resources.getString("cancelButton"));
    linkCreationTableView.setPlaceholder(new Text(resources.getString("linkCreationTableView")));
    linkColumn.setText(resources.getString("linkColumn"));
    passageCreationTableView.setPlaceholder(
        new Text(resources.getString("passageCreationTableView")));
    passageColumn.setText(resources.getString("passageColumn"));
    startingPassageColumn.setText(resources.getString("startingPassageColumn"));
    startingPassageTableView.setPlaceholder(
        new Text(resources.getString("startingPassageTableView")));
  }
}
