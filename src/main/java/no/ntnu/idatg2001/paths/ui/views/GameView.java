package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.ui.controllers.GameController;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.standardobjects.StandardMenuBar;

public class GameView implements View {
  private final GameController controller;
  private final HBox linksHBox;
  private final Text storyHeadlineText;
  private final Text passageTitleText;
  private final TextArea passageContentTextArea;
  private final Game game = CurrentGameHandler.getCurrentGame();
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

  public GameView(GameController controller, Stage stage) {
    this.controller = controller;
    stage.setTitle("Story");

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "languages/story",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(stage));
    AnchorPane rootAnchorPane = new AnchorPane();

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

    rootAnchorPane.getChildren().add(rootVBox);
    root.setCenter(rootAnchorPane);

    VBox playerInformationVBox = createPlayerInformationVBox();
    root.setRight(playerInformationVBox);

    controller.updateStoryViewToNewPath(
        storyHeadlineText, passageTitleText, passageContentTextArea, linksHBox);

    updateLanguage();
    updatePlayerInformation();

    stage.getScene().setRoot(root);
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "languages/game", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    nameText.setText(resources.getString("nameText"));
    scoreText.setText(resources.getString("scoreText"));
    goldText.setText(resources.getString("goldText"));
    healthText.setText(resources.getString("healthText"));
    manaText.setText(resources.getString("manaText"));
    energyText.setText(resources.getString("energyText"));
    playerInformationText.setText(resources.getString("playerInformationText"));
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
}
