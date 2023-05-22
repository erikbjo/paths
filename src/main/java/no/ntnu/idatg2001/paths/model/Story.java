package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;
import java.util.*;

/**
 * The Story class is a container for the story. It contains the title of the story, a map of all
 * the passages in the story, and the opening passage.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.05.20
 */
@Entity
@Table(name = "story")
public class Story {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "story_passage_mapping",
      joinColumns = {@JoinColumn(name = "story_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "passage_id", referencedColumnName = "id")})
  @MapKeyJoinColumn(name = "link_id")
  private Map<Link, Passage> passages;

  @OneToOne(cascade = {CascadeType.ALL})
  @JoinColumn
  private Passage openingPassage;

  private String title;

  /**
   * This function creates a new story with a title and an opening passage.
   *
   * @param title The title of the story.
   * @param openingPassage The opening passage of the story.
   * @throws IllegalArgumentException if the title is null, blank or empty.Also if the opening
   *     passage is null.
   */
  public Story(String title, Passage openingPassage) {
    if (title == null || title.isBlank() || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or blank");
    }
    if (openingPassage == null) {
      throw new IllegalArgumentException("Opening passage cannot be null");
    }
    this.passages = new HashMap<>();
    this.title = title;
    setOpeningPassage(openingPassage);
  }

  /**
   * Copy constructor for the Story class. Creates a new story with the same title, passages and
   * opening passage as the story passed in.
   *
   * @param story The story to copy.
   * @throws IllegalArgumentException if the story is null.
   */
  public Story(Story story) {
    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }
    this.title = story.getTitle();
    this.passages = new HashMap<>();
    this.openingPassage = story.getOpeningPassage();
    for (Link link : story.getPassagesHashMap().keySet()) {
      this.passages.put(link, story.getPassagesHashMap().get(link));
    }
  }

  /**
   * This function creates a new story with a title.
   *
   * @param title The title of the story.
   * @throws IllegalArgumentException if the title is null, blank or empty.
   */
  public Story(String title) {
    if (title == null || title.isBlank() || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or blank");
    }
    this.title = title;
    this.passages = new HashMap<>();
    this.openingPassage = null;
  }

  /** Default constructor for the Story class. */
  public Story() {
    this.passages = new HashMap<>();
    this.openingPassage = null;
  }

  /**
   * Returns the passages hashmap.
   *
   * @return The passages hashmap.
   */
  public Map<Link, Passage> getPassagesHashMap() {
    return passages;
  }

  /**
   * This function returns the title of the game
   *
   * @return The title of the game.
   */
  public String getTitle() {
    return title;
  }

  /**
   * This function sets the title of the game.
   *
   * @param text The title of the game.
   * @throws IllegalArgumentException if the title is null, blank or empty.
   */
  public void setTitle(String text) {
    if (text == null || text.isBlank() || text.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or blank");
    }
    this.title = text;
  }

  /**
   * This function returns the opening passage of the game.
   *
   * @return The opening passage of the game.
   */
  public Passage getOpeningPassage() {
    return openingPassage;
  }

  /**
   * This function sets the opening passage of the game. If the opening passage is already set, it
   * will be removed from the passages map.
   *
   * @param openingPassage The opening passage of the game.
   * @throws IllegalArgumentException if the opening passage is null.
   */
  public void setOpeningPassage(Passage openingPassage) {
    if (openingPassage == null) {
      throw new IllegalArgumentException("Opening passage cannot be null");
    }
    if (this.openingPassage != null) {
      passages.remove(new Link(this.openingPassage.getTitle(), this.openingPassage.getTitle()));
    }
    this.openingPassage = openingPassage;
    if (!passages.containsValue(openingPassage)) {
      addPassage(openingPassage);
    }
  }

  /**
   * This function adds a passage to the story.
   *
   * @param passage The passage that gets added to the game.
   * @throws IllegalArgumentException if the passage is null or already exists.
   */
  public void addPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null");
    }
    if (passages.containsValue(passage)) {
      throw new IllegalArgumentException("Passage already exists");
    }

    passage.setStory(this);
    Link link = new Link(passage.getTitle(), passage.getTitle());
    link.setStory(this);
    passages.put(link, passage);
  }

  /**
   * This function returns a list of all the passages in the story.
   *
   * @return A list of all the passages in the story.
   */
  public List<Passage> getPassages() {
    return new ArrayList<>(passages.values());
  }

  /**
   * This function returns a list of all the passages except for the opening passage.
   *
   * @return A list of all the passages except for the opening passage.
   */
  public List<Passage> getPassagesExceptForOpeningPassage() {
    return passages.values().stream().filter(passage -> !passage.equals(openingPassage)).toList();
  }

  /**
   * This function returns the "proxy" link of a another link.
   *
   * @param link The link that you want to get the proxy link of.
   * @return The proxy link of the given link.
   */
  public Link reverseLink(Link link) {
    return this.getPassagesHashMap().keySet().stream()
        .filter(l -> l.equals(link))
        .findFirst()
        .orElseThrow();
  }

  /**
   * Returns the passage that is linked to the given link.
   *
   * @param link The link that is connected to the passage that gets returned.
   * @return The passage that is linked to the given link.
   */
  public Passage getLinkedPassage(Link link) {
    return passages.get(link);
  }

  /**
   * This function removes a passage from the story.
   *
   * @param link The link that is connected to the passage that gets removed.
   * @throws IllegalArgumentException If the link is null or if the link does not exist in the
   *     story.
   */
  public void removePassage(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("Link cannot be null");
    }
    if (!passages.containsKey(link)) {
      throw new IllegalArgumentException("Link does not exist");
    }
    passages.remove(link);
  }

  /**
   * This function returns a list of all the links that are broken. A broken link is a link that
   * points to a passage that does not exist.
   *
   * @return A list of all the links that are broken.
   */
  public List<Link> getBrokenLinks() {
    return passages.values().stream()
        .flatMap(passage -> passage.getLinks().stream().filter(link -> !passages.containsKey(link)))
        .toList();
  }

  /**
   * Returns a string representation of the story. Returns the title of the story.
   *
   * @return The title of the story.
   */
  @Override
  public String toString() {
    return title;
  }

  /**
   * This function returns the id of the story. This is used by the database.
   *
   * @return The id of the story.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id of the story. This is used by the database.
   *
   * @param id The id of the story.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This function returns the minimum number of passages needed to traverse from the opening
   * passage to a given passage.
   *
   * @param to The passage to go to.
   * @return The minimum number of passages.
   * @throws IllegalArgumentException If the passage is null.
   */
  public int shortestPathFromOpeningPassage(Passage to) {
    if (to == null) {
      throw new IllegalArgumentException("Passage cannot be null");
    }
    Map<Passage, Passage> previousPassages = new HashMap<>();
    Queue<Passage> queue = new LinkedList<>();
    queue.add(openingPassage);
    previousPassages.put(openingPassage, null);

    while (!queue.isEmpty()) {
      Passage currentPassage = queue.remove();

      // If we've reached the opening passage, return the number of passages in the path
      if (currentPassage.equals(to)) {
        List<Passage> shortestPath = new ArrayList<>();
        for (Passage passage = to; passage != null; passage = previousPassages.get(passage)) {
          shortestPath.add(passage);
        }
        return shortestPath.size();
      }

      // Else, add all the linked passages to the queue
      for (Link link : currentPassage.getLinks()) {
        Passage linkedPassage = getLinkedPassage(link);
        if (linkedPassage != null && !previousPassages.containsKey(linkedPassage)) {
          queue.add(linkedPassage);
          previousPassages.put(linkedPassage, currentPassage);
        }
      }
    }

    return 1; // No path found
  }
}
