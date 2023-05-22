package no.ntnu.idatg2001.paths.ui.handlers;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import no.ntnu.idatg2001.paths.ui.alerts.ExceptionAlert;

public class MusicHandler {
  private static MediaPlayer mediaPlayer;
    private static final String MUSIC_FILE_PATH = "/music/";

  private MusicHandler() {}

  public static void initialize() {
    VolumeHandler.getVolumeIntegerProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (mediaPlayer != null) {
                mediaPlayer.setVolume(newValue.intValue() / 100.0);
              }
            });
  }

  public static void playMusic(String musicFile) {
    stopCurrentMusic();
    try {
      URL resource = MusicHandler.class.getResource(MUSIC_FILE_PATH + musicFile);
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
      // Do nothing
    }
  }
}
