package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.DefaultAttributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.alerts.WarningAlert;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.EditPlayerView;

public class EditPlayerController implements Controller {
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
    private final Stage stage;
    private final EditPlayerView view;
    private final Button saveButton;
    private final Button cancelButton;
    private final ComboBox<DefaultAttributes> defaultAttributesComboBox;
    private final Map<String, DefaultAttributes> defaultAttributesMap;
    private final Button showAttributesGridPaneButton;
    private final GridPane attributesGridPane;
    private ResourceBundle editPlayerResources;
    private ResourceBundle defaultAttributesResources;

    public EditPlayerController(Stage stage, Player player) {
        this.stage = stage;
        this.player = player;
        this.view = new EditPlayerView(this, stage, player);

        this.playerText = new Text();
        this.cheatsText = new Text();
        this.editPlayerText = new Text();
        this.nameText = new Text();
        this.healthText = new Text();
        this.manaText = new Text();
        this.energyText = new Text();
        this.goldText = new Text();
        this.scoreText = new Text();
        this.nameField = new TextField();
        this.healthField = new TextField();
        this.manaField = new TextField();
        this.energyField = new TextField();
        this.goldField = new TextField();
        this.scoreField = new TextField();
        this.attributesText = new Text();
        this.strengthText = new Text();
        this.perceptionText = new Text();
        this.enduranceText = new Text();
        this.charismaText = new Text();
        this.intelligenceText = new Text();
        this.agilityText = new Text();
        this.luckText = new Text();
        this.strengthTextField = new TextField();
        this.perceptionTextField = new TextField();
        this.enduranceTextField = new TextField();
        this.charismaTextField = new TextField();
        this.intelligenceTextField = new TextField();
        this.agilityTextField = new TextField();
        this.luckTextField = new TextField();
        this.cancelButton = new Button();
        this.saveButton = new Button();
        this.defaultAttributesComboBox = new ComboBox<>();
        this.showAttributesGridPaneButton = new Button();
        this.attributesGridPane = new GridPane();
        this.defaultAttributesMap = new HashMap<>();

        // Observes when the language is changed, then calls updateLanguage()
        LanguageHandler.getObservableIntegerCounter()
            .addListener((a, b, c) -> updateLanguage());

        // gets the correct resource bundle, depending on the current language in database
        editPlayerResources =
            ResourceBundle.getBundle(
                "languages/editPlayer",
                Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

        defaultAttributesResources =
            ResourceBundle.getBundle(
                "languages/defaultAttributes",
                Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

        configureAttributesVBox();
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
        playerText.setText(editPlayerResources.getString("playerText"));
        cheatsText.setText(editPlayerResources.getString("cheatsText"));
        editPlayerText.setText(editPlayerResources.getString("editPlayerText"));
        nameText.setText(editPlayerResources.getString("nameText"));
        healthText.setText(editPlayerResources.getString("healthText"));
        manaText.setText(editPlayerResources.getString("manaText"));
        energyText.setText(editPlayerResources.getString("energyText"));
        goldText.setText(editPlayerResources.getString("goldText"));
        scoreText.setText(editPlayerResources.getString("scoreText"));
        attributesText.setText(editPlayerResources.getString("attributesText"));
        strengthText.setText(editPlayerResources.getString("strengthText"));
        perceptionText.setText(editPlayerResources.getString("perceptionText"));
        enduranceText.setText(editPlayerResources.getString("enduranceText"));
        charismaText.setText(editPlayerResources.getString("charismaText"));
        intelligenceText.setText(editPlayerResources.getString("intelligenceText"));
        agilityText.setText(editPlayerResources.getString("agilityText"));
        luckText.setText(editPlayerResources.getString("luckText"));

        cancelButton.setText(editPlayerResources.getString("cancelButton"));
        saveButton.setText(editPlayerResources.getString("saveButton"));
        updateShowAttributesGridPaneButton();
        updateDefaultAttributesComboBox();

        for (Map.Entry<String, DefaultAttributes> entry : defaultAttributesMap.entrySet()) {
            String key = entry.getKey();
            DefaultAttributes value = entry.getValue();
            defaultAttributesComboBox.getItems().set(value.ordinal(),
                DefaultAttributes.valueOf(key));
        }
    }

    private void updateDefaultAttributesComboBox() {
        //Denne metoden fungerer ikke optimalt enda!
        defaultAttributesComboBox.setPromptText(
            editPlayerResources.getString("defaultAttributesComboBox"));

        ObservableList<DefaultAttributes> observableDefaultAttributesList =
            FXCollections.observableArrayList(DefaultAttributes.values());

        defaultAttributesComboBox.setItems(observableDefaultAttributesList);

        for (DefaultAttributes defaultAttribute : DefaultAttributes.values()) {
            String comboBoxString = defaultAttributesResources.getString(defaultAttribute.name());
            defaultAttributesMap.put(comboBoxString, defaultAttribute);
        }

        //version 1
        String hunter = defaultAttributesResources.getString("HUNTER");
        String warrior = defaultAttributesResources.getString("WARRIOR");
        String druid = defaultAttributesResources.getString("DRUID");
        String rouge = defaultAttributesResources.getString("ROGUE");
        String mage = defaultAttributesResources.getString("MAGE");
        String warlock = defaultAttributesResources.getString("WARLOCK");
        String priest = defaultAttributesResources.getString("PRIEST");
        String paladin = defaultAttributesResources.getString("PALADIN");
        String shaman = defaultAttributesResources.getString("SHAMAN");

        observableDefaultAttributesList.addAll(
        );
        /**
         defaultAttributesComboBox.setOnAction(actionEvent -> {
         DefaultAttributes attributeEnum = defaultAttributesComboBox.getValue();
         String attributeString = defaultAttributesResources.getString(attributeEnum.name());

         switch (attributeEnum) {
         case HUNTER -> attributeString = hunter;
         case WARRIOR -> attributeString = warrior;
         case DRUID -> attributeString = druid;
         case ROGUE -> attributeString = rouge;
         case MAGE -> attributeString = mage;
         case WARLOCK -> attributeString = warlock;
         case PRIEST -> attributeString = priest;
         case PALADIN -> attributeString = paladin;
         case SHAMAN -> attributeString = shaman;
         }
         });
         */
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

    @Override
    public Stage getStage() {
        return stage;
    }
}
