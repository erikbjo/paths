package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import no.ntnu.idatg2001.paths.model.goals.Goal;
import no.ntnu.idatg2001.paths.model.units.Player;

/**
 * The Game class represents a game. It contains a player, a story, and a list of goals. It also
 * contains the current passage of the game.
 *
 * @see Player
 * @see Story
 * @see Goal
 * @see Passage
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 */
@Entity
@Table(name = "game")
public class Game {
  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<Goal> goals = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "player_id")
  private Player player;

  @ManyToOne
  @JoinColumn(name = "story_id")
  private Story story;

  @OneToOne private Passage currentPassage;

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
    this.currentPassage = story.getOpeningPassage();
  }

  /** Default constructor for the Game class. For DB */
  public Game() {}

  /**
   * Gets the current passage of the game.
   *
   * @return the currentPassage of the game
   */
  public Passage getCurrentPassage() {
    return currentPassage;
  }

  /**
   * Sets the current passage of the game. Null values ARE allowed.
   *
   * @param currentPassage the currentPassage to set
   */
  public void setCurrentPassage(Passage currentPassage) {
    this.currentPassage = currentPassage;
  }

  /**
   * Gets the player of the game.
   *
   * @return the player of the game
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Sets the player of the game. Null values ARE allowed.
   *
   * @param player the player to set
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Gets the story of the game.
   *
   * @return the story
   */
  public Story getStory() {
    return story;
  }

  /**
   * Sets the story of the game. Null values ARE allowed.
   *
   * @param story the story to set
   */
  public void setStory(Story story) {
    this.story = story;
  }

  /**
   * Gets the goals of the game.
   *
   * @return the goals
   */
  public List<Goal> getGoals() {
    return goals;
  }

  /**
   * Sets the goals of the game. Removes all goals from the game and adds the goals in the list.
   * Empty lists are allowed.
   *
   * @param goals the goals to set
   * @throws IllegalArgumentException if the list of goals contains a null goal
   */
  public void setGoals(List<Goal> goals) {
    removeAllGoals();
    addGoals(goals);
  }

  /**
   * Adds a list of goals to the game. Empty lists are allowed.
   *
   * @param goals the goals to add
   * @throws IllegalArgumentException if the list of goals contains a null goal
   */
  public void addGoals(List<Goal> goals) {
    for (Goal goal : goals) {
      if (goal == null) {
        throw new IllegalArgumentException("Goals cannot contain null");
      }
    }
    goals.forEach(this::addGoal);
  }

  /**
   * Adds a goal to the game.
   *
   * @param goal the goal to add
   * @throws IllegalArgumentException if the goal is null
   */
  public void addGoal(Goal goal) {
    if (goal == null) {
      throw new IllegalArgumentException("Goal cannot be null");
    }
    goal.setGame(this);
    goals.add(goal);
  }

  /**
   * Removes a goal from the game.
   *
   * @param goal the goal to remove
   * @throws IllegalArgumentException if the goal is not in the game
   * @throws IllegalArgumentException if the goal is null
   */
  public void removeGoal(Goal goal) {
    if (goal == null) {
      throw new IllegalArgumentException("Goal cannot be null");
    }
    if (!goals.contains(goal)) {
      throw new IllegalArgumentException("Goal not in game");
    }
    goals.remove(goal);
  }

  /** Removes all goals from the game. */
  public void removeAllGoals() {
    goals.clear();
  }

  /**
   * Method to get the opening passage of the story.
   *
   * @return the opening passage of the story
   */
  public Passage begin() {
    return story.getOpeningPassage();
  }

  /**
   * Method to go to a passage. If the link is not available, an IllegalArgumentException is thrown.
   *
   * @param link the link to go to
   * @return the next passage from the link
   * @throws IllegalArgumentException if the link is not available
   */
  public Passage go(Link link) {
    List<Link> availableLinks = getCurrentPassage().getLinks();
    if (availableLinks.contains(link)) {
      link.getActions()
          .forEach(
              action -> {
                action.execute(player);
              });

      Link reversedLink = story.reverseLink(link);
      setCurrentPassage(story.getPassagesHashMap().get(reversedLink));
      return story.getPassagesHashMap().get(reversedLink);
    } else {
      throw new IllegalArgumentException("Link not available");
    }
  }

  /**
   * Returns the game id.
   *
   * @return the id as a String.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the game id.
   *
   * @param id the id as a String.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Returns a String representation of the Game class.
   *
   * @return a String representation of the Game class.
   */
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

  /** Method to restart the game. Sets the current passage to the opening passage of the story. */
  public void restart() {
    setCurrentPassage(story.getOpeningPassage());
  }
}
