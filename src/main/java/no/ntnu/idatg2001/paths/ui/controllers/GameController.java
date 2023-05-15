package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.List;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.GameView;

public class GameController {
  private final Stage stage;
  private final GameView view;
  private Link previousLink = null;

  public GameController(Stage stage) {
    this.stage = stage;
    this.view = new GameView(this, stage);
    LanguageHandler.getObservableIntegerCounter()
            .addListener((a, b, c) -> view.updateLanguage());
  }

  private void updateLinksHBox(
      Text storyHeadlineText,
      Text passageTitleText,
      TextArea passageContentTextArea,
      HBox linksHBox) {
    linksHBox.getChildren().clear();
    List<Link> links =
        CurrentGameHandler.getCurrentGame()
            .getStory()
            .getLinksConnectedWithPassage(
                CurrentGameHandler.getCurrentGame().getCurrentPassage());
    for (Link link : links) {
      Hyperlink hyperlink = new Hyperlink();

      if (link == previousLink) {
        hyperlink.setVisited(true);
        if (CurrentGameHandler.getCurrentGame().getCurrentPassage()
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
            updateStoryViewToNewPath(
                storyHeadlineText, passageTitleText, passageContentTextArea, linksHBox);
          });
      linksHBox.getChildren().add(hyperlink);
    }
  }

  private void setStoryHeadlineText(Text storyHeadlineText) {
    storyHeadlineText.setText(CurrentGameHandler.getCurrentGame().getStory().getTitle());
  }

  private void setPassageTitleText(Text passageTitleText) {
    passageTitleText.setText(
        CurrentGameHandler.getCurrentGame().getStory().getTitle());
  }

  private void setPassageContentTextArea(TextArea passageContentTextArea) {
    passageContentTextArea.setText(
        CurrentGameHandler.getCurrentGame().getCurrentPassage().getContent());
  }

  public void updateStoryViewToNewPath(
      Text storyHeadlineText,
      Text passageTitleText,
      TextArea passageContentTextArea,
      HBox linksHBox) {
    setStoryHeadlineText(storyHeadlineText);
    setPassageTitleText(passageTitleText);
    setPassageContentTextArea(passageContentTextArea);
    updateLinksHBox(storyHeadlineText, passageTitleText, passageContentTextArea, linksHBox);
  }
}
