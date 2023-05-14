package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.controllers.StoryViewController;
import no.ntnu.idatg2001.paths.ui.standardobjects.StandardMenuBar;

public class StoryView implements View {
  private ResourceBundle resources;
  private StoryViewController storyViewController;

  public void start(Stage stage) {
    stage.setTitle("Story");

    // Observes when the language in Database is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "languages/story", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    // Create a borderpane and a standard menubar
    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(stage));
    AnchorPane rootAnchorPane = new AnchorPane();

    // Create a VBox to hold all the elements
    VBox rootVBox = new VBox(50); // 100 pixels spacing between elements
    rootVBox.setPadding(new Insets(25));
    rootVBox.setAlignment(Pos.CENTER); // Center the elements vertically and horizontally

    // Create a headline and a text area for the story
    Text storyHeadlineText = new Text("storyHeadlineText");
    Text passageTitleText = new Text("passageTitleText");
    TextArea passageContentTextArea = new TextArea("passageContentTextArea");
    passageContentTextArea.setEditable(false);

    // HBox for the hyperlinks
    HBox linksHBox = new HBox(10);
    linksHBox.setAlignment(Pos.CENTER);

    // Add all the elements to the root VBox
    rootVBox.getChildren().add(storyHeadlineText);
    rootVBox.getChildren().add(passageTitleText);
    rootVBox.getChildren().add(passageContentTextArea);
    rootVBox.getChildren().add(linksHBox);

    rootAnchorPane.getChildren().add(rootVBox);
    root.setCenter(rootAnchorPane);

    // Initialize the controller with the dynamic elements
    storyViewController =
        new StoryViewController(
            linksHBox, storyHeadlineText, passageTitleText, passageContentTextArea);
    storyViewController.updateStoryViewToNewPath();


    stage.getScene().setRoot(root);
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "story", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  }
}
