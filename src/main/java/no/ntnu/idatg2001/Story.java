package no.ntnu.idatg2001;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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

    /**
     *
     * @param title
     * @param openingPassage
     */
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
        passages.put(link,openingPassage);
    }

    public void removePassage(Link link) {
        // if passasjen ikke har noen link til seg -> remove
        if (checkIfPassageHasLink())
        passages.remove(link,openingPassage);
    }

   public Passage getPassage(Link link) {
       return passages.get(link);
    }

    public Collection<Passage> getPassages() {
        Collection<Passage> passageCollection = new HashSet<>();
        for (Link link: passages.keySet()) {
            Passage passage = passages.get(link);
            if (passage != null){
                passageCollection.add(passage);
            }
        }
        return passageCollection;
    }

    public boolean checkIfLinkIsDead(Link link){
        link.getReference();
    }
}
