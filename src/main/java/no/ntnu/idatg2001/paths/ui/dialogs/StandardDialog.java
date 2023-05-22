package no.ntnu.idatg2001.paths.ui.dialogs;

import javafx.scene.control.*;
import javafx.util.Callback;

public interface StandardDialog<T> {

  void initComponents();

  void addComponentsToDialog();

  Callback<ButtonType, T> createCallback();

  void updateLanguage();

  void setDialogLanguage();
}
