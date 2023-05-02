package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;
import java.util.*;

/**
 * The Story class is a container for the story. It contains the title of the story, a map of all
 * the passages in the story, and the opening passage.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
@Entity
@Table(name = "story")
public class Story {
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "story_id")
  private HashMap<Link, Passage> passages;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "story_id")
  private Passage openingPassage;

  private String title;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "story_id")
  private Passage currentPassage;

  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.currentPassage = openingPassage;
    this.passages = new HashMap<>();
    addPassage(openingPassage);
  }

  public Story() {}

  public Passage getCurrentPassage() {
    return currentPassage;
  }

  public void setCurrentPassage(Passage passage) {
    this.currentPassage = passage;
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
   * This function returns the opening passage of the game.
   *
   * @return The opening passage of the game.
   */
  public Passage getOpeningPassage() {
    return openingPassage;
  }

  /**
   * This function adds a passage to the story
   *
   * @param passage The passage that gets added to the game.
   */
  public void addPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null.");
    }

    List<Link> links = passage.getLinks();
    for (Link link : links) {
      if (link == null) {
        throw new IllegalArgumentException("Dead link.");
      }

      passages.put(link, passage);
    }
  }

  public Map<Link, Passage> getPassagesHashMap() {
    return passages;
  }

  /**
   * This function returns a list of all the links connected to a passage.
   *
   * @return A list of all the links connected to a passage.
   */
  public List<Link> getLinksConnectedWithPassage(Passage passage) {
    List<Link> links = new ArrayList<>();

    for (Map.Entry<Link, Passage> entry : passages.entrySet()) {
      if (Objects.equals(entry.getValue(), passage)) {
        links.add(entry.getKey());
      }
    }

    return links;
  }

  /**
   * This function returns a list of all the passages connected to a link.
   *
   * @return A list of all the passages connected to a link.
   */
  public List<Passage> getPassagesConnectedWithLink(Link link) {
    List<Passage> passageList = new ArrayList<>(List.of(passages.get(link)));

    return passageList;
  }

  // Endre denne metoden til å ta en Story istedenfor en link, for å gjøre at passasjene ikke
  // kommer hulter til bulter. Pga hashMap ikke sorterer slik som arrayList.

  // gammel version
  /**
   * public Collection<Passage> getPassages() { Collection<Passage> passageCollection = new
   * HashSet<>(); for (Link link : passages.keySet()) { Passage passage = passages.get(link); if
   * (passage != null) { passageCollection.add(passage); } } return passageCollection; }
   */

  // Ny version 1

  /**
   * public Collection<Passage> getPassages() { Collection<Passage> passageCollection = new
   * HashSet<>(); for (Passage passage : passages.values()) { if (passage != null) {
   * passageCollection.add(passage); } } return passageCollection; }
   */
  public List<Passage> getPassages() {
    return passages.values().stream().toList();
  }

  public void removePassage(Link link) {
    boolean isLinkedToAPassage =
        passages.values().stream().anyMatch(passage -> passage.getLinks().contains(link));

    List<Passage> passagesToRemove = getPassagesConnectedWithLink(link);

    if (isLinkedToAPassage) {
      passagesToRemove.forEach(passage -> passages.remove(link));
    }
  }

  public List<Link> getBrokenLinks() {
    return passages.keySet().stream().filter(link -> passages.get(link) == null).toList();
  }

  @Override
  public String toString() {
    return "Story{"
        + "title='"
        + title
        + '\''
        + ", passages="
        + passages
        + ", openingPassage="
        + openingPassage
        + ", currentPassage="
        + currentPassage
        + '}';
  }

  public String getId() {
    return id;
  }

  // WIZARD ZONE

  private HashMap<Link, Passage[]> createHashMapFromLists(List<Link> keys, List<Passage[]> values) {
    if (keys == null || values == null) {
      throw new IllegalArgumentException("Input parameters cannot be null.");
    }

    if (keys.size() != values.size()) {
      throw new IllegalArgumentException("Both lists should have the same size.");
    }

    HashMap<Link, Passage[]> hashMap = new HashMap<>();
    for (int i = 0; i < keys.size(); i++) {
      hashMap.put(keys.get(i), values.get(i));
    }

    return hashMap;
  }

  private void breakDownHashMap(
      HashMap<Link, Passage[]> hashMap, List<Link> keys, List<Passage[]> values) {
    if (hashMap == null || keys == null || values == null) {
      throw new IllegalArgumentException("Input parameters cannot be null.");
    }

    keys.clear();
    values.clear();

    for (Map.Entry<Link, Passage[]> entry : hashMap.entrySet()) {
      keys.add(entry.getKey());
      values.add(entry.getValue());
    }
  }
}
