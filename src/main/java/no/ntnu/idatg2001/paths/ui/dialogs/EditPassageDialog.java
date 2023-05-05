package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.units.Attributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class EditPassageDialog extends Dialog<Passage> {
  private final Passage passage;
  private ResourceBundle resources;
  private TextField titleTextField;
  private TextArea contentTextArea;
  private Text titleText;
  private Text contentText;
  private GenericDialogController controller;

  public EditPassageDialog(Passage passage) {
    this.passage = passage;

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
            "playerInformation",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    controller = new GenericDialogController();

    titleTextField = new TextField();
    titleTextField.setText(passage.getTitle());

    contentTextArea = new TextArea();
    contentTextArea.setText(passage.getContent());

    titleText = new Text();
    contentText = new Text();

    controller.makeTextFieldNotStartWithSpace(titleTextField);
    controller.makeTextAreaNotStartWithSpace(contentTextArea);

    setResultConverter(createPassageCallback());
  }

  private Callback<ButtonType, Passage> createPassageCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        passage.setTitle(titleTextField.getText());
        passage.setContent(contentTextArea.getText());

        return passage;
      }
      return null;
    };
  }

  private void addComponentsToDialog() {
    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    gridPane.add(titleText, 0, 0);
    gridPane.add(titleTextField, 1, 0);
    gridPane.add(contentText, 0, 1);
    gridPane.add(contentTextArea, 1, 1);

    getDialogPane().setContent(gridPane);
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "editPassageDialog",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    // update text
    titleText.setText(resources.getString("titleText"));
    contentText.setText(resources.getString("contentText"));

    setTitle(resources.getString("dialogTitle"));
    setHeaderText(resources.getString("headerText"));

    // update button
    ButtonType editButtonType =
        new ButtonType(resources.getString("editButton"), ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType =
        new ButtonType(resources.getString("cancelButton"), ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().setAll(editButtonType, cancelButtonType);
  }
}
