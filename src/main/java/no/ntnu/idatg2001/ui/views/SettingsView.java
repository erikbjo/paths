package no.ntnu.idatg2001.ui.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import no.ntnu.idatg2001.ui.controllers.SettingsController;

public class SettingsView {

  private final Stage stage;
  private final SettingsController controller;
  private final TextField languageTextField;
  private final Slider volumeSlider;

  public SettingsView(SettingsController controller) {
    this.controller = controller;
    stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Settings");

    // Create controls
    Label languageLabel = new Label("Language:");
    languageTextField = new TextField();
    Label volumeLabel = new Label("Volume:");
    volumeSlider = new Slider(0, 100, 50);
    Button saveButton = new Button("Save");
    saveButton.setOnAction(event -> controller.saveSettings());

    // Add controls to layout
    GridPane layout = new GridPane();
    layout.setPadding(new Insets(10));
    layout.setHgap(10);
    layout.setVgap(10);
    layout.addRow(0, languageLabel, languageTextField);
    layout.addRow(1, volumeLabel, volumeSlider);
    layout.addRow(2, saveButton);

    // Show the dialog
    Scene scene = new Scene(layout);
    stage.setScene(scene);
    stage.show();
  }

  public void close() {
    stage.close();
  }

  public String getLanguage() {
    return languageTextField.getText();
  }

  public int getVolume() {
    return (int) volumeSlider.getValue();
  }
}
