package no.ntnu.idatg2001.paths.ui.views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.controllers.EditStoryController;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class EditStoryView implements View {
  private Story story;
  private EditStoryController controller;

  public EditStoryView(EditStoryController controller, Stage primaryStage, Story story) {
    this.controller = controller;
    this.story = story;
    primaryStage.setTitle("Edit Story");

    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(primaryStage));

    Pane pane = new Pane();
    controller.visualizeHashMap(pane);
    root.setCenter(pane);

    primaryStage.getScene().setRoot(root);
  }

  @Override
  public void updateLanguage() {

  }
}
