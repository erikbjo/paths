package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.storyvisualizers.LinkLine;
import no.ntnu.idatg2001.paths.ui.storyvisualizers.PassagePane;

public class EditStoryController {

  private final Map<Link, Passage[]> storyMap;
  private final Story story;
  private final Stage stage;

  public EditStoryController(Stage stage, Story story) {
    this.stage = stage;
    this.story = story;
    storyMap = story.getPassagesHashMap();
  }

  public void visualizeHashMap(Pane pane) {
    HashMap<Passage, PassagePane> passagePanes = new HashMap<>();

    for (Passage[] passages : storyMap.values()) {
      for (Passage passage : passages) {
        if (!passagePanes.containsKey(passage)) {
          PassagePane passagePane = new PassagePane(passage);
          passagePanes.put(passage, passagePane);
          pane.getChildren().add(passagePane);
        }
      }
    }

    int x = 10;
    int y = 10;

    for (Map.Entry<Link, Passage[]> entry : storyMap.entrySet()) {
      PassagePane startPane = passagePanes.get(entry.getValue()[0]);
      PassagePane endPane = passagePanes.get(entry.getValue()[1]);

      startPane.setLayoutX(x);
      startPane.setLayoutY(y);

      endPane.setLayoutX(x + 250);
      endPane.setLayoutY(y);

      LinkLine linkLine = new LinkLine(entry.getKey(), startPane, endPane);
      pane.getChildren().addAll(linkLine);

      linkLine.toBack();

      y += 150;
    }
  }
}
