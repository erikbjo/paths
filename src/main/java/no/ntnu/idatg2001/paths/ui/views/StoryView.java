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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Database;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

public class StoryView {
  private ResourceBundle resources;
  
  public void start(Stage stage) {
    // Observes when the language in Database is changed, then calls updateLanguage()
    Database.getObservableIntegerCounter()
        .addListener((obs, oldValue, newValue) -> updateLanguage());

    // gets the correct resource bundle, depending on the current language in database
    resources =
        ResourceBundle.getBundle(
            "story", Locale.forLanguageTag(Database.getCurrentLanguage().getLocalName()));

    BorderPane borderPane = new BorderPane();
    StandardMenuBar menuBar = new StandardMenuBar();
    borderPane.setTop(menuBar);
    AnchorPane rootAnchorPane = new AnchorPane();

    // Create a VBox to hold all the elements
    VBox rootVBox = new VBox(100); // 100 pixels spacing between elements
    rootVBox.setPadding(new Insets(50));
    rootVBox.setAlignment(Pos.CENTER); // Center the elements vertically and horizontally
    AnchorPane anchorPaneForVBox = new AnchorPane();

    AnchorPane bottumAnchorPane = new AnchorPane();
    
    rootAnchorPane.getChildren().add(borderPane);
    anchorPaneForVBox.getChildren().add(rootVBox);
    rootAnchorPane.getChildren().add(anchorPaneForVBox);

    Text storyHeadlineText = new Text("storyHeadlineText");
    rootVBox.getChildren().add(storyHeadlineText);
    rootVBox.getChildren().add(bottumAnchorPane);


    // create the horizontal splitpane
    SplitPane rootHorizontalSplitPane = new SplitPane();
    AnchorPane leftAnchorPane = new AnchorPane();
    AnchorPane rightAnchorPane = new AnchorPane();
    rootHorizontalSplitPane.getItems().add(leftAnchorPane);
    rootHorizontalSplitPane.getItems().add(rightAnchorPane);
    rootHorizontalSplitPane.setOrientation(Orientation.HORIZONTAL);
    rootHorizontalSplitPane.setDividerPosition(0, 0.5);
    
    bottumAnchorPane.getChildren().add(rootHorizontalSplitPane);

    // create the vertical splitpane
    SplitPane verticalSplitPane = new SplitPane();
    AnchorPane topRightAnchorPane = new AnchorPane();
    AnchorPane bottumRightAnchorPane = new AnchorPane();
    verticalSplitPane.getItems().add(topRightAnchorPane);
    verticalSplitPane.getItems().add(bottumRightAnchorPane);
    verticalSplitPane.setOrientation(Orientation.VERTICAL);

    rightAnchorPane.getChildren().add(verticalSplitPane);

    VBox leftVBox = new VBox();
    leftVBox.setAlignment(Pos.CENTER);
    leftVBox.setPrefWidth(200);
    
    TextArea storyTextArea = new TextArea("storyTextArea");
    storyTextArea.setEditable(false);
    leftVBox.getChildren().add(storyTextArea);
    
    leftAnchorPane.getChildren().add(leftVBox);

    TextArea pathOneTextArea = new TextArea("path1");
    pathOneTextArea.setEditable(false);
    pathOneTextArea.setMaxWidth(200);
    topRightAnchorPane.getChildren().add(pathOneTextArea);

    TextArea pathTwoTextArea = new TextArea("path2");
    pathTwoTextArea.setEditable(false);
    pathTwoTextArea.setMaxWidth(200);
    bottumRightAnchorPane.getChildren().add(pathTwoTextArea);

    Scene scene = new Scene(rootAnchorPane, 600, 600);
    scene.getStylesheets().add("cssfiles/storyStyle.css");
    stage.setScene(scene);
    stage.show();
  }

  public void updateLanguage() {
    // update resources
    resources =
        ResourceBundle.getBundle(
            "story", Locale.forLanguageTag(Database.getCurrentLanguage().getLocalName()));
  }
}
