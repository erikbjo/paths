package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.database.StoryDAO;
import no.ntnu.idatg2001.paths.ui.dialogs.EditLinkDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.EditPassageDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.NewLinkDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.NewPassageDialog;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class NewStoryController {
  private final Stage primaryStage;
  private final Text newStoryText;
  private final Text titleText;
  private final TextField titleTextField;
  private final Button editPassageButton;
  private final Button newPassageButton;
  private final Button deletePassageButton;
  private final Button editLinkButton;
  private final Button newLinkButton;
  private final Button deleteLinkButton;
  private final Button addToStoryButton;
  private final Button createStoryButton;
  private final Button cancelButton;
  private final TableView<Link> linkCreationTableView;
  private final TableColumn<Link, String> linkColumn;
  private final TableView<Passage> passageCreationTableView;
  private final TableColumn<Passage, String> passageColumn;
  private final TableColumn<Passage, String> startingPassageColumn;
  private final TableView<Passage> startingPassageTableView;
  private final Story story = new Story("placeholder");
  private ResourceBundle resources;

  public NewStoryController(
      Stage primaryStage,
      Text newStoryText,
      Text titleText,
      TextField titleTextField,
      Button editPassageButton,
      Button newPassageButton,
      Button deletePassageButton,
      Button editLinkButton,
      Button newLinkButton,
      Button deleteLinkButton,
      Button addToStoryButton,
      Button createStoryButton,
      Button cancelButton,
      TableView<Link> linkCreationTableView,
      TableColumn<Link, String> linkColumn,
      TableView<Passage> passageCreationTableView,
      TableColumn<Passage, String> passageColumn,
      TableColumn<Passage, String> startingPassageColumn,
      TableView<Passage> startingPassageTableView) {
    this.primaryStage = primaryStage;
    this.newStoryText = newStoryText;
    this.titleText = titleText;
    this.titleTextField = titleTextField;
    this.editPassageButton = editPassageButton;
    this.newPassageButton = newPassageButton;
    this.deletePassageButton = deletePassageButton;
    this.editLinkButton = editLinkButton;
    this.newLinkButton = newLinkButton;
    this.deleteLinkButton = deleteLinkButton;
    this.addToStoryButton = addToStoryButton;
    this.createStoryButton = createStoryButton;
    this.cancelButton = cancelButton;
    this.linkCreationTableView = linkCreationTableView;
    this.linkColumn = linkColumn;
    this.passageCreationTableView = passageCreationTableView;
    this.passageColumn = passageColumn;
    this.startingPassageColumn = startingPassageColumn;
    this.startingPassageTableView = startingPassageTableView;

    // Observes when the language in Database is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());
  }

  public void updateLanguage() {
    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "newStory", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    newStoryText.setText(resources.getString("newStoryText"));
    titleText.setText(resources.getString("titleText"));
    titleTextField.setPromptText(resources.getString("titleTextField"));
    editPassageButton.setText(resources.getString("editPassageButton"));
    newPassageButton.setText(resources.getString("newPassageButton"));
    deletePassageButton.setText(resources.getString("deletePassageButton"));
    editLinkButton.setText(resources.getString("editLinkButton"));
    newLinkButton.setText(resources.getString("newLinkButton"));
    deleteLinkButton.setText(resources.getString("deleteLinkButton"));
    addToStoryButton.setText(resources.getString("addToStoryButton"));
    createStoryButton.setText(resources.getString("createStoryButton"));
    cancelButton.setText(resources.getString("cancelButton"));
    linkCreationTableView.setPlaceholder(new Text(resources.getString("linkCreationTableView")));
    linkColumn.setText(resources.getString("linkColumn"));
    passageCreationTableView.setPlaceholder(
        new Text(resources.getString("passageCreationTableView")));
    passageColumn.setText(resources.getString("passageColumn"));
    startingPassageColumn.setText(resources.getString("startingPassageColumn"));
    startingPassageTableView.setPlaceholder(
        new Text(resources.getString("startingPassageTableView")));
  }

  public void configureButtons() {
    configurePassageCreationButtons();
    configureLinkCreationButtons();
    configureRemainingButtons();
  }

  public void configurePassageCreationButtons() {
    editPassageButton.setOnAction(
        event -> {
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
        });

    newPassageButton.setOnAction(
        event -> {
          NewPassageDialog newPassageDialog = new NewPassageDialog();

          Optional<Passage> result = newPassageDialog.showAndWait();
          result.ifPresent(passage -> passageCreationTableView.getItems().add(passage));
        });

    deletePassageButton.setOnAction(
        event -> {
          if (passageCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
            Passage passage = passageCreationTableView.getSelectionModel().getSelectedItem();
            passageCreationTableView.getItems().remove(passage);
          }
          // TODO: ADD FEEDBACK DIALOG HERE
        });
  }

  public void configureLinkCreationButtons() {
    editLinkButton.setOnAction(
        event -> {
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
          // TODO: ADD FEEDBACK DIALOG HERE
        });

    newLinkButton.setOnAction(
        event -> {
          NewLinkDialog newLinkDialog = new NewLinkDialog();

          Optional<Link> result = newLinkDialog.showAndWait();
          result.ifPresent(link -> linkCreationTableView.getItems().add(link));
        });

    deleteLinkButton.setOnAction(
        event -> {
          if (linkCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {
            Link link = linkCreationTableView.getSelectionModel().getSelectedItem();
            linkCreationTableView.getItems().remove(link);
          }
          // TODO: ADD FEEDBACK DIALOG HERE
        });
  }

  public void configureRemainingButtons() {
    addToStoryButton.setOnAction(
        event -> {
          if (passageCreationTableView.getSelectionModel().getSelectedItems().size() == 2
              && linkCreationTableView.getSelectionModel().getSelectedItems().size() == 1) {

            Passage passage1 =
                passageCreationTableView.getSelectionModel().getSelectedItems().get(0);
            Passage passage2 =
                passageCreationTableView.getSelectionModel().getSelectedItems().get(1);
            Link link = linkCreationTableView.getSelectionModel().getSelectedItems().get(0);

            passage1.addLink(link);
            passage2.addLink(link);

            story.addPassage(passage1);
            story.addPassage(passage2);

            passageCreationTableView.getSelectionModel().clearSelection();
            linkCreationTableView.getSelectionModel().clearSelection();

            linkCreationTableView.getItems().remove(link);

            updateStartingPassageTableView();
          }
        });

    createStoryButton.setOnAction(
        event -> {
          try {
            if (startingPassageTableView.getSelectionModel().getSelectedItems().size() == 1) {
              Passage startingPassage =
                  startingPassageTableView.getSelectionModel().getSelectedItems().get(0);
              story.setStartingPassage(startingPassage);
              story.setCurrentPassage(startingPassage);
              story.setTitle(titleTextField.getText());

              StoryDAO.getInstance().add(story);

              new SelectGameToContinueController(primaryStage);
            } else {
                // TODO: ADD FEEDBACK DIALOG HERE
              System.out.println("Please select a starting passage.");
            }
          } catch (Exception e) {
            //            ExceptionAlert alert = new ExceptionAlert(e);
            //            alert.showAndWait();
            e.printStackTrace();
          }
        });

    cancelButton.setOnAction(
        event -> {
          createTestItems();
        });
  }

  public void configureTableViews() {
    passageColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    passageColumn.setPrefWidth(250);
    passageCreationTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    linkColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
    linkColumn.setPrefWidth(250);

    startingPassageColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    startingPassageColumn.setPrefWidth(250);
  }

  private void createTestItems() {
    Random random = new Random();

    passageCreationTableView
        .getItems()
        .add(new Passage("Passage" + random.nextInt(1000), "Text" + random.nextInt(1000)));
    linkCreationTableView
        .getItems()
        .add(new Link("Link" + random.nextInt(1000), "Text" + random.nextInt(1000)));
  }

  public void updateStartingPassageTableView() {
    story
        .getAllPassages()
        .forEach(
            passage -> {
              if (!startingPassageTableView.getItems().contains(passage)) {
                startingPassageTableView.getItems().add(passage);
              }
            });
  }
}
