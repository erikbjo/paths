package no.ntnu.idatg2001.paths.ui.controllers;

import java.io.IOException;
import java.util.List;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.ui.alerts.ExceptionAlert;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.GameView;

public class GameController implements Controller {
  private final Stage stage;
  private final GameView view;
  private Link previousLink = null;

  public GameController(Stage stage) {
    this.stage = stage;
    this.view = new GameView(this, stage);
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> view.updateLanguage());
  }

  private void updateLinksHBox(
      Text storyHeadlineText,
      Text passageTitleText,
      TextArea passageContentTextArea,
      HBox linksHBox) {
    linksHBox.getChildren().clear();
    List<Link> links = CurrentGameHandler.getCurrentGame().getCurrentPassage().getLinks();
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
            try {
              CurrentGameHandler.getCurrentGame().go(link);
            } catch (Exception e) {
              new ExceptionAlert(e).showAndWait();
            }
            view.updatePlayerInformation();
            view.updateGoalInformation();
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
    passageTitleText.setText(CurrentGameHandler.getCurrentGame().getCurrentPassage().getTitle());
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

  @Override
  public Stage getStage() {
    return stage;
  }

  public void finishGame() {
    saveGame();
    GameDAO.getInstance().remove(CurrentGameHandler.getCurrentGame());
    try {
      new MainMenuController(stage);
    } catch (IOException e) {
      new ExceptionAlert(e).showAndWait();
      new SelectGameToContinueController(stage);
    }
  }

  public void saveGame() {
    GameDAO.getInstance().update(CurrentGameHandler.getCurrentGame());
    PlayerDAO.getInstance().update(CurrentGameHandler.getCurrentGame().getPlayer());
  }

  public void restartGame() {
    CurrentGameHandler.getCurrentGame().restart();
    // Creates a new controller instead of having lots of parameters in the method
    // Hopefully the garbage collector will remove the old controller
    new GameController(stage);
  }
}
