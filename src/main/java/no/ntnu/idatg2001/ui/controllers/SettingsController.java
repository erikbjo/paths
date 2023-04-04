package no.ntnu.idatg2001.ui.controllers;

import no.ntnu.idatg2001.model.Database;
import no.ntnu.idatg2001.model.Languages;
import no.ntnu.idatg2001.ui.views.SettingsView;

public class SettingsController {
  private final SettingsView view;
  private Languages language;
  private int volume;

  public SettingsController() {
    language = Database.getCurrentLanguage();
    volume = Database.getCurrentVolume();
    view = new SettingsView(this);
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
