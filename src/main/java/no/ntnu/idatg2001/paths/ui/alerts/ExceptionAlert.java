package no.ntnu.idatg2001.paths.ui.alerts;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 * General alert for exceptions.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class ExceptionAlert extends Alert {

    /**
     * Creates an alert with the exception's message as content text.
     *
     * @param exception the exception to be put in the alert.
     */
    public ExceptionAlert(Exception exception) {
        super(Alert.AlertType.WARNING);
        this.setHeaderText(null);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setContentText(exception.getMessage());
    }
}
