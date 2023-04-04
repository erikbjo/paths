package no.ntnu.idatg2001.ui.controllers;

import no.ntnu.idatg2001.ui.views.SettingsView;

public class SettingsController {

    private final SettingsView view;

    public SettingsController() {
    view = new SettingsView(this);
    }

    public void saveSettings() {
        // Save settings here
        String language = view.getLanguage();
        int volume = view.getVolume();
        // Do something with the language and volume settings
        view.close();
    }

}
