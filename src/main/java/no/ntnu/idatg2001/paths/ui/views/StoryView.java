package no.ntnu.idatg2001.paths.ui.views;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Database;
import no.ntnu.idatg2001.paths.model.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.controllers.StoryViewController;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class StoryView {
  private ResourceBundle resources;
  
  public void start(Stage stage) {
    StoryViewController storyViewController = new StoryViewController();

    // Observes when the language in Database is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "story", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));

    BorderPane borderPane = new BorderPane();
    StandardMenuBar menuBar = new StandardMenuBar();
    borderPane.setTop(menuBar);
    AnchorPane rootAnchorPane = new AnchorPane();

    // Create a VBox to hold all the elements
    VBox rootVBox = new VBox(100); // 100 pixels spacing between elements
    rootVBox.setPadding(new Insets(50));
    rootVBox.setAlignment(Pos.CENTER); // Center the elements vertically and horizontally


    Text storyHeadlineText = new Text("storyHeadlineText");
    TextArea storyTextArea = new TextArea("storyTextArea");
    storyTextArea.setEditable(false);

    HBox linksHBox = new HBox(100);
    linksHBox.setAlignment(Pos.CENTER);
    storyViewController.setLinksHBox(linksHBox);

    rootVBox.getChildren().add(storyHeadlineText);
    rootVBox.getChildren().add(storyTextArea);
    rootVBox.getChildren().add(linksHBox);

    rootAnchorPane.getChildren().add(borderPane);
    rootAnchorPane.getChildren().add(rootVBox);

    Scene scene = new Scene(rootAnchorPane, 600, 600);
    scene.getStylesheets().add("cssfiles/storyStyle.css");
    stage.setScene(scene);
    stage.show();
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "story", Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
  }
}
