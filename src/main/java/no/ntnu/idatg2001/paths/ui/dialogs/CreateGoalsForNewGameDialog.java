package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.goals.GoldGoal;
import no.ntnu.idatg2001.paths.model.goals.HealthGoal;
import no.ntnu.idatg2001.paths.model.goals.ScoreGoal;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class CreateGoalsForNewGameDialog extends Dialog<List<Goal>>
    implements StandardDialog<List<Goal>> {

  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/createGoalsForNewGameDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private final Game game;
  private TableView<Goal> goalsTableView;
  private TableColumn<Goal, String> goalTypeColumn;
  private TableColumn<Goal, String> goalValueColumn;
  private TableColumn<Goal, Void> goalRemoveColumn;
  private ComboBox<String> goalTypeComboBox;
  private Button addGoalButton;
  private Text scoreText;
  private Spinner<Integer> scoreSpinner;
  private Text goldText;
  private Spinner<Integer> goldSpinner;
  private Text healthText;
  private Spinner<Integer> healthSpinner;

  public CreateGoalsForNewGameDialog(Game game) {
    this.game = game;

    initComponents();
    addComponentsToDialog();
    updateLanguage();
    setDialogLanguage();
  }

  @Override
  public void initComponents() {
    setResultConverter(createCallback());
  }

  @Override
  public void addComponentsToDialog() {
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    gridPane.add(createActionsVBox(), 0, 0);

    getDialogPane().setContent(gridPane);
  }

  @Override
  public Callback<ButtonType, List<Goal>> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        return goalsTableView.getItems();
      }
      return null;
    };
  }

  @Override
  public void updateLanguage() {
    goalsTableView.setPlaceholder(new Label(resources.getString("goalsTableViewPlaceholder")));
    goalTypeColumn.setText(resources.getString("goalTypeColumn"));
    goalValueColumn.setText(resources.getString("goalValueColumn"));
    goalRemoveColumn.setText(resources.getString("goalRemoveColumn"));

    goalTypeComboBox
        .getItems()
        .addAll(
            resources.getString("goalTypeComboBoxHealth"),
            resources.getString("goalTypeComboBoxGold"),
            resources.getString("goalTypeComboBoxScore"));
    addGoalButton.setText(resources.getString("addGoalButton"));

    healthText.setText(resources.getString("healthText"));

    goldText.setText(resources.getString("goldText"));

    scoreText.setText(resources.getString("scoreText"));
  }

  @Override
  public void setDialogLanguage() {
    setTitle(resources.getString("dialogTitle"));
    setHeaderText(resources.getString("headerText"));

    ButtonType primaryButtonType =
        new ButtonType(resources.getString("primaryButton"), ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType =
        new ButtonType(resources.getString("cancelButton"), ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().setAll(primaryButtonType, cancelButtonType);
  }

  public TableView<Goal> getActionsTableView() {
    return goalsTableView;
  }

  public VBox createActionsVBox() {
    VBox actionsVBox = new VBox();
    goalsTableView = new TableView<>();
    goalTypeColumn = new TableColumn<>();
    goalValueColumn = new TableColumn<>();
    goalRemoveColumn = new TableColumn<>();
    goalsTableView.getColumns().addAll(goalTypeColumn, goalValueColumn, goalRemoveColumn);

    goalTypeColumn.setCellValueFactory(
        actionStringCellDataFeatures ->
            new ReadOnlyStringWrapper(
                actionStringCellDataFeatures.getValue().getClass().getSimpleName()));

    goalValueColumn.setCellValueFactory(
        actionStringCellDataFeatures ->
            new ReadOnlyStringWrapper(
                Objects.requireNonNull(actionStringCellDataFeatures.getValue())
                    .getGoalValue()
                    .toString()));

    goalRemoveColumn.setCellFactory(
        new Callback<>() {
          @Override
          public TableCell<Goal, Void> call(TableColumn<Goal, Void> actionVoidTableColumn) {
            return new TableCell<>() {
              private final Button removeButton = new Button(resources.getString("removeButton"));

              {
                removeButton.setOnAction(
                    actionEvent -> {
                      Goal goal = getTableView().getItems().get(getIndex());
                      getTableView().getItems().remove(goal);
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

    VBox healthGoalsVBox = createHealthGoalsVBox();
    VBox goldGoalsBox = createGoldGoalsVBox();
    VBox inventoryGoalsBox = createInventoryGoalsVBox();
    VBox scoreGoalsVBox = createScoreGoalsVBox();

    healthGoalsVBox.setVisible(false);
    healthGoalsVBox.setManaged(false);
    goldGoalsBox.setVisible(false);
    goldGoalsBox.setManaged(false);
    inventoryGoalsBox.setVisible(false);
    inventoryGoalsBox.setManaged(false);
    scoreGoalsVBox.setVisible(false);
    scoreGoalsVBox.setManaged(false);

    VBox actionCreationVbox = new VBox();
    goalTypeComboBox = new ComboBox<>();
    goalTypeComboBox.setOnAction(
        actionEvent -> {
          if (Objects.equals(
              goalTypeComboBox.getValue(), resources.getString("goalTypeComboBoxHealth"))) {
            healthGoalsVBox.setVisible(true);
            healthGoalsVBox.setManaged(true);
            goldGoalsBox.setVisible(false);
            goldGoalsBox.setManaged(false);
            inventoryGoalsBox.setVisible(false);
            inventoryGoalsBox.setManaged(false);
            scoreGoalsVBox.setVisible(false);
            scoreGoalsVBox.setManaged(false);
          } else if (Objects.equals(
              goalTypeComboBox.getValue(), resources.getString("goalTypeComboBoxGold"))) {
            goldGoalsBox.setVisible(true);
            goldGoalsBox.setManaged(true);
            healthGoalsVBox.setVisible(false);
            healthGoalsVBox.setManaged(false);
            inventoryGoalsBox.setVisible(false);
            inventoryGoalsBox.setManaged(false);
            scoreGoalsVBox.setVisible(false);
            scoreGoalsVBox.setManaged(false);
          } else if (Objects.equals(
              goalTypeComboBox.getValue(), resources.getString("goalTypeComboBoxInventory"))) {
            inventoryGoalsBox.setVisible(true);
            inventoryGoalsBox.setManaged(true);
            healthGoalsVBox.setVisible(false);
            healthGoalsVBox.setManaged(false);
            goldGoalsBox.setVisible(false);
            goldGoalsBox.setManaged(false);
            scoreGoalsVBox.setVisible(false);
            scoreGoalsVBox.setManaged(false);
          } else if (Objects.equals(
              goalTypeComboBox.getValue(), resources.getString("goalTypeComboBoxScore"))) {
            scoreGoalsVBox.setVisible(true);
            scoreGoalsVBox.setManaged(true);
            healthGoalsVBox.setVisible(false);
            healthGoalsVBox.setManaged(false);
            goldGoalsBox.setVisible(false);
            goldGoalsBox.setManaged(false);
            inventoryGoalsBox.setVisible(false);
            inventoryGoalsBox.setManaged(false);
          }
        });

    actionCreationVbox.getChildren().add(goalTypeComboBox);
    actionCreationVbox
        .getChildren()
        .addAll(healthGoalsVBox, goldGoalsBox, inventoryGoalsBox, scoreGoalsVBox);

    addGoalButton = new Button();
    addGoalButton.setOnAction(
        actionEvent -> {
          if (goalTypeComboBox.getValue().equals(resources.getString("goalTypeComboBoxHealth"))) {
            goalsTableView.getItems().add(new HealthGoal(healthSpinner.getValue()));
          } else if (goalTypeComboBox
              .getValue()
              .equals(resources.getString("goalTypeComboBoxGold"))) {
            goalsTableView.getItems().add(new GoldGoal(goldSpinner.getValue()));
          } else if (goalTypeComboBox
              .getValue()
              .equals(resources.getString("goalTypeComboBoxInventory"))) {
            /*goalsTableView
            .getItems()
            .add(
                new InventoryGoal(
                    inventoryComboBox.getValue());*/
          } else if (goalTypeComboBox
              .getValue()
              .equals(resources.getString("goalTypeComboBoxScore"))) {
            goalsTableView.getItems().add(new ScoreGoal(scoreSpinner.getValue()));
          }
        });

    actionsVBox.getChildren().addAll(goalsTableView, actionCreationVbox, addGoalButton);
    return actionsVBox;
  }

  private VBox createScoreGoalsVBox() {
    VBox scoreActionsVBox = new VBox();

    scoreText = new Text();
    scoreSpinner = new Spinner<>();
    scoreSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));

    GridPane scoreGridPane = new GridPane();
    scoreGridPane.add(scoreText, 0, 0);
    scoreGridPane.add(scoreSpinner, 1, 0);

    scoreActionsVBox.getChildren().add(scoreGridPane);
    return scoreActionsVBox;
  }

  private VBox createInventoryGoalsVBox() {
    VBox inventoryActionsVBox = new VBox();

    Text inventoryText = new Text();
    ComboBox<String> inventoryComboBox = new ComboBox<>();

    // Not yet implemented

    return inventoryActionsVBox;
  }

  private VBox createGoldGoalsVBox() {
    VBox goldActionsVBox = new VBox();

    goldText = new Text();
    goldSpinner = new Spinner<>();
    goldSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));

    GridPane goldGridPane = new GridPane();
    goldGridPane.add(goldText, 0, 0);
    goldGridPane.add(goldSpinner, 1, 0);

    goldActionsVBox.getChildren().add(goldGridPane);
    return goldActionsVBox;
  }

  private VBox createHealthGoalsVBox() {
    VBox healthActionsVBox = new VBox();

    healthText = new Text();
    healthSpinner = new Spinner<>();
    healthSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));

    GridPane healthGridPane = new GridPane();
    healthGridPane.add(healthText, 0, 0);
    healthGridPane.add(healthSpinner, 1, 0);

    healthActionsVBox.getChildren().add(healthGridPane);
    return healthActionsVBox;
  }

  public void updateActionTableView() {
    goalsTableView.getItems().clear();
    goalsTableView.getItems().addAll(game.getGoals());
  }
}
