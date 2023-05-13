package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.ui.dialogs.EditLinkDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.EditPassageDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.NewLinkDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.NewPassageDialog;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.NewStoryView;

public class NewStoryController implements Controller {
  private final Stage stage;
  private final Story story = new Story("placeholder");
  private final NewStoryView view;
  private ResourceBundle resources;

  public NewStoryController(Stage stage) {
    this.stage = stage;
    this.view = new NewStoryView(this, stage, story);
  }

  public void onEditPassageButtonClicked(TableView<Passage> passageCreationTableView) {
    if (passageCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
      Passage passage = passageCreationTableView.getSelectionModel().getSelectedItem();

      EditPassageDialog editPassageDialog = new EditPassageDialog(passage);

      Optional<Passage> result = editPassageDialog.showAndWait();
      result.ifPresent(
          newPassage -> {
            passageCreationTableView.getItems().remove(passage);
            passageCreationTableView.getItems().add(newPassage);
          });
    }
    // TODO: ADD FEEDBACK DIALOG HERE
  }

  public void onNewPassageButtonClicked(TableView<Passage> passageCreationTableView) {
    NewPassageDialog newPassageDialog = new NewPassageDialog();

    Optional<Passage> result = newPassageDialog.showAndWait();
    result.ifPresent(passage -> passageCreationTableView.getItems().add(passage));
  }

  public void onDeletePassageButtonClicked(TableView<Passage> passageCreationTableView) {
    if (passageCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
      Passage passage = passageCreationTableView.getSelectionModel().getSelectedItem();
      passageCreationTableView.getItems().remove(passage);
    }
    // TODO: ADD FEEDBACK DIALOG HERE
  }

  public void onEditLinkButtonClicked(TableView<Link> linkCreationTableView) {
    if (linkCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
      Link link = linkCreationTableView.getSelectionModel().getSelectedItem();

      EditLinkDialog editLinkDialog = new EditLinkDialog(link);

      Optional<Link> result = editLinkDialog.showAndWait();
      result.ifPresent(
          newLink -> {
            linkCreationTableView.getItems().remove(link);
            linkCreationTableView.getItems().add(newLink);
          });
    }
  }

  public void onNewLinkButtonClicked(TableView<Link> linkCreationTableView) {
    NewLinkDialog newLinkDialog = new NewLinkDialog();

    Optional<Link> result = newLinkDialog.showAndWait();
    result.ifPresent(link -> linkCreationTableView.getItems().add(link));
  }

  public void onDeleteLinkButtonClicked(TableView<Link> linkCreationTableView) {
    if (linkCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
      Link link = linkCreationTableView.getSelectionModel().getSelectedItem();
      linkCreationTableView.getItems().remove(link);
    }
  }

  public void onAddToStoryButtonClicked(
      TableView<Passage> passageCreationTableView, TableView<Link> linkCreationTableView) {
    if (passageCreationTableView.getSelectionModel().getSelectedItems().size() == 2
        && linkCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {

      Passage passage1 = passageCreationTableView.getSelectionModel().getSelectedItems().get(0);
      Passage passage2 = passageCreationTableView.getSelectionModel().getSelectedItems().get(1);
      Link link = linkCreationTableView.getSelectionModel().getSelectedItems().get(0);

      passage1.addLink(link);
      passage2.addLink(link);

      story.addPassage(passage1);
      story.addPassage(passage2);

      passageCreationTableView.getSelectionModel().clearSelection();
      linkCreationTableView.getSelectionModel().clearSelection();

      linkCreationTableView.getItems().remove(link);

      view.updateStartingPassageTableView();
    }
  }

  public void onCreateStoryButtonClicked(
      TableView<Passage> startingPassageTableView, TextField titleTextField) {
    try {
      if (startingPassageTableView.getSelectionModel().getSelectedItems().size() == 1) {
        Passage startingPassage =
            startingPassageTableView.getSelectionModel().getSelectedItems().get(0);
        story.setStartingPassage(startingPassage);
        story.setCurrentPassage(startingPassage);
        story.setTitle(titleTextField.getText());

        StoryDAO.getInstance().add(story);

        new SelectGameToContinueController(stage);
      } else {
        // TODO: ADD FEEDBACK DIALOG HERE
        System.out.println("Please select a starting passage.");
      }
    } catch (Exception e) {
      //            ExceptionAlert alert = new ExceptionAlert(e);
      //            alert.showAndWait();
      e.printStackTrace();
    }
  }

  public void onCancelButtonClicked() {
    new NewGameController(stage);
  }


  @Override
  public Stage getStage() {
    return stage;
  }
}
