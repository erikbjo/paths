package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.ui.dialogs.*;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.views.NewStoryView;

public class NewStoryController implements Controller {
  private final Stage stage;
  private final Story story = new Story();
  private final NewStoryView view;

  public NewStoryController(Stage stage) {
    this.stage = stage;
    this.view = new NewStoryView(this, stage, story);
    StoryDAO.getInstance().add(story);
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> view.updateLanguage());
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
  }

  public void onNewPassageButtonClicked() {
    NewPassageDialog newPassageDialog = new NewPassageDialog();

    Optional<Passage> result = newPassageDialog.showAndWait();
    result.ifPresent(
        passage -> {
          story.addPassage(passage);
          view.updatePassagesTableView();
        });
  }

  public void onDeletePassageButtonClicked(TableView<Passage> passageCreationTableView) {
    if (passageCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
      Passage passage = passageCreationTableView.getSelectionModel().getSelectedItem();
      passageCreationTableView.getItems().remove(passage);
    }
  }

  public void onEditLinkButtonClicked(TableView<Link> linkCreationTableView) {
    if (linkCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
      Link link = linkCreationTableView.getSelectionModel().getSelectedItem();

      EditLinkDialog editLinkDialog = new EditLinkDialog(link, story);

      Optional<Link> result = editLinkDialog.showAndWait();
      result.ifPresent(
          newLink -> {
            linkCreationTableView.getItems().remove(link);
            linkCreationTableView.getItems().add(newLink);
          });
    }
  }

  public void onNewLinkButtonClicked(TableView<Link> linkCreationTableView) {
    NewLinkDialogWithStartPassage newLinkDialogWithStartPassage =
        new NewLinkDialogWithStartPassage(story);

    Optional<Link> result = newLinkDialogWithStartPassage.showAndWait();
    result.ifPresent(link -> linkCreationTableView.getItems().add(link));
  }

  public void onDeleteLinkButtonClicked(TableView<Link> linkCreationTableView) {
    if (linkCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
      Link link = linkCreationTableView.getSelectionModel().getSelectedItem();
      linkCreationTableView.getItems().remove(link);
    }
  }

  public void onCreateStoryButtonClicked() {
    try {
      StoryDAO.getInstance().update(story);

      new NewGameController(stage);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void onCancelButtonClicked() {
    StoryDAO.getInstance().remove(story);
    new NewGameController(stage);
  }

  @Override
  public Stage getStage() {
    return stage;
  }

  public void configureVBoxes(VBox openingVBox, VBox mainVBox) {
    openingVBox.setManaged(true);
    openingVBox.setVisible(true);
    mainVBox.setManaged(false);
    mainVBox.setVisible(false);
  }

  public void onContinueButtonClicked(
      TextField storyTitleTextField,
      TextField openingPassageTitleTextField,
      TextArea openingPassageContentTextArea,
      VBox openingVBox,
      VBox mainVBox) {
    story.setTitle(storyTitleTextField.getText());
    //StoryDAO.getInstance().update(story); // Maybe not needed
    Passage openingPassage = new Passage();
    openingPassage.setTitle(openingPassageTitleTextField.getText());
    openingPassage.setContent(openingPassageContentTextArea.getText());
    story.setOpeningPassage(openingPassage);
    StoryDAO.getInstance().update(story);
    openingVBox.setManaged(false);
    openingVBox.setVisible(false);
    mainVBox.setManaged(true);
    mainVBox.setVisible(true);
    view.updatePassagesTableView();
  }
}
