package no.ntnu.idatg2001.model.units;

/**
 * A class that represents a player with different actions that can be used in a story.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.03
 */
public class Player extends Unit {

  private Player(PlayerBuilder builder) {
    super(builder);
  }

  public String dialog() {
    return "Hello, my name is " + super.getName();
  }

  /**
   * If the health is greater than 0, add it to the current health and return true. Otherwise,
   * return false
   *
   * @param health The amount of health to add to the player.
   * @return A boolean value.
   */
  public boolean addHealth(int health) {
    if (health > 0) {
      super.setHealth(super.getHealth() + health);
      return true;
    } else {
      return false;
    }
  }

  /**
   * If the mana is greater than 0, add it to the current mana and return true. Otherwise, return
   * false
   *
   * @param mana The amount of mana to add to the player.
   * @return A boolean value.
   */
  public boolean addMana(int mana) {
    if (mana > 0) {
      super.setMana(super.getMana() + mana);
      return true;
    } else {
      return false;
    }
  }

  /**
   * If the energy is greater than 0, add it to the current energy and return true. Otherwise,
   * return false
   *
   * @param energy The amount of energy to add to the robot.
   * @return The boolean value of the if statement.
   */
  public boolean addEnergy(int energy) {
    if (energy > 0) {
      super.setEnergy(super.getEnergy() + energy);
      return true;
    } else {
      return false;
    }
  }

  /**
   * If the gold is greater than or equal to zero, add the gold to the player's gold, and return
   * true. Otherwise, return false
   *
   * @param gold The amount of gold to add to the player's gold.
   * @return A boolean value.
   */
  public boolean addGold(int gold) {
    if (gold >= 0) {
      super.setGold(super.getGold() + gold);
      return true;
    } else {
      return false;
    }
  }

  public static class PlayerBuilder extends UnitBuilder<PlayerBuilder> {
    @Override
    public Player build() {
      return new Player(this);
    }
  }
}
