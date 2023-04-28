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
    private String title;
    private Map<Link, Passage> passages;
    private Passage openingPassage;

    public Story(String title, Passage openingPassage) {
        this.title = title;
        this.openingPassage = openingPassage;
        this.passages = new HashMap<>();
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
        this.openingPassage = passage;
        Link link = new Link(passage.getTitle(), passage.getTitle());
        passages.put(link, openingPassage);
    }

    public Passage getPassage(Link link) {
        return passages.get(link);
    }

    //Endre denne metoden til å ta en Story istedenfor en link, for å gjøre at passasjene ikke
    //kommer hulter til bulter. Pga hashMap ikke sorterer slik som arrayList.

    //gammel version
    /**
     * public Collection<Passage> getPassages() {
     * Collection<Passage> passageCollection = new HashSet<>();
     * for (Link link : passages.keySet()) {
     * Passage passage = passages.get(link);
     * if (passage != null) {
     * passageCollection.add(passage);
     * }
     * }
     * return passageCollection;
     * }
     */

    //Ny version 1

    /**
     * public Collection<Passage> getPassages() {
     * Collection<Passage> passageCollection = new HashSet<>();
     * for (Passage passage : passages.values()) {
     * if (passage != null) {
     * passageCollection.add(passage);
     * }
     * }
     * return passageCollection;
     * }
     */

    //Ny version 2
    public List<Passage> getPassages() {
        List<Passage> passageList = new ArrayList<>();
        for (Link link : passages.keySet()) {
            Passage passage = passages.get(link);
            if (passage != null) {
                passageList.add(passage);
            }
        }
        return passageList;
    }

    public void removePassage(Link link) {
        boolean isLinkedToAPassage = passages.keySet().stream()
            .filter(key -> !key.equals(link))
            .anyMatch(key -> passages.get(key).getLinks().stream()
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
