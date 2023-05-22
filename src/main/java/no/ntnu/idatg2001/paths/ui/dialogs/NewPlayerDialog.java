package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class NewPlayerDialog extends Dialog<Player> {
    private ResourceBundle newPlayerDialogResources;
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
        initComponents();
        addComponentsToDialog();
        setTitle(newPlayerDialogResources.getString("newPlayerDialogTitle"));
        setHeaderText(newPlayerDialogResources.getString("newPlayerDialogHeaderText"));
        updateLanguage();
    }

    private void initComponents() {
        // Observes when the language in Database is changed, then calls updateLanguage()
        LanguageHandler.getObservableIntegerCounter()
            .addListener((obs, oldValue, newValue) -> updateLanguage());

        // gets the correct resource bundle, depending on the current language in database
        newPlayerDialogResources = ResourceBundle.getBundle(
            "languages/newPlayerDialog",
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
        setResultConverter(createPlayerCallback());
    }

    private Callback<ButtonType, Player> createPlayerCallback() {
        return buttonType -> {
            if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
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
        // update labels
        playerNameText.setText(newPlayerDialogResources.getString("playerNameText"));
        playerHealthText.setText(newPlayerDialogResources.getString("playerHealthText"));
        playerManaText.setText(newPlayerDialogResources.getString("playerManaText"));
        playerEnergyText.setText(newPlayerDialogResources.getString("playerEnergyText"));
        strengthText.setText(newPlayerDialogResources.getString("strengthText"));
        perceptionText.setText(newPlayerDialogResources.getString("perceptionText"));
        enduranceText.setText(newPlayerDialogResources.getString("enduranceText"));
        charismaText.setText(newPlayerDialogResources.getString("charismaText"));
        intelligenceText.setText(newPlayerDialogResources.getString("intelligenceText"));
        agilityText.setText(newPlayerDialogResources.getString("agilityText"));
        luckText.setText(newPlayerDialogResources.getString("luckText"));

        // update text
        playerNameTextField.setPromptText(
            newPlayerDialogResources.getString("playerNameTextField"));
        playerHealthTextField.setPromptText(
            newPlayerDialogResources.getString("playerHealthTextField"));
        playerManaTextField.setPromptText(
            newPlayerDialogResources.getString("playerManaTextField"));
        playerEnergyTextField.setPromptText(
            newPlayerDialogResources.getString("playerEnergyTextField"));
        strengthTextField.setPromptText(newPlayerDialogResources.getString("strengthTextField"));
        perceptionTextField.setPromptText(
            newPlayerDialogResources.getString("perceptionTextField"));
        enduranceTextField.setPromptText(
            newPlayerDialogResources.getString("enduranceTextField"));
        charismaTextField.setPromptText(newPlayerDialogResources.getString("charismaTextField"));
        intelligenceTextField.setPromptText(
            newPlayerDialogResources.getString("intelligenceTextField"));
        agilityTextField.setPromptText(newPlayerDialogResources.getString("agilityTextField"));
        luckTextField.setPromptText(newPlayerDialogResources.getString("luckTextField"));

        // update button
        ButtonType createButtonType =
            new ButtonType(newPlayerDialogResources.getString("createButton"),
                ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType =
            new ButtonType(newPlayerDialogResources.getString("cancelButton"),
                ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialogPane().getButtonTypes().addAll(createButtonType, cancelButtonType);

        setResultConverter(createPlayerCallback());
    }
}
