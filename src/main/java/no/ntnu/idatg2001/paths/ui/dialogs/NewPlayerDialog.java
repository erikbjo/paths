package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class NewPlayerDialog extends Dialog<Player> {
  private ResourceBundle resources;
  private TextField playerNameTextField;
  private TextField playerHealthTextField;
  private TextField playerManaTextField;
  private TextField playerEnergyTextField;
  private TextField strengthTextField;
  private TextField perceptionTextField;
  private TextField enduranceTextField;
  private TextField charismaTextField;
  private TextField intelligenceTextField;
  private TextField agilityTextField;
  private TextField luckTextField;

  private Text playerNameText;
  private Text playerHealthText;
  private Text playerManaText;
  private Text playerEnergyText;
  private Text strengthText;
  private Text perceptionText;
  private Text enduranceText;
  private Text charismaText;
  private Text intelligenceText;
  private Text agilityText;
  private Text luckText;

  private GenericDialogController controller;

  public NewPlayerDialog() {
    setTitle("New Player");
    setHeaderText("Create a new player");

    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  private void initComponents() {
    // Observes when the language in Database is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "languages/playerInformation",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    controller = new GenericDialogController();

    playerNameTextField = new TextField();
    playerHealthTextField = new TextField();
    playerManaTextField = new TextField();
    playerEnergyTextField = new TextField();
    strengthTextField = new TextField();
    perceptionTextField = new TextField();
    enduranceTextField = new TextField();
    charismaTextField = new TextField();
    intelligenceTextField = new TextField();
    agilityTextField = new TextField();
    luckTextField = new TextField();

    playerNameText = new Text();
    playerHealthText = new Text();
    playerManaText = new Text();
    playerEnergyText = new Text();
    strengthText = new Text();
    perceptionText = new Text();
    enduranceText = new Text();
    charismaText = new Text();
    intelligenceText = new Text();
    agilityText = new Text();
    luckText = new Text();

    controller.makeTextFieldNotStartWithSpace(playerNameTextField);
    controller.makeTextFieldNumericOnly(playerHealthTextField);

    ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
    getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

    setResultConverter(createPlayerCallback());
  }

  private Callback<ButtonType, Player> createPlayerCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        // TODO: MAKE THIS MAKE USE OF BUILDER MAYBE
        Player player =
            new Player.PlayerBuilder()
                .withName(playerNameTextField.getText())
                .withHealth(Integer.parseInt(playerHealthTextField.getText()))
                .withMana(Integer.parseInt(playerManaTextField.getText()))
                .withEnergy(Integer.parseInt(playerEnergyTextField.getText()))
                .withAttributes(
                    new Attributes(
                        Integer.parseInt(strengthTextField.getText()),
                        Integer.parseInt(perceptionTextField.getText()),
                        Integer.parseInt(enduranceTextField.getText()),
                        Integer.parseInt(charismaTextField.getText()),
                        Integer.parseInt(intelligenceTextField.getText()),
                        Integer.parseInt(agilityTextField.getText()),
                        Integer.parseInt(luckTextField.getText())))
                .build();

        return player;
      }
      return null;
    };
  }

  private void addComponentsToDialog() {
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    gridPane.add(playerNameText, 0, 0);
    gridPane.add(playerNameTextField, 1, 0);
    gridPane.add(playerHealthText, 0, 1);
    gridPane.add(playerHealthTextField, 1, 1);
    gridPane.add(playerManaText, 0, 2);
    gridPane.add(playerManaTextField, 1, 2);
    gridPane.add(playerEnergyText, 0, 3);
    gridPane.add(playerEnergyTextField, 1, 3);
    gridPane.add(strengthText, 0, 4);
    gridPane.add(strengthTextField, 1, 4);
    gridPane.add(perceptionText, 0, 5);
    gridPane.add(perceptionTextField, 1, 5);
    gridPane.add(enduranceText, 0, 6);
    gridPane.add(enduranceTextField, 1, 6);
    gridPane.add(charismaText, 0, 7);
    gridPane.add(charismaTextField, 1, 7);
    gridPane.add(intelligenceText, 0, 8);
    gridPane.add(intelligenceTextField, 1, 8);
    gridPane.add(agilityText, 0, 9);
    gridPane.add(agilityTextField, 1, 9);
    gridPane.add(luckText, 0, 10);
    gridPane.add(luckTextField, 1, 10);

    getDialogPane().setContent(gridPane);
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "languages/newPlayerDialog",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    // update labels
    playerNameText.setText(resources.getString("playerNameText"));
    playerHealthText.setText(resources.getString("playerHealthText"));
    playerManaText.setText(resources.getString("playerManaText"));
    playerEnergyText.setText(resources.getString("playerEnergyText"));
    strengthText.setText(resources.getString("strengthText"));
    perceptionText.setText(resources.getString("perceptionText"));
    enduranceText.setText(resources.getString("enduranceText"));
    charismaText.setText(resources.getString("charismaText"));
    intelligenceText.setText(resources.getString("intelligenceText"));
    agilityText.setText(resources.getString("agilityText"));
    luckText.setText(resources.getString("luckText"));

    // update text
    playerNameTextField.setPromptText(resources.getString("playerNameTextField"));
    playerHealthTextField.setPromptText(resources.getString("playerHealthTextField"));
    playerManaTextField.setPromptText(resources.getString("playerManaTextField"));
    playerEnergyTextField.setPromptText(resources.getString("playerEnergyTextField"));
    strengthTextField.setPromptText(resources.getString("strengthTextField"));
    perceptionTextField.setPromptText(resources.getString("perceptionTextField"));
    enduranceTextField.setPromptText(resources.getString("enduranceTextField"));
    charismaTextField.setPromptText(resources.getString("charismaTextField"));
    intelligenceTextField.setPromptText(resources.getString("intelligenceTextField"));
    agilityTextField.setPromptText(resources.getString("agilityTextField"));
    luckTextField.setPromptText(resources.getString("luckTextField"));

    // update button
    ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
    getDialogPane().getButtonTypes().setAll(createButtonType, ButtonType.CANCEL);

    setResultConverter(createPlayerCallback());
  }
}
