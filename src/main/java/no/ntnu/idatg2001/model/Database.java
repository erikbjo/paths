package no.ntnu.idatg2001.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Database {
  private static int currentVolume = 50;
  private static Languages currentLanguage = Languages.ENGLISH;

  private static PropertyChangeSupport support = new PropertyChangeSupport(Database.class);

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
    support.firePropertyChange("currentLanguage", getCurrentLanguage(), currentLanguage);
    Database.currentLanguage = currentLanguage;
  }

  public void removePropertyChangeListener(PropertyChangeListener pcl) {
    support.removePropertyChangeListener(pcl);
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

}
