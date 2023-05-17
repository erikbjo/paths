package no.ntnu.idatg2001.paths.ui.controllers;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import no.ntnu.idatg2001.paths.model.Passage;

public class GenericDialogController {

  public Callback<ListView<Passage>, ListCell<Passage>> createPassageCallBack() {

    return new Callback<>() {
      @Override
      public ListCell<Passage> call(ListView<Passage> p) {
        return new ListCell<Passage>() {
          @Override
          protected void updateItem(Passage item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
              setGraphic(null);
            } else {
              setText(item.getTitle());
            }
          }
        };
      }
    };
  }

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

  public ListCell<Passage> createPassageListCell() {
    return new ListCell<Passage>() {
      @Override
      public void updateItem(Passage item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setGraphic(null);
        } else {
          setText(item.getTitle());
        }
      }
    };
  }
}
