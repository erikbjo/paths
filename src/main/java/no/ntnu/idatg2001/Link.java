package no.ntnu.idatg2001;

import java.util.List;

public class Link {
  private String text;
  private String reference;
  private List<Action> actions;

  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;

  }

  public String getText(){
    return text;
  }

  public String getReference(){
    return reference;
  }

  public void addAction(Action action){

  }
}
