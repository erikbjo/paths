package no.ntnu.idatg2001;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a player with different actions that can be used in a story.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.03
 */
public class Player {
  private String name;
  private int health;
  private int score;
  private int gold;
  private List<String> inventory;

  /**
   * A constructor that initializes the declared fields name, health, score and gold.
   *
   * @param name represents the player's name.
   * @param health represents the player's health.
   * @param score represents the player's score.
   * @param gold represents the player's gold.
   */
  public Player(String name, int health, int score, int gold) {
    this.name = name;
    addHealth(health);
    this.score = score;
    addGold(gold);
    this.inventory = new ArrayList<>();
  }

  /**
   * 123123123
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   *
   * @param health
   */
  public boolean addHealth(int health) {
    if (health > 0) {
      this.health = health;
      return true;
    } else {
      return false;
    }
  }

  /**
   *
   * @return
   */
  public int getHealth() {
    return health;
  }

  /**
   *
   * @param points
   */
  public void addScore(int points){

  }

  /**
   *
   * @return
   */
  public int getScore() {
    return score;
  }

  /**
   *
   * @param gold
   */
  public boolean addGold(int gold) {
    if (gold >= 0) {
      this.gold = gold;
      return true;
    } else {
      return false;
    }

  }

  /**
   *
   * @return
   */
  public int getGold() {
    return gold;
  }

  /**
   *
   * @param item
   */
  public void addToInventory(String item) {
    inventory.add(item);
  }

  /**
   *
   * @return
   */
  public List<String> getInventory() {
    return inventory;
  }
}
