package no.ntnu.idatg2001.paths.ui.views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class EditPlayerView {
  private Player player;

  public void start(Stage primaryStage, Player player) {
    this.player = player;
    primaryStage.setTitle("Edit Player");

    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(primaryStage));

    Scene scene = new Scene(root, 600, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
