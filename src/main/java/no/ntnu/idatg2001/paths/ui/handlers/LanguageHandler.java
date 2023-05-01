package no.ntnu.idatg2001.paths.ui.handlers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import no.ntnu.idatg2001.paths.model.Languages;

public class LanguageHandler {

  private static final IntegerProperty observableIntegerCounter = new SimpleIntegerProperty();
  // standard language
  private static Languages currentLanguage = Languages.ENGLISH;

  public static IntegerProperty getObservableIntegerCounter() {
    return observableIntegerCounter;
  }

  public static Languages getCurrentLanguage() {
    return currentLanguage;
  }

  public static void setCurrentLanguage(Languages currentLanguage) {
    LanguageHandler.currentLanguage = currentLanguage;
    observableIntegerCounter.set(observableIntegerCounter.get() + 1);
  }
}
