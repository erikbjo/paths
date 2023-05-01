package no.ntnu.idatg2001.paths.ui.handlers;

public class VolumeHandler {
  private static int currentVolume = 50;

  public static int getCurrentVolume() {
    return currentVolume;
  }

  public static void setCurrentVolume(int currentVolume) {
    VolumeHandler.currentVolume = currentVolume;
  }
}
