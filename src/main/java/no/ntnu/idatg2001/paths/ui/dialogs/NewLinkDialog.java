package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.actions.*;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class NewLinkDialog extends LinkDialog implements StandardDialog<Link> {
  private final GenericDialogController controller = new GenericDialogController();
  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/newLinkDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private final Story story;
  private TextField referenceTextField;
  private TextField linkTextTextField;
  private Text referenceText;
  private Text linkText;
  private ComboBox<Passage> referenceComboBox;
  private Text isPositiveScoreActionText;
  private CheckBox isPositiveScoreActionCheckBox;
  private Spinner<Integer> scoreSpinner;
  private Text scoreText;
  private Text goldText;
  private Spinner<Integer> goldSpinner;
  private Text isPositiveGoldActionText;
  private Text isPositiveHealthActionText;
  private CheckBox isPositiveHealthActionCheckBox;
  private Spinner<Integer> healthSpinner;
  private Text healthText;
  private CheckBox isPositiveGoldActionCheckBox;
  private Button addActionButton;
  private TableView<Action> actionsTableView;
  private TableColumn<Action, String> actionTypeColumn;
  private TableColumn<Action, String> actionValueColumn;
  private TableColumn<Action, Void> actionRemoveColumn;
  private ComboBox<String> actionTypeComboBox;
  private TableColumn<Action, Boolean> actionIsPositiveColumn;

  public NewLinkDialog(Story story) {
    this.story = story;
    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  // TODO: REMOVE THIS
  //
  // reference skal være en annen passage sin title,
  // lag derfor en combobox med alle navnene på passages
  // i stedet for en textfield

  public void initComponents() {
    // referenceTextField = new TextField();

    referenceComboBox = new ComboBox<>();
    referenceComboBox.getItems().addAll(story.getPassages());
    referenceComboBox.setCellFactory(controller.createPassageCallBack());
    referenceComboBox.setButtonCell(controller.createPassageListCell());

    linkTextTextField = new TextField();

    referenceText = new Text();
    linkText = new Text();

    // controller.makeTextFieldNotStartWithSpace(referenceTextField);
    controller.makeTextFieldNotStartWithSpace(linkTextTextField);

    setResultConverter(createCallback());
  }

  public Callback<ButtonType, Link> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        Link newLink =
            new Link(linkTextTextField.getText(), referenceComboBox.getValue().getTitle());
        super.getActionsTableView().getItems().forEach(newLink::addAction);

        return newLink;
      }
      return null;
    };
  }

  @Override
  public void updateLanguage() {
    setDialogLanguage();
  }

  public void addComponentsToDialog() {
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    gridPane.add(referenceText, 0, 0);
    gridPane.add(referenceComboBox, 1, 0);
    gridPane.add(linkText, 0, 1);
    gridPane.add(linkTextTextField, 1, 1);
    gridPane.add(super.createActionsVBox(), 0, 2, 2, 1);

    getDialogPane().setContent(gridPane);
  }

  public void setDialogLanguage() {
    setTitle(resources.getString("dialogTitle"));
    setHeaderText(resources.getString("headerText"));

    referenceText.setText(resources.getString("referenceText"));
    linkText.setText(resources.getString("linkText"));

    ButtonType primaryButtonType =
        new ButtonType(resources.getString("primaryButton"), ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType =
        new ButtonType(resources.getString("cancelButton"), ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().setAll(primaryButtonType, cancelButtonType);

    referenceComboBox.setPromptText(resources.getString("referenceComboBoxPromptText"));
    linkTextTextField.setPromptText(resources.getString("linkTextTextFieldPromptText"));

    super.updateLanguage();
  }

  /*public VBox createActionsVBox() {
    VBox actionsVBox = new VBox();
    actionsTableView = new TableView<>();
    actionTypeColumn = new TableColumn<>();
    actionValueColumn = new TableColumn<>();
    actionIsPositiveColumn = new TableColumn<>();
    actionRemoveColumn = new TableColumn<>();
    actionsTableView
        .getColumns()
        .addAll(actionTypeColumn, actionValueColumn, actionIsPositiveColumn, actionRemoveColumn);

    actionTypeColumn.setCellValueFactory(
        actionStringCellDataFeatures ->
            new ReadOnlyStringWrapper(
                actionStringCellDataFeatures.getValue().getClass().getSimpleName()));

    actionValueColumn.setCellValueFactory(
        actionStringCellDataFeatures ->
            new ReadOnlyStringWrapper(
                actionStringCellDataFeatures.getValue().getActionValue().toString()));

    actionIsPositiveColumn.setCellValueFactory(
        actionStringCellDataFeatures ->
            new ReadOnlyBooleanWrapper(
                actionStringCellDataFeatures.getValue().getActionIsPositive()));

    actionRemoveColumn.setCellFactory(
        new Callback<>() {
          @Override
          public TableCell<Action, Void> call(TableColumn<Action, Void> actionVoidTableColumn) {
            return new TableCell<>() {
              // private final Button removeButton = new
              // Button(resources.getString("removeButton"));
              private final Button removeButton = new Button(resources.getString("removeButton"));

              {
                removeButton.setOnAction(
                    actionEvent -> {
                      Action action = getTableView().getItems().get(getIndex());
                      getTableView().getItems().remove(action);
                    });
              }

              @Override
              protected void updateItem(Void aVoid, boolean b) {
                super.updateItem(aVoid, b);
                if (b) {
                  setGraphic(null);
                } else {
                  setGraphic(removeButton);
                }
              }
            };
          }
        });

    VBox healthActionsVBox = createHealthActionsVBox();
    VBox goldActionsVBox = createGoldActionsVBox();
    VBox inventoryActionsVBox = createInventoryActionsVBox();
    VBox scoreActionsVBox = createScoreActionsVBox();

    healthActionsVBox.setVisible(false);
    healthActionsVBox.setManaged(false);
    goldActionsVBox.setVisible(false);
    goldActionsVBox.setManaged(false);
    inventoryActionsVBox.setVisible(false);
    inventoryActionsVBox.setManaged(false);
    scoreActionsVBox.setVisible(false);
    scoreActionsVBox.setManaged(false);

    VBox actionCreationVbox = new VBox();
    actionTypeComboBox = new ComboBox<>();
    actionTypeComboBox.setOnAction(
        actionEvent -> {
          if (Objects.equals(
              actionTypeComboBox.getValue(), resources.getString("actionTypeComboBoxHealth"))) {
            healthActionsVBox.setVisible(true);
            healthActionsVBox.setManaged(true);
            goldActionsVBox.setVisible(false);
            goldActionsVBox.setManaged(false);
            inventoryActionsVBox.setVisible(false);
            inventoryActionsVBox.setManaged(false);
            scoreActionsVBox.setVisible(false);
            scoreActionsVBox.setManaged(false);
          } else if (Objects.equals(
              actionTypeComboBox.getValue(), resources.getString("actionTypeComboBoxGold"))) {
            goldActionsVBox.setVisible(true);
            goldActionsVBox.setManaged(true);
            healthActionsVBox.setVisible(false);
            healthActionsVBox.setManaged(false);
            inventoryActionsVBox.setVisible(false);
            inventoryActionsVBox.setManaged(false);
            scoreActionsVBox.setVisible(false);
            scoreActionsVBox.setManaged(false);
          } else if (Objects.equals(
              actionTypeComboBox.getValue(), resources.getString("actionTypeComboBoxInventory"))) {
            inventoryActionsVBox.setVisible(true);
            inventoryActionsVBox.setManaged(true);
            healthActionsVBox.setVisible(false);
            healthActionsVBox.setManaged(false);
            goldActionsVBox.setVisible(false);
            goldActionsVBox.setManaged(false);
            scoreActionsVBox.setVisible(false);
            scoreActionsVBox.setManaged(false);
          } else if (Objects.equals(
              actionTypeComboBox.getValue(), resources.getString("actionTypeComboBoxScore"))) {
            scoreActionsVBox.setVisible(true);
            scoreActionsVBox.setManaged(true);
            healthActionsVBox.setVisible(false);
            healthActionsVBox.setManaged(false);
            goldActionsVBox.setVisible(false);
            goldActionsVBox.setManaged(false);
            inventoryActionsVBox.setVisible(false);
            inventoryActionsVBox.setManaged(false);
          }
        });

    actionCreationVbox.getChildren().add(actionTypeComboBox);
    actionCreationVbox
        .getChildren()
        .addAll(healthActionsVBox, goldActionsVBox, inventoryActionsVBox, scoreActionsVBox);

    addActionButton = new Button();
    addActionButton.setOnAction(
        actionEvent -> {
          if (actionTypeComboBox
              .getValue()
              .equals(resources.getString("actionTypeComboBoxHealth"))) {
            actionsTableView
                .getItems()
                .add(
                    new HealthAction(
                        healthSpinner.getValue(), isPositiveHealthActionCheckBox.isSelected()));
          } else if (actionTypeComboBox
              .getValue()
              .equals(resources.getString("actionTypeComboBoxGold"))) {
            actionsTableView
                .getItems()
                .add(
                    new GoldAction(
                        goldSpinner.getValue(), isPositiveGoldActionCheckBox.isSelected()));
          } else if (actionTypeComboBox
              .getValue()
              .equals(resources.getString("actionTypeComboBoxInventory"))) {
            *//*actionsTableView
            .getItems()
            .add(
                new InventoryAction(
                    inventoryComboBox.getValue(),
                    isPositiveScoreActionCheckBox.isSelected()));*//*
          } else if (actionTypeComboBox
              .getValue()
              .equals(resources.getString("actionTypeComboBoxScore"))) {
            actionsTableView
                .getItems()
                .add(
                    new ScoreAction(
                        scoreSpinner.getValue(), isPositiveScoreActionCheckBox.isSelected()));
          }
        });

    actionsVBox.getChildren().addAll(actionsTableView, actionCreationVbox, addActionButton);
    return actionsVBox;
  }

  private VBox createScoreActionsVBox() {
    VBox scoreActionsVBox = new VBox();

    scoreText = new Text();
    scoreSpinner = new Spinner<>();
    scoreSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));

    isPositiveScoreActionText = new Text();
    isPositiveScoreActionCheckBox = new CheckBox();

    GridPane scoreGridPane = new GridPane();
    scoreGridPane.add(scoreText, 0, 0);
    scoreGridPane.add(scoreSpinner, 1, 0);
    scoreGridPane.add(isPositiveScoreActionText, 0, 1);
    scoreGridPane.add(isPositiveScoreActionCheckBox, 1, 1);

    scoreActionsVBox.getChildren().add(scoreGridPane);
    return scoreActionsVBox;
  }

  private VBox createInventoryActionsVBox() {
    VBox inventoryActionsVBox = new VBox();

    Text inventoryText = new Text();
    ComboBox<String> inventoryComboBox = new ComboBox<>();

    // TODO: IMPLEMENT INVENTORY ACTIONS

    return inventoryActionsVBox;
  }

  private VBox createGoldActionsVBox() {
    VBox goldActionsVBox = new VBox();

    goldText = new Text();
    goldSpinner = new Spinner<>();
    goldSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));

    isPositiveGoldActionText = new Text();
    isPositiveGoldActionCheckBox = new CheckBox();

    GridPane goldGridPane = new GridPane();
    goldGridPane.add(goldText, 0, 0);
    goldGridPane.add(goldSpinner, 1, 0);
    goldGridPane.add(isPositiveGoldActionText, 0, 1);
    goldGridPane.add(isPositiveGoldActionCheckBox, 1, 1);

    goldActionsVBox.getChildren().add(goldGridPane);
    return goldActionsVBox;
  }

  private VBox createHealthActionsVBox() {
    VBox healthActionsVBox = new VBox();

    healthText = new Text();
    healthSpinner = new Spinner<>();
    healthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));

    isPositiveHealthActionText = new Text();
    isPositiveHealthActionCheckBox = new CheckBox();

    GridPane healthGridPane = new GridPane();
    healthGridPane.add(healthText, 0, 0);
    healthGridPane.add(healthSpinner, 1, 0);
    healthGridPane.add(isPositiveHealthActionText, 0, 1);
    healthGridPane.add(isPositiveHealthActionCheckBox, 1, 1);

    healthActionsVBox.getChildren().add(healthGridPane);
    return healthActionsVBox;
  }*/
}
