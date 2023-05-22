package no.ntnu.idatg2001.paths.ui.handlers;

import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.units.Player;

public class CurrentGameHandler {

  private static Game currentGame;
  private static Player stashedPlayer;

  private CurrentGameHandler() {}

  public static Game getCurrentGame() {
    return currentGame;
  }

  public static void setCurrentGame(Game currentGame) {
    setStashedPlayer(currentGame.getPlayer());
    CurrentGameHandler.currentGame = currentGame;
  }

  public static void restartGame() {
    currentGame.setPlayer(stashedPlayer);
    currentGame.restart();
  }

  public static void updateCurrentGame() {
    setStashedPlayer(currentGame.getPlayer());
  }

  private static void setStashedPlayer(Player stashedPlayer1) {
    stashedPlayer = new Player(stashedPlayer1);
  }
}
