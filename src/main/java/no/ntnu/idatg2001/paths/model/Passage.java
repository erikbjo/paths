package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
  private Long id;

  
  
  /**
   * A constructor that initializes the declared fields for title, content and links.
   *
   * @param title the title of the passage, also works as a unique identifier.
   * @param content the content of the passage, typically a part of the story.
   */
  public Passage(String title, String content) {
    this.title = title;
    this.content = content;
    this.links = new ArrayList<>();
  }

  public Passage() {}

  /**
   * This function returns the title of the book
   *
   * @return The title of the book.
   */
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
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

  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Adds a link to an arrayList and return true.
   *
   * @param link The link to be added to the list.
   * @return A boolean value.
   */
  public boolean addLink(Link link) {
    boolean success = false;
    try {
      links.add(link);
      success = true;
    } catch (Exception e) {
      // TODO: replace sout
      System.out.println("Error: " + e.getMessage());
    }
    return success;
  }

  /**
   * This function returns a list of links.
   *
   * @return A list of links.
   */
  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
    /**
     * if (links != null && links.size() >= 2) { this.links = new ArrayList<>(links.subList(0, 2));
     * } else { this.links = links; }
     */
  }

  /**
   * Returns true if the list of links is not empty.
   *
   * @return A boolean value.
   */
  public boolean hasLinks() {
    return !links.isEmpty();
  }

  @Override
  public String toString() {
    return "Passage{" + "title='" + title + '\'' + ", content='" + content + '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  // TODO: implement equals and hashCode
  //  @Override
  //  public boolean equals(Object object) {
  //    if (this == object) return true;
  //    if (object == null || getClass() != object.getClass()) return false;
  //    Passage passage = (Passage) object;
  //    return Objects.equals(id, passage.id);
  //  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
