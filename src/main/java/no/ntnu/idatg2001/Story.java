package no.ntnu.idatg2001;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
     * This function returns the title of the book
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }


    /**
     * This function returns the opening passage of the story.
     *
     * @return The opening passage of the game.
     */
    public Passage getOpeningPassage() {
        return openingPassage;
    }


    /**
     * This function adds a passage to the game
     *
     * @param passage The passage that you want to add to the story.
     */
    public void addPassage(Passage passage) {
        this.openingPassage = passage;
        Link link = new Link(passage.getTitle(), passage.getTitle());
        passages.put(link,openingPassage);
    }


   public Passage getPassage(Link link) {
        Passage passage = new Passage(link.getReference(),"Where do you want to start?");
        return passage;
    }

/**
   public Collection<Passage> getPassages() {
       return new ArrayList<>();
    }*/
}
