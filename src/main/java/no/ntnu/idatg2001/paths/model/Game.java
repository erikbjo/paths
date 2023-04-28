package no.ntnu.idatg2001.paths.model;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.units.Player;

public class Game {
  private final Player player;
  private final Story story;
  private final List<Goal> goals;

  /**
   * Constructor for the Game class.
   *
   * @param player The player.
   * @param story The story.
   * @param goals The goals.
   */
  public Game(Player player, Story story, List<Goal> goals) {
    this.player = player;
    this.story = story;
    this.goals = new ArrayList<>();
    this.goals.addAll(goals);
  }

  public Player getPlayer() {
    return player;
  }

  public Story getStory() {
    return story;
  }

  public List<Goal> getGoals() {
    return goals;
  }

  public Passage begin() {
    Passage firstPassage = new Passage("Start your journey.", "Choose your player class.");
    return firstPassage;
  }

  public Passage go(Link link) {
    link = new Link("Cast a spell.", "Attack the enemy.");
    Passage matchingPassage = new Passage("Attack the enemy.", "Which action do you choose?");
    if (matchingPassage.getTitle().equals(link.getReference())) {
      return matchingPassage;
    } else {
      return null;
    }
  }
}
