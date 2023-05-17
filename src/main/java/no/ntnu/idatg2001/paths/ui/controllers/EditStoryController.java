package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.*;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.storyvisualizers.LinkLine;
import no.ntnu.idatg2001.paths.ui.storyvisualizers.PassagePane;
import no.ntnu.idatg2001.paths.ui.views.EditStoryView;

public class EditStoryController implements Controller {

  private final Map<Link, Passage> storyMap;
  private final Story story;
  private final Stage stage;
  private final EditStoryView view;

  public EditStoryController(Stage stage, Story story) {
    this.stage = stage;
    this.story = story;
    this.storyMap = story.getPassagesHashMap();
    this.view = new EditStoryView(this, stage, story);
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> view.updateLanguage());
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  public void visualizeHashMap(Pane pane) {
    HashMap<Passage, PassagePane> passagePanes = new HashMap<>();
    int gap = 200; // Gap between nodes

    Queue<PassagePane> queue = new LinkedList<>();

    PassagePane openingPane = new PassagePane(story.getOpeningPassage());
    openingPane.setLayoutX(10);
    openingPane.setLayoutY(10);
    passagePanes.put(story.getOpeningPassage(), openingPane);
    queue.add(openingPane);
    pane.getChildren().add(openingPane);

    while (!queue.isEmpty()) {
      PassagePane currentPane = queue.remove();
      List<Link> links = currentPane.getPassage().getLinks();

      int offsetX = gap;

      for (Link link : links) {
        List<Passage> linkedPassages = story.getPassagesConnectedWithLink(link);

        for (Passage passage : linkedPassages) {
          // If the passage is not yet drawn
          if (!passagePanes.containsKey(passage)) {
            PassagePane linkedPane = new PassagePane(passage);
            linkedPane.setLayoutX(currentPane.getLayoutX() + offsetX);
            linkedPane.setLayoutY(currentPane.getLayoutY() + gap);
            passagePanes.put(passage, linkedPane);
            queue.add(linkedPane);
            pane.getChildren().add(linkedPane);

            LinkLine linkLine = new LinkLine(link, currentPane, linkedPane);
            pane.getChildren().add(linkLine);
            linkLine.toBack();
          } else {
            LinkLine linkLine = new LinkLine(link, currentPane, passagePanes.get(passage));
            pane.getChildren().add(linkLine);
            linkLine.toBack();
          }
          offsetX += gap;
        }
      }
    }
  }
}
