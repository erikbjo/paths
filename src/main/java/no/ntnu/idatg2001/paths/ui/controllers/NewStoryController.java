package no.ntnu.idatg2001.paths.ui.controllers;

import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

import java.util.Locale;
import java.util.ResourceBundle;

public class NewStoryController {
  private ResourceBundle resources;

  public NewStoryController() {
    // Observes when the language in Database is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());
  }

  private void updateLanguage() {
    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "newStory", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  }
}
