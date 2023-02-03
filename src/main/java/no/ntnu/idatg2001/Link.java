package no.ntnu.idatg2001;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that makes it possible to move from one passage to another, by using links between the
 * different parts of the story's plot.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
public class Link {
  private String text;
  private String reference;
  private List<Action> actions;

  /**
   * A constructor that initializes the declared fields for text, reference and actions.
   *
   * @param text A string value that indicates the choices the player can make at a certain point
   *            in the story.
   * @param reference A string value that represents a unique passage identifier.
   */
  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
    this.actions = new ArrayList<>();
  }

  /**
   * An accessor method that returns the text field's String value.
   *
   * @return the player's choices.
   */
  public String getText() {
    return text;
  }

  /**
   * An accessor method that returns the reference field's string value.
   *
   * @return the string identifier for the chosen passage.
   */
  public String getReference() {
    return reference;
  }

  /**
   * A method that adds a action to the actions list.
   *
   * @param action Represents the action that gets added to the list of actions.
   */
  public void addAction(Action action) {
    actions.add(action);
  }

  /**
   * A method that returns the actions the makes it possible to influence the player's attributes.
   *
   * @return the actions in the actions list.
   */
  public List<Action> getActions() {
    return actions;
  }

  /**
   *
   * @return
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   *
   * @return
   */
  @Override
  public String toString() {
    return super.toString();
  }

  /**
   *
   * @param object
   * @return
   */
  @Override
  public boolean equals(Object object){
    return super.equals(object);
  }

}
