package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

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

    Callback<ListView<Passage>, ListCell<Passage>> cellFactory =
        comboBox ->
            new ListCell<>() {
              @Override
              protected void updateItem(Passage item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                  setText(null);
                } else {
                  setText(
                      resources.getString("itemTitle")
                          + item.getTitle()
                          + ", "
                          + resources.getString("itemContent")
                          + item.getContent()
                          + ", "
                          + resources.getString("itemAmountOfLinks")
                          + item.getLinks().size());
                }
              }
            };

    passageComboBox.setCellFactory(cellFactory);
    passageComboBox.setButtonCell(cellFactory.call(null));

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
  public void updateLanguage() {
    // User cant update language mid-dialog
  }

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
