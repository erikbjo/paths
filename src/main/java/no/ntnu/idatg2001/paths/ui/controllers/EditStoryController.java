package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.ui.dialogs.*;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.storyvisualizers.LinkLine;
import no.ntnu.idatg2001.paths.ui.storyvisualizers.PassagePane;
import no.ntnu.idatg2001.paths.ui.views.EditStoryView;

public class EditStoryController implements Controller {

  private final Map<Link, Passage> storyMap;
  private final Stage stage;
  private final EditStoryView view;
  private Story story;
  private Pane pane;
  private double dragInitialX;
  private double dragInitialY;
  private boolean dragMode = false;
  private boolean linkCreationMode = false;
  private HashMap<Passage, PassagePane> passagePanes;

  public EditStoryController(Stage stage, Story story) {
    this.stage = stage;
    this.story = story;
    this.storyMap = story.getPassagesHashMap();
    this.view = new EditStoryView(this, stage, story);
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> view.updateLanguage());
    setActionsForScene();
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  /**
   * Visualizes the story as panes and lines. The panes and lines are clickable and can be used to
   * edit them. Uses panes over canvas as it is easier to add click events to the objects.
   *
   * @param pane The pane to add the story to.
   */
  public void visualizeHashMap(Pane pane) {
    this.pane = pane;
    if (passagePanes != null) {
      passagePanes.clear();
    }
    passagePanes = new HashMap<>();

    Queue<PassagePane> queue = new LinkedList<>();

    PassagePane openingPane = new PassagePane(story.getOpeningPassage());
    setActionsForPassagePane(openingPane);
    openingPane.setLayoutX(0);
    openingPane.setLayoutY(0);

    passagePanes.put(story.getOpeningPassage(), openingPane);
    queue.add(openingPane);
    pane.getChildren().add(openingPane);

    story
        .getPassages()
        .forEach(
            passage -> {
              if (!passagePanes.containsKey(passage)) {
                PassagePane passagePane = new PassagePane(passage);
                setActionsForPassagePane(passagePane);
                passagePane.setLayoutX(0);
                passagePane.setLayoutY(0);
                passagePanes.put(passage, passagePane);
                pane.getChildren().add(passagePane);
                queue.add(passagePane);
              }
            });

    List<Link> deadLinks = story.getBrokenLinks();

    Map<Integer, Integer> amountOfPassagesAtDepth = new HashMap<>();
    int gapY = 150;
    int gapX = 200;

    while (!queue.isEmpty()) {
      PassagePane passagePane = queue.remove();
      Passage passage = passagePane.getPassage();

      // DRAW LINKS
      List<Link> links = passage.getLinks();
      for (Link link : links) {
        if (!deadLinks.contains(link)) {
          LinkLine linkLine =
              new LinkLine(link, passagePane, passagePanes.get(story.getLinkedPassage(link)));
          setActionsForLinkLine(linkLine);

          pane.getChildren().addAll(linkLine, linkLine.getArrows());

          linkLine.toBack();
          linkLine.getArrows().toBack();
        }
      }

      // POSITIONING
      int depth = story.shortestPathFromOpeningPassage(passage) - 1;
      passagePane.setLayoutX(gapX * depth);
      passagePane.setLayoutY(gapY * amountOfPassagesAtDepth.getOrDefault(depth, 0));
      amountOfPassagesAtDepth.put(depth, amountOfPassagesAtDepth.getOrDefault(depth, 0) + 1);
    }
  }

  public void setActionsForLinkLine(LinkLine linkLine) {
    linkLine.setOnMouseClicked(
        mouseEvent -> {
          EditLinkDialog editLinkDialog = new EditLinkDialog(linkLine.getLink(), story);

          Optional<Link> result = editLinkDialog.showAndWait();
          result.ifPresent(linkLine::setLink);
          result.ifPresent(link -> updatePane());
        });
  }

  public void setActionsForPassagePane(PassagePane passagePane) {
    passagePane.setOnMouseClicked(
        mouseEvent -> {
          if (!dragMode) {
            EditPassageDialog editPassageDialog = new EditPassageDialog(passagePane.getPassage());

            Optional<Passage> result = editPassageDialog.showAndWait();
            result.ifPresent(
                passage1 -> {
                  passagePane.setPassage(passage1);
                  passagePane.getTitle().setText(passage1.getTitle());
                  passagePane.getContent().setText(passage1.getContent());
                });
          } else {
            System.out.println("Drag mode");
          }
        });

    passagePane.setOnMousePressed(
        mouseEvent -> {
          dragInitialX = mouseEvent.getSceneX() - passagePane.getLayoutX();
          dragInitialY = mouseEvent.getSceneY() - passagePane.getLayoutY();
        });

    passagePane.setOnMouseDragged(
        mouseEvent -> {
          if (dragMode) {
            passagePane.setLayoutX(mouseEvent.getSceneX() - dragInitialX);
            passagePane.setLayoutY(mouseEvent.getSceneY() - dragInitialY);
          }
        });
    /*else if (linkCreationMode) { // link creation is not used atm.
      PassagePane startDrag = null;
      PassagePane endDrag = null;
      for (PassagePane passagePane1 : passagePanes.values()) {
        if ((passagePane1.getLayoutX() < dragInitialX)
            && (passagePane1.getLayoutY() + passagePane1.getWidth() > dragInitialX)
            && (passagePane1.getLayoutY() < dragInitialY)
            && (passagePane1.getLayoutY() + passagePane1.getHeight() > dragInitialY)) {
          startDrag = passagePane1;
        } else if ((passagePane1.getLayoutX() < mouseEvent.getSceneX())
            && (passagePane1.getLayoutY() + passagePane1.getWidth() > mouseEvent.getSceneX())
            && (passagePane1.getLayoutY() < mouseEvent.getSceneY())
            && (passagePane1.getLayoutY() + passagePane1.getHeight() > mouseEvent.getSceneY())
            && passagePane1 != startDrag) {
          endDrag = passagePane1;
        }
      }

      System.out.println(startDrag);
      System.out.println(endDrag);

      if (startDrag != null && endDrag != null) {
        Link link =
            new Link("Go to " + endDrag.getTitle().getText(), endDrag.getTitle().getText());
        startDrag.getPassage().addLink(link);
        updatePane();
      }
    }*/
  }

  private void setActionsForScene() {
    stage
        .getScene()
        .setOnKeyPressed(
            keyEvent -> {
              if (Objects.requireNonNull(keyEvent.getCode()) == KeyCode.SHIFT) {
                dragMode = true;
                System.out.println("Drag mode on");
              }
              if (Objects.requireNonNull(keyEvent.getCode()) == KeyCode.CONTROL) {
                linkCreationMode = true;
                System.out.println("Link creation mode on");
              }
            });

    stage
        .getScene()
        .setOnKeyReleased(
            keyEvent -> {
              if (Objects.requireNonNull(keyEvent.getCode()) == KeyCode.SHIFT) {
                dragMode = false;
                System.out.println("Drag mode off");
              }
              if (Objects.requireNonNull(keyEvent.getCode()) == KeyCode.CONTROL) {
                linkCreationMode = false;
                System.out.println("Link creation mode off");
              }
            });
  }

  public void updatePane() {
    pane.getChildren().clear();
    visualizeHashMap(pane);
  }

  public void onNewLinkButtonPressed() {
    NewLinkDialogWithStartPassage newLinkDialogWithStartPassage =
        new NewLinkDialogWithStartPassage(story);

    Optional<Link> result = newLinkDialogWithStartPassage.showAndWait();
    result.ifPresent(link -> updatePane());
  }

  public void onNewPassageButtonPressed() {
    NewPassageDialog newPassageDialog = new NewPassageDialog();

    Optional<Passage> result = newPassageDialog.showAndWait();
    result.ifPresent(
        passage -> {
          story.addPassage(passage);
          pane.getChildren().add(new PassagePane(passage));
        });
  }

  public void onSaveButtonPressed() {
    StoryDAO.getInstance().update(story);
    updatePane();
  }

  public void onLoadButtonPressed() {
    // TODO: FIX THIS, THE REFRESH IS NOT WORKING
    double oldStoryId = story.getId();
    this.story = StoryDAO.getInstance().find(story.getId()).orElse(story);
    if (oldStoryId != story.getId()) {
      updatePane();
    } else {
      updatePane();
      System.out.println("Story not found");
    }
  }

  public void onBackButtonPressed() {
    new NewGameController(stage);
  }

  public void onDeleteLinkButtonPressed() {
    DeleteLinkDialog deleteLinkDialog = new DeleteLinkDialog(story);
    Optional<Link> result = deleteLinkDialog.showAndWait();
    result.ifPresent(
        link -> {
          story.getPassages().stream()
              .filter(passage -> passage.getLinks().contains(link))
              .forEach(passage -> passage.getLinks().remove(link));
          updatePane();
        });
  }

  public void onDeletePassageButtonPressed() {
    DeletePassageDialog deletePassageDialog = new DeletePassageDialog(story);
    Optional<Passage> result = deletePassageDialog.showAndWait();
    result.ifPresent(
        passage -> {
          story.removePassage(new Link(passage.getTitle(), passage.getTitle()));
          updatePane();
        });
  }
}
