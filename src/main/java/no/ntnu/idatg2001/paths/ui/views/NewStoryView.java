package no.ntnu.idatg2001.paths.ui.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.ui.controllers.NewStoryController;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class NewStoryView implements View {
  private Stage primaryStage;
  private Text newStoryText;
  private Text titleText;
  private TextField titleTextField;
  private Button editPassageButton;
  private Button newPassageButton;
  private Button deletePassageButton;
  private Button editLinkButton;
  private Button newLinkButton;
  private Button deleteLinkButton;
  private Button addToStoryButton;
  private Button createStoryButton;
  private Button cancelButton;
  private TableView<Link> linkCreationTableView;
  private TableColumn<Link, String> linkColumn;
  private TableView<Passage> passageCreationTableView;
  private TableColumn<Passage, String> passageColumn;
  private TableColumn<Passage, String> startingPassageColumn;
  private TableView<Passage> startingPassageTableView;
  private NewStoryController controller;

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    primaryStage.setTitle("New Story");

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    StandardMenuBar menuBar = new StandardMenuBar(primaryStage);
    root.setTop(menuBar);
    AnchorPane rootAnchorPane = new AnchorPane();

    VBox mainVBox = new VBox();
    mainVBox.setAlignment(Pos.TOP_CENTER);

    newStoryText = new Text();
    mainVBox.getChildren().add(newStoryText);

    HBox upperHBox = new HBox();
    upperHBox.setAlignment(Pos.CENTER);

    VBox leftVBox = new VBox();

    passageCreationTableView = new TableView<>();
    passageColumn = new TableColumn<>();
    passageCreationTableView.getColumns().add(passageColumn);

    linkCreationTableView = new TableView<>();
    linkColumn = new TableColumn<>();
    linkCreationTableView.getColumns().add(linkColumn);

    HBox topTableViewHBox = new HBox();

    editPassageButton = new Button();
    newPassageButton = new Button();
    deletePassageButton = new Button();

    editLinkButton = new Button();
    newLinkButton = new Button();
    deleteLinkButton = new Button();

    addToStoryButton = new Button();
    createStoryButton = new Button();
    cancelButton = new Button();

    VBox passageCreationVBox =
        new VBox(
            passageCreationTableView,
            new HBox(editPassageButton, newPassageButton, deletePassageButton));
    passageCreationVBox.setAlignment(Pos.TOP_CENTER);

    VBox linkCreationVBox =
        new VBox(linkCreationTableView, new HBox(editLinkButton, newLinkButton, deleteLinkButton));
    linkCreationVBox.setAlignment(Pos.TOP_CENTER);

    //    topTableViewHBox.getChildren().addAll(passageCreationVBox, linkCreationVBox);
    upperHBox.getChildren().addAll(passageCreationVBox, linkCreationVBox, addToStoryButton);

    mainVBox.getChildren().add(upperHBox);

    HBox lowerHBox = new HBox();
    lowerHBox.setAlignment(Pos.CENTER);

    startingPassageTableView = new TableView<>();

    startingPassageColumn = new TableColumn<>();
    startingPassageTableView.getColumns().add(startingPassageColumn);

    VBox rightVBox = new VBox();
    rightVBox.setAlignment(Pos.CENTER);

    titleText = new Text();
    titleTextField = new TextField();

    rightVBox.getChildren().addAll(titleText, titleTextField, createStoryButton, cancelButton);
    lowerHBox.getChildren().addAll(startingPassageTableView, rightVBox);

    mainVBox.getChildren().add(lowerHBox);

    AnchorPane.setTopAnchor(mainVBox, 0.0);
    AnchorPane.setBottomAnchor(mainVBox, 0.0);
    AnchorPane.setLeftAnchor(mainVBox, 0.0);
    AnchorPane.setRightAnchor(mainVBox, 0.0);

    rootAnchorPane.getChildren().add(mainVBox);
    root.setCenter(rootAnchorPane);

    controller =
        new NewStoryController(
            primaryStage,
            newStoryText,
            titleText,
            titleTextField,
            editPassageButton,
            newPassageButton,
            deletePassageButton,
            editLinkButton,
            newLinkButton,
            deleteLinkButton,
            addToStoryButton,
            createStoryButton,
            cancelButton,
            linkCreationTableView,
            linkColumn,
            passageCreationTableView,
            passageColumn,
            startingPassageColumn,
            startingPassageTableView);

    controller.updateLanguage();
    controller.configureTableViews();
    controller.configureButtons();


    primaryStage.getScene().setRoot(root);
  }

  @Override
  public void updateLanguage() {

  }
}
