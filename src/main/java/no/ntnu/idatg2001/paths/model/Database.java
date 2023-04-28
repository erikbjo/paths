package no.ntnu.idatg2001.paths.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Database {
  private static int currentVolume = 50;
  private static IntegerProperty observableIntegerCounter = new SimpleIntegerProperty();

  // standard language
  private static Languages currentLanguage = Languages.ENGLISH;



  public static IntegerProperty getObservableIntegerCounter() {
    return observableIntegerCounter;
  }
  public static int getCurrentVolume() {
    return currentVolume;
  }

  public static void setCurrentVolume(int currentVolume) {
    Database.currentVolume = currentVolume;
  }

  public static Languages getCurrentLanguage() {
    return currentLanguage;
  }

  public static void setCurrentLanguage(Languages currentLanguage) {
    Database.currentLanguage = currentLanguage;
    observableIntegerCounter.set(observableIntegerCounter.get() + 1);
  }
}
