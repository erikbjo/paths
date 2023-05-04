package no.ntnu.idatg2001.paths.ui.views;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

import java.util.Locale;
import java.util.ResourceBundle;

public class NewStoryView {
  private final Stage primaryStage;

  public NewStoryView(Stage primaryStage) {
    this.primaryStage = primaryStage;
    primaryStage.setTitle("New Story");

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    StandardMenuBar menuBar = new StandardMenuBar(primaryStage);
    root.setTop(menuBar);
    AnchorPane rootAnchorPane = new AnchorPane();

    Scene scene = new Scene(root, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
