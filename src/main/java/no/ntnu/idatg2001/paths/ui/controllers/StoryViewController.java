package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.List;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import no.ntnu.idatg2001.paths.model.Database;
import no.ntnu.idatg2001.paths.model.Link;

public class StoryViewController {
  public void setLinksHBox(HBox linksHBox) {
    List<Link> links =
        Database.getCurrentGame()
            .getStory()
            .getLinksConnectedWithPassage(Database.getCurrentGame().getStory().getOpeningPassage());
    for (Link link : links) {
      Hyperlink hyperlink = new Hyperlink(link.getText());
      hyperlink.setOnAction(
          event -> Database.getCurrentGame().go(link));
      linksHBox.getChildren().add(hyperlink);
    }
  }
}
