package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class EditPlayerController {
  private final Text playerText;
  private final Text cheatsText;
  private final Text editPlayerText;
  private final Text nameText;
  private final Text healthText;
  private final Text manaText;
  private final Text energyText;
  private final Text goldText;
  private final Text scoreText;
  private final TextField nameField;
  private final TextField healthField;
  private final TextField manaField;
  private final TextField energyField;
  private final TextField goldField;
  private final TextField scoreField;
  private final Text attributesText;
  private final Text strengthText;
  private final Text perceptionText;
  private final Text enduranceText;
  private final Text charismaText;
  private final Text intelligenceText;
  private final Text agilityText;
  private final Text luckText;
  private final TextField strengthTextField;
  private final TextField perceptionTextField;
  private final TextField enduranceTextField;
  private final TextField charismaTextField;
  private final TextField intelligenceTextField;
  private final TextField agilityTextField;
  private final TextField luckTextField;
  private final Player player;
  private ResourceBundle resources;
  private Button saveButton;
  private Button cancelButton;

  public EditPlayerController(
      Text playerText,
      Text cheatsText,
      Text editPlayerText,
      Text nameText,
      Text healthText,
      Text manaText,
      Text energyText,
      Text goldText,
      Text scoreText,
      TextField nameField,
      TextField healthField,
      TextField manaField,
      TextField energyField,
      TextField goldField,
      TextField scoreField,
      Text attributesText,
      Text strengthText,
      Text perceptionText,
      Text enduranceText,
      Text charismaText,
      Text intelligenceText,
      Text agilityText,
      Text luckText,
      TextField strengthTextField,
      TextField perceptionTextField,
      TextField enduranceTextField,
      TextField charismaTextField,
      TextField intelligenceTextField,
      TextField agilityTextField,
      TextField luckTextField,
      Player player,
      Button cancelButton,
      Button saveButton) {
    this.playerText = playerText;
    this.cheatsText = cheatsText;
    this.editPlayerText = editPlayerText;
    this.nameText = nameText;
    this.healthText = healthText;
    this.manaText = manaText;
    this.energyText = energyText;
    this.goldText = goldText;
    this.scoreText = scoreText;
    this.nameField = nameField;
    this.healthField = healthField;
    this.manaField = manaField;
    this.energyField = energyField;
    this.goldField = goldField;
    this.scoreField = scoreField;
    this.attributesText = attributesText;
    this.strengthText = strengthText;
    this.perceptionText = perceptionText;
    this.enduranceText = enduranceText;
    this.charismaText = charismaText;
    this.intelligenceText = intelligenceText;
    this.agilityText = agilityText;
    this.luckText = luckText;
    this.strengthTextField = strengthTextField;
    this.perceptionTextField = perceptionTextField;
    this.enduranceTextField = enduranceTextField;
    this.charismaTextField = charismaTextField;
    this.intelligenceTextField = intelligenceTextField;
    this.agilityTextField = agilityTextField;
    this.luckTextField = luckTextField;
    this.player = player;
    this.cancelButton = cancelButton;
    this.saveButton = saveButton;

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "editPlayer",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  }

  public void updateLanguage() {
    resources =
        ResourceBundle.getBundle(
            "editPlayer",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    playerText.setText(resources.getString("playerText"));
    cheatsText.setText(resources.getString("cheatsText"));
    editPlayerText.setText(resources.getString("editPlayerText"));
    nameText.setText(resources.getString("nameText"));
    healthText.setText(resources.getString("healthText"));
    manaText.setText(resources.getString("manaText"));
    energyText.setText(resources.getString("energyText"));
    goldText.setText(resources.getString("goldText"));
    scoreText.setText(resources.getString("scoreText"));
    attributesText.setText(resources.getString("attributesText"));
    strengthText.setText(resources.getString("strengthText"));
    perceptionText.setText(resources.getString("perceptionText"));
    enduranceText.setText(resources.getString("enduranceText"));
    charismaText.setText(resources.getString("charismaText"));
    intelligenceText.setText(resources.getString("intelligenceText"));
    agilityText.setText(resources.getString("agilityText"));
    luckText.setText(resources.getString("luckText"));
  }

  public void addParametersFromPlayerIntoTextFields() {
    nameField.setText(player.getName());

    healthField.setText(String.valueOf(player.getHealth()));
    manaField.setText(String.valueOf(player.getMana()));
    energyField.setText(String.valueOf(player.getEnergy()));
    goldField.setText(String.valueOf(player.getGold()));
    scoreField.setText(String.valueOf(player.getScore()));

    strengthTextField.setText(String.valueOf(player.getAttributes().getStrength()));
    perceptionTextField.setText(String.valueOf(player.getAttributes().getPerception()));
    enduranceTextField.setText(String.valueOf(player.getAttributes().getEndurance()));
    charismaTextField.setText(String.valueOf(player.getAttributes().getCharisma()));
    intelligenceTextField.setText(String.valueOf(player.getAttributes().getIntelligence()));
    agilityTextField.setText(String.valueOf(player.getAttributes().getAgility()));
    luckTextField.setText(String.valueOf(player.getAttributes().getLuck()));

    cancelButton.setText(resources.getString("cancelButton"));
    saveButton.setText(resources.getString("saveButton"));
  }

  public void savePlayer() {
    if (assertAllFieldsValid()) {
      player.setName(nameField.getText());

      player.setHealth(Integer.parseInt(healthField.getText()));
      player.setMana(Integer.parseInt(manaField.getText()));
      player.setEnergy(Integer.parseInt(energyField.getText()));
      player.setGold(Integer.parseInt(goldField.getText()));
      player.setScore(Integer.parseInt(scoreField.getText()));

      player.getAttributes().setStrength(Integer.parseInt(strengthTextField.getText()));
      player.getAttributes().setPerception(Integer.parseInt(perceptionTextField.getText()));
      player.getAttributes().setEndurance(Integer.parseInt(enduranceTextField.getText()));
      player.getAttributes().setCharisma(Integer.parseInt(charismaTextField.getText()));
      player.getAttributes().setIntelligence(Integer.parseInt(intelligenceTextField.getText()));
      player.getAttributes().setAgility(Integer.parseInt(agilityTextField.getText()));
      player.getAttributes().setLuck(Integer.parseInt(luckTextField.getText()));

      PlayerDAO.getInstance().update(player);
    }
  }

  private boolean assertAllFieldsValid() {

    return assertTextFieldValid(nameField)
        && assertIntegerFieldValid(healthField)
        && assertIntegerFieldValid(manaField)
        && assertIntegerFieldValid(energyField)
        && assertIntegerFieldValid(goldField)
        && assertIntegerFieldValid(scoreField)
        && assertIntegerFieldValid(strengthTextField)
        && assertIntegerFieldValid(perceptionTextField)
        && assertIntegerFieldValid(enduranceTextField)
        && assertIntegerFieldValid(charismaTextField)
        && assertIntegerFieldValid(intelligenceTextField)
        && assertIntegerFieldValid(agilityTextField)
        && assertIntegerFieldValid(luckTextField);
  }

  private boolean assertTextFieldValid(TextField textField) {
    return !textField.getText().isEmpty() && !textField.getText().isBlank();
  }

  private boolean assertIntegerFieldValid(TextField textField) {
    return textField.getText().matches("[0-9]+");
  }
}