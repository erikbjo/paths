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
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToMany(cascade = CascadeType.ALL)
  private Map<Link, Passage> passages = new HashMap<>();

  @OneToOne
  @JoinColumn(name = "opening_passage_id")
  private Passage openingPassage;

  @OneToOne
  @JoinColumn(name = "current_passage_id")
  private Passage currentPassage;


  private String title;

  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.currentPassage = openingPassage;
  }

  public Story(String title) {
    this.title = title;
    this.passages = new HashMap<>();
  }

  protected Story() {}

  public List<Passage> getAllPassages() {
    List<Passage> allPassages = new ArrayList<>();

    for (Map.Entry<Link, Passage> entry : passages.entrySet()) {
      allPassages.add(entry.getValue());
    }

    return allPassages;
  }

  public Map<Link, Passage> getPassagesHashMap() {
    return passages;
  }

  public void setPassagesHashMap(Map<Link, Passage> passages) {
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

  public void setOpeningPassage(Passage openingPassage) {
    this.openingPassage = openingPassage;
  }

  /**
   * This function adds a passage to the story
   *
   * @param passage The passage that gets added to the game.
   */

  // Metoden addPassage skal legge til en passasje i passages. Da trenger vi også et Link-objekt.
  // Dette løser vi ved å opprette en ny link basert på passasjens tittel. Tittelen kan fungere både
  // som tekst og referanse.

  // • links: linker som kobler denne passasjen mot andre passasjer. En passasje med to eller
  // flere linker gjør historien ikke-lineær.

  // Diagrammet viser at Link-klassen har tre attributter:
  // • text: en beskrivende tekstsom indikerer et valg eller en handling i en historie. Teksten
  // er den delen av linken som vil være synlig for spilleren.
  // • reference: en streng som entydig identifiserer en passasje (en del av en historie). I
  // praksis vil dette være tittelen til passasjen man ønsker å referere til.
  public void addPassage(Passage passage) {
    passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
  }

  /**
   * This function returns a list of all the links connected to a passage.
   *
   * @return A list of all the links connected to a passage.
   */
  public List<Link> getLinksConnectedWithPassage(Passage passage) {
    return passages.keySet().stream().filter(link -> passages.get(link).equals(passage)).toList();
  }

  /**
   * This function returns a list of all the passages connected to a link.
   *
   * @return A list of all the passages connected to a link.
   */
  public List<Passage> getPassagesConnectedWithLink(Link link) {
    return new ArrayList<>(List.of(passages.get(link)));
  }

  // Endre denne metoden til å ta en Story istedenfor en link, for å gjøre at passasjene ikke
  // kommer hulter til bulter. Pga hashMap ikke sorterer slik som arrayList.

  // gammel version
  //
  // public Collection<Passage> getPassages() { Collection<Passage> passageCollection = new
  // HashSet<>(); for (Link link : passages.keySet()) { Passage passage = passages.get(link); if
  // (passage != null) { passageCollection.add(passage); } } return passageCollection; }
  //

  // Ny version 1

  //
  // public Collection<Passage> getPassages() { Collection<Passage> passageCollection = new
  // HashSet<>(); for (Passage passage : passages.values()) { if (passage != null) {
  // passageCollection.add(passage); } } return passageCollection; }
  //
  public List<Passage> getPassages() {
    return passages.values().stream().filter(Objects::nonNull).toList();
  }

  public void removePassage(Link link) {
    passages.remove(link);
  }

  public List<Link> getBrokenLinks() {
    return passages.keySet().stream().filter(link -> passages.get(link) == null).toList();
  }

  @Override
  public String toString() {
    return title;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setStartingPassage(Passage startingPassage) {
    this.openingPassage = startingPassage;
  }
}
