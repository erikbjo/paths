package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
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

public class EditStoryView implements View {
  private final Story story;
  private final EditStoryController controller;
  private Button newLinkButton;
  private Button newPassageButton;
  private Button saveButton;
  private Button loadButton;
  private Button backButton;
  private Button deleteLinkButton;
  private Button deletePassageButton;

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

    updateLanguage();
  }

  @Override
  public void updateLanguage() {
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "languages/editStory",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    newLinkButton.setText(resources.getString("newLinkButton"));
    deleteLinkButton.setText(resources.getString("deleteLinkButton"));
    newPassageButton.setText(resources.getString("newPassageButton"));
    deletePassageButton.setText(resources.getString("deletePassageButton"));
    saveButton.setText(resources.getString("saveButton"));
    loadButton.setText(resources.getString("loadButton"));
    backButton.setText(resources.getString("backButton"));
  }

  private HBox createButtonBox() {
    HBox buttonBox = new HBox();

    newLinkButton = new Button();
    newLinkButton.setOnAction(event -> controller.onNewLinkButtonPressed());

    deleteLinkButton = new Button();
    deleteLinkButton.setOnAction(event -> controller.onDeleteLinkButtonPressed());

    newPassageButton = new Button();
    newPassageButton.setOnAction(event -> controller.onNewPassageButtonPressed());

    deletePassageButton = new Button();
    deletePassageButton.setOnAction(event -> controller.onDeletePassageButtonPressed());

    saveButton = new Button();
    saveButton.setOnAction(event -> controller.onSaveButtonPressed());

    loadButton = new Button();
    loadButton.setOnAction(event -> controller.onLoadButtonPressed());

    backButton = new Button();
    backButton.setOnAction(event -> controller.onBackButtonPressed());

    buttonBox
        .getChildren()
        .addAll(
            newLinkButton,
            deleteLinkButton,
            newPassageButton,
            deletePassageButton,
            saveButton,
            loadButton,
            backButton);

    return buttonBox;
  }
}
