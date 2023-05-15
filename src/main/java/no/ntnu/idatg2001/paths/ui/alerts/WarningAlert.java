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
 * General alert for warnings.
 *
 * @author Erik Bjørnsen
 */
public class WarningAlert extends Alert {
    private ResourceBundle warningResources;
    private View view;

    /**
     * Creates an alert with the type warning. Uses the parameters to fill out the information in the
     * alert.
     *
     * @param contentText the content text of the alert
     */
    public WarningAlert(String contentText) {
        super(AlertType.WARNING);
        LanguageHandler.getObservableIntegerCounter()
            .addListener((obs, oldValue, newValue) -> view.updateLanguage());
        this.warningResources = ResourceBundle.getBundle(
            "languages/warnings",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
        this.setTitle(warningResources.getString("dialogTitle"));
        this.setHeaderText(warningResources.getString("dialogHeader"));
        this.setContentText(contentText);
        this.initModality(Modality.APPLICATION_MODAL);
        ButtonType okButtonType =
            new ButtonType(warningResources.getString("okButton"),
                ButtonBar.ButtonData.OK_DONE);
        this.getButtonTypes().setAll(okButtonType);
    }

    /**
     * Creates an alert with the type warning. Uses the parameters to fill out the information in the
     * alert.
     *
     * @param contentText the content text of the alert.
     * @param headerText  the header text of the alert.
     */
    public WarningAlert(String contentText, String headerText) {
        super(AlertType.WARNING);
        this.setTitle(warningResources.getString("dialogTitle"));
        this.setHeaderText(headerText);
        this.setContentText(contentText);
        this.initModality(Modality.APPLICATION_MODAL);
        ButtonType okButtonType =
            new ButtonType(warningResources.getString("okButton"),
                ButtonBar.ButtonData.OK_DONE);
        this.getButtonTypes().setAll(okButtonType);
    }

    /**
     * Standard constructor with no parameters, can be edited afterward.
     */
    public WarningAlert() {
        super(AlertType.WARNING);
        this.setTitle(warningResources.getString("dialogTitle"));
        this.setHeaderText(warningResources.getString("dialogHeader"));
        this.initModality(Modality.APPLICATION_MODAL);
        ButtonType okButtonType =
            new ButtonType(warningResources.getString("okButton"),
                ButtonBar.ButtonData.OK_DONE);
        this.getButtonTypes().setAll(okButtonType);
    }
}
