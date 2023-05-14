package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class NewPassageDialog extends Dialog<Passage> {
  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/newPassageDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private TextField titleTextField;
  private TextArea contentTextArea;
  private Text titleText;
  private Text contentText;
  private ButtonType createButtonType;
  private ButtonType cancelButtonType;
  private GenericDialogController controller;

  public NewPassageDialog() {
    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  private void initComponents() {
    controller = new GenericDialogController();

    titleTextField = new TextField();
    contentTextArea = new TextArea();

    titleText = new Text();
    contentText = new Text();

    controller.makeTextFieldNotStartWithSpace(titleTextField);
    controller.makeTextAreaNotStartWithSpace(contentTextArea);

    // update button
    createButtonType =
        new ButtonType(resources.getString("createButton"), ButtonBar.ButtonData.OK_DONE);
    cancelButtonType =
        new ButtonType(resources.getString("cancelButton"), ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().setAll(createButtonType, cancelButtonType);

    updateLanguage();
    setResultConverter(createPlayerCallback());
  }

  private Callback<ButtonType, Passage> createPlayerCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        // TODO: MAKE THIS MAKE USE OF BUILDER MAYBE
        return new Passage(titleTextField.getText(), contentTextArea.getText());
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
    // dialog etc
    setTitle(resources.getString("dialogTitle"));
    setHeaderText(resources.getString("headerText"));

    // update text
    titleText.setText(resources.getString("titleText"));
    contentText.setText(resources.getString("contentText"));

    // update prompt text
    titleTextField.setPromptText(resources.getString("titleTextField"));
    contentTextArea.setPromptText(resources.getString("contentTextArea"));
  }
}
