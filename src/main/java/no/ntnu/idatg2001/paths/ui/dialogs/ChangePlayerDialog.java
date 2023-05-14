package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.model.units.Unit;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class ChangePlayerDialog extends Dialog<Player> implements StandardDialog<Player> {
  private final GenericDialogController controller = new GenericDialogController();
  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/changePlayerDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private Player player;
  private Text infoText;
  private ComboBox<Player> playerComboBox;

  public ChangePlayerDialog(Player player) {
    this.player = player;

    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  public ChangePlayerDialog() {
    this.player = null;

    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  public void initComponents() {
    playerComboBox = new ComboBox<>();
    playerComboBox.getItems().addAll(PlayerDAO.getInstance().getAll());
    playerComboBox.setValue(player);

    infoText = new Text();

    setResultConverter(createCallback());
  }

  public Callback<ButtonType, Player> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        player = playerComboBox.getValue();

        return player;
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

    gridPane.add(infoText, 0, 0);
    gridPane.add(playerComboBox, 1, 0);

    getDialogPane().setContent(gridPane);
  }

  public void setDialogLanguage() {
    setTitle(resources.getString("dialogTitle"));
    setHeaderText(resources.getString("headerText"));

    infoText.setText(resources.getString("infoText"));

    ButtonType primaryButtonType =
        new ButtonType(resources.getString("primaryButton"), ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType =
        new ButtonType(resources.getString("cancelButton"), ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().setAll(primaryButtonType, cancelButtonType);
  }
}
