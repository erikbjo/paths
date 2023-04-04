package no.ntnu.idatg2001.model;

public class Database {
  private static int currentVolume = 50;
  private static Languages currentLanguage = Languages.ENGLISH;

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
  }
}
