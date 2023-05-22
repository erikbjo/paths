package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.Event;
import no.ntnu.idatg2001.paths.model.Languages;
import no.ntnu.idatg2001.paths.model.utilities.SettingsFileWriterReader;
import no.ntnu.idatg2001.paths.ui.dialogs.SettingsDialog;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.handlers.VolumeHandler;

public class SettingsController {
  private final SettingsDialog view;
  private Languages language;
  private int volume;

  public SettingsController(Event event) {
    language = LanguageHandler.getCurrentLanguage();
    volume = VolumeHandler.getCurrentVolume();
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "languages/messages",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    view = new SettingsDialog(this, resources);
  }

  public Languages getLanguage() {
    return language;
  }

  public int getVolume() {
    return volume;
  }

  public void saveSettings() {
    language = view.getLanguage();
    volume = view.getVolume();

    LanguageHandler.setCurrentLanguage(language);
    VolumeHandler.setCurrentVolume(volume);
    SettingsFileWriterReader.saveSettings();
    view.close();
  }
}
