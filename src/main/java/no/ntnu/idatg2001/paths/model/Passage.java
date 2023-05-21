package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a Passage that contains a title, content, and a list of links.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
@Entity
@Table(name = "passage")
public class Passage {
  private String title;
  private String content;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "passage_id")
  private List<Link> links;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "story_id")
  private Story story;

  /**
   * A constructor that initializes the declared fields for title, content and links.
   *
   * @param title the title of the passage, also works as a unique identifier.
   * @param content the content of the passage, typically a part of the story.
   */
  public Passage(String title, String content) {
    setTitle(title);
    setContent(content);
    this.links = new ArrayList<>();
  }

  /** Default constructor for the Passage class. For DB */
  public Passage() {}

  /**
   * Gets the story that the passage belongs to. Used for DB.
   *
   * @return the story that the passage belongs to.
   */
  public Story getStory() {
    return story;
  }

  /**
   * Sets the story that the passage belongs to. Used for DB.
   *
   * @param story the story that the passage belongs to.
   */
  public void setStory(Story story) {
    this.story = story;
  }

  /**
   * This function returns the title of the book
   *
   * @return The title of the book.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the passage.
   *
   * @param title the title of the passage.
   * @throws IllegalArgumentException if the title is null or empty/blank.
   */
  public void setTitle(String title) {
    if (title == null || title.isBlank() || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or empty/blank");
    }
    this.title = title;
  }

  /**
   * This function returns the content of the message.
   *
   * @return The content of the message.
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the content of the passage.
   *
   * @param content the content of the passage.
   * @throws IllegalArgumentException if the content is null or empty/blank.
   */
  public void setContent(String content) {
    if (content == null || content.isBlank() || content.isEmpty()) {
      throw new IllegalArgumentException("Content cannot be null or empty/blank");
    }
    this.content = content;
  }

  /**
   * Adds a link to the list of links.
   *
   * @param link The link to be added to the list.
   * @throws IllegalArgumentException if the link is null or already exists.
   */
  public void addLink(Link link) {
    if (link == null) {
      throw new IllegalArgumentException("Link cannot be null");
    }
    if (links.contains(link)) {
      throw new IllegalArgumentException("Link already exists");
    }

    links.add(link);
  }

  /**
   * This function returns a list of links.
   *
   * @return A list of links.
   */
  public List<Link> getLinks() {
    return links;
  }

  /**
   * Sets the list of links.
   *
   * @param links the list of links.
   * @throws IllegalArgumentException if the list of links is null or contains null values. Also
   *     throws an exception if the list of links is empty.
   */
  public void setLinks(List<Link> links) {
    if (links == null) {
      throw new IllegalArgumentException("Links cannot be null");
    }
    for (Link link : links) {
      if (link == null) {
        throw new IllegalArgumentException("Link cannot be null");
      }
    }
    if (links.isEmpty()) {
      throw new IllegalArgumentException("Links cannot be empty");
    }
    this.links.clear();
    for (Link link : links) {
      addLink(link);
    }
  }

  /**
   * Returns true if the list of links is not empty.
   *
   * @return A boolean value.
   */
  public boolean hasLinks() {
    return !links.isEmpty();
  }

  /**
   * Returns a string representation of the passage.
   *
   * @return A string representation of the passage.
   */
  @Override
  public String toString() {
    return "Passage{" + "title='" + title + '\'' + ", content='" + content + '}';
  }

  /**
   * Returns the id of the passage. Used for DB.
   *
   * @return the id of the passage.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id of the passage. Used for DB.
   *
   * @param id the id of the passage.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Removes a link from the list of links.
   *
   * @param deadLink The link to be removed.
   * @throws IllegalArgumentException if the link is not found in the list of links or if the link
   *     is null.
   */
  public void removeLink(Link deadLink) {
    if (deadLink == null) {
      throw new IllegalArgumentException("Link cannot be null");
    }
    if (!links.contains(deadLink)) {
      throw new IllegalArgumentException("Link not found");
    }

    links.remove(deadLink);
  }
}
