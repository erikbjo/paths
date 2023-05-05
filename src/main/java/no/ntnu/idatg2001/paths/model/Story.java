package no.ntnu.idatg2001.paths.model;

import jakarta.persistence.*;
import java.io.*;
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
public class Story implements Serializable {
  @Transient private Map<Link, Passage[]> passages;

  @Lob
  @Column(name = "serialized_passages")
  private byte[] serializedPassages;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "story_id")
  private Passage openingPassage;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "story_id", insertable = false, updatable = false)
  private Passage currentPassage;

  private String title;

  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.currentPassage = openingPassage;
    this.passages = new HashMap<>();
    addPassage(openingPassage);
  }

  public Story(String title) {
    this.title = title;
    this.passages = new HashMap<>();
  }

  public Story() {}

  // WIP
  @PrePersist
  @PreUpdate
  private void savePassagesAsByteArray() throws IOException {
    if (passages != null) {
      try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
          ObjectOutputStream oos = new ObjectOutputStream(baos)) {
        oos.writeObject(passages);
        oos.flush();
        serializedPassages = baos.toByteArray();
      }
    }
  }

  @PostLoad
  private void loadPassagesFromByteArray() throws IOException, ClassNotFoundException {
    if (serializedPassages != null) {
      try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedPassages);
          ObjectInputStream ois = new ObjectInputStream(bais)) {
        passages = (Map<Link, Passage[]>) ois.readObject();
      }
    } else {
      passages = new HashMap<>();
    }
  }

  public List<Passage> getAllPassages() {
    List<Passage> allPassages = new ArrayList<>();

    for (Passage[] passageArray : passages.values()) {
      for (Passage passage : passageArray) {
        if (passage != null && !allPassages.contains(passage)) {
          allPassages.add(passage);
        }
      }
    }

    return allPassages;
  }

  public Map<Link, Passage[]> getPassagesHashMap() {
    return passages;
  }

  public void setPassagesHashMap(Map<Link, Passage[]> passages) {
    this.passages = passages;
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

  public void setTitle(String text) {
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
   * This function adds a passage to the story
   *
   * @param passage The passage that gets added to the game.
   */
  public void addPassage(Passage passage) {
    List<Link> links = passage.getLinks();

    for (Link link : links) {
      if (!passages.containsKey(link)) {
        passages.put(link, new Passage[] {passage});
      } else if (passages.containsKey(link) && (passages.get(link).length < 2)) {
        Passage[] oldPassagesArray = passages.get(link);

        if (oldPassagesArray.length >= 2) {
          throw new IllegalArgumentException("Link already has two passages.");
        }
        if (oldPassagesArray[0].equals(passage)) {
          throw new IllegalArgumentException("Link already has this passage.");
        }

        Passage[] updatedPassagesArray =
            Arrays.copyOf(oldPassagesArray, oldPassagesArray.length + 1);
        updatedPassagesArray[updatedPassagesArray.length - 1] = passage;
        passages.put(link, updatedPassagesArray);
      } else if (Arrays.asList(passages.get(link)).contains(passage)) {
        // do nothing
      } else {
        throw new IllegalArgumentException("Link already has two passages.");
      }
    }
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
   * This function returns a list of all the passages connected to a link.
   *
   * @return A list of all the passages connected to a link.
   */
  public List<Passage> getPassagesConnectedWithLink(Link link) {
    return new ArrayList<>(List.of(passages.get(link)));
  }

  /**
   * public Collection<Passage> getPassages() { Collection<Passage> passageCollection = new
   * HashSet<>(); for (Passage passage : passages.values()) { if (passage != null) {
   * passageCollection.add(passage); } } return passageCollection; }
   */
  public List<Passage> getPassages() {
    return passages.values().stream().flatMap(Arrays::stream).distinct().toList();
  }

  public void removePassage(Link link) {
    passages.remove(link);
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

  public Long getId() {
    return id;
  }

  public void setStartingPassage(Passage startingPassage) {
    this.openingPassage = startingPassage;
  }
}
