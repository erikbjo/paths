package no.ntnu.idatg2001.paths.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    return story.getOpeningPassage();
  }

  public Passage go(Link link) {
    List<Link> availableLinks = story.getCurrentPassage().getLinks();
    if (availableLinks.contains(link)) {
      List<Passage> availablePassages = story.getPassagesConnectedWithLink(link);
      // get the passage that is not the current passage in availablePassages
      Passage nextPassage =
          availablePassages.stream()
              .filter(passage -> !passage.equals(story.getCurrentPassage()))
              .toList()
              .get(0);
      story.setCurrentPassage(nextPassage);
      return nextPassage;
    } else {
      return null;
    }
  }

  @Override
    public String toString() {
        return "Game{"
            + "player="
            + player
            + ", story="
            + story
            + ", goals="
            + goals.stream().map(Goal::toString).collect(Collectors.joining(", "))
            + '}';
    }
}
