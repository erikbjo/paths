package no.ntnu.idatg2001.model;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatg2001.model.actions.Action;

/**
 * A class that makes it possible to move from one passage to another, by using links between the
 * different parts of the story's plot.
 *
 * @author Erik Bjørnsen and Emil Klevgård-Slåttsveen
 * @version 2023.02.02
 */
public class Link {
    private final String text;
    private final String reference;
    private final List<Action> actions;

    private final List<PathActions> pathActions;

    /**
     * A constructor that initializes the declared fields for text, reference and actions.
     *
     * @param text      A string value that indicates the choices the player can make at a certain point in
     *                  the story.
     * @param reference A string value that represents a unique passage identifier.
     */
    public Link(String text, String reference) {
        this.text = text;
        this.reference = reference;
        this.actions = new ArrayList<>();
        this.pathActions = new ArrayList<>();
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

    public void addPathAction(PathActions pathAction) {
        pathActions.add(pathAction);
    }

    /**
     * A method that returns the actions the makes it possible to influence the player's attributes.
     *
     * @return the actions in the actions list.
     */
    public List<Action> getActions() {
        return actions;
    }

    public List<PathActions> getPathActions() {
        return pathActions;
    }

    @Override
    public final int hashCode() {
        int result = 17;
        // only using reference, because a passage can have multiple links
        if (reference != null) {
            result = reference.hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (!(o instanceof Link other)) {
        return false;
      }
        // checking only for reference
        return (this.getReference() == null && other.getReference() == null)
            || (this.getReference() != null && this.getReference().equals(other.getReference()));
    }
}
