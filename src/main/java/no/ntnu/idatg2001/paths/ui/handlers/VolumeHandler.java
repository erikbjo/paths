package no.ntnu.idatg2001.paths.ui.handlers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class VolumeHandler {
  private static IntegerProperty volume = new SimpleIntegerProperty();

  public static int getCurrentVolume() {
    return volume.get();
  }

  public static void setCurrentVolume(int newVolume) {
    VolumeHandler.volume.set(newVolume);
  }
}
