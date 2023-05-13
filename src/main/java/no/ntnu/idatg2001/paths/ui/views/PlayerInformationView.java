package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.controllers.PlayerInformationController;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class PlayerInformationView implements View {
  private ResourceBundle resources;
  private Text healthGoalText;
  private Text goldGoalText;
  private Text inventoryGoalText;
  private Text scoreGoalText;
  private Text playerNameText;
  private Text playerHealthText;
  private Text playerGoldText;
  private Text newPlayerText;
  private Button startButton;

  public void start(Stage stage) {
    stage.setTitle("Player Information");
    // Observes when the language in Database is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "playerInformation",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    PlayerInformationController controller = new PlayerInformationController();

    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(stage));

    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    newPlayerText = new Text(resources.getString("newPlayer"));

    VBox playerInformationVBox = new VBox();
    playerNameText = new Text(resources.getString("playerName"));
    TextField playerNameTextField = new TextField();
    playerHealthText = new Text(resources.getString("playerHealth"));
    TextField playerHealthTextField = new TextField();
    playerGoldText = new Text(resources.getString("playerGold"));
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

    controller.makeTextFieldNotStartWithSpace(playerNameTextField);
    controller.makeTextFieldNumericOnly(playerGoldTextField);
    controller.makeTextFieldNumericOnly(playerHealthTextField);

    GridPane goalCreationGridPane = new GridPane();
    healthGoalText = new Text(resources.getString("healthGoal"));
    goldGoalText = new Text(resources.getString("goldGoal"));
    inventoryGoalText = new Text(resources.getString("inventoryGoal"));
    scoreGoalText = new Text(resources.getString("scoreGoal"));

    Spinner<Integer> healthSpinner = new Spinner<>();
    healthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
    Spinner<Integer> goldSpinner = new Spinner<>();
    goldSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
    Spinner<Integer> inventorySpinner = new Spinner<>();
    inventorySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
    Spinner<Integer> scoreSpinner = new Spinner<>();
    scoreSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));

    goalCreationGridPane.add(healthGoalText, 0, 0);
    goalCreationGridPane.add(goldGoalText, 0, 1);
    goalCreationGridPane.add(inventoryGoalText, 0, 2);
    goalCreationGridPane.add(scoreGoalText, 0, 3);
    goalCreationGridPane.add(healthSpinner, 1, 0);
    goalCreationGridPane.add(goldSpinner, 1, 1);
    goalCreationGridPane.add(inventorySpinner, 1, 2);
    goalCreationGridPane.add(scoreSpinner, 1, 3);

    startButton = new Button(resources.getString("start"));
    startButton.setOnAction(
        event -> {
          StoryView storyView = new StoryView();
          storyView.start(stage);
        });

    gridPane.add(newPlayerText, 0, 0);
    gridPane.add(playerInformationVBox, 0, 1);
    gridPane.add(goalCreationGridPane, 1, 1);
    gridPane.add(startButton, 1, 2);

    VBox vBox = new VBox();
    vBox.getChildren().add(gridPane);
    root.setCenter(vBox);

    stage.getScene().setRoot(root);
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "playerInformation",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    // update text
    healthGoalText.setText(resources.getString("healthGoal"));
    goldGoalText.setText(resources.getString("goldGoal"));
    inventoryGoalText.setText(resources.getString("inventoryGoal"));
    scoreGoalText.setText(resources.getString("scoreGoal"));
    playerNameText.setText(resources.getString("playerName"));
    playerHealthText.setText(resources.getString("playerHealth"));
    playerGoldText.setText(resources.getString("playerGold"));
    newPlayerText.setText(resources.getString("newPlayer"));
    startButton.setText(resources.getString("start"));
  }
}
