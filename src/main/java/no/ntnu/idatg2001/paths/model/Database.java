package no.ntnu.idatg2001.paths.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Database {



  // for testing i think maybe
  private static Game currentGame;
  public static Game getCurrentGame() {
    return currentGame;
  }
  public static void setCurrentGame(Game currentGame) {
    Database.currentGame = currentGame;
  }
}
