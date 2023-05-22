package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class ChangeStoryDialog extends Dialog<Story> implements StandardDialog<Story> {
  private final ResourceBundle resources =
      ResourceBundle.getBundle(
          "languages/changeStoryDialog",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private Story story;
  private Text infoText;
  private ComboBox<Story> storyComboBox;

  public ChangeStoryDialog(Story story) {
    this.story = story;

    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  public ChangeStoryDialog() {
    this.story = null;

    initComponents();
    addComponentsToDialog();
    updateLanguage();
  }

  public void initComponents() {
    storyComboBox = new ComboBox<>();
    storyComboBox.getItems().addAll(StoryDAO.getInstance().getAll());
    storyComboBox.setValue(story);

    infoText = new Text();

    setResultConverter(createCallback());
  }

  public Callback<ButtonType, Story> createCallback() {
    return buttonType -> {
      if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        story = storyComboBox.getValue();

        return story;
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
    gridPane.add(storyComboBox, 1, 0);

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
