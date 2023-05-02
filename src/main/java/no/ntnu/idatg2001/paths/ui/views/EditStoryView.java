package no.ntnu.idatg2001.paths.ui.views;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditStoryView {
  public void start(Stage primaryStage) {
    AnchorPane rootAnchorPane = new AnchorPane();

    Scene scene = new Scene(rootAnchorPane, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
