package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.ui.controllers.GameController;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.standardobjects.StandardMenuBar;

public class GameView implements View {
  private final Game game = CurrentGameHandler.getCurrentGame();
  private final GameController controller;
  private final HBox linksHBox;
  private final Text storyHeadlineText;
  private final Text passageTitleText;
  private final TextArea passageContentTextArea;
  private ResourceBundle resources;
  private Text playerEnergyText;
  private Text playerInformationText;
  private Text nameText;
  private Text playerNameText;
  private Text scoreText;
  private Text playerScoreText;
  private Text goldText;
  private Text playerGoldText;
  private Text healthText;
  private Text playerHealthText;
  private Text manaText;
  private Text playerManaText;
  private Text energyText;
  private GridPane goalInformationGridPane;
  private Text goalInformationText;

  public GameView(GameController controller, Stage stage) {
    this.controller = controller;
    stage.setTitle("Story");

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "languages/game",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(stage));

    // Create a VBox to hold all the elements
    VBox rootVBox = new VBox(50); // 100 pixels spacing between elements
    rootVBox.setPadding(new Insets(25));
    rootVBox.setAlignment(Pos.CENTER); // Center the elements vertically and horizontally

    // Create a headline and a text area for the story
    storyHeadlineText = new Text("storyHeadlineText");
    passageTitleText = new Text("passageTitleText");
    passageContentTextArea = new TextArea("passageContentTextArea");
    passageContentTextArea.setEditable(false);
    passageContentTextArea.setWrapText(true);

    // HBox for the hyperlinks
    linksHBox = new HBox(10);
    linksHBox.setAlignment(Pos.CENTER);

    // Add all the elements to the root VBox
    rootVBox.getChildren().add(storyHeadlineText);
    rootVBox.getChildren().add(passageTitleText);
    rootVBox.getChildren().add(passageContentTextArea);
    rootVBox.getChildren().add(linksHBox);

    root.setCenter(rootVBox);

    VBox leftVBox = new VBox();
    VBox playerInformationVBox = createPlayerInformationVBox();
    VBox goalInformationVBox = createGoalInformationVBox();

    storyHeadlineText.setId("storyHeadlineText");
    passageTitleText.setId("passageTitleText");
    passageContentTextArea.setId("passageContentTextArea");

    linksHBox.setId("linksHBox");
    rootVBox.setId("rootVBox");
    playerInformationVBox.setId("playerInformationVBox");
    goalInformationVBox.setId("goalInformationVBox");
    leftVBox.setId("leftVBox");

    leftVBox.getChildren().addAll(playerInformationVBox, goalInformationVBox);
    root.setLeft(leftVBox);

    controller.updateStoryViewToNewPath(
        storyHeadlineText, passageTitleText, passageContentTextArea, linksHBox);

    updateLanguage();
    updatePlayerInformation();

    stage.getScene().setRoot(root);
    stage
        .getScene()
        .getStylesheets()
        .set(0, Objects.requireNonNull(getClass().getResource("/css/game.css")).toExternalForm());
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "languages/game",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    nameText.setText(resources.getString("nameText"));
    scoreText.setText(resources.getString("scoreText"));
    goldText.setText(resources.getString("goldText"));
    healthText.setText(resources.getString("healthText"));
    manaText.setText(resources.getString("manaText"));
    energyText.setText(resources.getString("energyText"));
    playerInformationText.setText(resources.getString("playerInformationText"));
    goalInformationText.setText(resources.getString("goalInformationText"));
  }

  public HBox getLinksHBox() {
    return linksHBox;
  }

  public Text getStoryHeadlineText() {
    return storyHeadlineText;
  }

  public Text getPassageTitleText() {
    return passageTitleText;
  }

  public TextArea getPassageContentTextArea() {
    return passageContentTextArea;
  }

  private VBox createPlayerInformationVBox() {
    VBox playerInformationVBox = new VBox();

    playerInformationText = new Text();
    nameText = new Text();
    playerNameText = new Text();
    scoreText = new Text();
    playerScoreText = new Text();
    goldText = new Text();
    playerGoldText = new Text();
    healthText = new Text();
    playerHealthText = new Text();
    manaText = new Text();
    playerManaText = new Text();
    energyText = new Text();
    playerEnergyText = new Text();

    GridPane playerInformationGridPane = new GridPane();
    playerInformationGridPane.add(nameText, 0, 0);
    playerInformationGridPane.add(playerNameText, 1, 0);
    playerInformationGridPane.add(scoreText, 0, 1);
    playerInformationGridPane.add(playerScoreText, 1, 1);
    playerInformationGridPane.add(goldText, 0, 2);
    playerInformationGridPane.add(playerGoldText, 1, 2);
    playerInformationGridPane.add(healthText, 0, 3);
    playerInformationGridPane.add(playerHealthText, 1, 3);
    playerInformationGridPane.add(manaText, 0, 4);
    playerInformationGridPane.add(playerManaText, 1, 4);
    playerInformationGridPane.add(energyText, 0, 5);
    playerInformationGridPane.add(playerEnergyText, 1, 5);

    setRowAndColumnConstraints(playerInformationGridPane);

    playerInformationVBox.getChildren().addAll(playerInformationText, playerInformationGridPane);
    return playerInformationVBox;
  }

  public void updatePlayerInformation() {
    playerNameText.setText(game.getPlayer().getName());
    playerScoreText.setText(String.valueOf(game.getPlayer().getScore()));
    playerGoldText.setText(String.valueOf(game.getPlayer().getGold()));
    playerHealthText.setText(String.valueOf(game.getPlayer().getHealth()));
    playerManaText.setText(String.valueOf(game.getPlayer().getMana()));
    playerEnergyText.setText(String.valueOf(game.getPlayer().getEnergy()));
  }

  private VBox createGoalInformationVBox() {
    VBox goalInformationVBox = new VBox();

    goalInformationText = new Text();

    goalInformationGridPane = new GridPane();
    setRowAndColumnConstraints(goalInformationGridPane);

    updateGoalInformation();

    goalInformationVBox.getChildren().addAll(goalInformationText, goalInformationGridPane);
    return goalInformationVBox;
  }

  public void updateGoalInformation() {
    goalInformationGridPane.getChildren().clear();
    int i = 0;
    for (Goal goal : CurrentGameHandler.getCurrentGame().getGoals()) {
      String goalType = goal.getClass().getSimpleName();
      String goalProgress = null;
      switch (goalType) {
        case "HealthGoal" -> goalProgress =
            game.getPlayer().getHealth() + "/" + goal.getGoalValue();
        case "ScoreGoal" -> goalProgress = game.getPlayer().getScore() + "/" + goal.getGoalValue();
        case "GoldGoal" -> goalProgress = game.getPlayer().getGold() + "/" + goal.getGoalValue();
      }

      Text goalNameText = new Text(goalType);
      if (goalProgress == null) {
        goalProgress = "0/0";
      }

      Text goalProgressText = new Text(goalProgress);

      goalInformationGridPane.add(goalNameText, 0, i);
      goalInformationGridPane.add(goalProgressText, 1, i);

      i++;
    }
  }

  private void setRowAndColumnConstraints(GridPane gridPane) {
    gridPane.getRowConstraints().add(new RowConstraints(30));
    gridPane.getColumnConstraints().add(new ColumnConstraints(100));
  }
}
