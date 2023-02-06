package no.ntnu.idatg2001;

import java.util.List;

/**
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
public class Passage
{
    private String title;
    private String content;
    private List<Link> links;

    /**
     *
     * @param title
     * @param content
     */
    public Passage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }


    public boolean addLinks(Link link) {
        if(links.size() >= 2) {
            links.add(link);
            return true;
        } else {
            return false;
        }
    }

    /**
    public List<Link> getLinks(){
        links.get();
    }

    public boolean hasLinks(){

    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object object)
    {
        return super.equals(object);
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
    */
}
