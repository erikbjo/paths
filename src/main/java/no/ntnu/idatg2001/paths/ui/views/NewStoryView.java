package no.ntnu.idatg2001.paths.ui.views;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Link;
import no.ntnu.idatg2001.paths.model.Passage;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.ui.controllers.NewStoryController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

import java.security.PublicKey;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class NewStoryView implements View {
  private final Stage stage;
  private final Text newStoryText;
  private Button editLinkButton;
  private Button newLinkButton;
  private Button deleteLinkButton;
  private final Button addToStoryButton;
  private TableView<Link> linkCreationTableView;
  private TableColumn<Link, String> linkColumn;
  private TableColumn<Passage, String> startingPassageColumn;
  private TableView<Passage> startingPassageTableView;
  private final NewStoryController controller;
  private final Story story;
  private Button editPassageButton;
  private Button newPassageButton;
  private Button deletePassageButton;
  private TableView<Passage> passageCreationTableView;
  private TableColumn<Passage, String> passageColumn;
  private Text titleText;
  private TextField titleTextField;
  private Button createStoryButton;
  private Button cancelButton;

  public NewStoryView(NewStoryController controller, Stage stage, Story story) {
    this.controller = controller;
    this.stage = stage;
    this.story = story;
    stage.setTitle("New Story");

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    StandardMenuBar menuBar = new StandardMenuBar(stage);
    root.setTop(menuBar);
    AnchorPane rootAnchorPane = new AnchorPane();

    VBox mainVBox = new VBox();
    mainVBox.setAlignment(Pos.TOP_CENTER);

    newStoryText = new Text();
    mainVBox.getChildren().add(newStoryText);

    HBox upperHBox = new HBox();
    upperHBox.setAlignment(Pos.CENTER);

    VBox leftVBox = new VBox();



    addToStoryButton = new Button();
    VBox passageCreationVBox = createPassageCreationVBox();
    VBox linkCreationVBox = createLinkCreationVBox();

    //    topTableViewHBox.getChildren().addAll(passageCreationVBox, linkCreationVBox);
    upperHBox.getChildren().addAll(passageCreationVBox, linkCreationVBox, addToStoryButton);

    mainVBox.getChildren().add(upperHBox);

    HBox lowerHBox = createLowerHBox();
    VBox rightVBox = createRightVBox();
    lowerHBox.getChildren().add(rightVBox);

    mainVBox.getChildren().add(lowerHBox);

    AnchorPane.setTopAnchor(mainVBox, 0.0);
    AnchorPane.setBottomAnchor(mainVBox, 0.0);
    AnchorPane.setLeftAnchor(mainVBox, 0.0);
    AnchorPane.setRightAnchor(mainVBox, 0.0);

    rootAnchorPane.getChildren().add(mainVBox);
    root.setCenter(rootAnchorPane);

    // TODO: REMOVE THIS BUTTON
    Button createTestItemsButton = new Button("Create test items");
    createTestItemsButton.setOnAction(e -> createTestItems());
    root.setBottom(createTestItemsButton);

    // CONTROLLER
    configureTableViews();

    // Observes when the language in Database is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());
    updateLanguage();

    stage.getScene().setRoot(root);
  }

  public VBox createRightVBox() {
    VBox rightVBox = new VBox();
    rightVBox.setAlignment(Pos.CENTER);

    titleText = new Text();
    titleTextField = new TextField();
    createStoryButton = new Button();
    cancelButton = new Button();

    rightVBox.getChildren().addAll(titleText, titleTextField, createStoryButton, cancelButton);
    return rightVBox;
  }

  public VBox createPassageCreationVBox() {
    passageCreationTableView = new TableView<>();
    passageColumn = new TableColumn<>();
    passageCreationTableView.getColumns().add(passageColumn);

    editPassageButton = new Button();
    newPassageButton = new Button();
    deletePassageButton = new Button();

    VBox passageCreationVBox =
        new VBox(
            passageCreationTableView,
            new HBox(editPassageButton, newPassageButton, deletePassageButton));
    passageCreationVBox.setAlignment(Pos.TOP_CENTER);
    return passageCreationVBox;
  }

  public VBox createLinkCreationVBox() {
    linkCreationTableView = new TableView<>();
    linkColumn = new TableColumn<>();
    linkCreationTableView.getColumns().add(linkColumn);

    editLinkButton = new Button();
    newLinkButton = new Button();
    deleteLinkButton = new Button();

    VBox linkCreationVBox =
            new VBox(linkCreationTableView, new HBox(editLinkButton, newLinkButton, deleteLinkButton));
    linkCreationVBox.setAlignment(Pos.TOP_CENTER);
    return linkCreationVBox;
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

  public HBox createLowerHBox() {
    HBox lowerHBox = new HBox();
    lowerHBox.setAlignment(Pos.CENTER);

    startingPassageTableView = new TableView<>();
    startingPassageColumn = new TableColumn<>();
    startingPassageTableView.getColumns().add(startingPassageColumn);
    lowerHBox.getChildren().add(startingPassageTableView);
    return lowerHBox;
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

  public void configureTableViews() {
    passageColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    passageColumn.setPrefWidth(250);
    passageCreationTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    linkColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
    linkColumn.setPrefWidth(250);

    startingPassageColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    startingPassageColumn.setPrefWidth(250);
  }

  @Override
  public void updateLanguage() {
    // gets the correct resource bundle, depending on the current language in database
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "languages/newStory",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
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
}
