package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

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
            story.getPassages().stream().flatMap(passage -> passage.getLinks().stream()).toList());

    Callback<ListView<Link>, ListCell<Link>> cellFactory =
        comboBox ->
            new ListCell<>() {
              @Override
              protected void updateItem(Link item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                  setText(null);
                } else {
                  setText(
                      resources.getString("start")
                          + story.getPassages().stream()
                              .filter(
                                  passage ->
                                      !passage.getLinks().stream()
                                          .filter(
                                              link -> Objects.equals(link.getId(), item.getId()))
                                          .toList()
                                          .isEmpty())
                              .toList()
                              .get(0)
                              .getTitle()
                          + ", "
                          + resources.getString("text")
                          + item.getText()
                          + ", "
                          + resources.getString("end")
                          + item.getReference());
                }
              }
            };

    linkComboBox.setCellFactory(cellFactory);
    linkComboBox.setButtonCell(cellFactory.call(null));

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
