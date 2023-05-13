package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.database.PlayerDAO;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.DefaultAttributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.EditPlayerView;

public class EditPlayerController implements Controller {
  private final ResourceBundle resources;
  private final Stage primaryStage;
  private final Player player;
  private final EditPlayerView view;

  public EditPlayerController(Stage primaryStage, Player player) {
    this.primaryStage = primaryStage;
    this.player = player;
    this.view = new EditPlayerView(this, primaryStage, player);

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "editPlayer",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  }

  public void configureAttributesVBox(
      GridPane attributesGridPane,
      Button showAttributesGridPaneButton,
      ComboBox<DefaultAttributes> defaultAttributesComboBox) {
    attributesGridPane.setVisible(false);

    showAttributesGridPaneButton.setOnAction(
        event -> {
          if (attributesGridPane.isVisible()) {
            attributesGridPane.setVisible(false);
            attributesGridPane.setManaged(false);
            defaultAttributesComboBox.setVisible(true);
            defaultAttributesComboBox.setManaged(true);
            updateShowAttributesGridPaneButton(attributesGridPane, showAttributesGridPaneButton);
          } else {
            attributesGridPane.setVisible(true);
            attributesGridPane.setManaged(true);
            defaultAttributesComboBox.setVisible(false);
            defaultAttributesComboBox.setManaged(false);
            updateShowAttributesGridPaneButton(attributesGridPane, showAttributesGridPaneButton);
          }
        });

    defaultAttributesComboBox.getItems().addAll(DefaultAttributes.values());
  }

  public void updateShowAttributesGridPaneButton(
      GridPane attributesGridPane, Button showAttributesGridPaneButton) {
    if (attributesGridPane.isVisible()) {
      showAttributesGridPaneButton.setText(resources.getString("hideAttributesGridPaneButton"));
    } else {
      showAttributesGridPaneButton.setText(resources.getString("showAttributesGridPaneButton"));
    }
  }

  public void savePlayer() {
    Attributes attributes = null;
    if (view.getDefaultAttributesComboBox().isVisible()) {
      attributes = new Attributes(view.getDefaultAttributesComboBox().getValue());
    } else if (view.assertAttributesFieldsValid()) {
      attributes =
          new Attributes(
              Integer.parseInt(view.getStrengthTextField().getText()),
              Integer.parseInt(view.getPerceptionTextField().getText()),
              Integer.parseInt(view.getEnduranceTextField().getText()),
              Integer.parseInt(view.getCharismaTextField().getText()),
              Integer.parseInt(view.getIntelligenceTextField().getText()),
              Integer.parseInt(view.getAgilityTextField().getText()),
              Integer.parseInt(view.getLuckTextField().getText()));
    } else {
      WarningAlert warningAlert = new WarningAlert(resources.getString("invalidAttributes"));
      warningAlert.showAndWait();
    }

    if (view.assertAllFieldsValid() && attributes != null) {
      player.setName(view.getNameField().getText());

      player.setHealth(Integer.parseInt(view.getHealthField().getText()));
      player.setMana(Integer.parseInt(view.getManaField().getText()));
      player.setEnergy(Integer.parseInt(view.getEnergyField().getText()));
      player.setGold(Integer.parseInt(view.getGoldField().getText()));
      player.setScore(Integer.parseInt(view.getScoreField().getText()));

        defaultAttributesComboBox.getItems().setAll(
            DefaultAttributes.valueOf(hunter),
            DefaultAttributes.valueOf(warrior),
            DefaultAttributes.valueOf(druid),
            DefaultAttributes.valueOf(rouge),
            DefaultAttributes.valueOf(mage),
            DefaultAttributes.valueOf(warlock),
            DefaultAttributes.valueOf(priest),
            DefaultAttributes.valueOf(paladin),
            DefaultAttributes.valueOf(shaman)
        );

        //version 1
        /**
         defaultAttributesComboBox.getItems().addAll(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("HUNTER")));
         defaultAttributesComboBox.getItems().addALL(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("WARRIOR")));
         defaultAttributesComboBox.getItems().addAll(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("DRUID")));
         defaultAttributesComboBox.getItems().addAll(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("ROGUE")));
         defaultAttributesComboBox.getItems().addAll(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("MAGE")));
         defaultAttributesComboBox.getItems().addAll(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("WARLOCK")));
         defaultAttributesComboBox.getItems().addAll(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("PRIEST")));
         defaultAttributesComboBox.getItems().addAll(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("PALADIN")));
         defaultAttributesComboBox.getItems().addAll(
         DefaultAttributes.valueOf(defaultAttributesResources.getString("SHAMAN")));
         */
    }

/**
 private void updateShowAttributesGridPaneButton() {
 if (attributesGridPane.isVisible()) {
 showAttributesGridPaneButton.setText(
 editPlayerResources.getString("hideAttributesGridPaneButton"));
 } else {
 showAttributesGridPaneButton.setText(
 editPlayerResources.getString("showAttributesGridPaneButton"));
 }
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
 }

 public void savePlayer() {
 Attributes attributes = null;
 if (defaultAttributesComboBox.isVisible()) {
 attributes = new Attributes(defaultAttributesComboBox.getValue());
 } else if (assertAttributesFieldsValid()) {
 attributes =
 new Attributes(
 Integer.parseInt(strengthTextField.getText()),
 Integer.parseInt(perceptionTextField.getText()),
 Integer.parseInt(enduranceTextField.getText()),
 Integer.parseInt(charismaTextField.getText()),
 Integer.parseInt(intelligenceTextField.getText()),
 Integer.parseInt(agilityTextField.getText()),
 Integer.parseInt(luckTextField.getText()));
 } else {
 WarningAlert warningAlert =
 new WarningAlert(editPlayerResources.getString("invalidAttributes"));
 warningAlert.showAndWait();
 }

 if (assertAllFieldsValid() && attributes != null) {
 player.setName(nameField.getText());

 player.setHealth(Integer.parseInt(healthField.getText()));
 player.setMana(Integer.parseInt(manaField.getText()));
 player.setEnergy(Integer.parseInt(energyField.getText()));
 player.setGold(Integer.parseInt(goldField.getText()));
 player.setScore(Integer.parseInt(scoreField.getText()));

 player.setAttributes(attributes);

 PlayerDAO.getInstance().update(player);
 }
 }

 private boolean assertAllFieldsValid() {
 return assertStandardFieldsValid() && assertAttributesFieldsValid();
 }

 private boolean assertStandardFieldsValid() {
 return assertTextFieldValid(nameField)
 && assertIntegerFieldValid(healthField)
 && assertIntegerFieldValid(manaField)
 && assertIntegerFieldValid(energyField)
 && assertIntegerFieldValid(goldField)
 && assertIntegerFieldValid(scoreField);
 }

 private boolean assertAttributesFieldsValid() {
 return assertIntegerFieldValid(strengthTextField)
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
 */
}
