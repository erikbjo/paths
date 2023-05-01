package no.ntnu.idatg2001.paths.ui.alerts;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 * General alert for exceptions.
 *
 * @author Erik Bjørnsen & Emil Klevgård-Slåttsveen
 */
public class ExceptionAlert extends Alert {

  /**
   * Creates an alert with the exception's message as content text.
   *
   * @param exception the exception to be put in the alert.
   */
  public ExceptionAlert(Exception exception) {
    super(Alert.AlertType.WARNING);
    this.setTitle("Error");
    this.setHeaderText(null);
    this.setContentText(exception.getMessage());
    this.initModality(Modality.APPLICATION_MODAL);
  }
}
