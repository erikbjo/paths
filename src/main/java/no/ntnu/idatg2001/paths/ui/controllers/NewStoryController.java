package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.*;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.dialogs.NewPassageDialog;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;

public class NewStoryController {
  private final Stage primaryStage;
  private final Text newStoryText;
  private final Text titleText;
  private final TextField titleTextField;
  private final Button newPassageButton;
  private final Button newLinkButton;
  private final Button addToStoryButton;
  private final Button createStoryButton;
  private final Button cancelButton;
  private final TableView<Link> linkCreationTableView;
  private final TableColumn<Link, String> linkColumn;
  private final TableView<Passage> passageCreationTableView;
  private final TableColumn<Passage, String> passageColumn;
  private final TableColumn<Passage, String> startingPassageColumn;
  private final TableView<Passage> startingPassageTableView;
  private final Story story = new Story("placeholder", null);
  private ResourceBundle resources;

  public NewStoryController(
      Stage primaryStage,
      Text newStoryText,
      Text titleText,
      TextField titleTextField,
      Button newPassageButton,
      Button newLinkButton,
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
    this.newPassageButton = newPassageButton;
    this.newLinkButton = newLinkButton;
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
    newPassageButton.setText(resources.getString("newPassageButton"));
    newLinkButton.setText(resources.getString("newLinkButton"));
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
    newPassageButton.setOnAction(
        event -> {
          NewPassageDialog newPassageDialog = new NewPassageDialog();
          newPassageDialog.showAndWait();

          Optional<Passage> result = newPassageDialog.showAndWait();
          result.ifPresent(passage -> passageCreationTableView.getItems().add(passage));
        });

    newLinkButton.setOnAction(event -> {});

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

            updateStartingPassageTableView();

            // TODO: REMOVE THIS
            System.out.println("size: " + story.getPassagesHashMap().size());
            story
                .getPassagesHashMap()
                .forEach((key, value) -> System.out.println(key + " " + Arrays.toString(value)));
          }
        });

    createStoryButton.setOnAction(event -> {});

    cancelButton.setOnAction(
        event -> {
          createTestItems();
        });
  }

  public void configureTableViews() {
    passageColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    passageColumn.setPrefWidth(250);
    passageCreationTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    linkColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
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
    // adds all passages to the startingPassageTableView, except passages that are already in the
    // tableview
    story.getPassagesHashMap().values().stream()
        .filter(
            passage -> {
              for (Passage nthPassage : passage) {
                if (startingPassageTableView.getItems().contains(nthPassage)) {
                  return false;
                }
              }
              return true;
            })
        .forEach(
            passage -> {
              for (Passage nthPassage : passage) {
                startingPassageTableView.getItems().add(nthPassage);
              }
            });
  }
}
