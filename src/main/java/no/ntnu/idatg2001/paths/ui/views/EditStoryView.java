package no.ntnu.idatg2001.paths.ui.views;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.controllers.EditStoryController;
import no.ntnu.idatg2001.paths.ui.standardobjects.StandardMenuBar;

public class EditStoryView implements View {
  private Story story;
  private EditStoryController controller;

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

    primaryStage.getScene().setRoot(root);
  }

  @Override
  public void updateLanguage() {

  }
}
