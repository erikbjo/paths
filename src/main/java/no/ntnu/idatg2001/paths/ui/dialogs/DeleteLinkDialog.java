package no.ntnu.idatg2001.paths.ui.dialogs;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteLinkDialog extends Dialog<Link> implements StandardDialog {
  private final Story story;
  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/deleteLinkDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private ComboBox<Link> linkComboBox;

  public DeleteLinkDialog(Story story) {
    this.story = story;

    initComponents();
    addComponentsToDialog();
    updateLanguage();
    setDialogLanguage();
  }

  @Override
  public void initComponents() {
    linkComboBox = new ComboBox<>();
    linkComboBox
        .getItems()
        .addAll(
            story.getPassages().stream()
                .flatMap(passage -> passage.getLinks().stream())
                .toList());

    setResultConverter(createCallback());
  }

  @Override
  public void addComponentsToDialog() {
    getDialogPane().setContent(linkComboBox);
  }

  @Override
  public Callback<ButtonType, Link> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        return linkComboBox.getValue();
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
