package no.ntnu.idatg2001.paths.ui.controllers;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GenericDialogController {
  public void makeTextFieldNumericOnly(TextField textField) {
    textField
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
              }
            });
  }

  public void makeTextFieldNotStartWithSpace(TextField textField) {
    textField
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if ((oldValue.isEmpty() || oldValue.isBlank()) && newValue.matches(" ")) {
                textField.clear();
              }
            });
  }

  public void makeTextAreaNotStartWithSpace(TextArea textArea) {
    textArea
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if ((oldValue.isEmpty() || oldValue.isBlank()) && newValue.matches(" ")) {
                textArea.clear();
              }
            });
  }
}
