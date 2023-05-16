package no.ntnu.idatg2001.paths.ui.alerts;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.View;

/**
 * General alert for confirmation's.
 *
 * @author Erik Bjørnsen
 */
public class ConfirmationAlert extends Alert {
  private final ResourceBundle confirmationResources =
      ResourceBundle.getBundle(
          "languages/confirmations",
          Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  private View view;

  /**
   * Crates the confirmation alert with the given title and content text.
   *
   * @param title the title of the alert.
   * @param contentText the content text of the alert.
   */
  public ConfirmationAlert(String title, String contentText) {
    super(AlertType.CONFIRMATION);
    this.initModality(Modality.APPLICATION_MODAL);
    this.setHeaderText(confirmationResources.getString("confirmationText"));
    this.setTitle(title);
    this.setContentText(contentText);
    ButtonType okButtonType =
        new ButtonType(confirmationResources.getString("okButton"), ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType =
        new ButtonType(
            confirmationResources.getString("cancelButton"), ButtonBar.ButtonData.CANCEL_CLOSE);
    this.getButtonTypes().setAll(okButtonType, cancelButtonType);
  }
}
