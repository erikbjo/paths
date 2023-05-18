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
    int gap = 200; // Gap between nodes

    Queue<PassagePane> queue = new LinkedList<>();

    PassagePane openingPane = new PassagePane(story.getOpeningPassage());
    setActionsForPassagePane(openingPane);
    openingPane.setLayoutX(0);
    openingPane.setLayoutY(0);

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
            setActionsForPassagePane(linkedPane);
            linkedPane.setLayoutX(currentPane.getLayoutX() + offsetX);
            linkedPane.setLayoutY(currentPane.getLayoutY() + gap);

            passagePanes.put(passage, linkedPane);
            queue.add(linkedPane);
            pane.getChildren().add(linkedPane);

            LinkLine linkLine = new LinkLine(link, currentPane, linkedPane);
            setActionsForLinkLine(linkLine);
            pane.getChildren().addAll(linkLine, linkLine.getArrows());

            linkLine.toBack();
            linkLine.getArrows().toBack();
          } else {
            LinkLine linkLine = new LinkLine(link, currentPane, passagePanes.get(passage));
            setActionsForLinkLine(linkLine);
            pane.getChildren().addAll(linkLine, linkLine.getArrows());

            linkLine.toBack();
            linkLine.getArrows().toBack();
          }
          offsetX += gap;
        }
      }
    }

    AtomicReference<Double> unLinkedPassageX = new AtomicReference<>((double) 200);
    AtomicReference<Double> unLinkedPassageY = new AtomicReference<>((double) 0);
    story
        .getAllPassagesThatDoesNotHaveALinkPointingToThem()
        .forEach(
            passage -> {
              if (!passagePanes.containsKey(passage)) {
                PassagePane passagePane = new PassagePane(passage);
                setActionsForPassagePane(passagePane);
                passagePane.setLayoutX(unLinkedPassageX.get());
                passagePane.setLayoutY(unLinkedPassageY.get());
                passagePanes.put(passage, passagePane);

                // TODO: add links from unlinked passages to other passages
                passage
                    .getLinks()
                    .forEach(
                        link -> {
                          List<Passage> linkedPassages = story.getPassagesConnectedWithLink(link);
                          for (Passage linkedPassage : linkedPassages) {
                            if (passagePanes.containsKey(linkedPassage)) {
                              LinkLine linkLine =
                                  new LinkLine(link, passagePane, passagePanes.get(linkedPassage));
                              setActionsForLinkLine(linkLine);
                              pane.getChildren().addAll(linkLine, linkLine.getArrows());

                              linkLine.toBack();
                              linkLine.getArrows().toBack();
                            }
                          }
                        });

                pane.getChildren().add(passagePane);
                unLinkedPassageX.set(unLinkedPassageX.get() + 200);
                unLinkedPassageY.set(unLinkedPassageY.get() + 50);
              }
            });
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
          dragInitialX = mouseEvent.getSceneX();
          dragInitialY = mouseEvent.getSceneY();

          System.out.println("initialX: " + dragInitialX);
          System.out.println("initialY: " + dragInitialY);
        });

    passagePane.setOnMouseDragged(
        mouseEvent -> {
          if (dragMode) {
            passagePane.setLayoutX(
                passagePane.getLayoutX() + mouseEvent.getSceneX() - dragInitialX);
            passagePane.setLayoutY(
                passagePane.getLayoutY() + mouseEvent.getSceneY() - dragInitialY);
          } else if (linkCreationMode) {
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
          }
        });
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
}
