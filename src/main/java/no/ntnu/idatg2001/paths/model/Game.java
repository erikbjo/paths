package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.units.Player;

@Entity(name = "Game")
@Table(name = "game")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "game id")
  private Player player = null;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "game id")
  private Story story = null;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "game id")
  private final List<Goal> goals = new ArrayList<>();

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
    this.goals.addAll(goals);
  }

  /** Default constructor for the Game class. For DB */
  public Game() {}

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

  /**
   * Returns the game id.
   *
   * @return the id as a String.
   */
  public String getId() {
    return id;
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
