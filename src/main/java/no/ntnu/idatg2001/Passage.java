package no.ntnu.idatg2001;

import java.util.List;

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
    public Passage(String title, String content){
        this.title = title;
        this.content = content;
    }

    /**
     *
     * @return
     */
    public String getTitle()
    {
        return title;
    }

    /**
     *
     * @return
     */
    public String getContent(){
        return content;
    }

    /**
    public boolean addLinks(Link link){

    }

    public List<Link> getLinks(){
        return links;
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
