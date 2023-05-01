package no.ntnu.idatg2001.paths.ui.handlers;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import no.ntnu.idatg2001.paths.ui.alerts.ExceptionAlert;

public class MusicHandler {
  private static MediaPlayer mediaPlayer;

  private MusicHandler() {}

  public static void playMusic(String musicFilePath) {
    stopCurrentMusic();
    try {
      URL resource = MusicHandler.class.getResource(musicFilePath);
      assert resource != null;
      Media media = new Media(resource.toString());
      mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setAutoPlay(true);
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
      mediaPlayer.setVolume(VolumeHandler.getCurrentVolume() / 100.0);
    } catch (Exception e) {
      ExceptionAlert exceptionAlert = new ExceptionAlert(e);
      exceptionAlert.showAndWait();
    }
  }

  public static void stopCurrentMusic() {
    try {
      mediaPlayer.stop();
    } catch (Exception ignored) {
    }
  }
}
