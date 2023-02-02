package no.ntnu.idatg2001;

import java.util.ArrayList;
import java.util.List;

public class Link {
  private String text;
  private String reference;
  private List<Action> actions;

  /**
   *
   * @param text
   * @param reference
   */
  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
    this.actions = new ArrayList<>();
  }

  /**
   *
   * @return
   */
  public String getText() {
    return text;
  }

  public String getReference() {
    return reference;
  }

  public void addAction(Action action) {
    actions.add(action);
  }
/**
  public List<Action> getActions() {
    return this.actions;
  }
  public void getActions(){

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public boolean equals(Object object){
    return super.equals(object);
  }*/
}
