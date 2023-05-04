package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.List;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.model.Link;

public class StoryViewController {
  private final HBox linksHBox;
  private final Text storyHeadlineText;
  private final Text passageTitleText;
  private final TextArea passageContentTextArea;
  private Link previousLink = null;

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
        CurrentGameHandler.getCurrentGame()
            .getStory()
            .getLinksConnectedWithPassage(CurrentGameHandler.getCurrentGame().getStory().getCurrentPassage());
    for (Link link : links) {
      Hyperlink hyperlink = new Hyperlink();

      if (link == previousLink) {
        hyperlink.setVisited(true);
        if (CurrentGameHandler.getCurrentGame().getStory().getCurrentPassage()
            == CurrentGameHandler.getCurrentGame().getStory().getOpeningPassage()) {
          hyperlink.setText(link.getText());
        } else {
          hyperlink.setText("Go back");
        }
      } else {
        hyperlink.setVisited(false);
        hyperlink.setText(link.getText());
      }

      hyperlink.setOnAction(
          event -> {
            CurrentGameHandler.getCurrentGame().go(link);
            previousLink = link;
            updateStoryViewToNewPath();
          });
      linksHBox.getChildren().add(hyperlink);
    }
  }

  private void setStoryHeadlineText() {
    storyHeadlineText.setText(CurrentGameHandler.getCurrentGame().getStory().getTitle());
  }

  private void setPassageTitleText() {
    passageTitleText.setText(CurrentGameHandler.getCurrentGame().getStory().getCurrentPassage().getTitle());
  }

  private void setPassageContentTextArea() {
    passageContentTextArea.setText(
        CurrentGameHandler.getCurrentGame().getStory().getCurrentPassage().getContent());
  }

  public void updateStoryViewToNewPath() {
    setStoryHeadlineText();
    setPassageTitleText();
    setPassageContentTextArea();
    updateLinksHBox();
  }
}
