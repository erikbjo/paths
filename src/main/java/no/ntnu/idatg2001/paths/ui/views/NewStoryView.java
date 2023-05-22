package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import no.ntnu.idatg2001.paths.ui.standardobjects.StandardMenuBar;

public class NewStoryView implements View {
  private final Stage stage;
  private final VBox openingVBox;
  private final VBox mainVBox;
  private final NewStoryController controller;
  private final Story story;
  private Text newStoryText;
  private Button cancelButton;
  private Button editLinkButton;
  private Button newLinkButton;
  private Button deleteLinkButton;
  private TableView<Link> linkCreationTableView;
  private TableColumn<Link, String> linkColumn;
  private Button editPassageButton;
  private Button newPassageButton;
  private Button deletePassageButton;
  private TableView<Passage> passageCreationTableView;
  private TableColumn<Passage, String> passageColumn;
  private Button createStoryButton;
  private Text storyTitleText;
  private TextField storyTitleTextField;
  private Text openingPassageTitleText;
  private TextField openingPassageTitleTextField;
  private Text openingPassageContentText;
  private TextArea openingPassageContentTextArea;
  private Button continueButton;
  private Text openingTitleText;
  private Button openingCancelButton;

  public NewStoryView(NewStoryController controller, Stage stage, Story story) {
    this.controller = controller;
    this.stage = stage;
    this.story = story;
    stage.setTitle("New Story");

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    StandardMenuBar menuBar = new StandardMenuBar(stage, story);
    root.setTop(menuBar);
    VBox rootVBox = new VBox();

    openingVBox = createOpeningVBox();
    mainVBox = createMainVBox();

    rootVBox.getChildren().addAll(openingVBox, mainVBox);
    root.setCenter(rootVBox);

    // CONTROLLER
    configureTableViews();
    controller.configureVBoxes(openingVBox, mainVBox);

    updateLanguage();

    rootVBox.setId("rootVBox");
    openingVBox.setId("openingVBox");
    mainVBox.setId("mainVBox");
    openingTitleText.setId("headlineText");
    newStoryText.setId("headlineText");

    stage.getScene().getStylesheets().add("css/newStory.css");
    stage.getScene().setRoot(root);
  }

  public VBox createLowerVBox() {
    VBox rightVBox = new VBox();

    createStoryButton = new Button();
    createStoryButton.setOnAction(e -> controller.onCreateStoryButtonClicked());

    cancelButton = new Button();
    cancelButton.setOnAction(e -> controller.onCancelButtonClicked());

    rightVBox.getChildren().addAll(createStoryButton, cancelButton);
    return rightVBox;
  }

  public VBox createPassageCreationVBox() {
    passageCreationTableView = new TableView<>();
    passageColumn = new TableColumn<>();
    passageCreationTableView.getColumns().add(passageColumn);

    editPassageButton = new Button();
    editPassageButton.setOnAction(
        e -> controller.onEditPassageButtonClicked(passageCreationTableView));

    newPassageButton = new Button();
    newPassageButton.setOnAction(
        e -> controller.onNewPassageButtonClicked());

    deletePassageButton = new Button();
    deletePassageButton.setOnAction(
        e -> controller.onDeletePassageButtonClicked(passageCreationTableView));

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
    editLinkButton.setOnAction(e -> controller.onEditLinkButtonClicked(linkCreationTableView));

    newLinkButton = new Button();
    newLinkButton.setOnAction(e -> controller.onNewLinkButtonClicked(linkCreationTableView));

    deleteLinkButton = new Button();
    deleteLinkButton.setOnAction(e -> controller.onDeleteLinkButtonClicked(linkCreationTableView));

    VBox linkCreationVBox =
        new VBox(linkCreationTableView, new HBox(editLinkButton, newLinkButton, deleteLinkButton));
    linkCreationVBox.setAlignment(Pos.TOP_CENTER);
    return linkCreationVBox;
  }

  public void configureTableViews() {
    passageColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    passageColumn.setPrefWidth(250);
    passageCreationTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    linkColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
    linkColumn.setPrefWidth(250);
  }

  @Override
  public void updateLanguage() {
    // gets the correct resource bundle, depending on the current language in database
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "languages/newStory",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    newStoryText.setText(resources.getString("newStoryText"));
    editPassageButton.setText(resources.getString("editPassageButton"));
    newPassageButton.setText(resources.getString("newPassageButton"));
    deletePassageButton.setText(resources.getString("deletePassageButton"));
    editLinkButton.setText(resources.getString("editLinkButton"));
    newLinkButton.setText(resources.getString("newLinkButton"));
    deleteLinkButton.setText(resources.getString("deleteLinkButton"));
    createStoryButton.setText(resources.getString("createStoryButton"));
    cancelButton.setText(resources.getString("cancelButton"));
    linkCreationTableView.setPlaceholder(new Text(resources.getString("linkCreationTableView")));
    linkColumn.setText(resources.getString("linkColumn"));
    passageCreationTableView.setPlaceholder(
        new Text(resources.getString("passageCreationTableView")));
    passageColumn.setText(resources.getString("passageColumn"));
    storyTitleText.setText(resources.getString("storyTitleText"));
    storyTitleTextField.setPromptText(resources.getString("storyTitleTextField"));
    openingPassageTitleText.setText(resources.getString("openingPassageTitleText"));
    openingPassageTitleTextField.setPromptText(resources.getString("openingPassageTitleTextField"));
    openingPassageContentText.setText(resources.getString("openingPassageContentText"));
    openingPassageContentTextArea.setPromptText(
        resources.getString("openingPassageContentTextArea"));
    continueButton.setText(resources.getString("continueButton"));
    openingCancelButton.setText(resources.getString("openingCancelButton"));
    openingTitleText.setText(resources.getString("openingTitleText"));
  }

  private VBox createOpeningVBox() {
    VBox tempOpeningVbox = new VBox();
    tempOpeningVbox.setAlignment(Pos.CENTER);

    openingTitleText = new Text();
    storyTitleText = new Text();
    storyTitleTextField = new TextField();
    openingPassageTitleText = new Text();
    openingPassageTitleTextField = new TextField();
    openingPassageContentText = new Text();
    openingPassageContentTextArea = new TextArea();

    HBox buttonsHBox = new HBox();
    openingCancelButton = new Button();
    openingCancelButton.setOnAction(e -> controller.onCancelButtonClicked());
    continueButton = new Button();
    continueButton.setOnAction(
        e ->
            controller.onContinueButtonClicked(
                storyTitleTextField,
                openingPassageTitleTextField,
                openingPassageContentTextArea,
                tempOpeningVbox,
                mainVBox));
    buttonsHBox.getChildren().addAll(openingCancelButton, continueButton);

    tempOpeningVbox
        .getChildren()
        .addAll(
            openingTitleText,
            storyTitleText,
            storyTitleTextField,
            openingPassageTitleText,
            openingPassageTitleTextField,
            openingPassageContentText,
            openingPassageContentTextArea,
            buttonsHBox);
    return tempOpeningVbox;
  }

  private VBox createMainVBox() {
    VBox tempMainVBox = new VBox();

    newStoryText = new Text();
    tempMainVBox.getChildren().add(newStoryText);

    HBox upperHBox = new HBox();
    upperHBox.setAlignment(Pos.CENTER);


    VBox passageCreationVBox = createPassageCreationVBox();
    VBox linkCreationVBox = createLinkCreationVBox();

    upperHBox.getChildren().addAll(passageCreationVBox, linkCreationVBox);

    tempMainVBox.getChildren().add(upperHBox);

    VBox rightVBox = createLowerVBox();

    tempMainVBox.getChildren().add(rightVBox);

    return tempMainVBox;
  }

  public void updatePassagesTableView() {
    story
        .getPassages()
        .forEach(
            passage -> {
              if (!passageCreationTableView.getItems().contains(passage)) {
                passageCreationTableView.getItems().add(passage);
              }
            });
  }
}
