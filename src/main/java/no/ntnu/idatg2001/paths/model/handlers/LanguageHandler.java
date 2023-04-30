package no.ntnu.idatg2001.paths.model.handlers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import no.ntnu.idatg2001.paths.model.Database;
import no.ntnu.idatg2001.paths.model.Languages;

public class LanguageHandler {

  // standard language
  private static final Languages currentLanguage = Languages.ENGLISH;
  private static final IntegerProperty observableIntegerCounter = new SimpleIntegerProperty();

  public static IntegerProperty getObservableIntegerCounter() {
    return observableIntegerCounter;
  }

  public static Languages getCurrentLanguage() {
    return currentLanguage;
  }

  public static void setCurrentLanguage(Languages currentLanguage) {
    Database.currentLanguage = currentLanguage;
    observableIntegerCounter.set(observableIntegerCounter.get() + 1);
  }
}
