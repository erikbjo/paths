package no.ntnu.idatg2001.paths.ui.views;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class EditStoryView {
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Edit Story");
    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(primaryStage));

    Scene scene = new Scene(root, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
