package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class EditLinkDialog extends LinkDialog implements StandardDialog<Link> {
  private final Link link;
  private final GenericDialogController controller = new GenericDialogController();
  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/editLinkDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private final Story story;
  private TextField linkTextTextField;
  private Text referenceText;
  private Text linkText;
  private ComboBox<Passage> referenceComboBox;

  public EditLinkDialog(Link link, Story story) {
    this.link = link;
    this.story = story;

    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  public void initComponents() {
    referenceComboBox = new ComboBox<>();
    // add all passages by the passage title
    referenceComboBox.setCellFactory(
        new Callback<>() {
          @Override
          public ListCell<Passage> call(ListView<Passage> p) {
            return new ListCell<>() {
              @Override
              protected void updateItem(Passage item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                  setGraphic(null);
                } else {
                  setText(item.getTitle());
                }
              }
            };
          }
        });

    referenceComboBox.setButtonCell(
        new ListCell<>() {
          @Override
          protected void updateItem(Passage item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
              setGraphic(null);
            } else {
              setText(item.getTitle());
            }
          }
        });

    referenceComboBox.getItems().addAll(story.getPassages());
    referenceComboBox.getSelectionModel().select(story.getLinkedPassage(link));

    linkTextTextField = new TextField();
    linkTextTextField.setText(link.getText());

    referenceText = new Text();
    linkText = new Text();

    controller.makeTextFieldNotStartWithSpace(linkTextTextField);

    setResultConverter(createCallback());
  }

  public Callback<ButtonType, Link> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        link.setReference(referenceComboBox.getSelectionModel().getSelectedItem().getTitle());
        link.setText(linkTextTextField.getText());

        link.getActions().forEach(action -> link.getActions().remove(action));
        super.getActionsTableView().getItems().forEach(link::addAction);

        return link;
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

    super.updateActionTableView(link);

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

    super.updateLanguage();
  }
}
