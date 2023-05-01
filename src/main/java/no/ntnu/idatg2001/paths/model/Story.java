package no.ntnu.idatg2001.paths.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Story class is a container for the story. It contains the title of the story, a map of all
 * the passages in the story, and the opening passage.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
public class Story {
  private final String title;
  private final Map<Link, Passage> passages;
  private final Passage openingPassage;
  private Passage currentPassage;

  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.currentPassage = openingPassage;
    this.passages = new HashMap<>();
    addPassage(openingPassage);
  }

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
   * This function adds a passage to the game
   *
   * @param passage The passage that gets added to the game.
   */
  public void addPassage(Passage passage) {
    if (passage == null) {
      throw new IllegalArgumentException("Passage cannot be null.");
    } else if (passages.containsValue(passage)) {
      throw new IllegalArgumentException("Passage already exists in story.");
    }
    List<Link> links = passage.getLinks();
    for (Link link : links) {
      passages.put(link, passage);
    }
  }

  public Passage getPassage(Link link) {
    return passages.get(link);
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
    return passages.entrySet().stream()
        .filter(entry -> entry.getValue().equals(passage))
        .map(Map.Entry::getKey)
        .toList();
  }

  /**
   * This function returns a list of all the passages connected to a link.
   *
   * @return A list of all the passages connected to a link.
   */
  public List<Passage> getPassagesConnectedWithLink(Link link) {
    List<Passage> passageList = new ArrayList<>();

    for (Link link1 : passages.keySet()) {
      if (link1.equals(link)) {
        passageList.add(passages.get(link1));
      }
    }

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

  // Ny version 2
  public List<Passage> getPassages() {
    List<Passage> passageList = new ArrayList<>();
    for (Passage passage : passages.values()) {
      if (passage != null && !passageList.contains(passage)) {
        passageList.add(passage);
      }
    }

    return passageList;
  }

  public void removePassage(Link link) {
    boolean isLinkedToAPassage =
        passages.keySet().stream()
            .filter(key -> !key.equals(link))
            .anyMatch(
                key ->
                    passages.get(key).getLinks().stream()
                        .anyMatch(l -> l.getReference().equals(link.getReference())));
    if (!isLinkedToAPassage) {
      passages.remove(link);
    }
  }

  public List<Link> getBrokenLinks() {
    return passages.keySet().stream()
        .filter(link -> passages.get(link) == null)
        .collect(Collectors.toList());
  }
}
