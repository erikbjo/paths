package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;

@Entity
@Table(name = "link_passage")
public class LinkPassage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "link_id")
  private Link link;

  @ManyToOne
  @JoinColumn(name = "passage_id")
  private Passage passage;

  public LinkPassage(Link link, Passage passage) {
    this.link = link;
    this.passage = passage;
  }

  protected LinkPassage() {}

  public Link getLink() {
    return link;
  }

  public void setLink(Link link) {
    this.link = link;
  }

  public Passage getPassage() {
    return passage;
  }

  public void setPassage(Passage passage) {
    this.passage = passage;
  }
}
