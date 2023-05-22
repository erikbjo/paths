package no.ntnu.idatg2001.paths.ui.views;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.controllers.EditStoryController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.standardobjects.StandardMenuBar;

import java.util.Locale;
import java.util.ResourceBundle;

public class EditStoryView implements View {
  private final Story story;
  private final EditStoryController controller;
  private Button newLinkButton;
  private Button newPassageButton;
  private Button saveButton;
  private Button loadButton;
  private Button backButton;

  public EditStoryView(EditStoryController controller, Stage primaryStage, Story story) {
    this.controller = controller;
    this.story = story;
    primaryStage.setTitle("Edit Story");

    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(primaryStage));

    ScrollPane scrollPane = new ScrollPane();
    Pane pane = new Pane();
    controller.visualizeHashMap(pane);
    scrollPane.setContent(pane);
    root.setCenter(scrollPane);

    HBox buttonBox = createButtonBox();
    root.setBottom(buttonBox);

    primaryStage.getScene().setRoot(root);
  }

  @Override
  public void updateLanguage() {
    ResourceBundle resources =
            ResourceBundle.getBundle(
                    "languages/editStory",
                    Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    newLinkButton.setText(resources.getString("newLinkButton"));
    newPassageButton.setText(resources.getString("newPassageButton"));
    saveButton.setText(resources.getString("saveButton"));
    loadButton.setText(resources.getString("loadButton"));
    backButton.setText(resources.getString("backButton"));
  }

  private HBox createButtonBox() {
    HBox buttonBox = new HBox();

    newLinkButton = new Button("New Link");
    newLinkButton.setOnAction(event -> controller.onNewLinkButtonPressed());

    newPassageButton = new Button("New Passage");
    newPassageButton.setOnAction(event -> controller.onNewPassageButtonPressed());

    saveButton = new Button("Save to database");
    saveButton.setOnAction(event -> controller.onSaveButtonPressed());

    loadButton = new Button("Restore from database");
    loadButton.setOnAction(event -> controller.onLoadButtonPressed());

    backButton = new Button("Back");
    backButton.setOnAction(event -> controller.onBackButtonPressed());

    buttonBox
        .getChildren()
        .addAll(newLinkButton, newPassageButton, saveButton, loadButton, backButton);

    return buttonBox;
  }
}
