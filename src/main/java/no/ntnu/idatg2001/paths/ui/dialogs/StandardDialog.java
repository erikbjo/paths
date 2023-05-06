package no.ntnu.idatg2001.paths.ui.dialogs;

import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.ui.controllers.GenericDialogController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public interface StandardDialog<T> {

  void initComponents();

  void addComponentsToDialog();

  Callback<ButtonType, T> createCallback();

  void updateLanguage();

  void setDialogLanguage();
}
