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
  private final Text passageTitleText;
  private final TextArea passageContentTextArea;

  public StoryViewController(
      HBox linksHBox,
      Text storyHeadlineText,
      Text passageTitleText,
      TextArea passageContentTextArea) {
    this.linksHBox = linksHBox;
    this.storyHeadlineText = storyHeadlineText;
    this.passageTitleText = passageTitleText;
    this.passageContentTextArea = passageContentTextArea;
  }

  private void updateLinksHBox() {
    linksHBox.getChildren().clear();
    List<Link> links =
        Database.getCurrentGame()
            .getStory()
            .getLinksConnectedWithPassage(Database.getCurrentGame().getStory().getCurrentPassage());
    System.out.println(
        "links connected to "
            + Database.getCurrentGame().getStory().getCurrentPassage()
            + ": "
            + links);
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

  private void setStoryHeadlineText() {
    storyHeadlineText.setText(Database.getCurrentGame().getStory().getTitle());
  }

  private void setPassageTitleText() {
    passageTitleText.setText(Database.getCurrentGame().getStory().getCurrentPassage().getTitle());
  }

  private void setPassageContentTextArea() {
    passageContentTextArea.setText(
        Database.getCurrentGame().getStory().getCurrentPassage().getContent());
  }

  public void updateStoryViewToNewPath() {
    setStoryHeadlineText();
    setPassageTitleText();
    setPassageContentTextArea();
    updateLinksHBox();
  }
}
