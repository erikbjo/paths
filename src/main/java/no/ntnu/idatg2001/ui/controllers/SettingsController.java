package no.ntnu.idatg2001.ui.controllers;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.Event;
import no.ntnu.idatg2001.model.Database;
import no.ntnu.idatg2001.model.Languages;
import no.ntnu.idatg2001.ui.views.SettingsDialog;

public class SettingsController {
  private final SettingsDialog view;
  private Languages language;
  private int volume;

  public SettingsController(Event event) {
    language = Database.getCurrentLanguage();
    volume = Database.getCurrentVolume();
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "messages", Locale.forLanguageTag(Database.getCurrentLanguage().getLocalName()));
    
    view = new SettingsDialog(this, resources);
  }

  public Languages getLanguage() {
    return language;
  }

  public int getVolume() {
    return volume;
  }


  public void saveSettings() {
    // Save settings here
    language = view.getLanguage();
    volume = view.getVolume();
    // Do something with the language and volume settings
    Database.setCurrentLanguage(language);
    Database.setCurrentVolume(volume);
    view.close();
  }
}
