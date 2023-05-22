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

public class EditPassageDialog extends Dialog<Passage> implements StandardDialog<Passage> {
  private final Passage passage;
  private final GenericDialogController controller = new GenericDialogController();
  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/editPassageDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private TextField titleTextField;
  private TextArea contentTextArea;
  private Text titleText;
  private Text contentText;

  public EditPassageDialog(Passage passage) {
    this.passage = passage;

    setDialogLanguage();
    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  public void initComponents() {
    titleTextField = new TextField();
    titleTextField.setText(passage.getTitle());

    contentTextArea = new TextArea();
    contentTextArea.setText(passage.getContent());

    titleText = new Text();
    contentText = new Text();

    controller.makeTextFieldNotStartWithSpace(titleTextField);
    controller.makeTextAreaNotStartWithSpace(contentTextArea);

    setResultConverter(createCallback());
  }

  public Callback<ButtonType, Passage> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        passage.setTitle(titleTextField.getText());
        passage.setContent(contentTextArea.getText());

        return passage;
      }
      return null;
    };
  }

  public void addComponentsToDialog() {
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
    // update text
    titleText.setText(resources.getString("titleText"));
    contentText.setText(resources.getString("contentText"));
  }

  public void setDialogLanguage() {
    setTitle(resources.getString("dialogTitle"));
    setHeaderText(resources.getString("headerText"));

    ButtonType primaryButtonType =
        new ButtonType(resources.getString("primaryButton"), ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType =
        new ButtonType(resources.getString("cancelButton"), ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().setAll(primaryButtonType, cancelButtonType);
  }
}
