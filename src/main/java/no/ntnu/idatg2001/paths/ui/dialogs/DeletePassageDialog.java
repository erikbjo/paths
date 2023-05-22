package no.ntnu.idatg2001.paths.ui.dialogs;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class DeletePassageDialog extends Dialog<Passage> implements StandardDialog {

  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/deletePassageDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private final Story story;
  private ComboBox<Passage> passageComboBox;

  public DeletePassageDialog(Story story) {
    this.story = story;

    initComponents();
    addComponentsToDialog();
    updateLanguage();
    setDialogLanguage();
  }

  @Override
  public void initComponents() {
    passageComboBox = new ComboBox<>();
    passageComboBox.getItems().addAll(story.getPassages());

    setResultConverter(createCallback());
  }

  @Override
  public void addComponentsToDialog() {
    getDialogPane().setContent(passageComboBox);
  }

  @Override
  public Callback<ButtonType, Passage> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        return passageComboBox.getValue();
      }
      return null;
    };
  }

  @Override
  public void updateLanguage() {}

  @Override
  public void setDialogLanguage() {
    this.setTitle(resources.getString("title"));
    this.setHeaderText(resources.getString("header"));

    ButtonType primaryButtonType =
        new ButtonType(resources.getString("primaryButton"), ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType =
        new ButtonType(resources.getString("cancelButton"), ButtonBar.ButtonData.CANCEL_CLOSE);
    getDialogPane().getButtonTypes().setAll(primaryButtonType, cancelButtonType);
  }
}
