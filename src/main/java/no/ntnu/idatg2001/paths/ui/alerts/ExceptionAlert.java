package no.ntnu.idatg2001.paths.ui.alerts;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

/**
 * General alert for exceptions.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
public class ExceptionAlert extends Alert {
    private ResourceBundle exceptionResources;

    /**
     * Creates an alert with the exception's message as content text.
     *
     * @param exception the exception to be put in the alert.
     */
    public ExceptionAlert(Exception exception) {
        super(Alert.AlertType.WARNING);
        this.exceptionResources = ResourceBundle.getBundle(
            "exceptions",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
        this.setTitle(exceptionResources.getString("alertTitle"));
        this.setHeaderText(null);
        this.setContentText(exception.getMessage());
        this.initModality(Modality.APPLICATION_MODAL);
    }
}
