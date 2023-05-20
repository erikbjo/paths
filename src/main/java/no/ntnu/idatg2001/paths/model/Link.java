package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.paths.model.actions.Action;

/**
 * A class that makes it possible to move from one passage to another, by using links between the
 * different parts of the story's plot. It contains a list of actions, a text, a reference and a
 * story.
 *
 * @see Story
 * @see Action
 * @see Passage
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
@Entity
public class Link {
  @OneToMany(mappedBy = "link", cascade = CascadeType.ALL)
  private final List<Action> actions = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;
  private String reference;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "story_id")
  private Story story;

  /**
   * A constructor that initializes the declared fields for text, reference and actions.
   *
   * @param text A string value that indicates the choices the player can make at a certain point in
   *     the story.
   * @param reference A string value that represents a unique passage identifier.
   */
  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
  }

  /** Default constructor for the Link class. For DB */
  public Link() {}

  /**
   * Gets the story that the link belongs to. Used for DB.
   *
   * @return the story that the link belongs to.
   */
  public Story getStory() {
    return story;
  }

  /**
   * Sets the story that the link belongs to. Used for DB.
   *
   * @param story the story that the link belongs to.
   */
  public void setStory(Story story) {
    this.story = story;
  }

  /**
   * Gets the id of the link. Used for DB.
   *
   * @return the id of the link.
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id of the link. Used for DB.
   *
   * @param id the id of the link.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * An accessor method that returns the text field's String value.
   *
   * @return the player's choices.
   */
  public String getText() {
    return text;
  }

  /**
   * A mutator method that sets the text field's string value.
   *
   * @param text Represents the player's choices.
   * @throws IllegalArgumentException if the text is null or empty/blank.
   */
  public void setText(String text) {
    if (text == null || text.isEmpty() || text.isBlank()) {
      throw new IllegalArgumentException("Text cannot be null or empty");
    }
    this.text = text;
  }

  /**
   * An accessor method that returns the reference field's string value.
   *
   * @return the string identifier for the chosen passage.
   */
  public String getReference() {
    return reference;
  }

  /**
   * A mutator method that sets the reference field's string value.
   *
   * @param reference Represents the string identifier for the chosen passage.
   * @throws IllegalArgumentException if the reference is null or empty/blank.
   */
  public void setReference(String reference) {
    if (reference == null || reference.isEmpty() || reference.isBlank()) {
      throw new IllegalArgumentException("Reference cannot be null or empty");
    }
    this.reference = reference;
  }

  /**
   * A method that adds a action to the actions list.
   *
   * @param action Represents the action that gets added to the list of actions.
   * @throws IllegalArgumentException if the action is null.
   */
  public void addAction(Action action) {
    if (action == null) {
      throw new IllegalArgumentException("Action cannot be null");
    }
    action.setLink(this);
    actions.add(action);
  }

  /**
   * A method that returns the actions the makes it possible to influence the player's attributes.
   *
   * @return the actions in the actions list.
   */
  public List<Action> getActions() {
    return actions;
  }

  /**
   * A method that returns the hash code of the Link class. It uses the reference field to calculate
   * the hash code.
   *
   * @return the hash code of the Link class.
   */
  @Override
  public final int hashCode() {
    int result = 17;
    // only using reference, because a passage can have multiple links
    if (reference != null) {
      result = reference.hashCode();
    }
    return result;
  }

  /**
   * A method that returns a string representation of the Link class.
   *
   * @return a string representation of the Link class.
   */
  @Override
  public String toString() {
    return "Link{"
        + "text='"
        + text
        + '\''
        + ", reference='"
        + reference
        + '\''
        + ", actions="
        + actions
        + '}';
  }

  /**
   * A method that compares two Link objects. It checks if the objects are equal by comparing their
   * reference fields.
   *
   * @param object Represents the object that gets compared to the Link object.
   * @return true if the objects are equal, false if they are not.
   */
  @Override
  public boolean equals(Object object) {
    if (object == this) return true;
    if (!(object instanceof Link other)) return false;
    return (this.reference == null && other.reference == null)
        || (this.reference != null && this.reference.equals(other.reference));
  }
}
