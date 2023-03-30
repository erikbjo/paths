package no.ntnu.idatg2001.ui.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerInformationView extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    AnchorPane anchorPane = new AnchorPane();
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    Text newPlayerText = new Text("New player");

    VBox playerInformationVBox = new VBox();
    Text playerNameText = new Text("Name:");
    TextField playerNameTextField = new TextField();
    Text playerHealthText = new Text("Health: ");
    TextField playerHealthTextField = new TextField();
    Text playerGoldText = new Text("Gold:");
    TextField playerGoldTextField = new TextField();
    playerInformationVBox
        .getChildren()
        .addAll(
            playerNameText,
            playerNameTextField,
            playerHealthText,
            playerHealthTextField,
            playerGoldText,
            playerGoldTextField);

    GridPane goalCreationGridPane = new GridPane();
    Text healthGoalText = new Text("Health goal:");
    Text goldGoalText = new Text("Gold goal:");
    Text inventoryGoalText = new Text("Inventory goal:");
    Text scoreGoalText = new Text("Gold goal:");
    Spinner<Integer> healthSpinner = new Spinner<>();
    Spinner<Integer> goldSpinner = new Spinner<>();
    Spinner<Integer> inventorySpinner = new Spinner<>();
    Spinner<Integer> scoreSpinner = new Spinner<>();

    goalCreationGridPane.add(healthGoalText, 0,0);
    goalCreationGridPane.add(goldGoalText,0,1);
    goalCreationGridPane.add(inventoryGoalText,0,2);
    goalCreationGridPane.add(scoreGoalText, 0,3);
    goalCreationGridPane.add(healthSpinner,1,0);
    goalCreationGridPane.add(goldSpinner,0,1);
    goalCreationGridPane.add(inventorySpinner,0,2);
    goalCreationGridPane.add(scoreSpinner, 0,3);

    Button startButton = new Button("Start");

    gridPane.add(newPlayerText, 0, 0);
    gridPane.add(playerInformationVBox, 0, 1);
    gridPane.add(goalCreationGridPane,1,1);
    gridPane.add(startButton,1,2);

    anchorPane.getChildren().add(gridPane);
    AnchorPane.setTopAnchor(gridPane, 10.0);
    AnchorPane.setLeftAnchor(gridPane, 10.0);
    AnchorPane.setRightAnchor(gridPane, 10.0);
    AnchorPane.setBottomAnchor(gridPane, 10.0);

    Scene scene = new Scene(anchorPane, 600, 600);
    stage.setScene(scene);
    stage.show();
  }
}
