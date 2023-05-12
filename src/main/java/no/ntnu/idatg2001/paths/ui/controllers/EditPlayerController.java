package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.Locale;
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
import no.ntnu.idatg2001.paths.ui.alerts.WarningAlert;
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

      player.setAttributes(attributes);

      PlayerDAO.getInstance().update(player);
    }
  }

  // TODO: FIX THIS
  @Override
  public Stage getStage() {
    return null;
  }
}
