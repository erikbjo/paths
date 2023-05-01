package no.ntnu.idatg2001.paths.model;

import java.util.*;

/**
 * The Story class is a container for the story. It contains the title of the story, a map of all
 * the passages in the story, and the opening passage.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
public class Story {
  private final String title;
  private final Map<Link, Passage[]> passages;
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
    }

    List<Link> links = passage.getLinks();
    for (Link link : links) {
      if (passages.containsKey(link)) {
        Passage[] oldPassagesArray = passages.get(link);

        if (Arrays.asList(oldPassagesArray).contains(passage)) {
          throw new IllegalArgumentException("Passage already exists.");
        }

        Passage[] updatedPassagesArray = Arrays.copyOf(oldPassagesArray, oldPassagesArray.length + 1);
        updatedPassagesArray[updatedPassagesArray.length - 1] = passage;
        passages.put(link, updatedPassagesArray);
      } else {
        passages.put(link, new Passage[] {passage});
      }
    }
  }

  public Map<Link, Passage[]> getPassagesHashMap() {
    return passages;
  }

  /**
   * This function returns a list of all the links connected to a passage.
   *
   * @return A list of all the links connected to a passage.
   */
  public List<Link> getLinksConnectedWithPassage(Passage passage) {
    List<Link> links = new ArrayList<>();

    for (Map.Entry<Link, Passage[]> entry : passages.entrySet()) {
      if (Arrays.asList(entry.getValue()).contains(passage)) {
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
    return passages.values().stream().flatMap(Arrays::stream).distinct().toList();
  }

  public void removePassage(Link link) {
    boolean isLinkedToAPassage =
        passages.keySet().stream()
            .filter(key -> !key.equals(link))
            .anyMatch(
                key ->
                    Arrays.stream(passages.get(key))
                        .map(Passage::getLinks)
                        .flatMap(List::stream)
                        .anyMatch(l -> l.getReference().equals(link.getReference())));

    if (!isLinkedToAPassage) {
      Passage[] existingPassages = passages.get(link);
      if (existingPassages != null) {
        List<Passage> passageList =
            Arrays.stream(existingPassages)
                .filter(passage -> !passage.getLinks().contains(link))
                .toList();

        if (passageList.isEmpty()) {
          passages.remove(link);
        } else {
          passages.put(link, passageList.toArray(new Passage[0]));
        }
      }
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
}
