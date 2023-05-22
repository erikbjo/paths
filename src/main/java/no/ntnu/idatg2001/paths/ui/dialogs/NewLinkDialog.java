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

public class NewLinkDialog extends LinkDialog implements StandardDialog<Link> {
  private final GenericDialogController controller = new GenericDialogController();
  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/newLinkDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private final Story story;
  private TextField linkTextTextField;
  private Text referenceText;
  private Text linkText;
  private ComboBox<Passage> referenceComboBox;

  public NewLinkDialog(Story story) {
    this.story = story;
    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  public void initComponents() {
    referenceComboBox = new ComboBox<>();
    referenceComboBox.getItems().addAll(story.getPassages());
    referenceComboBox.setCellFactory(controller.createPassageCallBack());
    referenceComboBox.setButtonCell(controller.createPassageListCell());

    linkTextTextField = new TextField();

    referenceText = new Text();
    linkText = new Text();

    controller.makeTextFieldNotStartWithSpace(linkTextTextField);

    setResultConverter(createCallback());
  }

  public Callback<ButtonType, Link> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        Link newLink =
            new Link(linkTextTextField.getText(), referenceComboBox.getValue().getTitle());
        super.getActionsTableView().getItems().forEach(newLink::addAction);

        return newLink;
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

    referenceComboBox.setPromptText(resources.getString("referenceComboBoxPromptText"));
    linkTextTextField.setPromptText(resources.getString("linkTextTextFieldPromptText"));

    super.updateLanguage();
  }
}
