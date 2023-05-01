package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.List;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.model.Database;
import no.ntnu.idatg2001.paths.model.Link;

public class StoryViewController {
  private final HBox linksHBox;
  private final Text storyHeadlineText;
  private final TextArea storyTextArea;

  public StoryViewController(HBox linksHBox, Text storyHeadlineText, TextArea storyTextArea) {
    this.linksHBox = linksHBox;
    this.storyHeadlineText = storyHeadlineText;
    this.storyTextArea = storyTextArea;
  }

  public void updateLinksHBox() {
    linksHBox.getChildren().clear();
    List<Link> links =
        Database.getCurrentGame()
            .getStory()
            .getLinksConnectedWithPassage(Database.getCurrentGame().getStory().getCurrentPassage());
    for (Link link : links) {
      Hyperlink hyperlink = new Hyperlink(link.getText());
      hyperlink.setOnAction(
          event -> {
            Database.getCurrentGame().go(link);
            updateStoryViewToNewPath();
          });
      linksHBox.getChildren().add(hyperlink);
    }
  }

  public void setStoryHeadlineText() {
    storyHeadlineText.setText(Database.getCurrentGame().getStory().getTitle());
  }

  public void setStoryTextArea() {
    storyTextArea.setText(Database.getCurrentGame().getStory().getCurrentPassage().getContent());
  }

  private void updateStoryViewToNewPath() {
    setStoryHeadlineText();
    setStoryTextArea();
    updateLinksHBox();
  }
}
