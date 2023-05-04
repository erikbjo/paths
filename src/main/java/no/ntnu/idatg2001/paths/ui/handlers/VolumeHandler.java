package no.ntnu.idatg2001.paths.ui.handlers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class VolumeHandler {
  private static final IntegerProperty volume = new SimpleIntegerProperty(50);

  public static int getCurrentVolume() {
    return volume.get();
  }

  public static void setCurrentVolume(int newVolume) {
    VolumeHandler.volume.set(newVolume);
  }

  public static IntegerProperty getVolumeIntegerProperty() {
    return volume;
  }
}
