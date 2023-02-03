package no.ntnu.idatg2001;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
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
    public Story(String title, Passage openingPassage){
        this.title = title;
        this.openingPassage = openingPassage;
        this.passages = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public String getTitle(){
        return title;
    }

    /**
     *
     * @return
     */
    public Passage getOpeningPassage(){
        return openingPassage;
    }

    /**
     *
     * @param passage
     */
    public void addPassage(Passage passage){
        this.openingPassage = passage;
        Link link = new Link("Where do you want to start?", "Start");
        passages.put(link,openingPassage);
    }


    /**
     *
     * @param link
     * @return
     */
   /** public Passage getPassage(Link link){
        return link;
    }

    /**
     *
     * @return
     */
   /** public Collection<Passage> getPassages(){
        passages.get();
    }

    */
}
