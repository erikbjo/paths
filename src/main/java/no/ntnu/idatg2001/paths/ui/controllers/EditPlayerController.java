package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.DefaultAttributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.alerts.WarningAlert;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.EditPlayerView;

public class EditPlayerController implements Controller {
  private final Stage stage;
  private final Player player;
  private final EditPlayerView view;
  private final ResourceBundle editPlayerResources;
  private final ResourceBundle defaultAttributesResources;
  private final GridPane attributesGridPane;
  private final ComboBox<DefaultAttributes> defaultAttributesComboBox;
  private final Button showAttributesGridPaneButton;

  public EditPlayerController(Stage stage, Player player) {
    this.stage = stage;
    this.player = player;
    this.view = new EditPlayerView(this, stage, player);

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    editPlayerResources =
        ResourceBundle.getBundle(
            "languages/editPlayer",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    defaultAttributesResources =
        ResourceBundle.getBundle(
            "languages/defaultAttributes",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    this.attributesGridPane = view.getAttributesGridPane();
    this.defaultAttributesComboBox = view.getDefaultAttributesComboBox();
    this.showAttributesGridPaneButton = view.getShowAttributesGridPaneButton();

    configureAttributesVBox();
    updateLanguage();
  }

  private void configureAttributesVBox() {
    attributesGridPane.setVisible(false);

    showAttributesGridPaneButton.setOnAction(
        event -> {
          if (attributesGridPane.isVisible()) {
            attributesGridPane.setVisible(false);
            attributesGridPane.setManaged(false);
            defaultAttributesComboBox.setVisible(true);
            defaultAttributesComboBox.setManaged(true);
            updateShowAttributesGridPaneButton();
          } else {
            attributesGridPane.setVisible(true);
            attributesGridPane.setManaged(true);
            defaultAttributesComboBox.setVisible(false);
            defaultAttributesComboBox.setManaged(false);
            updateShowAttributesGridPaneButton();
          }
        });

    defaultAttributesComboBox.getItems().addAll(DefaultAttributes.values());
  }

  public void updateLanguage() {
    updateShowAttributesGridPaneButton();
    updateDefaultAttributesComboBox();

    /*for (Map.Entry<String, DefaultAttributes> entry : defaultAttributesMap.entrySet()) {
      String key = entry.getKey();
      DefaultAttributes value = entry.getValue();
      defaultAttributesComboBox.getItems().set(value.ordinal(), DefaultAttributes.valueOf(key));
    }*/

    view.updateLanguage();
  }

  private void updateDefaultAttributesComboBox() {
    defaultAttributesComboBox.getItems().clear();
    defaultAttributesComboBox.getItems().addAll(DefaultAttributes.values());

    Callback<ListView<DefaultAttributes>, ListCell<DefaultAttributes>> cellFactory =
        comboBox ->
            new ListCell<>() {
              @Override
              protected void updateItem(DefaultAttributes item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                  setText(null);
                } else {
                  setText(defaultAttributesResources.getString(item.name()));
                }
              }
            };

    defaultAttributesComboBox.setCellFactory(cellFactory);
    defaultAttributesComboBox.setButtonCell(cellFactory.call(null));
  }

  private void updateShowAttributesGridPaneButton() {
    if (attributesGridPane.isVisible()) {
      showAttributesGridPaneButton.setText(
          editPlayerResources.getString("hideAttributesGridPaneButton"));
    } else {
      showAttributesGridPaneButton.setText(
          editPlayerResources.getString("showAttributesGridPaneButton"));
    }
  }

  public void savePlayer() {
    Attributes attributes = null;
    if (defaultAttributesComboBox.isVisible() && defaultAttributesComboBox.getValue() != null) {
      attributes = new Attributes(defaultAttributesComboBox.getValue());
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
      WarningAlert warningAlert =
          new WarningAlert(editPlayerResources.getString("invalidAttributes"));
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
    }
    PlayerDAO.getInstance().update(player);
  }

  @Override
  public Stage getStage() {
    return stage;
  }
}
